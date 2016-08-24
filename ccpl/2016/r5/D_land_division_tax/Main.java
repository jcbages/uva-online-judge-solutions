import java.io.*;

class Main {
	static int MAX = 1000000000;
	static int N;
	static int[] areas;
	static int[][] dp;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			if (vals[0].equals("0") && vals[1].equals("0")) {
				break;
			}
			N = Integer.parseInt(vals[0]);
			double F = Double.parseDouble(vals[1]);
			areas = new int[N];
			vals = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				areas[i] = Integer.parseInt(vals[i]);
			}

			if (N == 1) {
				System.out.println("0.00");
				continue;
			}

			if (N == 2) {
				System.out.printf("%.2f\n", (double) Math.max(areas[0], areas[1]));
				continue;
			}

			dp = new int[N][N];

			for (int i = 0; i < N; i++) {
				dp[i][i] = 0;
			}

			for (int level = 1; level < N - 1; level++) {
				int a = 0, b = (a + level) % N, area = 0;
				for (int i = a; i != (b + 1) % N; i = (i + 1) % N) {
					area += areas[i];
				}

				int i = a;
				do {
					dp[a][b] = getBest(a, b, area);
					area -= areas[a];
					a = (a + 1) % N;
					b = (b + 1) % N;
					area += areas[b];
				} while (a != i);
			}

			int total = 0;
			for (int i = 0; i < N; i++) {
				total += areas[i];
			}

			int ans = MAX;
			for (int i = 0; i < N; i++) {
				int area  = 0;
				for (int j = i; (j + 2) % N != i; j = (j + 1) % N) {
					area += areas[j];
					int a = (j + 1) % N, b = (i == 0 ? N - 1 : i - 1);
					int curr = dp[i][j] + Math.max(area, total - area) + dp[a][b];
					ans = Math.min(ans, curr);
				}
			}
			System.out.printf("%.2f\n", F * ans);
		}
	}

	static int getBest(int a, int b, int area) {
		int best = 0, bestDp1 = MAX, bestDp2 = bestDp1, current = 0;
		for (int i = a; i != b; i = (i + 1) % N) {
			current += areas[i];
			int midBest = Math.max(best, area - best);
			int midCurrent = Math.max(current, area - current);
			if (midCurrent + dp[a][i] + dp[(i + 1) % N][b] <= midBest + bestDp1 + bestDp2) {
				best = current;
				bestDp1 = dp[a][i];
				bestDp2 = dp[(i + 1) % N][b];
			}
		}
		bestDp1 = bestDp2 = MAX; current = 0;
		for (int i = b; i != a; i = (i == 0 ? N - 1 : i - 1)) {
			current += areas[i];
			int midBest = Math.max(best, area - best);
			int midCurrent = Math.max(current, area - current);
			if (midCurrent + dp[a][(i == 0) ? N - 1 : i - 1] + dp[i][b] <= midBest + bestDp1 + bestDp2) {
				best = current;
				bestDp1 = dp[a][(i == 0) ? N - 1 : i - 1];
				bestDp2 = dp[i][b];
			}
		}
		return Math.max(best, area - best) + bestDp1 + bestDp2;
	}
}