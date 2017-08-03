#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;
#define b 123L
#define MOD 1000000007L
#define MAX 100005
#define mod(a) (((a)%(MOD)+(MOD))%(MOD))

int main() {
	iostream::sync_with_stdio(false); cin.tie(NULL);
	int n, m, ans;
	string t, w;
	vector<int> wc;
	long long h[MAX], r[MAX], sub[MAX], val, curr;
	while (cin >> t >> w) {
		wc.clear();
		n = t.size(), m = w.size(), val = 0;
		for (int i = 1, c; i <= m; i++) {
			if (w[i-1] == '?') wc.push_back(i);
			c = (w[i-1] == '?') ? 0 : w[i-1];
			val = (val*b+c)%MOD;
		}

		h[0] = 0, r[0] = 1;
		for (int i = 1; i <= n; i++) {
			h[i] = (h[i-1]*b+t[i-1])%MOD;
			r[i] = (r[i-1]*b)%MOD;
		}

		for (int i = 0; i <= n-m; i++) {
			sub[i] = 0;
			for (int j = 0; j < wc.size(); j++) {
				sub[i] = (sub[i]+t[wc[j]+i-1]*r[m-wc[j]])%MOD;
			}
		}

		ans = 0;
		for (int i = m; i <= n; i++) {
			curr = mod(h[i]-mod(h[i-m]*r[m]));
			curr = mod(curr-sub[i-m]);
			ans += (curr == val);
		}
		cout << ans << '\n';
	}
}
