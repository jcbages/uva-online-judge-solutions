#include <stdio.h>
#include <stdlib.h>

#define N 1000
#define TRUE 1
#define FALSE 0

typedef struct {
	int p, c, i, valid;
} Elephant;

void getAns(Elephant *E, int n);
int max(int a, int b);
int cmpfunc(const void *a, const void *b);

int main() {
	int n = 0;
	Elephant *E = malloc(N * sizeof(Elephant));
	while (scanf("%d %d", &E[n].p, &E[n].c) != EOF) {
		E[n].i = n+1;
		E[n].valid = FALSE;
		n++;
	}
	getAns(E, n);
	return 0;
}

void getAns(Elephant *E, int n) {
	qsort(E, n, sizeof(Elephant), cmpfunc);

	int *L = malloc(N * sizeof(int)), i, j, len;
	for (i = 0; i < n; i++) {
		L[i] = 1;
	}

	len = 0;
	for (i = 0; i < n; i++) {
		for (j = i+1; j < n; j++) {
			if (E[j].p > E[i].p &&
				E[j].c < E[i].c) {
				L[j] = max(L[i] + 1, L[j]);
				len = (L[j] > L[len]) ? j : len;
			}
		}
	}
	printf("%d\n", L[len]);

	E[len].valid = TRUE;
	for (i = len; i >= 0; i--) {
		if (L[i] == L[len] - 1 &&
			E[i].p < E[len].p &&
			E[i].c > E[len].c) {
			len = i;
			E[len].valid = TRUE;
		}
	}

	for (i = 0; i < n; i++) {
		if (E[i].valid == 1) {
			printf("%d\n", E[i].i);
		}
	}

	free(E); free(L);
}

int max(int a, int b) {
	return (a >= b) ? a : b;
}

int cmpfunc(const void *a, const void *b) {
	Elephant *e1 = (Elephant *) a, *e2 = (Elephant *) b;
	int diffP = e1->p - e2->p, diffC = e1->c - e2->c;
	return (diffP != 0) ? diffP : diffC;
}