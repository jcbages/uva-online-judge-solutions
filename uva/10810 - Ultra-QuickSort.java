import java.util.*;

class Main {
	static long ans;

	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				int n = sc.nextInt();
				if (n == 0) break;
				int[] nums = new int[n];
				for (int i = 0; i < n; i++)
					nums[i] = sc.nextInt();
				ans = 0;
				getSwaps(nums, n);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static void getSwaps(int[] nums, int n) {
		if (n <= 1) return;
		int[] left  = new int[n/2];
		int[] right = new int[n-n/2];
		for (int i = 0; i < n/2; i++)   left[i]  = nums[i];
		for (int i = 0; i < n-n/2; i++) right[i] = nums[i+n/2];
		getSwaps(left, n/2);
		getSwaps(right, n-n/2);
		join(nums, left, right, n);
	}

	static void join(int nums[], int left[], int right[], int n) {
		int i = 0, j = 0, k = 0;
		while (i < n/2 || j < n-n/2) {
			if (i < n/2 && j < n-n/2) {
				if (left[i] < right[j]) {
					nums[k++] = left[i++];
				} else {
					nums[k++] = right[j++];
					ans += n/2 - i;
				}
			} else if (i < n/2) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = right[j++];
			}
		}
	}
}