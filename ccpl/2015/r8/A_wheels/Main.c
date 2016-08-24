#include <stdio.h>

void getAns(int x[], int y[], int r[], int move[], int n);
int theyTouch(int x[], int y[], int r[], int i, int j);
int getGcd(int a, int b);

int main() {
	int i, j, n, T, x[1000], y[1000], r[1000], move[1000];

	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		/* Init arrays. */
		for (j = 0; j < 1000; j++) {
			x[j] = 0; y[j] = 0; r[j] = 0; move[j] = 0;
		}

		scanf("%d", &n);
		for (j = 0; j < n; j++) {
			int xAux, yAux, rAux;
			scanf("%d %d %d", &xAux, &yAux, &rAux);
			x[j] = xAux; y[j] = yAux; r[j] = rAux;
		}

		/* Initial force. */
		move[0] = 1;

		getAns(x, y, r, move, n);
	}

	return 0;
}

void getAns(int x[], int y[], int r[], int move[], int n) {
	int i, l, stack[n];
	stack[0] = 0; l = 1;
	while (l > 0) {
		int u = stack[l-1]; l--;
		for (i = 0; i < n; i++) {
			if (theyTouch(x, y, r, u, i) == 1 && move[i] == 0) {
				int old = move[i];
				move[i] = -move[u];
				stack[l] = i; l++;
			}
		}
	}

	for (i = 0; i < n; i++) {
		if (move[i] == 0) {
			printf("not moving\n");
		} else {
			int gcd = getGcd(r[0], r[i]);
			if (r[i] == gcd) {
				if (move[i] == 1) {
					printf("%d clockwise\n", r[0] / gcd);
				} else {
					printf("%d counterclockwise\n", r[0] / gcd);
				}
			} else {
				if (move[i] == 1) {
					printf("%d/%d clockwise\n", r[0] / gcd, r[i] / gcd);
				} else {
					printf("%d/%d counterclockwise\n", r[0] / gcd, r[i] / gcd);
				}
			}
		}
	}
}

int theyTouch(int x[], int y[], int r[], int i, int j) {
	int distX = (x[i] - x[j]) * (x[i] - x[j]);
	int distY = (y[i] - y[j]) * (y[i] - y[j]);
	int rSquare = (r[i] + r[j]) * (r[i] + r[j]);
	if (distX + distY == rSquare) {
		return 1;
	} else {
		return 0;
	}
}

int getGcd(int a, int b) {
	if (a == 0) {
		return b;
	} else {
		return getGcd(b % a, a);
	}
}
