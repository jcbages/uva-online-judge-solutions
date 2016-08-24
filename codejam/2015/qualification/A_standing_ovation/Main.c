#include <stdio.h>

#define MAX 1001

int getAns(int S, char line[]);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 1; i <= T; i++) {
		int S, ans;
		char line[MAX];
		scanf("%d %s", &S, line);
		ans = getAns(S, line);
		printf("Case #%d: %d\n", i, ans);
	}
}

int getAns(int S, char line[]) {
	int i, curr = 0, ans = 0;
	for (i = 0; i < S+1; i++) {
		if (line[i] - '0' > 0) {
			int add = (curr < i) ? i - curr : 0;
			ans += add;
			curr += add + line[i] - '0';
		}
	}
	return ans;
}