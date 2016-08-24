#include <stdio.h>

#define LIM 128

int main() {
	int N, i, cost[LIM], ans;
	scanf("%d\n", &N);
	for (i = 0; i < N; i++) {
		int K, M, j;
		scanf("%d\n", &K);
		for (j = 0; j < LIM; j++)
			cost[j] = -1;
		for (j = 0; j < K; j++) {
			char key; int val;
			scanf("%c %d\n", &key, &val);
			cost[key] = val;
		}
		int c;
		ans = j = 0;
		scanf("%d\n", &M);
		while (j < M) {
			c = getchar();
			if (c == '\n') j++;
			else {
				if (cost[c] < 0 || c < 0 || c >= LIM)
					ans += 0;
				else
					ans += cost[c];
			}
		}
		if (ans % 100 < 10)
			printf("%d.0%d$\n", ans / 100, ans % 100);
		else
			printf("%d.%d$\n",  ans / 100, ans % 100);
	}
	return 0;
}