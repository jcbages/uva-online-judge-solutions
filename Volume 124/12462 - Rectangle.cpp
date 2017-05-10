#include <algorithm>
#include <iostream>
using namespace std;
#define MAX 100005
#define max(a, b) (((a)>(b))?(a):(b))

// Define required variables
long long ans;
int N, C, root, *h, *c, *idx, *uf;

// Get the root of given i
int find(int i) {
	return (uf[i] < 0) ? i : find(uf[i]);
}

// Join the two given i & j sets
void join(int i, int j) {
	int r1 = find(i), r2 = find(j);
	if (r1 == r2) return;
	if (-uf[r1] > -uf[r2]) {
		uf[r1] += uf[r2];
		uf[r2] = r1;
	} else {
		uf[r2] += uf[r1];
		uf[r1] = r2;
	}
	c[r1] = c[r2] = c[r1] | c[r2];
}

// Check if the bar at i is taller than the bar at j
bool is_taller(int i, int j) { return h[i] > h[j]; }

int main() {
	// Fast IO
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	// Init pointers
	h = (int*) malloc(sizeof(int)*MAX);
	c = (int*) malloc(sizeof(int)*MAX);
	idx = (int*) malloc(sizeof(int)*MAX);
	uf = (int*) malloc(sizeof(int)*MAX);

	// Iterate foreach test case
	while (cin >> N >> C) {
		// Check EOF
		if (N == 0 && C == 0) break;

		// Read & clean vars
		for (int i = 0; i < N; i++) {
			cin >> h[i];
			idx[i] = i;
			uf[i] = -1;
		}
		for (int i = 0; i < N; i++) {
			cin >> c[i];
			c[i] = (1<<c[i]);
		}

		// Sort the indices by height
		sort(idx, idx+N, is_taller);

		// Get & print the answer
		ans = 0;
		for (int i = 0; i < N; i++) {
			// Join with left if necessary
			if (idx[i]-1 >= 0 && h[idx[i]-1] >= h[idx[i]]) {
				join(idx[i]-1, idx[i]);
			}

			// Join with right if necessary
			if (idx[i]+1 < N && h[idx[i]+1] >= h[idx[i]]) {
				join(idx[i]+1, idx[i]);
			}

			// Update answer if all colors were used
			root = find(idx[i]);
			if (c[root] == ((1<<C)-1)) {
				ans = max(ans, (long long)(-uf[root])*(long long)(h[idx[i]]));
			}
		}
		cout << ans << '\n';
	}

	// Clean pointers
	free(h);
	free(c);
	free(idx);
	free(uf);
}
