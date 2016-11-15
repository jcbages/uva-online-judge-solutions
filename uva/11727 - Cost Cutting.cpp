#include <algorithm>
#include <iostream>
using namespace std;

int T, salaries[3];

int main() {
	cin >> T;
	for (int i = 0; i < T; i++) {
		for (int j = 0; j < 3; j++)
			cin >> salaries[j];
		sort(salaries, salaries + 3);
		cout << "Case " << i+1 << ": " << salaries[1] << endl;
	}
}