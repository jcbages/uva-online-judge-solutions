import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int M = sc.nextInt();
			for (int i = 0; i < M; i++) {
				int n = sc.nextInt();
				int m = sc.nextInt();
				Sequence[] sequences = new Sequence[m];
				for (int j = 0; j < m; j++) {
					String seq = sc.next();
					sequences[j] = new Sequence(j+1, seq);
				}
				Arrays.sort(sequences);
				for (int j = 0; j < m; j++)
					System.out.println(sequences[j].seq);
				if (i < M - 1)
					System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int getSwaps(String sequence) {
		int ans = 0, n = sequence.length();
		char[] clone = new char[n];
		for (int i = 0; i < n; i++)
			clone[i] = sequence.charAt(i);
		for (int i = n-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (clone[j] > clone[j+1]) {
					char tmp = clone[j];
					clone[j] = clone[j+1];
					clone[j+1] = tmp;
					ans += 1;
				}
			}
		}
		return ans;
	}

	static class Sequence implements Comparable<Sequence> {
		int pos;
		String seq;

		public Sequence(int pos, String seq) {
			this.pos = pos;
			this.seq = seq;
		}

		@Override
		public int compareTo(Sequence that) {
			int swaps1 = getSwaps(this.seq);
			int swaps2 = getSwaps(that.seq);
			if (swaps1 - swaps2 != 0)
				return swaps1 - swaps2;
			else
				return this.pos - that.pos;
		}
	}
}