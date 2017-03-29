import java.util.*;

class Main {
	final static int INF = 1000000;

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			// Number of vertices and edges in the graph.
			int N = sc.nextInt();
			int M = sc.nextInt();

			// Define a graph adjacency list (+1 is source).
			ArrayList<Edge>[] G = new ArrayList[N+1];
			for (int i = 0; i < N+1; i++) {
				G[i] = new ArrayList<Edge>();
			}

			// Define s and t, source is node 0, and sink is node N.
			int s = 0, t = N;

			// Source vertex has an edge to each city with capacity
			// ti, and cost 0, reverse edge has capacity and cost 0.
			for (int i = 1; i < N; i++) {
				// s -> v
				int ti = sc.nextInt();
				G[s].add(new Edge(s, i, ti, 0));
			}

			// Each edge will connect vertex (u -> v) with capacity
			// w, and cost c.
			for (int i = 0; i < M; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int w = sc.nextInt();
				int c = sc.nextInt();
				// u -> v
				G[u].add(new Edge(u, v, w, c));
				G[v].add(new Edge(v, u, w, c));
			}

			// Calculate maxflow and minprice for that flow, then
			// print the answer.
			int[] ans = getAns(G, s, t, N);
			System.out.printf("%d %d\n", ans[0], ans[1]);
		}
	}

	@SuppressWarnings("unchecked")
	static int[] getAns(ArrayList<Edge>[] G, int s, int t, int N) {
		// Create a residual graph with opposite edges.
		ArrayList<Edge>[] G2 = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			G2[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < N+1; i++) {
			for (Edge e : G[i]) {
				Edge r = new Edge(e.v, e.u, 0, -e.c);
				e.r = r;
				r.r = e;
				// u -> v & v -> u
				G2[e.u].add(e);
				G2[e.v].add(r);
			}
		}

		// Hold current maxflow and it's corresponding mincost.
		int maxflow = 0, mincost = 0;

		// Iterate until there are no more augmenting paths.
		// At each iteration, increase maxflow with current
		// found flow, and modify flow for each edge.
		while (true) {
			Edge[] D = new Edge[N+1]; // Vertex parent (path)
			int f = BF(G2, s, t, D, N); // flow at augmenting paths
			if (f == 0) break; // if no flow, break
			maxflow += f; // Add path flow to maxflow

			int v = t;
			while (v != s) {
				mincost += D[v].c * f;
				D[v].f += f;
				D[v].r.f -= f;
				v = D[v].u;
			}
		}

		return new int[] { maxflow, mincost };
	}

	static int BF(ArrayList<Edge>[] G, int s, int t, Edge[] D, int N) {
		int[] cos = new int[N+1];
		boolean[] inq = new boolean[N+1];
		for (int i = 0; i < N+1; i++) {
			cos[i] = INF;
			inq[i] = false;
			D[i] = null;
		}
		cos[s] = 0;
		inq[s] = true;

		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);

		while (!Q.isEmpty()) {
			int u = Q.poll();
			inq[u] = false;
			for (Edge e : G[u]) {
				int v = e.v;
				if (e.w - e.f > 0 && cos[v] > cos[u] + e.c) {
					cos[v] = cos[u] + e.c;
					D[v] = e;
					if (!inq[v]) {
						Q.add(v);
						inq[v] = true;
					}
				}
			}
		}

		int ans = INF, v = t;
		while (v != s) {
			if (D[v] == null) {
				return 0;
			}
			ans = Math.min(D[v].w - D[v].f, ans);
			v = D[v].u;
		}
		return ans;
	}

	static class Edge {
		int u, v, c, w, f;
		Edge r;

		public Edge(int u, int v, int w, int c) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.c = c;
			this.f = 0;
		}
	}
}