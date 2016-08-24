#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LIM 101
#define MAX 201
#define ALP 26

void getText(char **dict, char **text, int s1, int s2);
int isMatch(char *a, char *b);
int load(char **list);
void assign(char **list, char *aux, int n, int m);

int main() {
	int N, i, j;
	scanf("%d\n", &N);
	for (i = 0; i < N; i++) {
		/* Load the dictionary */
		char **dict = malloc(LIM * sizeof(char *));
		int s1 = load(dict);
		/* Load the test */
		char **text = malloc(LIM * sizeof(char *));
		int s2 = load(text);
		/* Get and print the clear text */
		getText(dict, text, s1, s2);
		for (j = 0; j < s2; j++) {
			printf("%s", text[j]);
			if (j < s2 - 1) printf(" ");
		}
		printf("\n");
	}
	return 0;
}

void getText(char **dict, char **text, int s1, int s2) {
	int i, j;
	for (i = 0; i < s2; i++) {
		int replaced = 0;
		for (j = 0; j < s1; j++) {
			if (isMatch(text[i], dict[j])) {
				if (!replaced || strcmp(text[i], dict[j]) > 0) {
					strcpy(text[i], dict[j]);
					replaced = 1;
				}
			}
		}
	}
}

int isMatch(char *a, char *b) {
	int count[ALP], i;
	int n = strlen(a), m = strlen(b);
	if (n != m)
		return 0;
	for (i = 0; i < ALP; i++)
		count[i] = 0;
	for (i = 0; i < n; i++)
		count[a[i]-'a'] += 1;
	for (i = 0; i < m; i++)
		count[b[i]-'a'] -= 1;
	for (i = 0; i < ALP; i++)
		if (count[i] != 0)
			return 0;
	return a[0] == b[0] && a[n-1] == b[m-1];
}

int load(char **list) {
	int n = 0, m = 0;
	char c, aux[MAX];
	while ((c = getchar()) != '\n') {
		if (c != ' ') {
			aux[n++] = c;
		} else {
			assign(list, aux, n, m);
			n = 0;
			m += 1;
		}
	}
	assign(list, aux, n, m);
	return m + 1;
}

void assign(char **list, char *aux, int n, int m) {
	aux[n++] = '\0';
	list[m] = malloc(n * sizeof(char));
	strcpy(list[m], aux);
	n = 0;
	m += 1;
}