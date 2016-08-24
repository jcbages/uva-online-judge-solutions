#include <stdio.h>
#include <stdlib.h>

#define N 7
#define M 6

char *getAns(int **vals);
int tryGames(int *used, int **vals, int c, int z, int p, int n);
int indexOf(int *arr, int n);
int match(int **vals, int c, int p, int i, int n);

int main() {
	int i, j, f;
	while (scanf("%d", &f) != EOF) {
		/* Read values for test case */
		int **vals = malloc(N * sizeof(int *));
		for (i = 0; i < N; i++) {
			vals[i] = malloc(M * sizeof(int));
			if (i == 0) {
				vals[i][0] = f;
				j = 1;
			}  else {
				j = 0;
			}
			for (; j < M; j++) {
				scanf("%d", &vals[i][j]);
			}
		}
		/* Get and print answer */
		char *ans = getAns(vals);
		printf("%s\n", ans);
		/* Release used memory */
		for (i = 0; i < N; i++) {
			free(vals[i]);
		}
		free(vals);
	}
	return 0;
}

char *getAns(int **vals) {
	/* Iterate variables */
	int i, j;
	/* Keep currently used hexagons */
	int used[N];
	for (i = 0; i < N; i++) {
		used[i] = 0;
	}
	/* Try to solve the problem with */
	/* every possible center (7 ps)  */
	for (i = 0; i < N; i++) {
		used[i] = 1;
		int try = tryGames(used, vals, i, -1, -1, 0);
		if (try) {
			return "YES";
		}
		used[i] = 0;
	}
	return "NO";
}

int tryGames(int *used, int **vals, int c, int z, int p, int n) {
	if (n == M) {
		return match(vals, c, p, z, n);
	} else {
		int i;
		for (i = 0; i < N; i++) {
			if (!used[i]) {
				int ct = indexOf(vals[i], vals[c][n]);
				if (p == -1 || match(vals, c, p, i, n)) {
					used[i] = 1;
					int try = 0;
					if (n == 0) {
						try = tryGames(used, vals, c, i, i, n+1);
					} else {
						try = tryGames(used, vals, c, z, i, n+1);
					}
					if (try) {
						return 1;
					}
					used[i] = 0;
				}
			}
		}
		return 0;
	}
}

int indexOf(int *arr, int n) {
	int i;
	for (i = 0; i < M; i++) {
		if (arr[i] == n) {
			return i;
		}
	}
	return -1;
}

int match(int **vals, int c, int p, int i, int n) {
	int ctp = indexOf(vals[p], vals[c][n-1]);
	int cti = indexOf(vals[i], vals[c][n%M]);
	int left  = (ctp == 0) ? M-1 : ctp-1;
	int right = (cti == M-1) ? 0 : cti+1;
	return vals[p][left] == vals[i][right];
}