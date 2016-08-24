#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int v, c;
} Item;

int getAns(int n, int k, Item* items);
int cmpItems(const void * a, const void * b);
int max(int a, int b);
int min(int a, int b);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int n, k;
		scanf("%d %d", &n, &k);

		int j;
		Item* items = malloc(n * sizeof(Item));
		for (j = 0; j < n; j++) {
			scanf("%d %d", &items[j].v, &items[j].c);
		}
		qsort(items, n, sizeof(Item), cmpItems);

		int ans = getAns(n, k, items);
		printf("%d\n", ans);

		free(items);
	}

	return 0;
}

int getAns(int n, int k, Item* items) {
	int dp[n+1][k+1], i, j;
	for (i = 0; i <= k; i++) {
		dp[0][i] = 0;
	}
	for (i = 1; i <= n; i++) {
		int prevEarn = dp[i-1][0];
		int thisEarn = items[i-1].v - items[i-1].c;
		dp[i][0] = max(prevEarn, thisEarn);
	}

	for (j = 1; j <= k; j++) {
		for (i = 1; i <= n; i++) {
			int remThis = dp[i-1][j-1] -items[i-1].c;
			int buyThis = items[i-1].v - items[i-1].c;
			int impDecision = min(remThis, buyThis);
			int ignoreItem = dp[i-1][j];
			dp[i][j] = max(impDecision, ignoreItem);
		}
	}

	return dp[n][k];
}

int cmpItems(const void * a, const void * b) {
	Item i1 = * (Item *) a;
	Item i2 = * (Item *) b;
	int diffV = i2.v - i1.v;
	int diffC = i2.c - i1.c;
	return (diffV == 0) ? diffC : diffV;
}

int max(int a, int b) {
	return (a >= b) ? a : b;
}

int min(int a, int b) {
	return (a <  b) ? a : b;
}