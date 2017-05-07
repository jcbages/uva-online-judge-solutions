#include <iostream>
#include <queue>
#include <sstream>
#include <string>
using namespace std;

class node {
public:
	string name;
	int priority, freq, time;
	node(int pr, int fq, int tm, string str) {
		priority = pr;
		freq = fq;
		time = tm;
		name = str;
	}
};

bool operator<(const node& n1, const node& n2) {
	bool diffTime = n1.time > n2.time;
	bool diffPriority = n1.priority > n2.priority;
	return (n1.time == n2.time) ? diffPriority : diffTime;
}

int main() {
	int T, N, K, fq;
	string str;
	ostringstream s;

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N >> K;
		priority_queue<node> pq;
		for (int i = 0; i < N; i++) {
			cin >> str >> fq;
			pq.push(node(i, fq, fq, str));
		}

		for (int i = 0; i < K; i++) {
			node n = pq.top(); pq.pop();
			s << n.time << ' ' << n.name << '\n';
			pq.push(node(n.priority, n.freq, n.time+n.freq, n.name));
		}
	}
	cout << s.str();
}