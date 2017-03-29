import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			if (N == 0 && K == 0) {
				break;
			}
			int[][] dp = new int[K][N + 1];
			for (int n = 0; n < N + 1; n++) {
				dp[0][n] = 1;
			}
			for (int k = 1; k < K; k++) {
				for (int n = 0; n < N + 1; n++) {
					for (int m = 0; m < N + 1; m++) {
						if (n - m >= 0 && n - m <= n) {
							dp[k][n] += dp[k - 1][m];
							dp[k][n] %= 1000000;
						}
					}
				}
			}
			System.out.println(dp[K - 1][N]);
		}
	}
}
