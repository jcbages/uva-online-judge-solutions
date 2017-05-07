#include <cmath>
#include <iostream>
#include <sstream>
#include <map>
#include <vector>
using namespace std;
#define LIM 1000000

int main() {
	bool numbers[LIM+1];
	for (int i = 0; i < LIM+1; i++) {
		numbers[i] = false;
	}

	vector<int> primes;
	for (int i = 2; i < LIM+1; i++) {
		if (!numbers[i]) {
			for (int j = i; j < LIM+1; j += i) {
				numbers[j] = true;
			}
			primes.push_back(i);
		}
	}

	long long T, N, M, total, ans, sum, val;
	map<int, int> factors;
	map<int, int>::iterator it;
	ostringstream s;

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N;
		M = N;
		factors.clear();
		for (int i = 0; i < primes.size() && N > 1;) {
			if (N % primes[i] == 0) {
				if (factors.find(primes[i]) == factors.end()) {
					factors.insert(make_pair(primes[i], 0));
				}
				factors[primes[i]]++;
				N /= primes[i];
			} else {
				i++;
			}
		}
		if (N > 1) {
			factors.insert(make_pair(N, 1));
		}

		ans = 1;
		for (it = factors.begin(); it != factors.end(); it++) {
			sum = 0;
			val = 1;
			for (int i = 0; i < it->second + 1; i++) {
				sum += val;
				val *= it->first;
			}
			ans *= sum;
		}

		ans -= M;
		if (ans < M) {
			s << "deficient\n";
		} else if (ans > M) {
			s << "abundant\n";
		} else {
			s << "perfect\n";
		}
	}
	cout << s.str();
}