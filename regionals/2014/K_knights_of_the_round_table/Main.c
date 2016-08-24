#include <stdio.h>

#define MOD 1000000007

long long getAns(int K, int D, int A[], int B[]);
long long getFactorial(int n);

int main() {
	int K, D;
	while (scanf("%d %d", &K, &D) != EOF) {
		int A[K], B[K], i;
		for (i = 0; i < K; i++) {
			A[i] = B[i] = 0;
		}
		int a, b;
		for (i = 0; i < D; i++) {
			scanf("%d %d", &a, &b);
			A[a-1] = 1;
			B[b-1] = 1;
		}

		for (i = 0; i < K; i++) printf("%d ", A[i]);
		printf("\n");
		for (i = 0; i < K; i++) printf("%d ", B[i]);
		printf("\n");

		long long ans = getAns(K, D, A, B);
		printf("%lld\n", ans);
	}
	return 0;
}

long long getAns(int K, int D, int A[], int B[]) {
	int init = -1, end = -1, i;
	i = 0;
	while (i < K && init == -1) {
		if (A[i] == 0 && B[i] == 1)
			init = i;
		i = i + 1;
	}
	if (init == -1) {

		printf("init:%d end:%d n:%d\n", init, end, 0);

		return 1;
	}
	i = (init + 1) % K;
	while (i != (init % K)) {
		if (A[i] == 1 && B[i] == 0)
			end = i;
		i = (i + 1) % K;
	}
	if (end == -1) {

		printf("init:%d end:%d n:%d\n", init, end, 0);

		return 1;
	}
	int n = 0;
	i = (init + 1) % K;
	while (i != (end + 1) % K) {
		if (A[i] <= 1 && B[i] == 0)
			n += 1;
		i = (i + 1) % K;
	}

	printf("init:%d end:%d n:%d\n", init, end, n);

	return getFactorial(n);
}

long long getFactorial(int n) {
	long long ans = 1;
	while (n > 1) {
		ans *= (n % MOD);
		n -= 1;
	}
	return ans % MOD;
}