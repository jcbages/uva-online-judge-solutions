#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int cmpfunc(const void *a, const void *b);
int getSwaps(char *sequence);

typedef struct {
	int pos;
	char *seq;
} Sequence;

int main() {
	int M, i;
	scanf("%d", &M);
	for (i = 0; i < M; i++) {
		int n, m, j;
		scanf("%d %d", &n, &m);
		Sequence *sequences = malloc(m * sizeof(Sequence));
		for (j = 0; j < m; j++) {
			sequences[j].pos = j+1;
			sequences[j].seq = malloc((n+1) * sizeof(char));
			scanf("%s", sequences[j].seq);
		}
		qsort(sequences, m, sizeof(Sequence), cmpfunc);
		for (j = 0; j < m; j++)
			printf("%s\n", sequences[j].seq);
		if (i < M - 1)
			printf("\n");
		for (j = 0; j < m; j++)
			free(sequences[j].seq);
		free(sequences);
	}
	return 0;
}

int cmpfunc(const void *a, const void *b) {
	Sequence *s1 = (Sequence *) a;
	Sequence *s2 = (Sequence *) b;
	int swaps1 = getSwaps(s1->seq);
	int swaps2 = getSwaps(s2->seq);
	if (swaps1 - swaps2 != 0)
		return swaps1 - swaps2;
	else
		return s1->pos - s2->pos;
}

int getSwaps(char *sequence) {
	int i, j, ans = 0, n = strlen(sequence);
	char clone[n+1];
	strcpy(clone, sequence);
	for (i = n-1; i >= 0; i--) {
		for (j = 0; j < i; j++) {
			if (clone[j] > clone[j+1]) {
				char tmp = clone[j];
				clone[j] = clone[j+1];
				clone[j+1] = tmp;
				ans += 1;
			}
		}
	}
	return ans;
}