import java.util.*;

class Main {
	static int time;
	static ArrayList<Integer[]> ans;
	static int[] pre;
	static int[] low;

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			// Read n & m, if both are 0, is EOF
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == 0 && m == 0) break;
			// Read each graph vertex, and add it
			ArrayList<Integer>[] TC = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				TC[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < m; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				if (TC[u].indexOf(v) == -1) TC[u].add(v);
				if (TC[v].indexOf(u) == -1) TC[v].add(u);
			}
			// Get and print ans
			String ans = getAns(TC, n);
			System.out.println(ans);
		}
	}

	static String getAns(ArrayList<Integer>[] TC, int n) {
		// Initialize variables
		time = 0;
		ans = new ArrayList<Integer[]>();
		pre = new int[n];
		low = new int[n];
		for (int i = 0; i < n; i++) pre[i] = low[i] = -1;
		// Just let it flow
		for (int i = 0; i < n; i++) if (pre[i] == -1) dfs(i, -1, TC);
		// Sort ans
		Collections.sort(ans, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] a, Integer[] b) {
				return (a[0] - b[0] == 0) ? a[1] - b[1] : a[0] - b[0];
			}
		});
		// Build String from ans, and return
		StringBuilder sb = new StringBuilder();
		sb.append(ans.size());
		for (Integer[] w : ans) {
			String pt = String.format(" %d %d", w[0], w[1]);
			sb.append(pt);
		}
		return sb.toString();
	}

	static void dfs(int u, int parent, ArrayList<Integer>[] TC) {
		pre[u] = low[u] = ++time;
		for (int v : TC[u]) {
			if (pre[v] == -1) {
				dfs(v, u, TC);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] == pre[v]) {
					int x = Math.min(u, v);
					int y = Math.max(u, v);
					ans.add(new Integer[] { x, y });
				}
			} else if (v != parent) {
				low[u] = Math.min(low[u], low[v]);
			}
		}
	}

}