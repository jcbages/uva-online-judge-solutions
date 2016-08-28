import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt(), M = sc.nextInt();
			
			int[] sumEnergy = new int[N];
			sumEnergy[0] = sc.nextInt();
			for (int i = 1; i < N; i++) {
				sumEnergy[i] = sumEnergy[i-1] + sc.nextInt();
			}

			PriorityQueue<Segment> segments = new PriorityQueue<Segment>(M, new Comparator<Segment>() {
				@Override
				public int compare(Segment a, Segment b) {
					int ds = a.start - b.start;
					int de = a.end - b.end;
					return (ds == 0) ? de : ds;
				}
			});
			for (int i = 0; i < M; i++) {
				int start = sc.nextInt() - 1;
				int strength = sc.nextInt();
				int cost = sc.nextInt();
				int end = findEnd(sumEnergy, start, strength);
				if (end >= start) {
					segments.add(new Segment(start, end + 1, cost));
				}
			}

			PriorityQueue<Segment> acc = new PriorityQueue<Segment>(M, new Comparator<Segment>() {
				@Override
				public int compare(Segment a, Segment b) {
					return a.cost - b.cost;
				}
			});
			while (segments.size() > 0) {
				Segment u = segments.poll();
				if (u.start == 0) {
					acc.add(u);
				} else {
					Segment v = null;
					while (acc.size() > 0 && v == null) {
						v = acc.poll();
						if (v.end < u.start) {
							v = null;
						}
					}

					if (v != null) {
						acc.add(v);
						acc.add(new Segment(v.start, u.end, u.cost + v.cost));
					}
				}
			}

			Segment ans = null;
			while (acc.size() > 0 && ans == null) {
				ans = acc.poll();
				if (ans.start != 0 || ans.end != N) {
					ans = null;
				}
			}

			if (ans == null) {
				System.out.println(-1);
			} else {
				System.out.println(ans.cost);
			}
		}
	} 

	static int findEnd(int[] sumEnergy, int start, int strength) {
		if (start > 0) {
			strength += sumEnergy[start - 1];
		}
		int lo = 0, hi = sumEnergy.length - 1, ans = -1;
		while (lo <= hi) {
			int mid = (lo + hi) >>> 1;
			if (sumEnergy[mid] == strength) {
				// Best possible
				return mid;
			} else if (sumEnergy[mid] < strength) {
				// Can support more
				ans = Math.max(ans, mid);
				lo = mid + 1;
			} else {
				// Its too much :(
				hi = mid - 1;
			}
		}
		return ans;
	}

	static class Segment {
		int start, end, cost;

		public Segment(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
}