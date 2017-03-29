import java.io.*;
import java.util.*;

class Main {
	static class Node {
		int v, A, B;

		public Node(int v, int A, int B) {
			this.v = v;
			this.A = A;
			this.B = B;
		}
	}

	static class DNode implements Comparable<DNode> {
		int v;
		double c;

		public DNode(int v, double c) {
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(DNode that) {
			if (this.c > that.c + 1e-9) return 1;
			else return -1;
		}
	}

	static double f(ArrayList<Node>[] graph, int N, double t) {
		double[] d = new double[N];
		for (int i = 0; i < N; i++) d[i] = 1e+12;
		d[0] = 0;
		PriorityQueue<DNode> Q = new PriorityQueue<DNode>();
		Q.add(new DNode(0, 0));

		while (Q.size() > 0) {
			DNode node = Q.poll();
			int u = node.v;
			for (int i = 0; i < graph[u].size(); i++) {
				Node target = graph[u].get(i);
				int v = target.v;
				double cost = target.A * t + target.B;
				if (d[v] > d[u] + cost + 1e-9) {
					d[v] = d[u] + cost;
					Q.add(new DNode(v, d[v]));
				}
			}
		}

		return d[N-1];
	}

	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			int M = Integer.parseInt(vals[1]);

			ArrayList<Node>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++) graph[i] = new ArrayList<Node>();

			int I, J, A, B;
			double lo = 0, hi = 24 * 60;

			for (int i = 0; i < M; i++) {
				vals = in.readLine().split(" ");
				I = Integer.parseInt(vals[0]);
				J = Integer.parseInt(vals[1]);
				A = Integer.parseInt(vals[2]);
				B = Integer.parseInt(vals[3]);
				graph[I-1].add(new Node(J-1, A, B));
				graph[J-1].add(new Node(I-1, A, B));
			}

			double l, r;
			for (int i = 0; i < 100; i++) {
				l = lo + (hi - lo) / 3;
				r = hi - (hi - lo) / 3;

				if (1e-9 + f(graph, N, l) < f(graph, N, r)) {
					lo = l;
				} else {
					hi = r;
				}
			}

			double t = (lo + hi) / 2;
			System.out.printf("%.5f\n", f(graph, N, t));
		}
	}
}