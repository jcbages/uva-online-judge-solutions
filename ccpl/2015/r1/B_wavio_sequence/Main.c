#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int getAns(int N, int *arr);
int *LIS(int N, int *arr);
int *reverse(int N, int *arr);
int max(int a, int b);
int min(int a, int b);

int main() {
	int N, i, *arr;
	while (scanf("%d", &N) != EOF) {
		arr = malloc(N * sizeof(int));
		for (i = 0; i < N; i++) scanf("%d", &arr[i]);
		int ans = getAns(N, arr);
		printf("%d\n", ans);
	}

	return 0;
}

int getAns(int N, int *arr) {
	int *incr, *decr, ans, i;
	
	incr = LIS(N, arr);
	arr = reverse(N, arr);
	decr = LIS(N, arr);
	decr = reverse(N, decr);

	ans = 0;
	for (i = 0; i < N; i++)
		ans = max(2 * min(incr[i], decr[i]) - 1, ans);

	free(arr);
	free(incr);
	free(decr);

	return ans;
}

int *LIS(int N, int *arr) {
	int *L, *I, i, low, mid, high;
	L = malloc(N * sizeof(int));
	I = malloc((N+1) * sizeof(int));

	I[0] = -INT_MAX;
	for (i = 1; i < N+1; i++) {
		L[i-1] = 1;
		I[i] = INT_MAX;
	}

	for (i = 1; i < N+1; i++) {
		low = 0;
		high = i;
		while (low <= high) {
			mid = (low + high) / 2;
			if (I[mid] < arr[i-1])
				low = mid + 1;
			else
				high = mid - 1;
		}
		I[low] = arr[i-1];
		L[i-1] = low;
	}
	free(I);

	return L;
}

int *reverse(int N, int *arr) {
	int i, *ans;
	ans = malloc(N * sizeof(int));
	for (i = 0; i < N; i++)
		ans[i] = arr[N-i-1];
	free(arr);
	return ans;
}

int max(int a, int b) {
	return (a >= b) ? a : b;
}

int min(int a, int b) {
	return (a < b) ? a : b;
}
