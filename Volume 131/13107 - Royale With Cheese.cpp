#include <iostream>
#include <string>
using namespace std;
#define LET 26
#define MAX 100005

int main() {
	// Define required vars
	string house;
	int cnt, j;
	int letters[LET];
	char ans[MAX];

	// Iterate foreach test case
	while (cin >> house) {
		// Clean vars
		cnt = 0;
		for (int i = 0; i < LET; i++) {
			letters[i] = 0;
		}

		// Build initial police id
		j = 0;
		for (int i = 0; i < house.size(); i++) {
			// Check if letter was seen before
			if (!letters[house[i]-'a']) {
				letters[house[i]-'a'] = ++cnt;
			}

			// Add number to the ans array
			if (letters[house[i]-'a'] < 10) {
				ans[j++] = letters[house[i]-'a'] + '0';
			} else {
				ans[j++] = letters[house[i]-'a']/10 + '0';
				ans[j++] = letters[house[i]-'a']%10 + '0';
			}
		}
		ans[j] = '\0';

		// Replace required chars
		for (int i = 0; i < j; i++) {
			if (ans[i] == '5') {
				ans[i] = '2';
			} else if (ans[i] == '2') {
				ans[i] = '5';
			} else if (ans[i] == '6') {
				ans[i] = '9';
			} else if (ans[i] == '9') {
				ans[i] = '6';
			}
		}

		// Print final array
		cout << ans << '\n';
	}
}
