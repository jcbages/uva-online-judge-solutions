#include <iostream>
using namespace std;

const int INF = 10000000;
int N, B, H, W, cost, beds, ans;

int main() {
	// until EOF
	while (cin >> N) {
		// read budget, hotels and weeks
		cin >> B; cin >> H; cin >> W;
		// min answer is very big
		ans = INF;
		// read each hotel
		for (int i = 0; i < H; i++) {
			cin >> cost;
			// read each week
			for (int j = 0; j < W; j++) {
				cin >> beds;
				// if valid # of beds and cost, get min
				if (beds >= N && cost * N <= B) {
					ans = min(ans, cost * N);
				}
			}
		}
		// print answer
		if (ans == INF)
			cout << "stay home" << endl;
		else
			cout << ans << endl;
	}
}