import java.io.*;
import java.util.*;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(in.readLine());

			Alien[] aliens = new Alien[n];
			for (int j = 0; j < n; j++) {
				String[] vals = in.readLine().split(" ");
				aliens[j] = new Alien(
					Integer.parseInt(vals[0]),
					Integer.parseInt(vals[1]),
					Integer.parseInt(vals[2])
				);
			}
			Arrays.sort(aliens);

			int ans = getAns(n, aliens);
			System.out.println(ans);
		}
	}

	static int getAns(int n, Alien[] aliens) {
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (!aliens[i].k) {
				for (int j = 0; j < n; j++) {
					if (aliens[j].d <= aliens[i].d) {
						aliens[j].k = true;
					}
				}
				ans += aliens[i].d;
			}
		}
		return ans;
	}

	static class Alien implements Comparable<Alien> {
		int a, b, d;
		boolean k;

		public Alien(int a, int b, int d) {
			this.a = a;
			this.b = b;
			this.d = d;
			this.k = false;
		}

		@Override
		public int compareTo(Alien that) {
			int diffB = this.b - that.b;
			int diffD = that.d - this.d;
			return (diffB == 0) ? diffD : diffB;
		}
	}
}