#include <iostream>
using namespace std;
#define MAX 300
#define max(a, b) (((a)>(b))?(a):(b))
#define min(a, b) (((a)<(b))?(a):(b))

int main() {
	iostream::sync_with_stdio(false);
	cin.tie(NULL);

	char c, m[MAX][MAX];
	int tc, n, ans, val[MAX][MAX];
	for (tc = 1; cin >> n && n > 0; tc++) {
		ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= 2*n-i; j++) {
				cin >> m[i][j];
				val[i][j] = 0;
			}
		}

		for (int j = 1; j <= 2*n-1; j += 2) {
			val[1][j] = (m[1][j] != '#');
			ans = max(ans, val[1][j]);
		}

		for (int i = 2; i <= n; i++) {
			for (int j = i; j <= 2*n-i; j += 2) {
				if (m[i][j] == '#') {
					val[i][j] = 0;
				} else if (m[i-1][j] == '#') {
					val[i][j] = 1;
				} else {
					val[i][j] = min(val[i-1][j-1], val[i-1][j+1]) + 1;
				}
				ans = max(ans, val[i][j]);
			}
		}

		for (int i = n-1; i >= 1; i--) {
			for (int j = i+1; j <= 2*n-i-1; j += 2) {
				if (m[i][j] == '#') {
					val[i][j] = 0;
				} else if (m[i+1][j] == '#' || j-1 < i+2 || j+1 > 2*n-i-1) {
					val[i][j] = 1;
				} else {
					val[i][j] = min(val[i+1][j-1], val[i+1][j+1]) + 1;
				}
				ans = max(ans, val[i][j]);
			}
		}

		cout << "Triangle #" << tc << '\n';
		cout << "The largest triangle area is " << ans*ans << ".\n";
		cout << '\n';
	}
}
