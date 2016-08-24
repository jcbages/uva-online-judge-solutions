#include <stdio.h>

int getSwaps(int nums[], int N);
int join(int nums[], int left[], int right[], int N);

int main() {
	int N, i;
	while (1) {
		scanf("%d", &N);
		if (N == 0) break;
		int nums[N];
		for (i = 0; i < N; i++)
			scanf("%d", &nums[i]);
		int swaps = getSwaps(nums, N);
		if (swaps % 2 != 0)
			printf("Marcelo\n");
		else
			printf("Carlos\n");
	}
	return 0;
}

int getSwaps(int nums[], int N) {
	if (N <= 1) return 0;
	int i, ans = 0;
	int left[N/2];
	for (i = 0; i < N/2; i++)
		left[i] = nums[i];
	ans += getSwaps(left, N/2);
	int right[N-N/2];
	for (i = 0; i < N-N/2; i++)
		right[i] = nums[i+N/2];
	ans += getSwaps(right, N-N/2);
	ans += join(nums, left, right, N);
	return ans;
}

int join(int nums[], int left[], int right[], int N) {
	int i = 0, j = 0, k = 0, ans = 0;
	while (i < N/2 || j < N-N/2) {
		if (i < N/2 && j < N-N/2) {
			if (left[i] < right[j]) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = right[j++];
				ans += N/2 - i;
			}
		} else if (i < N/2) {
			nums[k++] = left[i++];
		} else {
			nums[k++] = right[j++];
		}
	}
	return ans;
}