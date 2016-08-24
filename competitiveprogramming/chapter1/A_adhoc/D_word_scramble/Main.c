#include <stdio.h>
#include <string.h>

int main() {
	char line[100];
	int n, i;
	while (scanf("%s", line) != EOF) {
		n = strlen(line);
		for (i = n-1; i >= 0; i--)
			printf("%c", line[i]);
		printf("%c", getchar());
	}
	return 0;
}