import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		int T = 0;
		while ((line = in.readLine()) != null) {
			if (T > 0) {
				System.out.println("*");
			}
			String[] vals = line.split(" ");
			int N = vals.length + 2;
			Node[] nodes = new Node[N];
			for (int i = 0; i < N; i++) {
				nodes[i] = new Node(i);
			}
			String ans = getAns(nodes, vals, N);
			System.out.println(ans);
			T += 1;
		}
	}

	static String getAns(Node[] nodes, String[] vals, int N) {
		Integer[][] lastTime = new Integer[N][2];
		for (int i = 0; i < N; i++) {
			lastTime[i][0] = i;
			lastTime[i][1] = -1;
		}
		for (int i = 0; i < N-2; i++) {
			int u = Integer.parseInt(vals[i]);
			if (u < 0 || u >= N) {
				return "impossible";
			}
			lastTime[u][1] = i;
		}
		Arrays.sort(lastTime, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] a, Integer[] b) {
				return (a[1] == b[1]) ? a[0] - b[0] : a[1] - b[1];
			}
		});

		boolean[] used = new boolean[N];
		int lastIndex = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < N-2; i++) {
			lastIndex = fillPQ(pq, lastTime, i, N, lastIndex);
			if (pq.size() == 0) {
				return "impossible";
			} else {
				int u = Integer.parseInt(vals[i]);
				int v = pq.poll();
				nodes[u].edges.add(nodes[v]);
				nodes[v].edges.add(nodes[u]);
				used[v] = true;
			}
		}

		int a = -1, b = -1, count = 0;
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				if (count == 0) {
					a = i;
				} else if (count == 1) {
					b = i;
				} else {
					return "impossible";
				}
				count += 1;
			}
		}
		nodes[a].edges.add(nodes[b]);
		nodes[b].edges.add(nodes[a]);

		return str(nodes, N);
	}

	static int fillPQ(PriorityQueue<Integer> pq, Integer[][] lastTime, int i, int N, int lastIndex) {
		while (lastIndex < N && lastTime[lastIndex][1] < i) {
			pq.add(lastTime[lastIndex][0]);
			lastIndex += 1;
		}
		return lastIndex;
	}

	static String str(Node[] nodes, int N) {
		StringBuilder ans = new StringBuilder();
		ans.append(N);
		ans.append('\n');
		for (int i = 0; i < N; i++) {
			PriorityQueue<Node> edges = nodes[i].edges;
			while (edges.size() > 0) {
				ans.append(edges.poll().id);
				if (edges.size() > 0) {
					ans.append(' ');
				}
			}
			if (i < N-1) {
				ans.append('\n');
			}
		}
		return ans.toString();
	}

	static class Node implements Comparable<Node> {
		int id;
		PriorityQueue<Node> edges;

		public Node(int id) {
			this.id = id;
			this.edges = new PriorityQueue<Node>();
		}

		@Override
		public int compareTo(Node that) {
			return this.id - that.id;
		}
	}
}