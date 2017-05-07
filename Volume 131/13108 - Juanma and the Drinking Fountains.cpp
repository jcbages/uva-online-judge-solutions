#include <iostream>
using namespace std;

int main() {
	// Define required vars
	int T, N;
	long long ans, val;

	// Iterate foreach test case
	cin >> T;
	for (int t = 0; t < T; t++) {
		// Read case params
		cin >> N;

		// Get & print the answer for curr case
		ans = N;
		for (int n = 2; n < N; n++) {
			val = 1;
			for (int i = 0; i < N-n; i++) {
				ans += val;
				val += n-1;
			}
		}
		cout << ans << '\n';
	}
}
