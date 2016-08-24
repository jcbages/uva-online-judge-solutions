#include <stdio.h>

#define N 0
#define Y 1

int main() {
	char c, s = N;
	while ((c = getchar()) != EOF) {
		if (c == '"' && s == N) {
			s = Y;
			printf("``");
		} else if (c == '"' && s == Y) {
			s = N;
			printf("''");
		} else {
			printf("%c", c);
		}
	}
	return 0;
}