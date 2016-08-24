#include <stdio.h>
#include <stdlib.h>

void getAns(char **field, int n, int m);
int getNum(char **field, int n, int m, int i, int j);

int main() {
	int n, m, j, i = 1;
	char **field;
	scanf("%d %d", &n, &m);
	while (n != 0 && m != 0) {
		if (i != 1) {
			printf("\n");
		}

		field = malloc(n * sizeof(char *));
		for (j = 0; j < n; j++) {
			field[j] = malloc(m * sizeof(char));
			scanf("%s", field[j]);
		}

		printf("Field #%d:\n", i);
		getAns(field, n, m);

		free(field);
		scanf("%d %d", &n, &m);
		i++;
	}
	return 0;
}

void getAns(char **field, int n, int m) {
	int i, j, num;
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (field[i][j] == '*') {
				printf("*");
			} else {
				num = getNum(field, n, m, i, j);
				printf("%d", num);
			}
		}
		printf("\n");
	}
}

int getNum(char **field, int n, int m, int i, int j) {
	int ans = 0, a, b;
	for (a = i-1; a <= i+1; a++) {
		for (b = j-1; b <= j+1; b++) {
			if (a >= 0 && a < n && b >= 0 && b < m) {
				ans = (field[a][b] == '*') ? ans+1 : ans;
			}
		}
	}
	return ans;
}