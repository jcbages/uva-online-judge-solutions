import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}
			int[] ans = getAns(nums, N);
			System.out.printf("Case #%d: %d %d\n", t+1, ans[0], ans[1]);
		}
	}

	static int[] getAns(int[] nums, int N) {
		int ans1 = 0;
		for (int i = 0; i < N-1; i++) {
			if (nums[i] > nums[i+1]) {
				ans1 += nums[i] - nums[i+1];
			}
		}

		int rate = 0;
		for (int i = 0; i < N-1; i++) {
			if (nums[i] > nums[i+1]) {
				rate = Math.max(nums[i]-nums[i+1], rate);
			}
		}

		int ans2 = 0;
		for (int i = 0; i < N-1; i++) {
			ans2 += Math.min(rate, nums[i]);
		}

		return new int[] { ans1, ans2 };
	}
}