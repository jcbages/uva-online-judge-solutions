import java.io.*;
import java.awt.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			int K = Integer.parseInt(vals[1]);

			Point[] points = new Point[N];
			for (int i = 0; i < N; i++) {
				vals = in.readLine().split(" ");
				int x = Integer.parseInt(vals[0]);
				int y = Integer.parseInt(vals[1]);
				points[i] = new Point(x, y);
			}

			double[][] dp = new double[K + 1][N];
			for (int j = 1; j < N; j++) {
				dp[0][j] = dp[0][j-1] + points[j].distance(points[j-1]);
			}

			for (int i = 1; i < K + 1; i++) {
				dp[i][i+1] = points[i+1].distance(points[0]);
			}

			for (int i = 1; i < K + 1; i++) {
				for (int j = i+2; j < N; j++) {
					dp[i][j] = Double.POSITIVE_INFINITY;
					for (int ii = i, jj = j-1; ii >= 0 && jj >= 0; ii--, jj--) {
						dp[i][j] = Math.min(dp[i][j], dp[ii][jj] + points[j].distance(points[jj]));
					}
				}
			}

			String ans = String.format("%.3f", dp[K][N-1]).replace(",", ".");
			System.out.println(ans);
		}
	}
}