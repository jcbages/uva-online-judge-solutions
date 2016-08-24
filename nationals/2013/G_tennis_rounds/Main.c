#include <stdio.h>

int main() {
	int N, i, j;
	while (scanf("%d %d %d", &N, &i, &j) != EOF) {
		if (i > j) {
			int k = i;
			i = j;
			j = k;
		}
		int ans = 1;
		while (i + 1 != j || j % 2 != 0) {
			i = (i % 2 == 0) ? i/2 : (i + 1)/2;
			j = (j % 2 == 0) ? j/2 : (j + 1)/2;
			ans += 1;
		}
		printf("%d\n", ans);
	}
	return 0;
}