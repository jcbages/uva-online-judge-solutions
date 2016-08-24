#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LENGTH 51

void getAns(char *line);
void release(char stack[], int *size, char line);
int isValid(char a, char b);
char *getEq();
int isDigit(char c);
int isOperator(char c);

int main() {
	int N, i, j;
	scanf("%d\n\n", &N);
	for (i = 0; i < N; i++) {
		char *line = getEq();
		getAns(line);
		free(line);
		if (i < N-1) printf("\n");
	}
}

void getAns(char *line) {
	int i, size = 0, n = strlen(line);
	char stack[n];
	for (i = 0; i < n; i++) {
		if (isDigit(line[i])) {
			printf("%c", line[i]);
		} else if (isOperator(line[i])) {
			release(stack, &size, line[i]);
			stack[size++] = line[i];
		} else if (line[i] == '(') {
			stack[size++] = line[i];
		} else /* line[i] == ')' */ {
			release(stack, &size, '+'); /* + because all ops will print */
			size--;
		}
	}
	release(stack, &size, '+'); /* + because all ops will print */
	printf("\n");
}

void release(char stack[], int *size, char c) {
	while (*size > 0 && isValid(stack[*(size)-1], c) == 1) {
		printf("%c", stack[--*(size)]);
	}
}

int isValid(char a, char b) {
	if (a == '*' ||a == '/') {
		return 1;
	} else if (a == '+' || a == '-') {
		return b == '+' || b == '-';
	} else {
		return 0;
	}

}

char *getEq() {
	char *ans = malloc(MAX_LENGTH * sizeof(char));
	int i = 0, c;
	while ((c = getchar()) != '\n' && c != EOF) {
		ans[i++] = c;
		getchar();
	}
	ans[i] = '\0';
	return ans;
}

int isDigit(char c) {
	return c >= '0' && c <= '9';
}

int isOperator(char c) {
	return c == '+' || c == '-' || c == '*' || c == '/';
}