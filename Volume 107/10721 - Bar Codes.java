import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int M = sc.nextInt();

			long[][] dp = new long[N][K];
			for (int m = 0; m < M; m++) {
				dp[m][0] = 1;
			}

			for (int n = 1; n < N; n++) {
				for (int m = 0; m < M; m++) {
					for (int k = 1; k < K; k++) {
						if (n - m - 1 >= 0) {
							dp[n][k] += dp[n - m - 1][k - 1];
						}
					}
				}
			}

			System.out.println(dp[N - 1][K - 1]);
		}
	}
}