import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		int[][] g = new int[31][2];
		g[0][0] =  1; g[0][1] = 0;
		g[1][0] = -1; g[1][1] = 1;
		for (int i = 2; i < 31; i++) {
			// g[i] = g[i-1] * g[1]
			int ac = g[i-1][0] * g[1][0];
			int bd = g[i-1][1] * g[1][1];
			int ad = g[i-1][0] * g[1][1];
			int bc = g[i-1][1] * g[1][0];
			g[i][0] = ac - bd;
			g[i][1] = ad + bc;
		}

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int p = Integer.parseInt(line);
			String bn = Integer.toBinaryString(p);
			
			int[] ans = new int[2];
			for (int i = 0; i < bn.length(); i++) {
				int b = bn.charAt(i) - '0';
				if (b == 1) {
					ans[0] += g[bn.length()-i-1][0];
					ans[1] += g[bn.length()-i-1][1];
				}
			}
			System.out.printf("%d %d\n", ans[0], ans[1]);
		}
	}
}