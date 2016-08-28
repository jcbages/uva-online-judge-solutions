import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int C = sc.nextInt();
			int P = sc.nextInt();
			ArrayList<Integer>[] G = new ArrayList[C];
			for (int i = 0; i < C; i++) {
				G[i] = new ArrayList<Integer>(C);
			}
			for (int i = 0; i < P; i++) {
				int U = sc.nextInt()-1;
				int V = sc.nextInt()-1;
				int a = Math.min(U, V);
				int b = Math.max(U, V);
				G[b].add(a); // Add in reverse order
			}
			int ans = getAns(G, C);
			System.out.println(ans);
		}
	}

	static int getAns(ArrayList<Integer>[] G, int C) {
		int[][] dp = new int[C][C];
		// Initialize DP matrix
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < C; j++) {
				dp[i][j] = -1;
			}
		}
		// Oh oh oh It's magic... (8)
		int ans = 0;
		for (int i = 0; i < C; i++) {
			for (int j = i+1; j < C; j++) {
				int a = Math.min(i, j);
				int b = Math.max(i, j);
				ans += getDp(a, b, dp, G);
			}
		}
		return ans;
	}

	static int getDp(int i, int j, int[][] dp, ArrayList<Integer>[] G) {
		if (i == 0) {
			return 1;
		} else if (i == j) {
			return 0;
		} else if (dp[i][j] != -1) {
			return dp[i][j];
		} else {
			dp[i][j] = 0;
			for (int k : G[j]) {
				int a = Math.min(i, k);
				int b = Math.max(i, k);
				if (getDp(a, b, dp, G) == 1) {
					dp[i][j] = 1;
					break;
				}		
			}
			return dp[i][j];
		}
	}
}