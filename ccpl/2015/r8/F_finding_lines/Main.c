#include <stdio.h>
#include <stdlib.h>

#define NAN (0.0 / 0.0)
#define ITERS 30

void getAns(int n, int p, int x[], int y[]);
int cmpSlope(const void * a, const void * b);
int cmpInt(const void * a, const void * b);
int getMaxSlope(double arr[], int n);
int getMaxVertical(int arr[], int n);

int main() {
	int i, n, p, x[100000], y[100000];

	while (scanf("%d", &n) != EOF) {
		scanf("%d", &p);

		for (i = 0; i < n; i++) {
			scanf("%d %d", &x[i], &y[i]);
		}

		getAns(n, p, x, y);
	}

	return 0;
}

void getAns(int n, int p, int x[], int y[]) {
	int ans = 0, sub = 0, count, i, j, u;
	double current, map[n];

	/* Check base case */
	if (n <= 2) {
		printf("possible\n");
	} else {

		/* Check y=mx+b lines */
		for (u = 0; u < ITERS; u++) {
			i = rand() % n;
			for (j = 0; j < n; j++) {
				if (i != j && x[i] != x[j]) {
					map[j] = (0.0 + y[j] - y[i]) / (x[j] - x[i]);
				} else {
					map[j] = NAN;
				}
			}

			qsort(map, n, sizeof(double), cmpSlope);
			sub = getMaxSlope(map, n);
			ans = (sub > ans) ? sub : ans;
		}

		/* Check vertical lines */
		qsort(x, n, sizeof(int), cmpInt);
		sub = getMaxVertical(x, n);
		ans = (sub > ans) ? sub : ans;

		if (ans * 100 >= n * p) {
			printf("possible\n");
		} else {
			printf("impossible\n");
		}
	}
}

int cmpSlope(const void * a, const void * b) {
	double v1 = * (double *) a;
	double v2 = * (double *) b;
	if (v1 != v1) return -1;
	if (v2 != v2) return  1;
	if (v1 > v2)  return  1;
	if (v1 < v2)  return -1;
	else		  return  0;
}

int cmpInt(const void * a, const void * b) {
	int v1 = * (int *) a;
	int v2 = * (int *) b;
	if (v1 > v2) return  1;
	if (v1 < v2) return -1;
	else		 return  0;
}

int getMaxSlope(double arr[], int n) {
	int ans = 0, count = 0, j;
	double current = arr[0];
	for (j = 0; j < n; j++) {
		if (current != current) {
			current = arr[j];
			count = (current != current) ? 0 : 1;
		} else if (current - arr[j] == 0) {
			count++;
		} else {
			current = arr[j];
			ans = (count > ans) ? count : ans;
			count = 1;
		}
	}
	ans = (count > ans) ? count : ans;
	return ans + 1;
}

int getMaxVertical(int arr[], int n) {
	int ans = 0, count = 0, current = arr[0], j;
	for (j = 0; j < n; j++) {
		if (current == arr[j]) {
			count++;
		} else {
			current = arr[j];
			ans = (count > ans) ? count : ans;
			count = 1;
		}
	}
	ans = (count > ans) ? count : ans;
	return ans;
}