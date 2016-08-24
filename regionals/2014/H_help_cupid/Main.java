import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			String[] vals = in.readLine().split(" ");
			int[] timezones = new int[N];
			int[] paired = new int[N];
			for (int i = 0; i < N; i++) {
				timezones[i] = Integer.parseInt(vals[i]);
				paired[i] = -1;
			}
			Arrays.sort(timezones);

			ArrayList<Tuple> tuples = new ArrayList<Tuple>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					tuples.add(new Tuple(i, j, dist(i, j, timezones)));
				}
			}
			Collections.sort(tuples);

			int ans = 0;
			for (Tuple t : tuples) {
				if (paired[t.i1] == -1 && paired[t.i2] == -1) {
					ans += t.dist;
					paired[t.i1] = t.i2;
					paired[t.i2] = t.i1;
				}
			}

			System.out.println(ans);
		}
	}

	static int dist(int i, int j, int[] timezones) {
		int diff = Math.abs(timezones[i] - timezones[j]);
		return Math.min(diff, 24 - diff);
	}

	static class Tuple implements Comparable<Tuple> {
		int i1, i2, dist;

		public Tuple(int i1, int i2, int dist) {
			this.i1 = i1;
			this.i2 = i2;
			this.dist = dist;
		}

		@Override
		public int compareTo(Tuple that) {
			return this.dist - that.dist;
		}
	}
}