#include <stdio.h>

int max(int a, int b);

int main() {
	long long H, U, D, F, i, t, v;
	while (scanf("%lld %lld %lld %lld", &H, &U, &D, &F) != EOF) {
		if (H == 0) break;
		i = t = 0;
		v = U * F;
		while (1) {
			t += max(U * 100 - v * i, 0);
			if (t < 0 || t > H * 100) break;
			t -= D * 100;
			if (t < 0 || t > H * 100) break;
			i++;
		}
		if (t < 0)	printf("failure on day %lld\n", i+1);
		else		printf("success on day %lld\n", i+1);
	}
	return 0;
}

int max(int a, int b) {
	return (a >= b) ? a : b;
}