import java.io.*;
import java.util.*;

class Main {
	static final int ROWS = 0, COLS = 1;

	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			char[][] board = new char[N][N];
			for (int i = 0; i < N; i++) {
				line = in.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j);
				}
			}

			int[][] rowscount = new int[N][N];
			int rowslength = flag(board, ROWS, rowscount, N, 1);
			int[][] colscount = new int[N][N];
			int colslength = flag(board, COLS, colscount, N, rowslength);

			int s = 0, t = colslength;
			ArrayList<Edge>[] G = new ArrayList[colslength+1];
			for (int i = 0; i < colslength+1; i++) {
				G[i] = new ArrayList<Edge>();
			}

			for (int i = 1; i < rowslength; i++) {
				Edge a = new Edge(s, i, 1);
				Edge b = new Edge(i, s, 0);
				a.r = b;
				b.r = a;
				G[s].add(a);
				G[i].add(b);
			}

			for (int i = rowslength; i < colslength; i++) {
				Edge a = new Edge(i, t, 1);
				Edge b = new Edge(t, i, 0);
				a.r = b;
				b.r = a;
				G[i].add(a);
				G[t].add(b);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int u = rowscount[i][j];
					if (u > 0) {
						int v = colscount[i][j];
						if (!contains(G[u], v)) {
							Edge a = new Edge(u, v, 1);
							Edge b = new Edge(v, u, 0);
							a.r = b;
							b.r = a;
							G[u].add(a);
							G[v].add(b);
						}
					}
				}
			}

			int ans = getAns(G, s, t);
			System.out.println(ans);
		}
	}

	static int getAns(ArrayList<Edge>[] G, int s, int t) {
		int maxflow = 0;
		while (true) {
			Edge[] D = new Edge[G.length];
			int f = bfs(G, D, s, t);
			if (f == 0) break;
			maxflow += f;

			int v = t;
			while (v != s) {
				D[v].f += f;
				D[v].r.f -= f;
				v = D[v].u;
			}
		}
		return maxflow;
	}

	static int bfs(ArrayList<Edge>[] G, Edge[] D, int s, int t) {
		boolean[] visited = new boolean[G.length];
		Queue<Integer> Q = new LinkedList<Integer>();
		visited[s] = true;
		Q.add(s);

		boolean found = false;
		while (Q.size() > 0) {
			int u = Q.poll();
			for (Edge e : G[u]) {
				if (e.w - e.f > 0 && !visited[e.v]) {
					visited[e.v] = true;
					Q.add(e.v);
					D[e.v] = e;

					if (e.v == t) {
						found = true;
						break;
					}
				}
			}
		}

		if (!found) {
			return 0;
		} else {
			int ans = Integer.MAX_VALUE, v = t;
			while (v != s) {
				ans = Math.min(D[v].w - D[v].f, ans);
				v = D[v].u;
			}
			return ans;
		}
	}

	static boolean contains(ArrayList<Edge> E, int v) {
		for (Edge e : E) {
			if (e.v == v) {
				return true;
			}
		}
		return false;
	} 

	static int flag(char[][] board, int selection,
	int[][] count, int N, int actual) {
		for (int i = 0; i < N; i++) {
			boolean flagged = false;
			for (int j = 0; j < N; j++) {
				if (selection == ROWS) {
					if (board[i][j] == '.') {
						count[i][j] = actual;
						flagged = true;
					} else if (flagged) {
						actual += 1;
						flagged = false;
					}
				} else {
					if (board[j][i] == '.') {
						count[j][i] = actual;
						flagged = true;
					} else if (flagged) {
						actual += 1;
						flagged = false;
					}
				}
			}
			if (flagged) {
				actual += 1;
			}
		}
		return actual;
	}

	static class Edge {
		int u, v, w, f;
		Edge r;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.f = 0;
		}
	}
}