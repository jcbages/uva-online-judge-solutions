import java.io.*;
import java.util.*;

class Main {
	static int M, W;
	static long[] machines;
	static LinkedList<Edge>[] edges;

	final static long INF = (long) Math.pow(10, 15);

	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			M = Integer.parseInt(vals[0]);
			W = Integer.parseInt(vals[1]);
			if (M == 0 && W == 0) {
				break;
			}

			machines = new long[M];
			machines[0] = INF;
			machines[M - 1] = INF;
			for (int i = 0; i < M - 2; i++) {
				vals = in.readLine().split(" ");
				int id = Integer.parseInt(vals[0]) - 1;
				long cost = Long.parseLong(vals[1]);
				machines[id] = cost;
			}

			edges = new LinkedList[2 * M - 2];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new LinkedList<Edge>();
			}

			for (int i = 0; i < W; i++) {
				vals = in.readLine().split(" ");
				int j = Integer.parseInt(vals[0]) - 1;
				int k = Integer.parseInt(vals[1]) - 1;
				long cost = Long.parseLong(vals[2]);
				
				addEdge(end(j), k, cost);
			}

			for (int i = 1; i < M - 1; i++) {
				Edge e1 = new Edge(i, end(i), machines[i]);
				Edge e2 = new Edge(end(i), i, machines[i]);

				e1.op = e2;
				e2.op = e1;
				edges[i].add(e1);
				edges[end(i)].add(e2);
			}

			long ans = maxFlow(0, M - 1);
			System.out.println(ans);
		}
	}

	static void addEdge(int u, int v, long cost) {
		Edge e1 = new Edge(u, v, cost);
		Edge e2 = new Edge(v, u, cost);
		e1.op = e2;
		e2.op = e1;
		edges[u].add(e1);
		edges[v].add(e2);
	}

	static int end(int i) {
		int ans = (i == 0 || i == M - 1) ? i : M + i - 1;
		return ans;
	}

	static long maxFlow(int s, int t) {
		long max = 0;
		while (true) {
			long flow = bfs(s, t);
			if (flow == 0) {
				break;
			}
			max += flow;
		}
		return max;
	}

	static long bfs(int s, int t) {
		Edge[] parent = new Edge[edges.length];
		parent[s] = new Edge(s, s, 0);

		long[] flow = new long[edges.length];
		flow[s] = INF;

		boolean arrived = false;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);
		while (!Q.isEmpty() && !arrived) {
			int u = Q.poll();
			for (Edge edge : edges[u]) {
				// edge has flow and has not been visited
				if ((edge.cost - edge.flow > 0 || edge.cost == 0) && parent[edge.v] == null) {
					parent[edge.v] = edge;
					flow[edge.v] = Math.min(flow[u], edge.cost - edge.flow);

					if (edge.v == t) {
						arrived = true;
						break;
					} else {
						Q.add(edge.v);
					}
				}
			}
		}

		if (arrived) {
			int v = t;
			while (v != s) {
				Edge e = parent[v];
				e.flow += flow[t];
				e.op.flow -= flow[t];
				v = e.u;
			}
			return flow[t];
		}

		return 0;
	}

	static class Edge {
		int u, v;
		long cost, flow;
		Edge op;

		public Edge(int u, int v, long cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
			this.flow = 0;
		}
	}
}