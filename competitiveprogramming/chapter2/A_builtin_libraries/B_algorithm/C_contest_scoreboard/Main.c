#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define T 100
#define P 9
#define PENALTY 20

typedef struct {
	int number;
	int problems[P];
	int solved[P];
	int submissions;
	int points;
	int timePenalty;
} Contestant;

int cmpfunc(const void *a, const void *b);
void getLine(char *line, char eol);

int main() {
	int N, i = 0, j;
	Contestant *contestants = malloc(T * sizeof(Contestant));
	scanf("%d\n\n", &N);
	while (i < N) {
		int m, n;
		for (m = 0; m < T; m++) {
			for (n = 0; n < P; n++)
				contestants[m].problems[n] = contestants[m].solved[n] = 0;
			contestants[m].number = m+1;
			contestants[m].timePenalty = 0;
			contestants[m].submissions = 0;
			contestants[m].points = 0;
		}

		char line[100];
		getLine(line, '\n');
		while (strlen(line) > 1) {
			int contestant, problem, actualTime;
			char status;
			sscanf(line, "%d %d %d %c", &contestant, &problem, &actualTime, &status);
			if (status == 'C' && contestants[contestant-1].solved[problem-1] == 0) {
				contestants[contestant-1].timePenalty +=
						contestants[contestant-1].problems[problem-1] *
						PENALTY +
						actualTime;
				contestants[contestant-1].solved[problem-1] = 1;
				contestants[contestant-1].points += 1;
			} else if (status == 'I') {
				contestants[contestant-1].problems[problem-1] += 1;
			}
			contestants[contestant-1].submissions += 1;
			getLine(line, '\n');
		}
		qsort(contestants, T, sizeof(Contestant), cmpfunc);
		for (j = 0; j < T; j++) {
			if (contestants[j].submissions > 0) {
				printf(
					"%d %d %d\n",
					contestants[j].number,
					contestants[j].points,
					contestants[j].timePenalty
				);
			}
		}
		if (i < N-1)
			printf("\n");
		i++;
	}
	free(contestants);
	return 0;
}

int cmpfunc(const void *a, const void *b) {
	Contestant *c1 = (Contestant *) a, *c2 = (Contestant *) b;
	if (c1->points - c2->points != 0)
		return -(c1->points - c2->points);
	if (c1->timePenalty - c2->timePenalty != 0)
		return c1->timePenalty - c2->timePenalty;
	else
		return c1->number - c2->number;
}

void getLine(char *line, char eol) {
	int c, i = 0;
	while ((c = getchar()) != eol && c != EOF) {
		line[i++] = c;
	}
	line[i] = '\0';
}
