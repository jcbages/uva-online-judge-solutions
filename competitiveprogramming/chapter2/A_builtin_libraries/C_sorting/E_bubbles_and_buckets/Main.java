import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				int N = sc.nextInt();
				if (N == 0) break;
				int[] nums = new int[N];
				for (int i = 0; i < N; i++)
					nums[i] = sc.nextInt();
				int swaps = getSwaps(nums, N);
				if (swaps % 2 != 0)
					System.out.println("Marcelo");
				else
					System.out.println("Carlos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int getSwaps(int[] nums, int N) {
		if (N <= 1) return 0;
		int ans = 0;
		int[] left = new int[N/2];
		for (int i = 0; i < N/2; i++)
			left[i] = nums[i];
		ans += getSwaps(left, N/2);
		int[] right = new int[N-N/2];
		for (int i = 0; i < N-N/2; i++)
			right[i] = nums[i+N/2];
		ans += getSwaps(right, N-N/2);
		ans += join(nums, left, right, N);
		return ans;
	}

	static int join(int[] nums, int[] left, int[] right, int N) {
		int i = 0, j = 0, k = 0, ans = 0;
		while (i < N/2 || j < N-N/2) {
			if (i < N/2 && j < N-N/2) {
				if (left[i] < right[j]) {
					nums[k++] = left[i++];
				} else {
					nums[k++] = right[j++];
					ans += N/2 - i;
				}
			} else if (i < N/2) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = right[j++];
			}
		}
		return ans;
	}
}