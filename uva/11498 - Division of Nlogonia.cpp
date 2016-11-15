#include <iostream>
using namespace std;

int K, N, M, X, Y;

int main() {
	while (cin >> K && K > 0) {
		cin >> N; cin >> M;
		for (int i = 0; i < K; i++) {
			cin >> X; cin >> Y;
			if (X < N && Y < M)
				cout << "SO" << endl;
			else if (X < N && Y > M)
				cout << "NO" << endl;
			else if (X > N && Y < M)
				cout << "SE" << endl;
			else if (X > N && Y > M)
				cout << "NE" << endl;
			else
				cout << "divisa" << endl;
		}
	}
}