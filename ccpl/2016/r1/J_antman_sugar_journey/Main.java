import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int M = sc.nextInt();

			ArrayList<Integer>[] G = new ArrayList[N];
			for (int i = 0; i < N; i++)
				G[i] = new ArrayList<Integer>();

			int[] indegree = new int[N];
			for (int i = 0; i < M; i++) {
				int u = sc.nextInt(), v = sc.nextInt();
				G[u].add(v);
				indegree[v]++;
			}

			int s = -1;
			for (int i = 0; i < N && s == -1; i++)
				if (indegree[i] == 0)
					s = i;
			
			int ans = getAns(G, s, N);
			System.out.println(ans);
		}
	}

	@SuppressWarnings("unchecked")
	static int getAns(ArrayList<Integer>[] G, int s, int N) {
		ArrayList<Integer>[] T = new ArrayList[N];
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++)
			T[i] = new ArrayList<Integer>();
		dfs(G, T, visited, s);
		return leaves(T, s);
	}

	static void dfs(ArrayList<Integer>[] G, ArrayList<Integer>[] T, boolean[] visited, int u) {
		visited[u] = true;
		for (int v : G[u]) {
			if (!visited[v]) {
				T[u].add(v);
				dfs(G, T, visited, v);
			}
		}
	}

	static int leaves(ArrayList<Integer>[] T, int u) {
		if (T[u].size() == 0) {
			return 1;
		} else {
			int ans = 0;
			for (int v : T[u])
				ans += leaves(T, v);
			return ans;
		}
	}
}