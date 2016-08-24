#include <stdio.h>
#include <stdlib.h>

int *getAns(int *slots, int N, long long R);
int getRnd(int *slots, int i, int N);
int getSlot(int *slots, int i, int N, int R);

int main() {
	int N, i;
	long long R;
	while (scanf("%d %lld", &N, &R) != EOF) {
		int *slots = malloc(N * sizeof(int));
		for (i = 0; i < N; i++) {
			scanf("%d", &slots[i]);
		}
		int *ans = getAns(slots, N, R);
		for (i = 0; i < N; i++) {
			printf("%d", ans[i]);
			if (i < N-1) {
				printf(" ");
			}
		}
		printf("\n");
		free(slots);
		free(ans);
	}
	return 0;
}

int *getAns(int *slots, int N, long long R) {
	int i;
	int *ans = malloc(N * sizeof(int));
	for (i = 0; i < N; i++) {
		if (slots[i]-1 == i) {
			ans[i] = slots[i];
		} else {
			int rnd = getRnd(slots, i, N);
			int mod = R % rnd;
			ans[i] = getSlot(slots, i, N, mod);
		}
	}
	return ans;
}

int getRnd(int *slots, int i, int N) {
	int curr = slots[i]-1, ans = 1;
	while (curr != i) {
		curr = slots[curr]-1;
		ans += 1;
	}
	return ans;
}

int getSlot(int *slots, int i, int N, int R) {
	int j;
	int curr = i;
	for (j = 0; j < R; j++) {
		curr = slots[curr]-1;
	}
	return curr+1;
}
