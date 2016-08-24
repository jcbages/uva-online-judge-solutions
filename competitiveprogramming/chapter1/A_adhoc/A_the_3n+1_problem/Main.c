#include <stdio.h>

int max(int a, int b);
int min(int a, int b);
int cycle(unsigned int n);

int main() {
	int i, j, n, ans;
	while (scanf("%d %d", &i, &j) != EOF) {
		ans = 0;
		for (n = min(i, j); n <= max(i, j); n++) {
			ans = max(cycle(n), ans);
		}
		printf("%d %d %d\n", i, j, ans);
	}
	return 0;
}

int max(int a, int b) {
	return (a > b) ? a : b;
}

int min(int a, int b) {
	return (a < b) ? a : b;
}

int cycle(unsigned int n) {
	int ans = 1;
	while (n != 1) {
		if (n % 2 == 0) {
			n = n / 2;
		} else {
			n = 3 * n + 1;
		}
		ans++;
	}
	return ans;
}