#include <iostream>
using namespace std;
#define max(a, b) (((a) > (b)) ? (a) : (b))
#define MAX 22
#define TIME 1002

// Define required vars
int T, N, M, P, r0, c0, ri, ci, ti, vi;
int G[MAX][MAX][TIME];
int capacity[MAX][MAX][TIME];

void dfs(int r, int c, int t) {
	// Base case 1, already looked
	if (capacity[r][c][t] != -1) {
		return;
	}

	// Base case 2, on time limit
	if (t == 2*P) {
		capacity[r][c][t] = G[r][c][t];
		return;
	}

	// Get best answer
	dfs(r, c, t+1);
	int ans = capacity[r][c][t+1];
	if (r-1 > 0) {
		dfs(r-1, c, t+1);
		ans = max(ans, capacity[r-1][c][t+1]);
	}
	if (r+1 <= N) {
		dfs(r+1, c, t+1);
		ans = max(ans, capacity[r+1][c][t+1]);
	}
	if (c-1 > 0) {
		dfs(r, c-1, t+1);
		ans = max(ans, capacity[r][c-1][t+1]);
	}
	if (c+1 <= M) {
		dfs(r, c+1, t+1);
		ans = max(ans, capacity[r][c+1][t+1]);
	}

	// Assign answer to capacity
	capacity[r][c][t] = ans + G[r][c][t];
}

int main() {
	// Iterate foreach test case
	cin >> T;
	for (int t = 0; t < T; t++) {
		// Read & clean case params
		cin >> N >> M >> P;
		cin >> r0 >> c0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int t = 0; t <= 2*P; t++) {
					G[i][j][t] = 0;
					capacity[i][j][t] = -1;
				}
			}
		}

		for (int i = 0; i < P; i++) {
			cin >> ri >> ci >> ti >> vi;
			G[ri][ci][ti] = vi;
		}

		// Search for the best path & print it
		dfs(r0, c0, 0);
		cout << capacity[r0][c0][0] << '\n';
	}
}
