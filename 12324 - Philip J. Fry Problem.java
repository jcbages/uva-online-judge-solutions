import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while ((n = sc.nextInt()) != 0) {
			Mission[] missions = new Mission[n];
			for (int i = 0; i < n; i++) {
				int t = sc.nextInt();
				int b = sc.nextInt();
				missions[i] = new Mission(t, b);
			}

			for (int i = n - 1; i >= 0; i--) {
				Mission m1 = missions[i];
				if (m1.b > 0) {
					ArrayList<Mission> tmp = new ArrayList<Mission>();
					for (int j = i + 1; j < n; j++) {
						Mission m2 = missions[j];
						if (!m2.used) {
							tmp.add(m2);
						}
					}
					Collections.sort(tmp);
					for (int j = 0, tot = Math.min(tmp.size(), m1.b); j < tot; j++) {
						tmp.get(j).used = true;
					}
				}
			}

			long total = 0;
			for (Mission m : missions) {
				total += m.used ? m.t / 2 : m.t;
			}
			System.out.println(total);
		}
	}

	static class Mission implements Comparable<Mission> {
		int t, b;
		boolean used;

		public Mission(int t, int b) {
			this.t = t;
			this.b = b;
			this.used = false;
		}

		@Override
		public int compareTo(Mission that) {
			return that.t - this.t;
		}
	}
}