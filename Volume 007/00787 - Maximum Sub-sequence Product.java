import java.util.*;
import java.math.BigInteger;

class Main {
	public static void main(String... args) {
		// Initialize I/O readers
		Scanner sc = new Scanner(System.in);
		
		// Read until EOF
		while (sc.hasNextInt()) {
			// Build the sequence until a -999999 (not included)
			ArrayList<BigInteger> sequence = new ArrayList<BigInteger>();
			for (int n = sc.nextInt(); n != -999999; n = sc.nextInt()) {
				sequence.add(BigInteger.valueOf(n));
			}

			// Initialize de dp matrix for subsequences of size = 1
			BigInteger[][] dp = new BigInteger[sequence.size()][sequence.size()];
			for (int j = 0; j < sequence.size(); j++) {
				dp[0][j] = sequence.get(j);
			}

			// Build the dp matrix for each subsequence size
			for (int i = 1; i < sequence.size(); i++) {
				for (int j = i; j < sequence.size(); j++) {
					dp[i][j] = dp[i-1][j-1].multiply(sequence.get(j));
				}
			}

			// Get & print the answer as the maximum value in the dp matrix
			BigInteger ans = sequence.get(0);
			for (int i = 0; i < sequence.size(); i++) {
				for (int j = i; j < sequence.size(); j++) {
					ans = ans.compareTo(dp[i][j]) > 0 ? ans : dp[i][j];
				}
			}
			System.out.println(ans);
		}
	}
}