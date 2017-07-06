#include <algorithm>
#include <iostream>
using namespace std;
#define MAX 105
#define max(a, b) (((a)>(b))?(a):(b))

int main() {
	long long ans, n, d, r, arr1[MAX], arr2[MAX];
	while (true) {
		cin >> n >> d >> r;
		if (n+d+r == 0) break;

		for (int i = 0; i < n; i++) cin >> arr1[i];
		for (int i = 0; i < n; i++) cin >> arr2[i];
		sort(arr1, arr1+n); sort(arr2, arr2+n);

		ans = 0;
		for (int i = 0; i < n; i++) ans += max(arr1[i]+arr2[n-i-1]-d, 0) * r;
		cout << ans << '\n';
	}
}
