import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = sc.nextInt();
			}
			int ans = getAns(nums, n);
			System.out.println(ans);
		}
	}

	static int getAns(int[] nums, int n) {
		// Base case
		if (n == 1) {
			return 1;
		}
		// Count sequences
		ArrayList<Integer> sums = new ArrayList<Integer>();
		int count = 1;
		for (int i = 1; i < n; i++) {
			if (nums[i-1] <= nums[i]) {
				count += 1;
			} else {
				sums.add(count);
				count = 1;
			}
		}
		sums.add(count);
		// Get biggets 2-sum
		if (sums.size() == 1) {
			return sums.get(0);
		}
		int ans = 0;
		for (int i = 0; i < sums.size()-1; i++) {
			ans = Math.max(sums.get(i) + sums.get(i+1), ans);
		}
		return ans;
	}
}