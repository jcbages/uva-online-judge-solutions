#include <stdio.h>

char *getAns(int X, int R, int C);
int dims(int R, int C, int x, int y);
int fit(int R, int C, int x, int y);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int X, R, C;
		scanf("%d %d %d", &X, &R, &C);
		char *ans = getAns(X, R, C);
		printf("Case #%d: %s\n", i+1, ans);
	}
}

char *getAns(int X, int R, int C) {
	if (R < X && C < X) {
		return "RICHARD";
	} else if (X == 1) {
		return "GABRIEL";
	} else if (X <= 4) {
		if (fit(R, C, X, X-1)) {
			return "GABRIEL";
		} else {
			return "RICHARD";
		}
	} else if (X == 5) {
		if (fit(R, C, X, X-2)) {
			return "GABRIEL";
		} else {
			return "RICHARD";
		}
	} else if (X == 6) {
		return "RICHARD";
	} else {
		return "RICHARD";
	}
}

int dims(int R, int C, int x, int y) {
	return (R == x && C == y) || (R == y && C == x);
}

int fit(int R, int C, int x, int y) {
	return (R % x == 0 && C >= y) || (R >= y && C % x == 0);
}