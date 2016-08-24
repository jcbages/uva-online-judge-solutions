#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
	char name[100];
	int A, B, min;
} Agency;

void getAns(int N, int M, int L, int T, Agency* agencies);
int getMinCost(int N, int M, Agency agency);
int cmpAgencies(const void * a, const void * b);
int min(int a, int b);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int N, M, L;
		scanf("%d %d %d", &N, &M, &L);

		int j;
		Agency* agencies = malloc(N * sizeof(Agency));
		for (j = 0; j < L; j++) {
			agencies[j].min = -1;
			scanf("\n%[^:]:%d,%d",
				agencies[j].name,
				&agencies[j].A,
				&agencies[j].B
			);
		}
		getAns(N, M, L, i+1, agencies);
		free(agencies);
	}

	return 0;
}

void getAns(int N, int M, int L, int T, Agency* agencies) {
	int i;
	for (i = 0; i < L; i++) {
		agencies[i].min = getMinCost(N, M, agencies[i]);
	}
	
	qsort(agencies, L, sizeof(Agency), cmpAgencies);

	printf("Case %d\n", T);
	for (i = 0; i < L; i++) {
		printf("%s %d\n", agencies[i].name, agencies[i].min);
	}
}

int getMinCost(int N, int M, Agency agency) {
	if (N == M) return 0;
	int half = N / 2;
	int ans = 0;
	if (half >= M) {
		ans = min(agency.A * (N - half), agency.B) + getMinCost(half, M, agency);
	} else {
		ans = agency.A + getMinCost(N-1, M, agency);
	}
	return ans;
}

int cmpAgencies(const void * a, const void * b) {
	Agency a1 = * (Agency *) a;
	Agency a2 = * (Agency *) b;
	int diffMin  = a1.min  - a2.min;
	int diffName = strcmp(a1.name, a2.name);
	return (diffMin == 0) ? diffName : diffMin;
}

int min(int a, int b) {
	return (a < b) ? a : b;
}