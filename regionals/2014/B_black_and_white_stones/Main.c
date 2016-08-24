#include <stdio.h>
#include <string.h>

#define MAX 5001

long long getAns(int A, int B, char line[]);
int count(char c, char line[], int n);
long long min(long long a, long long b);
long long absVal(int a);

int main() {
	int A, B;
	while (scanf("%d %d", &A, &B) != EOF) {
		char line[MAX];
		scanf("%s", line);
		long long ans = getAns(A, B, line);
		printf("%lld\n", ans);
	}
	return 0;
}

long long getAns(int A, int B, char line[]) {
	int n = strlen(line);
	if (n <= 1) {
		return 0;
	} else {
		long long ans = 0;
		int b = count('B', line, n), i = 0, j = n-1;
		while (i < b) {
			if (line[i] == 'W') {
				if (line[j] == 'B') {
					long long x = absVal(j - i);
					ans += min(A, (A - B) * x);
					i += 1;
					j -= 1;
				} else {
					j -= 1;
				}
			} else {
				i += 1;
			}
		}
		return ans;
	}
}

int count(char c, char line[], int n) {
	int i, ans = 0;
	for (i = 0; i < n; i++)
		if (line[i] == c)
			ans += 1;
	return ans;
}

long long min(long long a, long long b) {
	return (a < b) ? a : b;
}

long long absVal(int a) {
	return (a >= 0) ? a : -a;
}