#include <stdio.h>
#include <stdlib.h>

#define AGES 99

int main() {
	int n, i, j, age, ages[AGES];
	while (1) {
		scanf("%d", &n);
		if (n == 0) break;
		for (i = 0; i < AGES; i++) {
			ages[i] = 0;
		}
		for (i = 0; i < n; i++) {
			scanf("%d", &age);
			ages[age-1] += 1;
		}
		for (i = 0; i < AGES; i++) {
			for (j = 0; j < ages[i]; j++) {
				if (i > 0 || j > 0) printf(" ");
				printf("%d", i+1);
			}
		}
		printf("\n");
	}
	return 0;
}