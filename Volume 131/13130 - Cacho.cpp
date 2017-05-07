#include <iostream>
using namespace std;
#define MAX 5

int main() {
	// Define required vars
	int T, arr[MAX];
	bool valid;

	// Iterate foreach test case
	cin >> T;
	for (int t = 0; t < T; t++) {
		// Read dice values
		for (int i = 0; i < MAX; i++) {
			cin >> arr[i];
		}

		// Check if valid scale
		valid = true;
		for (int i = 1; i < MAX && valid; i++) {
			valid = arr[i] == arr[i-1]+1;
		}

		// Print answer
		cout << (valid ? 'Y' : 'N') << '\n';
	}
}
