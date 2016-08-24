#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX 10

int getAns(int b1, int b2, int r1, int r2);
int *getBase(int n, int b);
int getDecimal(int *base, int b);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int b1, b2, r1, r2;
		scanf("%d %d %d %d", &b1, &b2, &r1, &r2);
		int ans = getAns(b1, b2, r1, r2);
		if (ans != -1) {
			printf("%d\n", ans);
		} else {
			printf("Non-existent.\n");
		}
	}
	return 0;
}

int getAns(int b1, int b2, int r1, int r2) {
	int ans = -1, n;
	for (n = r1; n <= r2; n++) {
		int *base = getBase(n, b1);
		int decimal = getDecimal(base, b2);
		if (decimal % n == 0)
			ans = (n > ans) ? n : ans;
		free(base);
	}
	return ans;
}

int *getBase(int n, int b) {
	int *ans = malloc(MAX * sizeof(int)), i;
	for (i = 0; i < MAX; i++)
		ans[i] = -1;
	i = 0;
	while (n > 1) {
		ans[i++] = n % b;
		n /= b;
	}
	if (n == 1) ans[i] = n;
	return ans;
}

int getDecimal(int *base, int b) {
	int ans = 0, i = 0;
	while (base[i] != -1) {
		int p = (int) pow(b, i);
		ans += p * base[i++];
	}
	return ans;
}