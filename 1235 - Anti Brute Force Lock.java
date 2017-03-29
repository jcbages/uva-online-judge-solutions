import java.util.Scanner;

class Main {
	final static int[] base = new int[] { 0, 0, 0, 0 };

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[][] keys = new int[N][4];
			for (int i = 0; i < N; i++) {
				String v = sc.next();
				for (int j = 0; j < 4; j++)
					keys[i][j] = v.charAt(j) - '0';
			}

			int[][] G = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = i+1; j < N; j++)
					G[i][j] = G[j][i] = diff(keys[i], keys[j]);

			int s = 0, min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int v = diff(base, keys[i]);
				if (v < min) {
					min = v;
					s = i;
				}
			}

			int ans = min + getAns(G, s, N);
			System.out.println(ans);
		}
	}

	static int diff(int[] ki, int[] kj) {
		int diff = 0;
		for (int k = 0; k < 4; k++) {
			int a = Math.min(ki[k], kj[k]);
			int b = Math.max(ki[k], kj[k]);
			diff += Math.min(b - a, 10 + a - b);
		}
		return diff;
	}

	static int getAns(int[][] G, int s, int N) {
		int[] parent = new int[N];
		int[] key = new int[N];
		boolean[] mstSet = new boolean[N];
		for (int i = 0; i < N; i++)
			key[i] = Integer.MAX_VALUE;
		key[s] = 0;
		parent[s] = -1;

		int ans = 0;
		for (int count = 0; count < N; count++) {
			int u = 0, min = Integer.MAX_VALUE;
			for (int v = 0; v < N; v++) {
				if (!mstSet[v] && key[v] < min) {
					min = key[v];
					u = v;
				}
			}
			mstSet[u] = true;
			ans += min;
			
			for (int v = 0; v < N; v++) {
				if (G[u][v] != 0 && !mstSet[v] && G[u][v] < key[v]) {
					parent[v] = u;
					key[v] = G[u][v];
				}
			}
		}
		return ans;
	}
}