#include <cmath>
#include <iostream>
#include <string>
using namespace std;

long long ans, A, B;
int N, black;
string stones;

int main() {
	// until EOF
	while (cin >> A && cin >> B) {
		// read input and length of input
		cin >> stones;
		N = stones.length();
		// count black stones
		black = 0;
		for (int i = 0; i < N; i++)
			black += (stones[i] == 'B') ? 1 : 0;
		// get minimum cost
		ans = 0;
		for (int i = black - 1, j = black; i >= 0; i--) {
			if (stones[i] == 'W') { // its out of place
				for (; stones[j] != 'B'; j++); // just iterate, there must be a black out there
				stones[i] = 'B'; stones[j] = 'W';
				long long v1 = A, v2 = (long long) (A - B) * (j - i);
				ans += min(v1, v2);
			}
		}
		cout << ans << endl;
	}
}