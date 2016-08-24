#include <stdio.h>
#include <stdlib.h>

long long max(long long a, long long b);

int main() {
	long long n, m, c, s=1;
	while (scanf("%lld %lld %lld", &n, &m, &c) == 3) {
		if (n + m + c == 0) break;
		if (s > 1) printf("\n");
		long long maxpow = 0, ans = 0, i, op;
		long long *consumption = malloc(n * sizeof(long long));
		long long *status = malloc(n * sizeof(long long));
		for (i = 0; i < n; i++) {
			scanf("%lld", &consumption[i]);
			status[i] = -1;
		}
		for (i = 0; i < m; i++) {
			scanf("%lld", &op);
			status[op-1] *= -1;
			maxpow += consumption[op-1] * status[op-1];
			ans = max(maxpow, ans);
		}
		printf("Sequence %lld\n", s);
		if (ans > c) {
			printf("Fuse was blown.\n");
		} else {
			printf("Fuse was not blown.\n");
			printf("Maximal power consumption was %lld amperes.\n", ans);
		}
		free(consumption);
		free(status);
		s++;
	}
	return 0;
}

long long max(long long a, long long b) {
	return (a >= b) ? a : b;
}