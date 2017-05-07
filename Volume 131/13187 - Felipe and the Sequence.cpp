#include <iostream>
#include <sstream>
using namespace std;

int main() {
	int T;
	long long S;
	ostringstream s;
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> S;
		s << (S+1)*(S+1)-1 << '\n';
	}
	cout << s.str();
}