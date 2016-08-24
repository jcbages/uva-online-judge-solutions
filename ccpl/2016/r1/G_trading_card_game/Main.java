import java.io.*;
import java.math.BigInteger;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int N = Integer.parseInt(vals[0]);
			int m = Integer.parseInt(vals[1]);
			int k = Integer.parseInt(vals[2]);
			if (N == 0 && m == 0 && k == 0) {
				break;
			}
			if (k == 0 || k > N || m == 0 || m < k) {
				System.out.println("0/1");
			} else if (k == 1) {
				System.out.println("1/1");
			} else {
				BigInteger num = getNum(N, m, k);
				BigInteger den = BigInteger.valueOf(N).pow(m);
				BigInteger gcd = num.gcd(den);
				System.out.printf("%s/%s\n", num.divide(gcd), den.divide(gcd));
			}
		}
	}

	static BigInteger getNum(int N, int m, int k) {
		BigInteger[][] p = new BigInteger[m+1][k+1];
		// Initialize DP
		for (int i = 0; i <= m; i++) {
			p[i][0] = BigInteger.ZERO;
		}
		for (int i = 0; i <= k; i++) {
			p[0][i] = BigInteger.ZERO;
		}
		p[0][0] = BigInteger.ONE;
		// Let the magic run
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= k; j++) {
				BigInteger v1 = p[i-1][j].multiply(BigInteger.valueOf(j));
				BigInteger v2 = p[i-1][j-1].multiply(BigInteger.valueOf(N-(j-1)));
				p[i][j] = v1.add(v2);
			}
		}
		return p[m][k];
	}
}