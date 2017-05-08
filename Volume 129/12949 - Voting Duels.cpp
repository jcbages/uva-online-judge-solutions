#include <iostream>
#include <sstream>
using namespace std;
#define LET 30
#define MAX 105

// Define required vars
char candidate;
int n, b, sizes[MAX], votes[LET][LET];
string sequences[MAX];
bool ans, beatable[LET], visited[LET], G[LET][LET];
ostringstream ss;

void dfs(int u) {
	// Mark as visited
	visited[u] = true;

	// Search & recurse on neighbors
	for (int v = 0; v < n; v++) {
		if (G[u][v] && !visited[v]) {
			dfs(v);
		}
	}
}

void build_votes() {
	// Clean votes matrix
	for (int i = 0; i < LET; i++) {
		for (int j = 0; j < LET; j++) {
			votes[i][j] = 0;
		}
	}

	// Fill votes matrix
	for (int i = 0; i < b; i++) {
		for (int j = 0; j < sequences[i].size(); j++) {
			for (int k = j+1; k < sequences[i].size(); k++) {
				votes[sequences[i][j]-'a'][sequences[i][k]-'a'] += sizes[i];
			}
		}
	}
}

int main() {
	// Iterate foreach test case
	while (cin >> n >> b >> candidate) {

		// Read & clear case params
		for (int i = 0; i < b; i++) {
			cin >> sizes[i] >> sequences[i];
		}

		for (int i = 0; i < LET; i++) {
			for (int j = 0; j < LET; j++) {
				G[i][j] = false;
			}
			visited[i] = beatable[i] = false;
		}

		build_votes();

		// Get the list of candidates which I can beat or tie
		for (int i = 0; i < n; i++) {
			bool more_votes = votes[candidate-'a'][i] >= votes[i][candidate-'a'];
			beatable[i] = i != candidate-'a' && more_votes;
		}

		// Build the graph of beatable candidates
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// Invalid cases
				if (i == j || i == candidate-'a' || j == candidate-'a') {
					continue;
				}

				// i can beat j & j is not beatable by candidate
				if (votes[i][j] > votes[j][i] && !beatable[j]) {
					G[i][j] = true;
				}

				// j can beat i & i is not beatable by candidate
				if (votes[j][i] > votes[i][j] && !beatable[i]) {
					G[j][i] = true;
				}
			}
		}

		// Perform DFS to find if its possible to win
		for (int i = 0; i < n; i++) {
			if (beatable[i]) {
				dfs(i);
			}
		}

		// Check if win was feasible & print
		ans = true;
		for (int i = 0; i < n && ans; i++) {
			ans = i == candidate-'a' || visited[i] || beatable[i];
		}
		ss << (ans ? 'Y' : 'N') << '\n';
	}
	cout << ss.str();
}
