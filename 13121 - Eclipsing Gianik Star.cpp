#include <iostream>
#include <cmath>
#include <utility>
using namespace std;

const int m = 360, MAX = 400;
int N, t, p, cnt[m];
pair<int, int> planets[MAX];

int mod(int n) {
	return (n < 0) ? (m - (abs(n) % m)) % m : (n % m);
}

int main() {
	while (cin >> N) {
		// read planets data
		for (int i = 0; i < N; i++) {
			cin >> p;
			cin >> planets[i].first;
			cin >> planets[i].second;
		}
		// get answer
		bool ans = false;
		for (t = 0; t <= 361 && !ans; t++) {
			for (int i = 0; i < m; i++)
				cnt[i] = 0;
			for (int i = 0; i < N; i++)
				cnt[mod(planets[i].first + planets[i].second * t)]++;
			for (int i = 0; i < m; i++)
				ans = ans || (cnt[i] > 1);
		}
		// print ans
		if (ans)
			cout << t-1 << endl;
		else
			cout << "GIANIK IS NEVER ECLIPSED" << endl;
	}
}
