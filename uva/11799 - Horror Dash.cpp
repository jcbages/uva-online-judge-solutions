#include <cmath>
#include <iostream>
using namespace std;

int T, N, actual, ans;

int main() {
	// # of test cases
	cin >> T;
	for (int i = 0; i < T; i++) {
		// # of runners
		cin >> N;
		// get and print answer
		ans = 0;
		for (int j = 0; j < N; j++) {
			cin >> actual;
			ans = max(ans, actual);
		}
		cout << "Case " << i+1 << ": " << ans << endl;
	}
}