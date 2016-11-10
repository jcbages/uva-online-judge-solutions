import java.io.*;
import java.util.*;

class Main {
	final static int OTHER = 0, BANK = 1, POLICE = 2;
	final static long INF = 1000000000000000L;

	static int N, M, B, P;
	static LinkedList<Edge>[] G;
	static int[] status;
	static long[] distances;

	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			N = Integer.parseInt(vals[0]);
			M = Integer.parseInt(vals[1]);
			B = Integer.parseInt(vals[2]);
			P = Integer.parseInt(vals[3]);

			G = new LinkedList[N];
			for (int i = 0; i < N; i++) {
				G[i] = new LinkedList<Edge>();
			}

			for (int i = 0; i < M; i++) {
				vals = in.readLine().split(" ");
				int u = Integer.parseInt(vals[0]);
				int v = Integer.parseInt(vals[1]);
				int c = Integer.parseInt(vals[2]);
				G[u].add(new Edge(u, v, c));
				G[v].add(new Edge(v, u, c));
			}

			status = new int[N];

			if (B > 0) {
				vals = in.readLine().split(" ");
				for (int i = 0; i < B; i++) {
					int n = Integer.parseInt(vals[i]);
					status[n] = BANK;
				}
			}

			if (P > 0) {
				vals = in.readLine().split(" ");
				for (int i = 0; i < P; i++) {
					int n = Integer.parseInt(vals[i]);
					status[n] = POLICE;
				}
			}

			shortestPath();

			long max = -1;
			for (int i = 0; i < distances.length; i++) {
				if (distances[i] > max && status[i] == BANK) {
					max = distances[i];
				}
			}

			ArrayList<Integer> ans = new ArrayList<Integer>();
			for (int i = 0; i < distances.length; i++) {
				if (distances[i] == max && status[i] == BANK) {
					ans.add(i);
				}
			}

			System.out.printf("%d %s\n", ans.size(), max == INF ? "*" : max + "");
			for (int i = 0; i < ans.size(); i++) {
				if (i > 0) System.out.print(" ");
				System.out.print(ans.get(i));
			}
			System.out.println();
		}
	}

	static void shortestPath() {
		distances = new long[N];
		Arrays.fill(distances, INF);
		
		PriorityQueue<Node> PQ = new PriorityQueue<Node>();
		
		for (int i = 0; i < N; i++) {
			if (status[i] == POLICE) {
				distances[i] = 0;
				PQ.add(new Node(i, 0));
			}
		}

		while (!PQ.isEmpty()) {
			Node n = PQ.poll();
			for (Edge e : G[n.v]) {
				if (distances[e.u] + e.c < distances[e.v]) {
					distances[e.v] = distances[e.u] + e.c;
					PQ.add(new Node(e.v, distances[e.v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int v; long c;
		public Node(int v, long c) {
			this.v = v;
			this.c = c;
		}
		@Override
		public int compareTo(Node that) {
			long d = this.c - that.c;
			return d < 0 ? -1 : d > 0 ? 1 : 0;
		}
	}

	static class Edge {
		int u, v, c;
		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
}