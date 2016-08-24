import java.io.*;
import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int V = Integer.parseInt(vals[0]);
			int C = Integer.parseInt(vals[1]);
			String[] jack = new String[V];
			for (int i = 0; i < V; i++) {
				jack[i] = in.readLine();
			}
			String[] jill = new String[C];
			for (int i = 0; i < C; i++) {
				jill[i] = in.readLine();
			}
			int s = 0, t = V+C+1;
			ArrayList<Edge>[] G = new ArrayList[V+C+2]; // +2 is s & t
			for (int i = 0; i < V+C+2; i++) {
				G[i] = new ArrayList<Edge>();
			}
			for (int i = 1; i <= V; i++) {
				Edge a = new Edge(s, i, 1);
				Edge b = new Edge(i, s, 0);
				a.r = b;
				b.r = a;
				G[s].add(a);
				G[i].add(b);
			}
			for (int i = V+1; i <= V+C; i++) {
				Edge a = new Edge(i, t, 1);
				Edge b = new Edge(t, i, 0);
				a.r = b;
				b.r = a;
				G[i].add(a);
				G[t].add(b);
			}
			for (int i = 1; i <= V; i++) {
				for (int j = V+1; j <= V+C; j++) {
					if (match(jack[i-1], jill[j-V-1])) {
						Edge a = new Edge(i, j, 1);
						Edge b = new Edge(j, i, 0);
						a.r = b;
						b.r = a;
						G[i].add(a);
						G[j].add(b);
					}
				}
			}

			// for (int i = 0; i < V+C+2; i++) {
			// 	System.out.print(i+"->");
			// 	for (Edge e : G[i]) {
			// 		System.out.print("("+e.v+","+e.w+"), ");
			// 	}
			// 	System.out.println("");
			// }

			int ans = getAns(G, s, t, V, C);
			System.out.println(ans);
		}
	}

	static int getAns(ArrayList<Edge>[] G, int s, int t, int V, int C) {
		int maxflow = 0;
		while (true) {
			Edge[] D = new Edge[V+C+2];
			int f = bfs(G, s, t, V, C, D);
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

	static int bfs(ArrayList<Edge>[] G, int s, int t, int V, int C, Edge[] D) {
		boolean[] visited = new boolean[V+C+2];
		Queue<Integer> Q = new LinkedList<Integer>();
		visited[s] = true;
		Q.add(s);

		boolean found = false;
		while (!Q.isEmpty() && !found) {
			int u = Q.poll();
			for (Edge e : G[u]) {
				int v = e.v;
				if (e.w - e.f > 0 && !visited[v]) {
					D[v] = e;
					visited[v] = true;
					Q.add(v);
					if (v == t) {
						found = true;
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

	static boolean match(String a, String b) {
		int[] count = new int[26];
		for (int i = 0; i < a.length(); i++) {
			count[a.charAt(i)-'a'] += 1;
		}
		for (int i = 0; i < b.length(); i++) {
			count[b.charAt(i)-'a'] -= 1;
		}
		boolean valid = true;
		for (int i = 0; i < 26 && valid; i++) {
			valid = count[i] >= 0;
		}
		return valid;
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