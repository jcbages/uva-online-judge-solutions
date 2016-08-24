#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define XLIM 10000
#define ZLIM 100
#define BLEN 101

typedef struct {
	char *num;
} Bigint;

Bigint getAns(char *X, char *Z);
Bigint add(Bigint *a, Bigint *b);
void initBigint(Bigint *n);
void printBigint(Bigint *n);
void freeDp(Bigint **dp, int n, int m);

int main() {
	int N, i;
	char *X = malloc(XLIM * sizeof(char));
	char *Z = malloc(ZLIM * sizeof(char));
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		scanf("%s", X);
		scanf("%s", Z);
		Bigint ans = getAns(X, Z);
		printBigint(&ans);
	}
	free(X);
	free(Z);
	return 0;
}

Bigint getAns(char *X, char *Z) {
	int n = strlen(Z), m = strlen(X), i, j;
	Bigint **dp = malloc((n+1) * sizeof(Bigint *));
	for (i = 0; i <= n; i++)
		dp[i] = malloc((m+1) * sizeof(Bigint));

	/* Init first row */
	for (j = 0; j <= m; j++) {
		initBigint(&dp[0][j]);
		dp[0][j].num[BLEN-1] = 1;
	}
	/* Init first col */
	for (i = 1; i <= n; i++) {
		initBigint(&dp[i][0]);
	}

	for (i = 1; i <= n; i++) {
		for (j = 1; j <= m; j++) {
			if (Z[i-1] == X[j-1]) {
				dp[i][j] = add(&dp[i-1][j-1], &dp[i][j-1]);
			} else {
				dp[i][j] = dp[i][j-1];
			}
		}
	}
	return dp[n][m];
}

Bigint add(Bigint *a, Bigint *b) {
	Bigint ans;
	initBigint(&ans);
	int i = BLEN - 1, carry = 0, res;
	while (i >= 0) {
		res = a->num[i] + b->num[i] + carry;
		if (res >= 10) carry = 1;
		else carry = 0;
		ans.num[i] = res % 10;
		i--;
	}
	return ans;
}

void initBigint(Bigint *n) {
	int i;
	n->num = malloc(BLEN * sizeof(char));
	for (i = 0; i < BLEN; i++) {
		n->num[i] = 0;
	}
}

void printBigint(Bigint *n) {
	int started = 0, i;
	for (i = 0; i < BLEN; i++) {
		if (started == 0 && n->num[i] != 0)
			started = 1;
		if (started == 1)
			printf("%d", n->num[i]);
	}
	printf("\n");
}

void freeDp(Bigint **dp, int n, int m) {
	int i, j;
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			free(dp[i][j].num);
		}
		free(dp[i]);
	}
	free(dp);
}