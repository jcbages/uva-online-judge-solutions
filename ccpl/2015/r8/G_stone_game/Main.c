#include <stdio.h>
#include <stdlib.h>

int getAns(int N, int nums[]);

int main() {
	int N, ans;

	scanf("%d", &N);
	while (N != 0) {
		int nums[N], i = 0;
		while (i < N) {
			scanf("%d", &nums[i]);
			i++;
		}

		ans = getAns(N, nums);
		if (ans != 0) {
			printf("Yes\n");
		} else {
			printf("No\n");
		}

		scanf("%d", &N);
	}

	return 0;
}

int getAns(int N, int nums[]) {
	int i, ans = nums[0];
	for (i = 1; i < N; i++) {
		ans ^= nums[i];
	}
	return ans;
}