import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			long N = sc.nextLong(), ans = -1, last = -1;
			for (long i = 1; ans == -1; i++) {
				long sum = (i * (i + 1)) / 2;
				if (sum >= N) break;
				if ((N + sum) % (i + 1) == 0) {
					ans = (N + sum) / (i + 1);
					last = i;
				}
			}

			if (ans == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(N);
				sb.append(" = ");
				for (long i = ans - last; i <= ans; i++) {
					sb.append(i);
					if (i < ans) {
						sb.append(" + ");
					}
				}
				System.out.println(sb);
			}
		}
	}
}