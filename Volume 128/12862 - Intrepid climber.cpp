#include <cmath>
#include <iostream>
#include <vector>
using namespace std;

const int MAX = 100005;
const long long INF = 1000000000000000;
int N, F, A, B, C;
bool friends[MAX];
vector<int> graph[MAX], out[MAX], fullcost[MAX], cost[MAX];

bool simplify(int u) {
	bool ans = false;
	for (int v = 0; v < graph[u].size(); v++) {
		bool curr = simplify(graph[u][v]);
		if (curr) {
			out[u].push_back(graph[u][v]);
			cost[u].push_back(fullcost[u][v]);
		}
		ans = ans || curr;
	}
	return ans || friends[u];
}

long long dfs_return(int u) {
	long long ans = 0;
	for (int v = 0; v < out[u].size(); v++) {
		ans += dfs_return(out[u][v]) + cost[u][v];
	}
	return ans;
}

long long dfs_stay(int u) {
	if (out[u].size() == 0) {
		return 0;
	} else if (out[u].size() == 1) {
		return dfs_stay(out[u][0]);
	} else {
		long long ans = INF, total = 0;
		for (int v = 0; v < out[u].size(); v++) {
			long long ret = dfs_return(out[u][v]) + cost[u][v];
			long long stay = dfs_stay(out[u][v]);
			total += ret;
			ans = min(ans, stay - ret);
		}
		return total + ans;
	}
}

int main() {
	// until EOF
	while (cin >> N && cin >> F) {
		// reset data structures
		for (int i = 0; i < N; i++) {
			graph[i].clear();
			out[i].clear();
			fullcost[i].clear();
			cost[i].clear();
			friends[i] = false;
		}
		// read graph
		for (int i = 0; i < N-1; i++) {
			cin >> A; cin >> B; cin >> C;
			graph[A-1].push_back(B-1);
			fullcost[A-1].push_back(C);
		}
		// read friends
		for (int i = 0; i < F; i++) {
			int f; cin >> f;
			friends[f-1] = true;
		}
		// simplify graph so algorithm is simpler
		simplify(0);
		// solve problem and print ans
		cout << dfs_stay(0) << endl;
	}
}