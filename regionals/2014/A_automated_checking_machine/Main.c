#include <stdio.h>

#define NUM 5

int main() {
	int fst;
	while (scanf("%d", &fst) != EOF) {
		int l1[NUM], l2[NUM], i;
		l1[0] = fst;
		for (i = 1; i < NUM; i++)
			scanf("%d", &l1[i]);
		for (i = 0; i < NUM; i++)
			scanf("%d", &l2[i]);
		char ans = 'Y';
		for (i = 0; i < NUM; i++)
			if (l1[i] == l2[i])
				ans = 'N';
		printf("%c\n", ans);
	}
	return 0;
}