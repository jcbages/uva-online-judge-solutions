#include <stdio.h>
#include <string.h>

#define MAX 1000001

long long getAns(char *line, int n);

int main() {
	char line[MAX];
	while (scanf("%s", line) != EOF) {
		int n = strlen(line);
		long long ans = getAns(line, n);
		printf("%lld\n", ans);
	}
	return 0;
}

long long getAns(char *line, int n) {
	long long ans = 0;
	int x[3], A = 0, i;
	for (i = 0; i < 3; i++) x[i] = 0;
	for (i = 0; i < n; i++) {
		if (line[i] >= '0' && line[i] <= '9') {
			int v = (line[i] - '0') % 3;
			A = (A + v) % 3;
			if (A == 0) ans += 1;
			ans += x[A];
			x[A] += 1;
		} else {
			x[0] = x[1] = x[2] = A = 0;
		}
	}
	return ans;
}
