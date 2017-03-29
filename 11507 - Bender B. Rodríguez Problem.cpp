#include <iostream>
using namespace std;

int L;
string curr, ans;

int main() {
	// until L = 0
	while (cin >> L && L > 0) {
		ans = "+x";
		for (int i = 0; i < L-1; i++) {
			cin >> curr;
			if (curr == "No") {
				continue;
			} else if (ans[1] == 'x') {
				if (ans[0] == '-')
					curr[0] = (curr[0] == '+') ? '-' : '+';
				ans = curr;
			} else if (ans[1] == curr[1]) {
				ans = (ans[0] == curr[0]) ? "-x" : "+x";
			}
		}
		cout << ans << endl;
	}
}