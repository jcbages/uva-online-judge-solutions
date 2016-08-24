#include <stdio.h>
#include <stdlib.h>

#define MAX 100000

int getAns(int *nums, int N);

int main() {
	int N = 0;
	int *nums = malloc(MAX * sizeof(int));
	while (scanf("%d", &nums[N++]) != EOF) {
		if (getchar() == '\n') {
			int ans = getAns(nums, N);
			printf("%d\n", ans);
			N = 0;
		}
	}
	return 0;
}

int getAns(int *nums, int N) {
	int max = 0, i, curr = 0;
	for (i = 0; i < N; i++) {
		if (nums[i] > curr + nums[i])
			curr = nums[i];
		else
			curr += nums[i];
		max = (curr > max) ? curr : max;
	}
	max = (curr > max) ? curr : max;
	return max;
}