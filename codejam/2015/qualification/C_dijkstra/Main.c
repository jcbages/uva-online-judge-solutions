#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 4

const int table[4][4] = {
	{ 1,  2,  3,  4 },
	{ 2, -1,  4, -3 },
	{ 3, -4, -1,  2 },
	{ 4,  3, -2, -1 }
};

char *getAns(char *line, int L, long long subX);
char *getFullLine(char *line, int L, int X);
int findK(char *line, int n);
int toNum(char *l);
char *toSym(int l);
char *divide(char *a, char *b);
char *multiply(char *a, char *b);
char *multiplyAll(char *l, int i, int n);
long long min(long long a, long long b);
int abs(int n);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int L;
		long long X;
		scanf("%d %lld", &L, &X);
		char *line = malloc((L+1) * sizeof(char));
		scanf("%s", line);
		char *ans = getAns(line, L, X);
		printf("Case #%d: %s\n", i+1, ans);
		free(line);
	}
	return 0;
}

char *getAns(char *line, int L, long long subX) {
	int X = (int) min(MAX + (subX % MAX), subX);
	char *test = getFullLine(line, L, X);
	int k = findK(test, L * X), i;
	char *left = malloc(3 * sizeof(char));
	char *right = multiplyAll(test, 1, L * X);
	left[0] = test[0]; left[1] = '\0';
	for (i = 1; i < L * X - 1; i++) {
		if (left[0] == 'i' && right[0] == 'i' && k >= i)
			return "YES";
		strcpy(left, multiply(left, &test[i]));
		strcpy(right, divide(right, &test[i]));
	}
	free(test);
	free(left);
	free(right);
	return "NO";
}

char *getFullLine(char *line, int L, int X) {
	char *test = malloc(L * X * sizeof(char) + 1);
	strcpy(test, line);
	int i;
	for (i = 1; i < X; i++) {
		strcat(test, line);
	}
	return test;
}

int findK(char *line, int n) {
	int ans = n-2;
	char *curr = malloc(3 * sizeof(char));
	strcpy(curr, &line[n-1]);
	for (; ans >= 0; ans--) {
		if (curr[0] == 'k') {
			free(curr);
			return ans+1;
		}
		strcpy(curr, multiply(&line[ans], curr));
	}
	free(curr);
	return ans;
}

int toNum(char *l) {
	if (l[0] == '-') {
		if (l[1] == '1') return -1;
		if (l[1] == 'i') return -2;
		if (l[1] == 'j') return -3;
		if (l[1] == 'k') return -4;
	} else {
		if (l[0] == '1') return  1;
		if (l[0] == 'i') return  2;
		if (l[0] == 'j') return  3;
		if (l[0] == 'k') return  4;
	}
	return 0;
}

char *toSym(int l) {
	if (l == -1) return "-1";
	if (l == -2) return "-i";
	if (l == -3) return "-j";
	if (l == -4) return "-k";
	if (l ==  1) return  "1";
	if (l ==  2) return  "i";
	if (l ==  3) return  "j";
	if (l ==  4) return  "k";
	return "";
}

char *divide(char *a, char *b) {
	if (strcmp(multiply(b,  "1"), a) == 0) return  "1";
	if (strcmp(multiply(b,  "i"), a) == 0) return  "i";
	if (strcmp(multiply(b,  "j"), a) == 0) return  "j";
	if (strcmp(multiply(b,  "k"), a) == 0) return  "k";
	if (strcmp(multiply(b, "-1"), a) == 0) return "-1";
	if (strcmp(multiply(b, "-i"), a) == 0) return "-i";
	if (strcmp(multiply(b, "-j"), a) == 0) return "-j";
	if (strcmp(multiply(b, "-k"), a) == 0) return "-k";
	return "";
}

char *multiply(char *a, char *b) {
	int v1 = toNum(a), v2 = toNum(b);
	int ans = table[abs(v1)-1][abs(v2)-1];
	if (v1 > 0 && v2 > 0) return toSym(ans);
	if (v1 > 0 && v2 < 0) return toSym(-ans);
	if (v1 < 0 && v2 > 0) return toSym(-ans);
	if (v1 < 0 && v2 < 0) return toSym(ans);
	return "";
}

char *multiplyAll(char *l, int i, int n) {
	char *ans = malloc(3 * sizeof(char));
	ans[0] = l[i++]; ans[1] = '\0';
	for (; i < n; i++) {
		strcpy(ans, multiply(ans, &l[i]));
	}
	return ans;
}

long long min(long long a, long long b) {
	return (a < b) ? a : b;
}

int abs(int n) {
	return (n >= 0) ? n : -n;
}
