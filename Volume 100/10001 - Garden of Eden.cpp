#include <cstring>
#include <iostream>
using namespace std;
#define MAX 35
#define STATES 8

bool found, visited[MAX][STATES];
int id, N, first;
string state;

bool dfs(int n, int curr) {
	if (n == N-1) {
		return ((curr>>1)&1) == ((first>>2)&1) && (curr&1) == ((first>>1)&1);
	}

	visited[n][curr] = true;
	for (int i = 0; i < STATES; i++) {
		if (((id>>i)&1) == state[n+1]-'0' && ((curr>>1)&1) == ((i>>2)&1) && (curr&1) == ((i>>1)&1)) {
			if (n < N-1 && !visited[n+1][i] && dfs(n+1, i)) {
				return true;
			}
		}
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	while (cin >> id >> N >> state) {
		found = false;
		for (int i = 0; i < STATES && !found; i++) {
			if (((id>>i)&1) == state[0]-'0') {
				for (int i = 0; i < N; i++) memset(visited[i], false, STATES);
				first = i, found = dfs(0, i);
			}
		}
		cout << (!found ? "GARDEN OF EDEN" : "REACHABLE") << '\n';
	}
}
