import java.util.Scanner;

class Main {
	public static void main(String... args) {
		int seq = 1;
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (seq > 1) {
				System.out.println();
			}
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			int c = sc.nextInt();
			if (n + m + c == 0) {
				break;
			}

			int[] devices = new int[n];
			boolean[] used = new boolean[n];
			for (int i = 0; i < n; i++) {
				devices[i] = sc.nextInt();
			}

			long curr = 0, ans = 0;
			for (int i = 0; i < m; i++) {
				int query = sc.nextInt() - 1;
				curr += used[query] ? -devices[query] : devices[query];
				used[query] = !used[query];
				ans = Math.max(ans, curr);
			}
			
			System.out.printf("Sequence %d\n", seq++);
			if (ans > c) {
				System.out.println("Fuse was blown.");
			} else {
				System.out.println("Fuse was not blown.");
				System.out.printf("Maximal power consumption was %d amperes.\n", ans);
			}
		}
	}
}