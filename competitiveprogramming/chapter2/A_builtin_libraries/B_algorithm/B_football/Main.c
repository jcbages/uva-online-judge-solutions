#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

#define TITLE_LIM 101
#define TEAM_LIMIT 31

typedef struct {
	char name[TEAM_LIMIT];
	int points;
	int played;
	int wins;
	int ties;
	int losses;
	int scored;
	int against;
} Team;

void getLine(char *line, char eol);
int indexOf(Team *teams, char *name, int T);
int cmpfunc(const void *a, const void *b);
char *lower(char *line);

int main() {
	int N, i;
	scanf("%d\n", &N);
	for (i = 0; i < N; i++) {
		char title[TITLE_LIM];
		getLine(title, '\n');
		int T, j;
		scanf("%d\n", &T);
		Team *teams = malloc(T * sizeof(Team));
		for (j = 0; j < T; j++) {
			getLine(teams[j].name, '\n');
			teams[j].points = 0;
			teams[j].played = 0;
			teams[j].wins = 0;
			teams[j].ties = 0;
			teams[j].losses = 0;
			teams[j].scored = 0;
			teams[j].against = 0;
		}
		int G;
		scanf("%d\n", &G);
		for (j = 0; j < G; j++) {
			char name1[TEAM_LIMIT];
			getLine(name1, '#');
			int score1, score2;
			scanf("%d@%d#", &score1, &score2);
			char name2[TEAM_LIMIT];
			getLine(name2, '\n');
			
			int i1 = indexOf(teams, name1, T);
			int i2 = indexOf(teams, name2, T);

			if (score1 > score2) {
				teams[i1].points += 3;
				teams[i1].wins   += 1;
				teams[i2].points += 0;
				teams[i2].losses += 1;	
			} else if (score1 == score2) {
				teams[i1].points += 1;
				teams[i1].ties   += 1;
				teams[i2].points += 1;
				teams[i2].ties   += 1;
			} else {
				teams[i1].points += 0;
				teams[i1].losses += 1;
				teams[i2].points += 3;
				teams[i2].wins   += 1;
			}
			teams[i1].played  += 1;
			teams[i1].scored  += score1;
			teams[i1].against += score2;
			teams[i2].played  += 1;
			teams[i2].scored  += score2;
			teams[i2].against += score1;
		}
		qsort(teams, T, sizeof(Team), cmpfunc);
		printf("%s\n", title);
		for (j = 0; j < T; j++) {
			printf(
				"%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n",
				j+1,
				teams[j].name,
				teams[j].points,
				teams[j].played,
				teams[j].wins,
				teams[j].ties,
				teams[j].losses,
				teams[j].scored - teams[j].against,
				teams[j].scored,
				teams[j].against
			);
		}
		if (i < N-1)
			printf("\n");
		free(teams);
	}
	return 0;
}

void getLine(char *line, char eol) {
	int i = 0, c;
	while ((c = getchar()) != eol)
		line[i++] = c;
	line[i] = '\0';
}

int indexOf(Team *teams, char *name, int T) {
	int i;
	for (i = 0; i < T; i++)
		if (strcmp(teams[i].name, name) == 0)
			return i;
	return -1;
}

int cmpfunc(const void *a, const void *b) {
	Team *t1 = (Team *) a, *t2 = (Team *) b;
	if (t1->points - t2->points != 0) {
		return -(t1->points - t2->points);
	} if (t1->wins - t2->wins != 0) {
		return -(t1->wins - t2->wins);
	} if ((t1->scored - t1->against) - (t2->scored - t2->against) != 0) {
		return -((t1->scored - t1->against) - (t2->scored - t2->against));
	} if (t1->scored - t2->scored != 0) {
		return -(t1->scored - t2->scored);
	} if (t1->played - t2->played != 0) {
		return t1->played - t2->played;
	} else {
		char *l1 = lower(t1->name);
		char *l2 = lower(t2->name);
		int ans = strcmp(l1, l2);
		free(l1);
		free(l2);
		return ans;
	}
}

char *lower(char *line) {
	int n = strlen(line), i;
	char *ans = malloc(n);
	for (i = 0; i < n; i++)
		ans[i] = (char) tolower(line[i]);
	return ans;
}