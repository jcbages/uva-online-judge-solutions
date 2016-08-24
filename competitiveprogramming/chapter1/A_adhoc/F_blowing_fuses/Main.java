import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int n, m, c, s=1;
			while (sc.hasNext()) {
				n = sc.nextInt();
				m = sc.nextInt();
				c = sc.nextInt();
				if (n + m + c == 0) break;
				if (s > 1) System.out.println("");
				int maxpow = 0, ans = 0, i, op;
				int[] consumption = new int[n];
				int[] status = new int[n];
				for (i = 0; i < n; i++) {
					consumption[i] = sc.nextInt();
					status[i] = -1;
				}
				for (i = 0; i < m; i++) {
					op = sc.nextInt();
					status[op-1] *= -1;
					maxpow += consumption[op-1] * status[op-1];
					ans = Math.max(maxpow, ans);
				}
				System.out.println("Sequence " + s);
				if (ans > c) {
					System.out.println("Fuse was blown.");
				} else {
					System.out.println("Fuse was not blown.");
					System.out.println("Maximal power consumption was " + ans + " amperes.");
				}
				s++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}