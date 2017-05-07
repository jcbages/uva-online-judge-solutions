#include <iostream>
#include <sstream>
using namespace std;
#define BITS 31

int main() {
	ostringstream s;
	unsigned int T, N, mask, index, bit;
	cin >> T;
	for (unsigned int t = 0; t < T; t++) {
		cin >> N;
		mask = 0;
		for (int i = 0; i < BITS+1; i++) {
			mask |= (1 << (N & BITS));
			bit = N & 1;
			N >>= 1;
			N |= (bit << BITS);
		}
		s << (mask == 4294967295 ? "yes" : "no") << '\n';
	}
	cout << s.str();
}
