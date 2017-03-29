import java.util.*;

class Main {
	static final int IINF = 10000000;
	static final long LINF = Long.MAX_VALUE / 10;

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			// Get R & C
			int R = sc.nextInt();
			int N = sc.nextInt();
			// Initialize graph
			ArrayList<Edge>[] G = new ArrayList[R+N+2];
			for (int i = 0; i < R+N+2; i++) {
				G[i] = new ArrayList<Edge>();
			}
			// Set source and sink
			int s = 0, t = R+N+1;
			// Add (source -> restaurants)
			for (int i = 1; i <= R; i++) {
				Edge a = new Edge(s, i, 1, 0);
				Edge b = new Edge(i, s, 0, 0);
				a.r = b;
				b.r = a;
				G[s].add(a);
				G[i].add(b);
			}
			// Add (orders -> sink)
			for (int i = R+1; i <= R+N; i++) {
				Edge a = new Edge(i, t, 1, 0);
				Edge b = new Edge(t, i, 0, 0);
				a.r = b;
				b.r = a;
				G[i].add(a);
				G[t].add(b);
			}
			// Read restaurants coords
			long[][] restaurants = new long[R][2];
			for (int i = 0; i < R; i++) {
				restaurants[i][0] = sc.nextLong();
				restaurants[i][1] = sc.nextLong();
			}
			// Read orders coords
			long[][] orders = new long[N][2];
			for (int i = 0; i < N; i++) {
				orders[i][0] = sc.nextLong();
				orders[i][1] = sc.nextLong();
			}
			// Add (restaurants -> orders)
			for (int i = 1; i <= R; i++) {
				for (int j = R+1; j <= R+N; j++) {
					long x = orders[j-(R+1)][0] - restaurants[i-1][0];
					long y = orders[j-(R+1)][1] - restaurants[i-1][1];
					long c = Math.abs(x) + Math.abs(y);

					Edge a = new Edge(i, j, 1, c);
					Edge b = new Edge(j, i, 0,-c);
					a.r = b;
					b.r = a;
					G[i].add(a);
					G[j].add(b);
				}
			}
			// Get and substract min-distance
			long min = LINF;
			for (int i = 0; i < R+N+2; i++) {
				for (Edge e : G[i]) {
					if (e.u != s && e.u != t && e.v != s && e.v != t) {
						min = Math.min(Math.abs(e.c), min);
					}
				}
			}
			if (min > 0) {
				min -= 1;
			}

			for (int i = 0; i < R+N+2; i++) {
				for (Edge e : G[i]) {
					if (e.c > 0) e.c -= min;
					if (e.c < 0) e.c += min;
				}
			}

			// Get & print answer
			long ans = getAns(G, s, t, R, N, min);
			System.out.println(ans);
		}
	}

	static long getAns(ArrayList<Edge>[] G, int s, int t, int R, int N, long min) {
		int maxflow = 0;
		long mincost = 0;
		while (true) {
			Edge[] D = new Edge[R+N+2];
			int f = BF(G, s, t, D, R, N);
			if (f == 0) break;
			maxflow += f;

			int v = t;
			while (v != s) {
				long cost = 0;
				if (D[v].c > 0) cost = D[v].c + min;
				if (D[v].c < 0) cost = D[v].c - min;
				mincost += cost * (long) f;
				D[v].f += f;
				D[v].r.f -= f;
				v = D[v].u;
			}
		}
		return mincost;
	}

	static int BF(ArrayList<Edge>[] G, int s, int t, Edge[] D, int R, int N) {
		long[] cos = new long[R+N+2];
		boolean[] inq = new boolean[R+N+2];
		for (int i = 0; i < R+N+2; i++) {
			cos[i] = LINF;
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

		int ans = IINF, v = t;
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
		int u, v, w, f;
		long c;
		Edge r;

		public Edge(int u, int v, int w, long c) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.c = c;
			this.f = 0;
		}
	}
}