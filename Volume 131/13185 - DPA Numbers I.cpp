#include <iostream>
#include <sstream>
using namespace std;

int main() {
	int T, N;
	ostringstream s;
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N;
		int sum = 0;
		for (int i = 1; i < N; i++) {
			if (N % i == 0) {
				sum += i;
			}
		}

		if (sum > N) {
			s << "abundant\n";
		} else if (sum < N) {
			s << "deficient\n";
		} else {
			s << "perfect\n";
		}
	}
	cout << s.str();
}