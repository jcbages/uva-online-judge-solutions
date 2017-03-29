import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			// Read graph
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == 0 && m == 0) {
				break;
			}
			ArrayList<Integer>[] G = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				G[i] = new ArrayList<Integer>();
			}
			int[] in = new int[n];
			for (int i = 0; i < m; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				G[a].add(b);
				in[b] += 1;
			}
			// Initialize variables
			boolean[] visited = new boolean[n];
			boolean[] stack = new boolean[n];
			Set<Integer>[] acc = new HashSet[n];
			for (int i = 0; i < n; i++) {
				acc[i] = new HashSet<Integer>();
			}
			// Process answer
			boolean res = false;
			for (int i = 0; i < n && !res; i++) {
				if (in[i] == 0) {
					res = res || dfs(i, G, visited, stack, acc);
				}
			}
			for (int i = 0; i < n && !res; i++) {
				if (!visited[i]) {
					res = true;
				}
			}
			// Print answer
			int ans = res ? 0 : 1;
			System.out.println(ans);
		}
	}

	static boolean dfs(int u, ArrayList<Integer>[] G, boolean[] visited, boolean[] stack, Set<Integer>[] acc) {
		visited[u] = true;
		stack[u] = true;
		for (int v : G[u]) {
			if (stack[v]) {
				return true;
			} else if (!visited[v] && dfs(v, G, visited, stack, acc)) {
				return true;
			}
			acc[u].addAll(acc[v]);
			if (acc[u].size() > 1) {
				return true;
			}
		}
		if (G[u].size() == 0) {
			acc[u].add(u);
		}
		stack[u] = false;
		return acc[u].size() > 1;
	}
}