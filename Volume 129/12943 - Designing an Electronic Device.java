import java.util.Scanner;
import java.math.BigInteger;

class Main {
	static final int MAX = 32;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[] M = new int[N];
			for (int i = 0; i < N; i++) {
				M[i] = sc.nextInt();
			}

			int[][] cost = new int[N][MAX];
			Frac[][] nofail = new Frac[N][MAX];
			Frac[][] dp = new Frac[N+1][K+1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M[i]; j++) {
					int x = sc.nextInt();
					int y = sc.nextInt();
					int z = sc.nextInt();
					nofail[i][j] = new Frac(y-x, y);
					cost[i][j] = z;
				}
			}

			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= K; j++) {
					dp[i][j] = new Frac(0, 1);
				}
			}

			dp[0][0] = new Frac(1, 1);
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= K; j++) {
					for (int k = 0; k < M[i-1]; k++) {
						if (j - cost[i-1][k] >= 0) {
							Frac p = mul(dp[i-1][j-cost[i-1][k]], nofail[i-1][k]);
							if (less(dp[i][j], p)) {
								dp[i][j] = new Frac(p.x, p.y);
							}
						}
					}
				}
			}

			Frac ret = new Frac(0, 1);
			for (int i = 0; i <= K; i++) {
				if (less(ret, dp[N][i])) {
					ret = new Frac(dp[N][i].x, dp[N][i].y);
				}
			}
			Frac ans = new Frac(ret.y - ret.x, ret.y);
			System.out.printf("%d/%d\n", ans.x, ans.y);
		}
	}

	static long gcd(long x, long y) {
		long t;
		while (x % y != 0) {
			t = x;
			x = y;
			y = t % y;
		}
		return y;
	}

	static Frac mul(Frac x, Frac y) {
		return new Frac(x.x * y.x, x.y * y.y);
	}

	static boolean less(Frac x, Frac y) {
    	double a = 1.0 * x.x / x.y;
    	double b = 1.0 * y.x / y.y;
    	return (Math.abs(a - b) < 1e-9) ? false : a < b;
	}

	static class Frac {
		long x, y;

		public Frac(long x, long y) {
			this.x = x;
			this.y = y;
			normal();
		}

		void normal() {
			if (y < 0) {
				x = -x;
				y = -y;
			}
			long common = gcd(x, y);
			x /= common;
			y /= common;
			if (y < 0) {
				x = -x;
				y = -y;
			}
		}
	}
}