import java.io.*;

class Main {
	static double[][] dp;
	static boolean[][] mk;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int l = Integer.parseInt(vals[0]);
			int s = Integer.parseInt(vals[1]);

			if (l == 0 && s == 0)
				break;

			dp = new double[l+1][l+s+1];
			mk = new boolean[l+1][l+s+1];
			dp[l][s] = 1.0;
			mk[l][s] = true;

			// Expected # of small pills
			double es = 0.0;
			for (int i = 0; i < l+s+1; i++)
				es += getDp(l, s, 0, i) * i;

			// Expected # for last day
			double ed = 0.0;
			for (int i = 0; i < l+s+1; i++)
				ed += getDp(l, s, 0, i) * (2 * l + s - i);

			System.out.printf("%.12f %.12f\n", es, ed);
		}
	}

	static double getDp(int l, int s, int u, int v) {
		if (u < 0 || u > l || v < 0 || v > l+s || u+v > l+s) {
			return 0.0;
		} else if (mk[u][v]) {
			return dp[u][v];
		} else if (u == 0) {
			double p;
			if (u+v == 0)
				p = 1.0;
			else
				p = (u + 1.0) / (u + v);
			dp[u][v] = getDp(l, s, u+1, v-1) * p;
			mk[u][v] = true;
			return dp[u][v];
		} else {
			double pa, pb;
			pa = (v + 1.0) / (u + v + 1.0);
			if (u+v == 0)
				pb = 1.0;
			else
				pb = (u + 1.0) / (u + v);
			double a = getDp(l, s, u+0, v+1) * pa;
			double b = getDp(l, s, u+1, v-1) * pb;
			dp[u][v] = a + b;
			mk[u][v] = true;
			return dp[u][v];
		}
	}
}