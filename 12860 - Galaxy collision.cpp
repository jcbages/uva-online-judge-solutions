#include <iostream>
#include <cmath>
#include <map>
#include <utility>
#include <vector>
using namespace std;

const int MAX_POINTS = 50002, MAX_PLANE = 500002, MAX_DIST = 25, NONE = -1, RED = 0, BLUE = 1;
int N, x, y;
int visited[MAX_POINTS];
map<pair<int, int>, int> points;
vector<vector<int> > graph;

inline int dist(const pair<int, int>& a, const pair<int, int>& b) {
	return pow(a.first - b.first, 2) + pow(a.second - b.second, 2);
}

void dfs(int u, int color) {
	visited[u] = color;
	for (int v = 0; v < graph[u].size(); v++) {
		if (visited[graph[u][v]] == NONE) {
			dfs(graph[u][v], 1 - color);
		}
	}
}

int main() {
	// while not EOF
	while (cin >> N) {
		// read data
		points.clear();
		for (int i = 0; i < N; i++) {
			cin >> x; cin >> y;
			points[make_pair(x, y)] = i;
		}
		// get distance graph
		graph.clear();
		for (int i = 0; i < N; i++) {
			vector<int> v;
			graph.push_back(v);
		}
		for (map<pair<int, int>, int>::iterator it = points.begin(); it != points.end(); it++) {
			int loX = max(it->first.first - 5, 1), hiX = min(it->first.first + 5, MAX_PLANE);
			int loY = max(it->first.second - 5, 1), hiY = min(it->first.second + 5, MAX_PLANE);
			for (int x = loX; x <= hiX; x++) {
				for (int y = loY; y <= hiY; y++) {
					pair<int, int> other = make_pair(x, y);
					if (dist(it->first, other) <= MAX_DIST && points.find(other) != points.end()) {
						if (it->second != points[other]) {
							graph[it->second].push_back(points[other]);
						}
					}
				}
			}
		}
		// get bipartite graph
		for (int i = 0; i < N; i++) {
			visited[i] = NONE;
		}
		for (int i = 0; i < N; i++) {
			if (graph[i].size() > 0 && visited[i] == NONE) {
				dfs(i, RED);
			}
		}
		// get ans
		int red = 0, blue = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i] == RED) {
				red++;
			} else if (visited[i] == BLUE) {
				blue++;
			}
		}
		cout << min(red, blue) << endl;
	}
	return 0;
}