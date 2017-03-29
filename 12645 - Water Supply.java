import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] G = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				G[a][b] = 1;
			}
			int ans = getAns(G, N);
			System.out.println(ans);
		}
	}

	static int getAns(int[][] G, int N) {
		boolean[] visited;
		visited = new boolean[N+1];
		dfs(G, -1, 0, N, visited);
		int ans = 0;
		for (int i = 0; i < N+1; i++) {
			if (!visited[i]) {
				ans += 1;
			}
		}
		for (int i = 1; i < N+1; i++) {
			G[0][i] = !visited[i] ? 1 : 0;
		}
		visited = new boolean[N+1];
		visited[0] = true;
		for (int i = 0; i < N+1; i++) {
			if (G[0][i] == 1 && !visited[i]) {
				dfs(G, i, i, N, visited);
			}
		}
		for (int i = 0; i < N+1; i++) {
			if (G[0][i] == 1 && visited[i]) {
				ans -= 1;
			}
		}
		return ans;
	}

	static void dfs(int[][] G, int o, int s, int N, boolean[] visited) {
		if (s != o) {
			visited[s] = true;
		}
		for (int i = 0; i < N+1; i++) {
			if (G[s][i] == 1 && !visited[i]) {
				dfs(G, o, i, N, visited);
			}
		}
	}
}