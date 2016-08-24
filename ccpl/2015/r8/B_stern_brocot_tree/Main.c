#include <stdio.h>

int main() {
	int i, j, T;
	long long l1, l2, r1, r2, a1, a2;
	char line[90];

	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		scanf("%s", line);
		if (line[0] == '\0') {
			printf("1/1\n");
		} else {
			if (line[0] == 'L') {
				l1 = 0; l2 = 1;
				r1 = 1; r2 = 1;
			} else {
				l1 = 1; l2 = 1; 
				r1 = 1; r2 = 0;
			}

			j = 1;
			while (line[j] != '\0') {
				a1 = l1 + r1;
				a2 = l2 + r2;

				if (line[j] == 'L') {
					r1 = a1; r2 = a2;
				} else {
					l1 = a1; l2 = a2;
				}

				j++;
			}

			a1 = l1 + r1;
			a2 = l2 + r2;

			printf("%llu/%llu\n", a1, a2);
		}
	}

	return 0;
}