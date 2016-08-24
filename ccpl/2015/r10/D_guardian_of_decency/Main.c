#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define STR 101

typedef struct {
	int height;
	char sex;
	char music[STR];
	char sport[STR];
} Person;

int canGo(Person *people, int i, int j);
int **getMatrix(Person *people, int N);
int **getWeights(int **matrix, int N);
int setWeight(int **matrix, int **weights, int i, int j, int N);
int getAns(Person *people, int N);
int abs(int n);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int N, j;
		scanf("%d", &N);
		Person *people = malloc(N * sizeof(Person));
		for (j = 0; j < N; j++) {
			scanf(
				"%d %c %s %s",
				&people[j].height,
				&people[j].sex,
				people[j].music,
				people[j].sport
			);
		}
		int ans = getAns(people, N);
		printf("%d\n", ans);
		free(people);
	}
	return 0;
}

int canGo(Person *people, int i, int j) {
	if (abs(people[i].height - people[j].height) > 40)
		return 1;
	if (people[i].sex == people[j].sex)
		return 1;
	if (strcmp(people[i].music, people[j].music) != 0)
		return 1;
	if (strcmp(people[i].sport, people[j].sport) == 0)
		return 1;
	return 0;
}

int **getMatrix(Person *people, int N) {
	int **matrix = malloc(N * sizeof(int *));
	int i;
	for (i = 0; i < N; i++) {
		int j;
		matrix[i] = malloc(N * sizeof(int));
		for (j = 0; j < N; j++) {
			if (i == j || !canGo(people, i, j)) {
				matrix[i][j] = 0;
			} else {
				matrix[i][j] = 1;
			}
		}
	}
	return matrix;
}

int **getWeights(int **matrix, int N) {
	int i, j;
	int **weights = malloc(N * sizeof(int *));
	for (i = 0; i < N; i++) {
		weights[i] = malloc(N * sizeof(int));
		for (j = 0; j < N; j++) {
			weights[i][j] = -1;
		}
	}
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			if (j <= i || matrix[i][j] == 0) {
				weights[i][j] = 0;
			} else if (weights[i][j] == -1) {
				weights[i][j] = 1 + setWeight(matrix, weights, i, j, N);
			}
		}
	}
	return weights;
}

int setWeight(int **matrix, int **weights, int i, int j, int N) {
	if (j+1 >= N) return 0;
	int k, ans = 0;
	for (k = j+1; k < N; k++) {
		if (matrix[i][k] == 1 && matrix[j][k] == 1 && weights[j][k] == -1) {
			weights[j][k] = 1 + setWeight(matrix, weights, j, k, N);
		}
	}
	for (k = j+1; k < N; k++) {
		if (matrix[i][k] == 1 && matrix[j][k] == 1 && weights[j][k] > ans)
		ans = weights[j][k];
	}
	return ans;
}

int getAns(Person *people, int N) {
	if (N == 0)	return 0;
	if (N == 1) return 1;
	int **matrix = getMatrix(people, N);
	int **weights = getWeights(matrix, N);
	int i, j, ans = 0;
	for (i = 0; i < N; i++) {
		int numRow = 0;
		int currMax = 0;
		for (j = 0; j < N; j++) {
			if (weights[i][j] > currMax)
				currMax = weights[i][j];
			numRow += matrix[i][j];
		}
		int cand = (numRow < currMax) ? numRow : currMax;
		ans = (cand > ans) ? cand : ans;
	}
	/*
	printf("MATRIX...\n");
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			printf("%d ", matrix[i][j]);
		}
		printf("\n");
	}
	printf("WEIGHTS...\n");
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			printf("%d ", weights[i][j]);
		}
		printf("\n");
	}
	*/
	for (i = 0; i < N; i++) {
		free(matrix[i]);
		free(weights[i]);
	}
	free(matrix);
	free(weights);
	return ans + 1;
}

int abs(int n) {
	return (n >= 0) ? n : -n;
}