import java.io.*;

class Main {
	final static long MOD = Long.parseLong("9999959999");

	static long[][] dp;

	public static void main(String... args) throws IOException {
		dp = new long[1001][2002];
		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 2002; j++) {
				dp[i][j] = -1;
			}
		}

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int l = Integer.parseInt(vals[0]);
			int s = Integer.parseInt(vals[1]);
			if (l == 0 && s == 0) break;
			System.out.println(getDp(l, s));
		}
	}

	static long getDp(int u, int v) {
		if (dp[u][v] != -1) {
			return dp[u][v];
		} else if (u == 0 && v == 0) {
			return 1;
		} else if (v == 0) {
			dp[u][v] = (getDp(u-1, v+1) + 1) % MOD;
			return dp[u][v];
		} else if (u == 0) {
			dp[u][v] = (getDp(u, v-1) + 1) % MOD;
			return dp[u][v];
		} else {
			dp[u][v] = ((getDp(u-1, v+1) + getDp(u, v-1)) % MOD + 1) % MOD;
			return dp[u][v];
		}
	}
}