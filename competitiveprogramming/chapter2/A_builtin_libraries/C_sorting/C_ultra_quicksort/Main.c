#include <stdio.h>
#include <stdlib.h>

void getSwaps(int *nums, int n, long long *ans);
void join(int *nums, int *left, int *right, int n, long long *ans);

int main() {
	int n, i;
	while (1) {
		scanf("%d", &n);
		if (n == 0) break;
		int nums[n];
		for (i = 0; i < n; i++)
			scanf("%d", &nums[i]);
		long long ans = 0;
		getSwaps(nums, n, &ans);
		printf("%lld\n", ans);
	}
	return 0;
}

void getSwaps(int nums[], int n, long long *ans) {
	if (n <= 1) return;
	int i;
	int left[n/2];
	int right[n-n/2];
	for (i = 0; i < n/2; i++)   left[i]  = nums[i];
	for (i = 0; i < n-n/2; i++) right[i] = nums[i+n/2];
	getSwaps(left, n/2, ans);
	getSwaps(right, n-n/2, ans);
	join(nums, left, right, n, ans);
}

void join(int nums[], int left[], int right[], int n, long long *ans) {
	int i = 0, j = 0, k = 0;
	while (i < n/2 || j < n-n/2) {
		if (i < n/2 && j < n-n/2) {
			if (left[i] < right[j]) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = right[j++];
				*ans += n/2 - i;
			}
		} else if (i < n/2) {
			nums[k++] = left[i++];
		} else {
			nums[k++] = right[j++];
		}
	}
}