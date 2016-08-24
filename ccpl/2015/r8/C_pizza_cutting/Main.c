#include <stdio.h>

int main() {
	long long N;

	scanf("%llu", &N);
	while (N >= 0) {
		long long ans = (N * (N + 1)) / 2 + 1;
		printf("%llu\n", ans);
		scanf("%llu", &N);
	}

	return 0;
}