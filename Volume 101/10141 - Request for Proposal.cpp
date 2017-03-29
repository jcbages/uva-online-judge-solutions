#include <iostream>
using namespace std;

const double INF = 10e15;
int n, p, maxreqs, reqs, casenum;
double mincost, cost;
string aux, ans;

int main() {
	// until n = p = 0
	casenum = 1;
	while (cin >> n && cin >> p && n + p > 0) {
		// read reqs
		getchar(); // \n of prev line
		for (int i = 0; i < n; i++)
			getline(cin, aux);
		// read proposals
		mincost = INF; maxreqs = 0; ans = "";
		for (int i = 0; i < p; i++) {
			getline(cin, aux);
			cin >> cost; cin >> reqs;
			getchar(); // \n of prev line
			// get best answer
			if (reqs > maxreqs || (reqs == maxreqs && cost < mincost)) {
				ans = aux;
				maxreqs = reqs;
				mincost = cost;
			}
			// read reqs
			for (int j = 0; j < reqs; j++)
				getline(cin, aux);
		}
		// print answer
		if (casenum > 1)
			cout << endl;
		cout << "RFP #" << casenum++ << endl;
		cout << ans << endl;
	}
}