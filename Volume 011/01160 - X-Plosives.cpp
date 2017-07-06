#include <iostream>
#include <sstream>
using namespace std;
#define MAX 100005

int u, v, ans, sets[MAX];

int find(int u) { return (sets[u] < 0) ? u : find(sets[u]); }
void join(int u, int v) {
	int fu = find(u), fv = find(v);
	if (-sets[fu] > -sets[fv]) {
		sets[fv] = fu;
		sets[fu]--;
	} else {
		sets[fu] = fv;
		sets[fv]--;
	}
} 

int main() {
	iostream::sync_with_stdio(false);
	cin.tie(NULL);

	ans = 0;
	for (int i = 0; i < MAX; i++) sets[i] = -1;
	while (cin >> u) {
		if (u == -1) {
			cout << ans << '\n';
			ans = 0;
			for (int i = 0; i < MAX; i++) sets[i] = -1;
		} else {
			cin >> v;
			if (find(u) == find(v)) {
				ans++;
			} else {
				join(u, v);
			}
		}
	}
}
