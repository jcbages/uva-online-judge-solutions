import java.io.*;
import java.util.*;

class Main {
	final static int N = 302; // max 300 stops in-between (u ->* v)
	final static long INF = Long.MAX_VALUE / 10;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		int T = 0;
		while ((line = in.readLine()) != null) {
			if (T > 0) {
				System.out.println(".");
			}

			String[] vals = line.split(" ");
			int p = Integer.parseInt(vals[0]);
			int f = Integer.parseInt(vals[1]);
			int q = Integer.parseInt(vals[2]);

			Hashtable<String, Integer> planets = new Hashtable<String, Integer>();
			for (int i = 0; i < p; i++) {
				String name = in.readLine();
				planets.put(name, i);
			}

			Node[] graph = new Node[p];
			for (int i = 0; i < p; i++) {
				graph[i] = new Node(i);
			}

			for (int i = 0; i < f; i++) {
				vals = in.readLine().split(" ");
				int u = planets.get(vals[0]);
				int v = planets.get(vals[1]);
				if (u != v) {
					int c = Integer.parseInt(vals[2]);
					int t = Integer.parseInt(vals[3]);
					Edge edge = new Edge(u, v, c, t);
					graph[u].edges.add(edge);
				}
			}

			int origin = planets.get(in.readLine());

			SPNode[][] sp = mkSP(origin, p, graph);
			for (int i = 0; i < q; i++) {
				vals = in.readLine().split(" ");
				int v = planets.get(vals[0]);
				int n = Integer.parseInt(vals[1]);
				
				SPNode ans = sp[v][n+1];
				if (ans.c == INF && ans.t == INF) {
					System.out.println("* *");
				} else {
					System.out.printf("%d %d\n", ans.c, ans.t);
				}
			}

			T += 1;
		}
	}

	static SPNode[][] mkSP(int origin, int p, Node[] graph) {
		SPNode[][] sp = new SPNode[p][N];
		// Initialize to inf
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < N; j++) {
				sp[i][j] = new SPNode(INF, INF);
			}
		}
		// Set origin
		sp[origin][0].c = 0;
		sp[origin][0].t = 0;
		// Calc SP distances
		for (int j = 1; j < N; j++) {
			for (int i = 0; i < p; i++) {
				for (Edge e : graph[i].edges) {
					SPNode u = sp[e.u][j-1];
					SPNode v = sp[e.v][j];
					if (v.c > u.c + e.c) {
						sp[e.v][j].c = u.c + e.c;
						sp[e.v][j].t = u.t + e.t;
					} else if (v.c == u.c + e.c) {
						sp[e.v][j].t = Math.min(v.t, u.t + e.t);
					}
				}
			}
		}
		// Let min value
		for (int i = 0; i < p; i++) {
			for (int j = 1; j < N; j++) {
				if (sp[i][j].c > sp[i][j-1].c) {
					// Set new values
					sp[i][j].c = sp[i][j-1].c;
					sp[i][j].t = sp[i][j-1].t;
				} else if (sp[i][j].c == sp[i][j-1].c) {
					sp[i][j].t = Math.min(sp[i][j].t, sp[i][j-1].t);
				}
			}
		}
		return sp;
	}

	static class SPNode {
		long c, t;

		public SPNode(long c, long t) {
			this.c = c;
			this.t = t;
		}
	}

	static class Node {
		int id;
		ArrayList<Edge> edges;

		public Node(int id) {
			this.id = id;
			this.edges = new ArrayList<Edge>();
		}
	}

	static class Edge {
		int u, v, c, t;

		public Edge(int u, int v, int c, int t) {
			this.u = u;
			this.v = v;
			this.c = c;
			this.t = t;
		}
	}
}