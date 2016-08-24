#include <stdio.h>
#include <string.h>

#define LET 26
#define LIM 50
#define BASE 97

void nextPerm(char lets[], char line[], int n);

int main() {
	char lets[LET];
	char line[LIM];
	while (1) {
		scanf("%s", line);
		if (line[0] == '#')
			break;
		int i, n = strlen(line);
		for (i = 0; i < LET; i++)
			lets[i] = 0;
		for (i = 0; i < n; i++)
			lets[line[i]-BASE] += 1;
		nextPerm(lets, line, n);
	}
}

void nextPerm(char lets[], char line[], int n) {
	char myLets[LET];
	int i;
	for (i = 0; i < LET; i++)
		myLets[i] = lets[i];

	
	line[n-1] = nextLet(lets, myLets, n)
	while 
}