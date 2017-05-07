#include <cmath>
#include <iostream>
using namespace std;

int main() {
	// Read number of test cases
	int T;
	cin >> T;

	// Iterate foreach test case
	for (int t = 0; t < T; t++) {
		// Read n
		long long n;
		cin >> n;
		n--;

		// Base case
		if (n == 0) {
			cout << 0 << ' ' << 0 << '\n';
			continue;
		}

		// Get sqrt of n
		long long root = (long long) sqrt(n);

		// Get x coordinate
		long long x = 0;
		if (root % 2 != 0) {
			x = -root + (n - root*root);
		} else {
			x = root - (n - root*root);
		}

		// Get y coordinate
		long long lim = (root+1)*(root+1) - root*root;
		long long y = -root / 2;
		if (root % 2 != 0) {
			if (n - root*root <= lim/2) {
				y += n - root*root;
			} else {
				y += lim/2;
				y -= n - root*root - lim/2;
			}
		}

		// Print answer
		cout << x << ' ' << y << '\n';
	}
}
