#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LENGTH 130

char *getLine();
void getAns(char *line);
int noMatch(char a, char b);

int main() {
	int N, i;
	scanf("%d", &N); getchar();
	for (i = 0; i < N; i++) {
		char *line = getLine();
		getAns(line);
		free(line);
	}
	return 0;
}

char *getLine() {
	char *line = malloc(MAX_LENGTH * sizeof(char));
	int size = 0, c;
	while ((c = getchar()) != '\n' && c != EOF) {
		line[size++] = c;
	}
	line[size] = '\0';
	return line;
}

void getAns(char *line) {
	int i, size = 0, n = strlen(line);
	char stack[n];
	for (i = 0; i < n; i++) {
		if (line[i] == '(' || line[i] == '[') {
			stack[size++] = line[i];
		} else {
			if (size == 0 || noMatch(stack[size-1], line[i]) == 1) {
				printf("No\n");
				return;
			} else {
				size--;
			}
		}
	}
	if (size == 0) printf("Yes\n");
	else printf("No\n");
}

int noMatch(char a, char b) {
	return (a == '(' && b == ']') || (a == '[' && b == ')');
}