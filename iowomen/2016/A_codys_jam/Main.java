import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			// Read test case
			int N = 2 * sc.nextInt();
			int[] nums = new int[N];
			boolean[] mark = new boolean[N];
			for (int i = 0; i < N; i++)
				nums[i] = sc.nextInt();

			// Process test case
			for (int i = N-1; i >= 0; i--) {
				if (!mark[i]) {
					int discount = nums[i] - nums[i] / 4;
					for (int j = i-1; j >= 0; j--) {
						if (nums[j] == discount && !mark[j]) {
							mark[j] = true;
							break;
						}
					}
				}
			}

			// Create and print answer
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < N; i++)
				if (mark[i])
					ans.append(" " + nums[i]);
			System.out.printf("Case #%d:%s\n", t+1, ans);
		}
	}
}