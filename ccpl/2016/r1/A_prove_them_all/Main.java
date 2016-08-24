import java.util.*;

class Main {
	static int ans;
	static ArrayList<Integer>[] G;
	static boolean[] global, visited, isnotans;

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			G = new ArrayList[m];
			for (int i = 0; i < m; i++) {
				G[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				G[a-1].add(b-1);
			}
			ans = m;
			global = new boolean[m];
			isnotans = new boolean[m];
			for (int i = 0; i < m; i++) {
				if (!global[i]) {
					visited = new boolean[m];
					dfs(i);
					for (int j = 0; j < m; j++) {
						if (i != j && !isnotans[j] && visited[j]) {
							ans--;
							isnotans[j] = true;
						}
					}
				}
			}
			System.out.printf("Case %d: %d\n", t+1, ans);
		}
	}

	static void dfs(int u) {
		visited[u] = global[u] = true;
		for (int v : G[u]) {
			if (!visited[v]) {
				dfs(v);
			}
		}
	}
}