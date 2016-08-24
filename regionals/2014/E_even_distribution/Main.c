#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX_POSS 100000

typedef struct {
	int n, m, candies;
	int *services;
	int *divisors;
	int *states;
} Node;

int gcd(int a, int b);
int indexOf(Node *island, int n);
void dfs(int A, int v, Node *islands, int *poss);
void getDivisors(Node *island);
int cmpfunc(const void *a, const void *b);

int main() {
	int I, S;
	while (scanf("%d %d", &I, &S) != EOF) {
		/* Read candies per island */
		int i;
		Node *islands = malloc(I * sizeof(Node));
		for (i = 0; i < I; i++) {
			islands[i].n = islands[i].m = 0;
			scanf("%d", &islands[i].candies);
		}
		/* Initialize boat services counter */
		int count[I];
		for (i = 0; i < I; i++) {
			count[i] = 0;
		}
		/* Read boat services data */
		int paths[S][2];
		for (i = 0; i < S; i++) {
			scanf("%d %d", &paths[i][0], &paths[i][1]);
			count[paths[i][0]-1] += 1;
			count[paths[i][1]-1] += 1;
		}
		/* Initialize boat services per island */
		for (i = 0; i < I; i++) {
			islands[i].services = malloc(count[i] * sizeof(int));
		}
		/* Add boat services data per island */
		for (i = 0; i < S; i++) {
			int A = paths[i][0], B = paths[i][1];
			islands[A-1].services[islands[A-1].n] = B;
			islands[B-1].services[islands[B-1].n] = A;
			islands[A-1].n += 1;
			islands[B-1].n += 1;
		}
		/* Initialize possible integers k */
		int poss[MAX_POSS];
		for (i = 0; i < MAX_POSS; i++) {
			poss[i] = 0;
		}
		/* Get number of divisors per island */
		for (i = 0; i < I; i++) {
			getDivisors(islands+i);
		}
		/* Get number of possible integers k */
		for (i = 0; i < I; i++) {
			if (islands[i].states[0] == 0) {
				dfs(i+1, 0, islands, poss);
			}
		}
		/* Count number of possible integers k */
		int ans = 0;
		for (i = 0; i < MAX_POSS; i++) {
			ans += poss[i];
		}
		/* Free allocated memory */
		for (i = 0; i < I; i++) {
			free(islands[i].services);
			free(islands[i].divisors);
			free(islands[i].states);
		}
		free(islands);
		/* Print number of possible integers k */
		printf("%d\n", ans);
	}
	return 0;
}

int gcd(int a, int b) {
	if (a < b) {
		int c = a;
		a = b;
		b = c;
	}

	if (b == 0) {
		return a;
	} else {
		return gcd(b, a%b);
	}
}

int indexOf(Node *island, int n) {
	int i;
	for (i = 0; i < island->m; i++)
		if (island->divisors[i] == n)
			return i;
	return -1; /* Never gets here */
}

void dfs(int A, int v, Node *islands, int *poss) {
	Node *is = islands+A-1;
	is->states[v] = 1;

	int d = is->divisors[v];
	poss[d-1] = 1;

	int i;
	for (i = 0; i < is->n; i++) {
		Node *op = islands+is->services[i]-1;
		int n = gcd(d, op->candies);
		int j = indexOf(op, n);
		if (j != -1 && op->states[j] == 0) {
			dfs(is->services[i], j, islands, poss);
		}
	}
}

void getDivisors(Node *island) {
	int nums[200], s = 0, i = 1, n = island->candies;
	while (i <= sqrt(n)) {
		if (n % i == 0) {
			nums[s++] = i;
			if (n/i > i) {
				nums[s++] = n/i;
			}
		}
		i += 1;
	}

	island->m = s;
	island->divisors = malloc(s * sizeof(int));
	island->states = malloc(s * sizeof(int));
	for (i = 0; i < s; i++) {
		island->divisors[i] = nums[i];
		island->states[i] = 0;
	}
	qsort(island->divisors, s, sizeof(int), cmpfunc);
}

int cmpfunc(const void *a, const void *b) {
	const int n = *((const int *) a);
	const int m = *((const int *) b);
	return m - n;
}