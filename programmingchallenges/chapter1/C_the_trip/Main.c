#include <stdio.h>
#include <stdlib.h>

double getAns(double *money, int n);

int main() {
	int i, n;
	double *money, ans;
	scanf("%d", &n);
	while (n != 0) {
		money = malloc(n * sizeof(double));
		for (i = 0; i < n; i++) {
			scanf("%lf", &money[i]);
		}
		ans = getAns(money, n);
		printf("$%.2lf\n", ans);
		scanf("%d", &n);
		free(money);
	}
	return 0;
}

double getAns(double *money, int n) {
	int i;
	double mean = 0.0, pos = 0.0, neg = 0.0, diff;
	for (i = 0; i < n; i++) {
		mean += money[i];
	}
	mean /= n;
	for (i = 0; i < n; i++) {
		diff = (int) ((money[i] - mean) * 100.00) / 100.00;
		if (diff > 0) {
			pos += diff;
		} else {
			neg += diff;
		}
	}
	return (pos >= -neg) ? pos : -neg;
}