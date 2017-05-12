#include <iostream>
#include <vector>
using namespace std;
#define MAX 100005
#define INF 10000000
#define ULTRA 150000
#define min(a, b) (((a)<(b))?(a):(b))
#define max(a, b) (((a)>(b))?(a):(b))

// Define required vars
int N, M, h[MAX], d[MAX], l[MAX], r[MAX], rmq[3*MAX];
vector<int> peaks;

void build(int i, int l, int r) {
	if (l == r) {
		rmq[i] = h[l];
	} else {
		build(2*i, l, (l+r)/2);
		build(2*i+1, (l+r)/2+1, r);
		rmq[i] = min(rmq[2*i], rmq[2*i+1]);
	}
}

int query(int i, int l, int r, int lo, int hi) {
	if (r < lo || l > hi) {
		return INF;
	} else if (l <= lo && hi <= r) {
		return rmq[i];
	} else {
		int a = query(2*i, l, r, lo, (lo+hi)/2);
		int b = query(2*i+1, l, r, (lo+hi)/2+1, hi);
		return min(a, b);
	}
}

int query(int l, int r) { return query(1, l, r, 0, N-1); }

int get_n(int i, int j, int *arr) {
	if (j == -1) {
		return -1;
	} else if (h[j] > h[i]) {
		return j;
	} else {
		return get_n(i, arr[j], arr);
	}
}

int main() {
	// Fast IO
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	// Iterate foreach test case
	while (cin >> N) {
		// Read & clean case params
		for (int i = 0; i < N; i++) {
			cin >> h[i];
			d[i] = 0;
			l[i] = r[i] = -1;
		}

		for (int i = 0; i < 3*N; i++) {
			rmq[i] = 0;
		}

		// Build an RMQ of points
		build(1, 0, N-1);

		// Build the peaks vector
		peaks.clear();
		for (int i = 1; i < N-1; i++) {
			if (h[i-1] < h[i] && h[i] > h[i+1]) {
				peaks.push_back(i);
			}
		}

		// Get left & right foreach peak
		l[peaks[0]] = -1;
		for (int i = 1; i < peaks.size(); i++) {
			l[peaks[i]] = get_n(peaks[i], peaks[i-1], l);
		}

		M = peaks.size();
		r[peaks[M-1]] = -1;
		for (int i = M-2; i >= 0; i--) {
			r[peaks[i]] = get_n(peaks[i], peaks[i+1], r);
		}

		// Get prominence foreach peak
		for (int i = 0, j, a, b; i < M; i++) {
			j = peaks[i];
			if (l[j] == -1 && r[j] == -1) {
				d[j] = h[j];
			} else if (r[j] == -1) {
				d[j] = h[j] - query(l[j], j);
			} else if (l[j] == -1) {
				d[j] = h[j] - query(j, r[j]);
			} else {
				a = query(l[j], j), b = query(j, r[j]);
				d[j] = h[j] - max(a, b);
			}
		}

		// Print the ultras
		for (int i = 0, j = 0; i < M; i++) {
			if (d[peaks[i]] >= ULTRA) {
				if (j++ > 0) cout << ' ';
				cout << peaks[i]+1;
			}
		}
		cout << '\n';
	}
}
