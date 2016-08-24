#include <stdio.h>
#include <math.h>

#define PI 3.14159265358979323846

double getAns(int p, int a, int b, int c, int d, int n);
double getPrice(int p, int a, int b, int c, int d, int k);

int main() {
	int p, a, b, c, d, n;
	while (scanf("%d %d %d %d %d %d", &p, &a, &b, &c, &d, &n) != EOF) {
		double ans = getAns(p, a, b, c, d, n);
		printf("%f\n", ans);
	}
	return 0;
}

double getAns(int p, int a, int b, int c, int d, int n) {
	double ans = 0.0, gst = getPrice(p, a, b, c, d, 1), act = 0;
	int k;
	for (k = 1; k <= n; k++) {
		act = getPrice(p, a, b, c, d, k);
		gst = (act > gst) ? act : gst;
		ans = (gst - act > ans) ? gst - act : ans;
	}
	return ans;
}

double getPrice(int p, int a, int b, int c, int d, int k) {
	int v1 = a * k + b;
	int v2 = c * k + d;
	double d1 = sin(fmod(v1, 2 * M_PI));
	double d2 = cos(fmod(v2, 2 * M_PI));
	return p * (d1 + d2 + 2);
}