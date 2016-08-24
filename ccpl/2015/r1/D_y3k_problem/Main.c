#include <stdio.h>

#define leap  366
#define nleap 365
#define months 12

const int dmonths[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

void getAns(int n, int d, int m, int y);
int isLeap(int y);
int daysMonth(int m, int y);

int main() {
	int n, d, m, y;
	while (scanf("%d %d %d %d", &n, &d, &m, &y) != EOF) {
		if (n + d + m + y == 0) break;
		getAns(n, d, m, y);
	}
	return 0;
}

void getAns(int n, int d, int m, int y) {
	int dd = d, mm = m, yy = y;
	while (n > 0) {
		if (isLeap(yy) == 1 && n >= leap) {
			n -= leap;
			yy++;
		} else if (isLeap(yy) == 0 && n >= nleap) {
			n -= nleap;
			yy++;
		} else if (n >= daysMonth(mm, yy)) {
			n -= daysMonth(mm, yy);
			mm++;
		} else {
			n--;
			dd++;
		}

		if (dd > daysMonth(mm, yy)) {
			dd = 1;
			mm++;
		}
		if (mm > months) {
			mm = 1;
			yy++;
		}
	}
	printf("%d %d %d\n", dd, mm, yy);
}

int isLeap(int y) {
	if (y % 4 == 0 && y % 400 == 0)
		return 1;
	else if (y % 4 == 0 && y % 100 != 0)
		return 1;
	else
		return 0;
}

int daysMonth(int m, int y) {
	if (isLeap(y))
		return (m-1 == 1) ? 29 : dmonths[m-1];
	else
		return dmonths[m-1];
}