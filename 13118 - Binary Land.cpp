#include <iostream>
#include <queue>
#include <utility>
#include <vector>
using namespace std;

struct state {
	int rg, cg, rm, cm, time;
};

const int MAX = 42;
int R, C;
char board[MAX][MAX];
vector<state> v;
bool love, visited[MAX][MAX][MAX][MAX];
pair<int, int> lovec, gurin, malon;

inline int goup(int r, int c) { return r+1 < R && board[r+1][c] != '#' ? r+1 : r; }
inline int godw(int r, int c) { return r-1 >= 0 && board[r-1][c] != '#' ? r-1 : r; }
inline int golt(int r, int c) { return c+1 < C && board[r][c+1] != '#' ? c+1 : c; }
inline int gort(int r, int c) { return c-1 >= 0 && board[r][c-1] != '#' ? c-1 : c; }

void next_states(state& s) {
	state possible[] = {
		{goup(s.rg, s.cg), s.cg, goup(s.rm, s.cm), s.cm, s.time+1},
		{godw(s.rg, s.cg), s.cg, godw(s.rm, s.cm), s.cm, s.time+1},
		{s.rg, golt(s.rg, s.cg), s.rm, gort(s.rm, s.cm), s.time+1},
		{s.rg, gort(s.rg, s.cg), s.rm, golt(s.rm, s.cm), s.time+1}
	};
	v.clear();
	for (int i = 0; i < 4; i++) {
		state pos = possible[i];
		if (!visited[pos.rg][pos.cg][pos.rm][pos.cm])
			v.push_back(pos);
	}
}

int main() {
	while (cin >> R && cin >> C) {
		// read love and penguins positions
		cin >> lovec.first; lovec.first--;
		cin >> lovec.second; lovec.second--;
		cin >> gurin.first; gurin.first--;
		cin >> gurin.second; gurin.second--;
		cin >> malon.first; malon.first--;
		cin >> malon.second; malon.second--;
		// read board matrix
		for (int i = 0; i < R; i++)
			cin >> board[i];
		// clean visited matrix
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				for (int k = 0; k < R; k++)
					for (int l = 0; l < C; l++)
						visited[i][j][k][l] = false;
		// perform bfs search and search ans
		queue<state> q; love = false;
		state source = {gurin.first, gurin.second, malon.first, malon.second, 0};
		q.push(source);
		while (!q.empty()) {
			state curr = q.front(); q.pop();
			bool gpos = (curr.rg == lovec.first && curr.cg == lovec.second);
			bool mpos = (curr.rm == lovec.first && curr.cm == lovec.second);
			if (gpos && mpos) {
				love = true;
				cout << curr.time << endl;
				break;
			}
			next_states(curr);
			for (int i = 0; i < v.size(); i++) {
				state s = v[i];
				visited[s.rg][s.cg][s.rm][s.cm] = true;
				q.push(s);
			}
		}
		// love was not found
		if (!love)
			cout << "NO LOVE" << endl;
	}
}
