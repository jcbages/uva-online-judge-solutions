import java.util.*;
import java.math.BigInteger;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt(), i;
			for (i = 0; i < N; i++) {
				String X = sc.next(), Z = sc.next();
				BigInteger ans = getAns(X, Z);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static BigInteger getAns(String X, String Z) {
		int n = Z.length(), m = X.length(), i, j;
		BigInteger[][] dp = new BigInteger[n+1][m+1];

		/* Init first row */
		for (j = 0; j <= m; j++)
			dp[0][j] = BigInteger.ONE;
		/* Init first col */
		for (i = 1; i <= n; i++)
			dp[i][0] = BigInteger.ZERO;

		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				if (Z.charAt(i-1) == X.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1].add(dp[i][j-1]);
				} else {
					dp[i][j] = dp[i][j-1];
				}
			}
		}
		return dp[n][m];
	}
}