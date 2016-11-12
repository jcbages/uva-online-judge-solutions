#include <cstdio>
#include <iostream>
#include <queue>
#include <set>
#include <string>
using namespace std;

const int MAX = 1000005;
int k, q, gt[MAX][52], f[MAX], newstate;
bool found[1005], visited[MAX];
set<int> output[MAX];
string S, T[1005];

void insert(string& actual, int i) {
	int s = 0, j = 0, m = actual.length(), index;
	// go forward at existing states
	for (; j < m; j++) {
		index = isupper(actual[j]) ? actual[j]-'A' : actual[j]-'a';
		if (gt[s][index] == -1) break;
		s = gt[s][index];
	}
	// add missing states
	for (; j < m; j++) {
		index = isupper(actual[j]) ? actual[j]-'A' : actual[j]-'a';
		gt[s][index] = ++newstate;
		s = gt[s][index];
	}
	// add output
	output[s].insert(i);
}

void build_goto() {
	// reset counter
	newstate = 0;
	// add each word to the automata
	for (int i = 0; i < q; i++)
		insert(T[i], i);
	// set gt[0][x] = 0 for all gt[0][x] = -1
	for (int i = 0; i < 52; i++)
		gt[0][i] = (gt[0][i] == -1) ? 0 : gt[0][i];
}

void build_failure() {
	// add initial states
	queue<int> Q;
	for (int i = 0; i < 52; i++) {
		if (gt[0][i] != 0) {
			Q.push(gt[0][i]);
			f[gt[0][i]] = 0;
		}
	}

	// perform bfs
	int r, s, state;
	while (!Q.empty()) {
		r = Q.front(); Q.pop();
		for (int i = 0; i < 52; i++) {
			// its a transition
			s = gt[r][i];
			if (s != -1) {
				// get longest prefix/suffix
				Q.push(s);
				state = f[r];
				while (gt[state][i] == -1) {
					state = f[state];
				}
				f[s] = gt[state][i];
				// merge outputs
				for (set<int>:: iterator it = output[f[s]].begin(); it != output[f[s]].end(); it++)
					output[s].insert(*it);
			}
		}
	}
}

void aho_corasick() {
	build_goto();
	build_failure();
	int state = 0;
	for (int i = 0, n = S.length(); i < n; i++) {
		// get next state
		int index = isupper(S[i]) ? S[i]-'A' : S[i]-'a';
		while (gt[state][index] == -1)
			state = f[state];
		state = gt[state][index];
		// mark output
		for (set<int>::iterator it = output[state].begin(); it != output[state].end(); it++) {
			found[*it] = true;
		}
	}
}

int main() {
	// number of test cases
	cin >> k;
	for (int t = 0; t < k; t++) {
		// read data and clean structures
		cin >> S;
		cin >> q;
		for (int i = 0; i < q; i++) {
			cin >> T[i];
			found[i] = false;
		}
		for (int i = 0, n = MAX; i < n; i++) {
			for (int j = 0; j < 52; j++)
				gt[i][j] = -1;
			f[i] = -1;
			output[i].clear();
			visited[i] = false;
		}
		// build aho_corasick automata
		aho_corasick();
		// print answer based on occs
		for (int i = 0; i < q; i++)
			cout << (found[i] ? "y" : "n") << endl;
	}
}