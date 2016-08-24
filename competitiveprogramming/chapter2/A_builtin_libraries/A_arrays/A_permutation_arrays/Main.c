#include <stdio.h>
#include <stdlib.h>

#define T 100000
#define C 30

typedef struct {
	int pos;
	char val[C];
} Item;

int cmpfunc (const void * a, const void * b);

int main() {
	Item items[T];
	int N, i, j;
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		int n = 0;
		scanf("%d", &items[n++].pos);
		while (getchar() != '\n')
			scanf("%d", &items[n++].pos);
		for (j = 0; j < n; j++)
			scanf("%s", items[j].val);
		qsort(items, n, sizeof(Item), cmpfunc);
		for (j = 0; j < n; j++)
			printf("%s\n", items[j].val);
		if (i < N-1)
			printf("\n");
	}
	return 0;
}

int cmpfunc (const void * a, const void * b) {
	Item i1 = *(Item *) a, i2 = *(Item *) b;
	return i1.pos - i2.pos;
}