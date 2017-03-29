import java.io.*;
import java.util.*;

class Main {
	final static long INF = 100000000L;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int P = Integer.parseInt(vals[0]);
			int V = Integer.parseInt(vals[1]);
			int E = Integer.parseInt(vals[2]);
			if (P == 0 && V == 0 && E == 0) {
				break;
			}
			Superpower[] superpowers = new Superpower[P];
			for (int i = 0; i < P; i++) {
				vals = in.readLine().split(" ");
				String s = vals[0];
				int a = Integer.parseInt(vals[1]);
				int c = Integer.parseInt(vals[2]);
				superpowers[i] = new Superpower(s, a, c);
			}
			Villain[] villains = new Villain[V];
			for (int i = 0; i < V; i++) {
				vals = in.readLine().split(" ");
				String s = vals[0];
				int d = Integer.parseInt(vals[1]);
				String[] powers = vals[2].split(",");
				villains[i] = new Villain(s, d, powers);
			}
			if (P < V) {
				System.out.println("No");
				continue;
			}
			long[][] dp = new long[P + 1][V + 1];
			for (int j = 1; j <= V; j++) { // no powers left
				dp[0][j] = INF;
			}
			for (int i = 0; i <= P; i++) { // no villains left
				dp[i][0] = 0;
			}
			for (int i = 1; i <= P; i++) {
				for (int j = 1; j <= V; j++) {
					if (villains[j-1].superpowers.contains(superpowers[i-1].s) &&
					villains[j-1].d <= superpowers[i-1].a) {
						dp[i][j] = Math.min(superpowers[i-1].c + dp[i-1][j-1], dp[i-1][j]);
					} else {
						dp[i][j] = dp[i-1][j];
					}
				}
			}
			System.out.println(dp[P][V] <= E ? "Yes" : "No");
		}
	}

	static class Superpower {
		String s;
		int a, c;

		public Superpower(String s, int a, int c) {
			this.s = s;
			this.a = a;
			this.c = c;
		}
	}

	static class Villain {
		String s;
		int d;
		Set<String> superpowers;

		public Villain(String s, int d, String[] superpowers) {
			this.s = s;
			this.d = d;
			this.superpowers = new HashSet<String>();
			for (String power : superpowers) {
				this.superpowers.add(power);
			}
		}
	}
}