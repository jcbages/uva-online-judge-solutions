#include <stdio.h>

void swap(char *pos, int i, int j);

int main() {
	int a, b;
	while (scanf("%d", &a) != EOF) {
		b = a;
		char *pos = (char *) &b;
		swap(pos, 0, 3);
		swap(pos, 1, 2);
		printf("%d converts to %d\n", a, b);
	}
	return 0;
}

void swap(char *pos, int i, int j) {
	char tmp = pos[i];
	pos[i] = pos[j];
	pos[j] = tmp;
}