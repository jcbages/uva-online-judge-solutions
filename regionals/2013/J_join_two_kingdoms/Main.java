import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			int Q = Integer.parseInt(vals[1]);
			ArrayList<Integer>[] G1 = getGraph(in, N);
			ArrayList<Integer>[] G2 = getGraph(in, Q);
			int s1 = getSource(G1, N);
			int s2 = getSource(G2, Q);
			int[] max1 = getMax(G1, s1, N);
			int[] max2 = getMax(G2, s2, Q);
			double ans = getAns(max1, max2, s1, s2, N, Q);
			System.out.printf("%.3f\n", ans);
		}
	}

	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] getGraph(BufferedReader in, int n) throws IOException {
		ArrayList<Integer>[] G = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			G[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n-1; i++) {
			String[] vals = in.readLine().split(" ");
			int u = Integer.parseInt(vals[0]);
			int v = Integer.parseInt(vals[1]);
			G[u-1].add(v-1);
			G[v-1].add(u-1);
		}
		return G;
	}

	static int getSource(ArrayList<Integer>[] G, int n) {
		int u = 0;
		boolean[] vi = new boolean[n];
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(0);
		vi[0] = true;
		while (!Q.isEmpty()) {
			u = Q.poll();
			for (int v : G[u]) {
				if (!vi[v]) {
					Q.add(v);
					vi[v] = true;
				}
			}
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	static int[] getMax(ArrayList<Integer>[] G, int s, int n) {
		// First pass, get parents, distances, and collect leaves...
		int[] d = new int[n]; // distances
		int[] p = new int[n]; // parents
		boolean[] vi = new boolean[n]; // visited
		ArrayList<Integer>[] levels = new ArrayList[n]; // levels for snd pass
		for (int i = 0; i < n; i++) {
			levels[i] = new ArrayList<Integer>();
		}
		Queue<Integer> Q = new LinkedList<Integer>(); // bfs
		Q.add(s);
		d[s] = 0;
		p[s] = -1;
		vi[s] = true;
		levels[0].add(s);
		while (!Q.isEmpty()) {
			int u = Q.poll();
			for (int v : G[u]) {
				if (!vi[v] && v != p[u]) { // if v is not the parent of u...
					Q.add(v);
					d[v] = d[u] + 1;
					p[v] = u;
					vi[v] = true;
					levels[d[v]].add(v);
				}
			}
		}
		// System.out.print("par: "); for (int m : p) System.out.print(m+", "); System.out.println();
		// System.out.print("dis: "); for (int m : d) System.out.print(m+", "); System.out.println();
		// Second pass, set matrix paths of two largest paths...
		int[][] paths = new int[n][4]; // fst & snd largest in order with targets
		for (int i = n-1; i > 0; i--) {
			for (int v : levels[i]) {
				int u = p[v];
				updatePaths(paths, u, v);
			}
		}
		// System.out.print("pt0: "); for (int[] m : paths) System.out.print(m[0]+", "); System.out.println();
		// System.out.print("pt1: "); for (int[] m : paths) System.out.print(m[2]+", "); System.out.println();
		// Third pass, get max for each vertex...
		for (int i = 0; i < n; i++) {
			vi[i] = false;
		}
		int[] max = new int[n];
		Q.add(s);
		vi[s] = true;
		while (!Q.isEmpty()) {
			int u = Q.poll();
			max[u] = getDiameter(G, p, d, paths, u);
			for (int v : G[u]) {
				if (!vi[v] && v != p[u]) {
					Q.add(v);
					vi[v] = true;
				}
			}
		}
		// System.out.print("max: "); for (int m : max) System.out.print(m+", "); System.out.println();
		return max;
	}

	static void updatePaths(int[][] paths, int u, int v) {
		if (paths[u][0] < paths[v][0] + 1) {
			paths[u][0] = paths[v][0] + 1;
			paths[u][1] = v;
		} else if (paths[u][2] < paths[v][0] + 1) {
			paths[u][2] = paths[v][0] + 1;
			paths[u][3] = v;
		}
	}

	static int getDiameter(ArrayList<Integer>[] G, int[] p, int[] d, int[][] paths, int u) {
		int[] cands = new int[] {
			paths[u][0],			// u's fst largest path
			paths[u][2],			// u's snd largest path
			d[u],					// u's distance to root
			bp(G, paths, p[u], u)	// 1 + largest parent path != me
		};
		// System.out.print("cands for "+u+" -> "); for (int c : cands) System.out.print(c+", "); System.out.println();
		int ans = 0;
		for (int c : cands) {
			ans = Math.max(c, ans);
		}
		return ans;
	}

	static int bp(ArrayList<Integer>[] G, int[][] paths, int u, int v) {
		if (u == -1) {
			return 0;
		} else if (paths[u][1] != v) {
			return paths[u][0] + 1;
		} else if (G[u].size() > 1) {
			return paths[u][2] + 1;
		} else {
			return 0;
		}
	}

	static int findLeft(int[] max1, int[] max2, int u, int l, int Q) {
		int lo = 0, hi = Q-1, ans = Q;
		while (lo <= hi) {
			int mid = (lo + hi) >>> 1;
			if (max1[u] + max2[mid] + 1 >= l) {
				ans = Math.min(mid, ans);
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return ans;
	}

	static double getAns(int[] max1, int[] max2, int s1, int s2, int N, int Q) {
		// Save largest and sort
		int l = Math.max(max1[s1], max2[s2]);
		Arrays.sort(max1);
		Arrays.sort(max2);
		// Save accumulates for Quadradonia kingdom...
		int[] acc = new int[Q+1];
		acc[0] = 0;
		for (int i = 1; i <= Q; i++) {
			acc[i] = acc[i-1] + max2[i-1] + 1;
		}
		// Get answer...
		int ans = 0;
		for (int i = 0; i < N; i++) {
			int lef = findLeft(max1, max2, i, l, Q);
			int sum = acc[Q] - acc[lef];
			int act = max1[i] * (Q - lef);
			int max = lef * l;
			ans += sum + act + max;
		}
		return (double) ans / (N * Q);
	}
}