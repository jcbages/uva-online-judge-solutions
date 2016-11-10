#include <array>
#include <iostream>
#include <functional>
#include <queue>
#include <set>
#include <string>
#include <utility>
#include <vector>
using namespace std;

const int LETT = 26, MAX = 100005;

int ans;
string t, w;
vector<int> dist;
vector<string> pieces;
int g[MAX][LETT], f[MAX];
set<int> output[MAX];
priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > pq;

void preprocess_pattern() {
	dist.clear();
	pieces.clear();
	string actual;
	int count = 0;
	for (int i = 0; i < w.size(); i++) {
		if (w[i] == '?') {
			count++;
			if (actual.size() > 0) {
				pieces.push_back(actual);
				actual.clear();
			}
		} else {
			if (i == 0 || count > 0) {
				dist.push_back(count);
				count = 0;
			}
			actual.push_back(w[i]);
		}
	}
	if (actual.size() > 0) {
		pieces.push_back(actual);
		actual.clear();
	}
	dist.push_back(count);
}

void insert(int piece, string& s, int& newstate) {
	int state = 0, j = 0;
	while (j < s.size() && g[state][s[j]-'a'] != -1) {
		state = g[state][s[j]-'a'];
		j++;
	}
	for (; j < s.size(); j++) {
		newstate++;
		g[state][s[j]-'a'] = newstate;
		state = newstate;
	}
	output[state].insert(piece);
}

void goto_function() {
	for (int i = 0; i < MAX; i++) {
		for (int j = 0; j < LETT; j++)
			g[i][j] = -1;
		output[i].clear();
	}

	int newstate = 0;
	for (int i = 0; i < pieces.size(); i++) {
		insert(i, pieces[i], newstate);
	}
	for (int i = 0; i < LETT; i++)
		if (g[0][i] == -1)
			g[0][i] = 0;
}

void failure_function() {
	for (int i = 0; i < MAX; i++)
		f[i] = -1;

	int state;
	queue<int> q;
	for (int i = 0; i < LETT; i++) {
		if (g[0][i] != 0) {
			q.push(g[0][i]);
			f[g[0][i]] = 0;
		}
	}
	while (!q.empty()) {
		int r = q.front(); q.pop();
		for (int i = 0; i < LETT; i++) {
			int s = g[r][i];
			if (s != -1) {
				q.push(s);
				state = f[r];
				while (g[state][i] == -1)
					state = f[state];
				f[s] = g[state][i];
				for (set<int>::iterator it = output[f[s]].begin(); it != output[f[s]].end(); it++)
					output[s].insert(*it);
			}
		}
	}
}

void aho_corasick() {
	// build the goto function and partial output function
	goto_function();
	// build the failure function and complete output function
	failure_function();
	// search for occurrences on the given text
	int state = 0, n = dist.size();
	for (int i = dist[0]; i < t.size() - dist[n-1]; i++) {
		while (g[state][t[i]-'a'] == -1)
			state = f[state];
		state = g[state][t[i]-'a'];
		// try to add new possible occs
		if (output[state].find(0) != output[state].end()) { // possible occ
			if (pieces.size() > 1)
				pq.push(make_pair(i + pieces[1].size() + dist[1], 1));
			else
				ans++;
		}
		// try to get next values from pq
		while (!pq.empty() && pq.top().first == i) {
			int curr = pq.top().second; pq.pop();
			if (output[state].find(curr) != output[state].end()) {
				if (curr == pieces.size() - 1)
					ans++;
				else
					pq.push(make_pair(i + pieces[curr+1].size() + dist[curr+1], curr+1));
			}
		}
	}
}

int main() {
	while (cin >> t && cin >> w) {
		// special case #1: |w| > |t|
		if (w.size() > t.size()) {
			cout << 0 << endl;
			continue;
		}
		// get pieces and distances
		preprocess_pattern();
		// special case #2: just wildcards (???)
		if (pieces.size() == 0) {
			cout << t.size() - w.size() + 1 << endl;
			continue;
		}
		// get occurrences aho-corasick =)
		ans = 0;
		pq = priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > >();
		aho_corasick();
		cout << ans << endl;
	}
}