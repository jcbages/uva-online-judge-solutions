#include <stdio.h>

int getAns(int P[], int D);
int arrMax(int P[], int D);
int max(int a, int b);
int min(int a, int b);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int D, j;
		scanf("%d", &D);
		int P[D];
		for (j = 0; j < D; j++) {
			scanf("%d", &P[j]);
		}
		int ans = getAns(P, D);
		printf("Case #%d: %d\n", i+1, ans);
	}
	return 0;
}

int getAns(int P[], int D) {
	int i, ans = 1000000000;
	for (i = 1; i <= arrMax(P, D); i++) {
		int j, curr = i;
		for (j = 0; j < D; j++) {
			curr += P[j] / i;
			if (P[j] % i == 0) {
				curr -= 1;
			}
		}
		ans = min(curr, ans);
	}
	return ans;
}

int arrMax(int P[], int D) {
	int i, ans = P[0];
	for (i = 0; i < D; i++) {
		ans = max(P[i], ans);
	}
	return ans;
}

int max(int a, int b) {
	return (a >= b) ? a : b;
}

int min(int a, int b) {
	return (a < b) ? a : b;
}