import java.util.*;

class Main {
	final static int MAX = 11;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 0, T = sc.nextInt(); t < T; t++) {
			int x = sc.nextInt(), y = sc.nextInt();
			int[][] coords = new int[MAX][2];
			coords[0][0] = sc.nextInt();
			coords[0][1] = sc.nextInt();

			int n = sc.nextInt();
			for (int i = 1; i <= n; i++) {
				coords[i][0] = sc.nextInt();
				coords[i][1] = sc.nextInt();
			}

			int[][] G = new int[n+1][n+1];
			for (int u = 0; u < n+1; u++) {
				for (int v = u+1; v < n+1; v++) {
					int dx = Math.abs(coords[u][0] - coords[v][0]);
					int dy = Math.abs(coords[u][1] - coords[v][1]);
					G[u][v] = G[v][u] = dx + dy;
				}
			}

			boolean[] visited = new boolean[n+1];
			int ans = getAns(G, 0, n, 0, 0, visited);
			System.out.printf("The shortest path has length %d\n", ans);
		}
	}

	static int getAns(int[][] G, int cnt, int n, int u, int acc, boolean[] visited) {
		if (cnt == n) {
			return acc + G[u][0];
		}
		visited[u] = true;
		int ans = Integer.MAX_VALUE;
		for (int v = 0; v < n+1; v++) {
			if (!visited[v]) {
				int val = getAns(G, cnt+1, n, v, acc+G[u][v], visited);
				ans = Math.min(val, ans);
			}
		}
		visited[u] = false;
		return ans;
	}
}