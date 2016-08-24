import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// Read test case
			int R = sc.nextInt();
			int C = sc.nextInt();
			int W = sc.nextInt();

			// Get first collision
			boolean[] row = new boolean[C];
			int cnt = 0, mid;
			PriorityQueue<Range> Q = new PriorityQueue<Range>();
			Q.add(new Range(0, C-1));
			while (Q.size() > 0 && Q.peek().diff >= W) {
				Range r = Q.poll();
				mid = (r.r1 + r.r2) >>> 1;
				row[mid] = true;
				if (r.r1 <= mid-1) {
					Q.add(new Range(r.r1, mid-1));
				}
				if (mid+1 <= r.r2) {
					Q.add(new Range(mid+1, r.r2));
				}
				cnt++;
			}

			// If already processed all, print and continue
			if (true || cnt == C) {
				System.out.printf("Case #%d: %d\n", t, cnt * R);
				continue;
			}
		}
	}

	static class Range implements Comparable<Range> {
		int r1, r2, diff;

		public Range(int r1, int r2) {
			this.r1 = r1;
			this.r2 = r2;
			this.diff = Math.abs(r1 - r2) + 1;
		}

		@Override
		public int compareTo(Range that) {
			int val = this.diff - that.diff;
			return (val == 0) ? this.r1 - that.r1 : -val;
		}
	}
}