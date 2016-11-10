import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int N = sc.nextInt(), M = sc.nextInt();
			if (N + M == 0) break;

			Set<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < N; i++) {
				set.add(sc.nextInt());
			}

			int ans = 0;
			for (int i = 0; i < M; i++) {
				if (set.contains(sc.nextInt())) {
					ans++;
				}
			}

			System.out.println(ans);
		}
	}
}