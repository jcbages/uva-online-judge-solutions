#include <algorithm>
#include <iostream>
using namespace std;
#define MAX 100005

int main() {
	// Define requried variables
	int T, M, W, ans, tot, weights[MAX];

	// Iterate foreach test case
	cin >> T;
	for (int t = 0; t < T; t++) {
		// Read params for curr case
		cin >> M >> W;

		// Read elephants weights
		for (int i = 0; i < M; i++) {
			cin >> weights[i];
		}

		// Sort elephants weigths
		sort(weights, weights+M);

		// Get & print the max number of elephants
		ans = tot = 0;
		for (int i = 0; i < M; i++) {
			if (tot + weights[i] > W) {
				break;
			}
			tot += weights[i];
			ans += 1;
		}
		cout << ans << '\n';
	}
}
