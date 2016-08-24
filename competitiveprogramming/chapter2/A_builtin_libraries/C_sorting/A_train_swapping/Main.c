#include <stdio.h>

int getSwaps(int carriages[], int L);

int main() {
	int N, i;
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		int L, j;
		scanf("%d", &L);
		int carriages[L];
		for (j = 0; j < L; j++)
			scanf("%d", &carriages[j]);
		int swaps = getSwaps(carriages, L);
		printf("Optimal train swapping takes %d swaps.\n", swaps);
	}
	return 0;
}

int getSwaps(int carriages[], int L) {
	int i, j, ans = 0;
	for (i = L-1; i >= 0; i--) {
		for (j = 0; j < i; j++) {
			if (carriages[j] > carriages[j+1]) {
				int tmp = carriages[j];
				carriages[j] = carriages[j+1];
				carriages[j+1] = tmp;
				ans += 1;
			}
		}
	}
	return ans;
}