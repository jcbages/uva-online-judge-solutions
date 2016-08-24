import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 0, T = sc.nextInt(); t < T; t++) {
			int N = sc.nextInt();
			int[] bffs = new int[N];
			for (int i = 0; i < N; i++)
				bffs[i] = sc.nextInt() - 1;
			int ans = getAns(bffs, N);
			System.out.printf("Case #%d: %d\n", t+1, ans);
		}
	}

	static int getAns(int[] bffs, int N) {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			dfs(i, bffs, visited);
			int count = 0;
			for (int j = 0; j < N; j++)
				if (visited[j])
					count++;
			ans = Math.max(ans, count);
		}
		return ans;
	}

	static void dfs(int u, int[] bffs, boolean[] visited) {
		visited[u] = true;
		if (!visited[bffs[u]])
			dfs(bffs[u], bffs, visited);
	}
}