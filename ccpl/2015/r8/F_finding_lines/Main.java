import java.io.*;
import java.util.*;

public class Main {
	final static int ITERS = 30;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line);
			int p = Integer.parseInt(in.readLine());

			int[] x = new int[n];
			int[] y = new int[n];
			for (int i = 0; i < n; i++) {
				String[] vals = in.readLine().split("[ \t]");
				x[i] = Integer.parseInt(vals[0]);
				y[i] = Integer.parseInt(vals[1]);
			}

			String ans = getAns(n, p, x, y);
			System.out.println(ans);
		}
	}

	static String getAns(int n, int p, int[] x, int[] y) {
		int ans = 0;
		double actual, slope;
		double[] map = new double[n];

		// Check base case.
		if (n <= 2) {
			return "possible";
		} else {

			// Check y=mx+b lines.
			for (int u = 0; u < ITERS; u++) {
				int i = (int) (Math.random() * 1000000000) % n;
				for (int j = 0; j < n; j++) {
					if (i != j && x[i] != x[j]) {
						map[j] = (0.0 + y[j] - y[i]) / (x[j] - x[i]);
					} else {
						map[j] = Double.NaN;
					}
				}

				Arrays.sort(map);
				ans = Math.max(getMaxSlope(map, n), ans);
			}

			// Check vertical lines.
			Arrays.sort(x);
			ans = Math.max(getMaxVertical(x, n), ans);

			return (ans * 100 >= p * n) ? "possible" : "impossible";
		}
	}

	static int getMaxSlope(double[] map, int n) {
		int ans = 0, count = 0;
		double current = map[0];
		for (double s : map) {
			if (Double.isNaN(current)) {
				current = s;
				count = (Double.isNaN(current)) ? 0 : 1;
			} else if (current - s == 0) {
				count++;
			} else {
				current = s;
				ans = Math.max(count, ans);
				count = 1;
			}
		}
		ans = Math.max(count, ans);
		return ans + 1;
	}

	static int getMaxVertical(int[] x, int n) {
		int ans = 0, count = 0, current = x[0];
		for (int xi : x) {
			if (current == xi) {
				count++;
			} else {
				current = xi;
				ans = Math.max(count, ans);
				count = 1;
			}
		}
		ans = Math.max(count, ans);
		return ans;
	}

	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
}