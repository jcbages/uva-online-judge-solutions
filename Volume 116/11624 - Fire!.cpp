#include <iostream>
#include <queue>
using namespace std;
#define MAX 1005
#define INF 10000000
typedef pair<int, int> pii;

int main() {
	iostream::sync_with_stdio(false);
	cin.tie(NULL);

	pii p;
	string m[MAX];
	queue<pii> qf, qj;
	int lf[MAX][MAX], lj[MAX][MAX];

	int T, R, C, ans; cin >> T;
	while (T-- > 0) {
		cin >> R >> C;
		qf = queue<pii>(); qj = queue<pii>();
		for (int i = 0; i < R; i++) {
			cin >> m[i];
			for (int j = 0; j < C; j++) {
				lf[i][j] = lj[i][j] = INF;
				if (m[i][j] == 'F') {
					qf.push(make_pair(i, j));
					lf[i][j] = 1;
					m[i][j] = '.';
				} else if (m[i][j] == 'J') {
					qj.push(make_pair(i, j));
					lj[i][j] = 1;
					m[i][j] = '.';
				}
			}
		}

		while (qf.size() > 0) {
			p = qf.front(); qf.pop();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 && j != 0) continue;
					if (p.first+i < 0 || p.first+i >= R) continue;
					if (p.second+j < 0 || p.second+j >= C) continue;
					if (m[p.first+i][p.second+j] != '.') continue;

					if (lf[p.first+i][p.second+j] == INF) {
						qf.push(make_pair(p.first+i, p.second+j));
						lf[p.first+i][p.second+j] = lf[p.first][p.second]+1;
					}
				}
			}
		}

		ans = -1;
		while (qj.size() > 0) {
			p = qj.front(); qj.pop();
			if (p.first == 0 || p.first == R-1 || p.second == 0 || p.second == C-1) {
				ans = lj[p.first][p.second];
				break;
			}

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 && j != 0) continue;
					if (p.first+i < 0 || p.first+i >= R) continue;
					if (p.second+j < 0 || p.second+j >= C) continue;
					if (m[p.first+i][p.second+j] != '.') continue;

					if (lj[p.first+i][p.second+j] == INF && lf[p.first+i][p.second+j] > lj[p.first][p.second]+1) {
						qj.push(make_pair(p.first+i, p.second+j));
						lj[p.first+i][p.second+j] = lj[p.first][p.second]+1;
					}
				}
			}
		}

		if (ans == -1) {
			cout << "IMPOSSIBLE" << '\n';
		} else {
			cout << ans << '\n';
		}
	}
}
