import java.util.*;
import java.math.BigInteger;

class Main {
	static BigInteger MOD = BigInteger.valueOf(1000000007);

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			// Read test case
			BigInteger C = BigInteger.valueOf(sc.nextInt());
			BigInteger V = BigInteger.valueOf(sc.nextInt());
			int L = sc.nextInt();

			// Process and print test answer
			BigInteger[][] dp = new BigInteger[L][2];
			dp[0][0] = V;
			dp[0][1] = C;
			for (int i = 1; i < L; i++) {
				dp[i][0] = V.multiply(dp[i-1][0].add(dp[i-1][1]));
				dp[i][1] = dp[i-1][0].multiply(C);
			}
			System.out.printf("Case #%d: %d\n", t+1, dp[L-1][0].mod(MOD));
		}
	}
}