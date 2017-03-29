import java.util.*;

class Main {
	final static int LIM = 128;

	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int[] cost = new int[LIM];
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int ans = 0;
				int K = sc.nextInt();
				for (int j = 0; j < LIM; j++)
					cost[j] = -1;
				for (int j = 0; j < K; j++) {
					int key = (int) sc.next().charAt(0);
					int val = sc.nextInt();
					cost[key] = val;
				}
				int M = sc.nextInt();
				sc.nextLine();
				for (int j = 0; j < M; j++) {
					String line = sc.nextLine();
					for (int k = 0; k < line.length(); k++) {
						int c = (int) line.charAt(k);
						if (c < 0 || c >= LIM || cost[c] < 0)
							ans += 0;
						else
							ans += cost[c];
					}
				}
				String res = "";
				if (ans % 100 < 10)
					res = String.format("%d.0%d$", ans / 100, ans % 100);
				else
					res = String.format("%d.%d$",  ans / 100, ans % 100);
				System.out.println(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}