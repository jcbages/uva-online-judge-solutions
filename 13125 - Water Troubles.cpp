#include <cmath>
#include <iostream>
using namespace std;

long long T, ans;
int arr[9], N, used;

bool final_ans(long long curr) {
	// best case, we arrived
	if (curr == T)
		ans = curr;
	// mid case, we can approximate
	if (curr > T)
		ans = (ans < T) ? curr : min(ans, curr);
	return (ans == T);
}

long long backtrack(long long acc) {
	// base case 1, acc == T
	if (final_ans(acc))
		return acc;
	// recursive step, for acc <> T
	for (int i = 0; i < N; i++) {
		// not used yet
		if (!(used & (1 << i))) {
			// start using it
			used |= (1 << i);
			// option 1 - try subtract
			if (acc - arr[i] > 0 && final_ans(backtrack(acc - arr[i])))
				return T;
			// option 2 - try divide
			if (acc % arr[i] == 0 && final_ans(backtrack(acc / arr[i])))
				return T;
			// option 3 - try add
			if (final_ans(backtrack(acc + arr[i])))
				return T;
			// option 4 - try multiply
			if (final_ans(backtrack(acc * arr[i])))
				return T;
			// stop using it
			used &= ~(1 << i);
		}
	}
	return acc;
}

int main() {
	// until T = 0
	while (cin >> T && T > 0) {
		// read data
		ans = N = used = 0;
		while (1) {
			cin >> arr[N++];
			if (getchar() == '\n')
				break;
		}
		// solve answer recursively
		for (int i = 0; i < N; i++) {
			// start using it
			used |= (1 << i);
			// try to get the answer
			if (backtrack(arr[i]) == T)
				break;
			// stop using it
			used &= ~(1 << i);
		}
		// print best answer
		cout << ans << endl;
	}
	return 0;
}
