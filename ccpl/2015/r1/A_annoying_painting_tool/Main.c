#include <stdio.h>
#include <stdlib.h>

int getAns(int n, int m, int r, int c, char **wall);
void paint(int r, int c, int x, int y, char **blank);
int compare(int n, int m, char **wall, char **blank);

int main() {
	int n, m, r, c, i, ans;
	char line[1000];
	while (scanf("%d %d %d %d", &n, &m, &r, &c) == 4) {
        if (n + m + r + c == 0) break;
		char **wall = malloc(n * sizeof(char *));
		for (i = 0; i < n; i++) {
			wall[i] = malloc((m+1) * sizeof(char));
			scanf("%s", wall[i]);
		}

		ans = getAns(n, m, r, c, wall);
		printf("%d\n", ans);

		for (i = 0; i < n; i++) free(wall[i]);
		free(wall);
	}
	return 0;
}

int getAns(int n, int m, int r, int c, char **wall) {
	int ans = 0, i, j;
	char **blank;

	blank = malloc(n * sizeof(char *));
	for (i = 0; i < n; i++) {
		blank[i] = malloc(m * sizeof(char));
		for (j = 0; j < m; j++) blank[i][j] = '0';
	}

	for (i = 0; i < n-r+1; i++) {
		for (j = 0; j < m-c+1; j++) {
			if (blank[i][j] != wall[i][j]) {
				paint(r, c, i, j, blank);
				ans++;
			}
		}
	}

	ans = (compare(n, m, wall, blank) == 1) ? ans : -1;
	for (i = 0; i < n; i++) free(blank[i]);
	free(blank);
	return ans;
}

void paint(int r, int c, int x, int y, char **blank) {
	int rlim = r+x, clim = c+y, i, j;
	for (i = x; i < rlim; i++) {
		for (j = y; j < clim; j++) {
			blank[i][j] = (blank[i][j] == '1') ? '0' : '1';
		}
	}
}

int compare(int n, int m, char **wall, char **blank) {
	int i, j, ans = 1;
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (wall[i][j] != blank[i][j]) {
				ans = 0;
			}
		}
	}
	return ans;
}