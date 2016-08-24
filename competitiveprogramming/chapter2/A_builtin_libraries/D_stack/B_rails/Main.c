#include <stdio.h>

void getAns(int nums[], int N);

int main() {
	while (1) {
		int beginBlock;
		scanf("%d", &beginBlock);
		if (beginBlock == 0) break;
		while (1) {
			int endBlock;
			scanf("%d", &endBlock);
			if (endBlock == 0) break;
			int i, nums[beginBlock]; nums[0] = endBlock;
			for (i = 1; i < beginBlock; i++) scanf("%d", &nums[i]);
			getAns(nums, beginBlock);
		}
		printf("\n");
	}
	return 0;
}

void getAns(int nums[], int N) {
	int numToPush = 1;
	int actualNum = 0;
	int stackSize = 0;
	int stack[N];
	stack[stackSize++] = numToPush++;
	while (actualNum < N) {
		if (stackSize > 0 && stack[stackSize-1] == nums[actualNum]) {
			stackSize--; 	/* pop */
			actualNum++;	/* incr actualNum */
		} else {
			if (numToPush > N) {
				printf("No\n");
				return;
			} else {
				stack[stackSize++] = numToPush++;
			}
		}
	}
	printf("Yes\n");
}
