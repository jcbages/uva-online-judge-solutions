#include <stdio.h>

#define MAX 1000000

int gcd(int a, int b);

int main() {
	int a, b, c, d;
	while (scanf("%d %d", &a, &b) != EOF) {
		printf("[%d;", a/b);
		while (a%b != 0) {
			printf("%d,", b/a);
			b %= a;

			c = a;
			a = b;
			b = c;
		}
		printf("%d]\n", b);
	}
	return 0;
}