import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int ans = 1080, sum = 0;
			int[] nums = new int[4];
			for (int i = 0; i < 4; i++) {
				nums[i] = sc.nextInt();
				sum += nums[i];
			}
			if (sum == 0) {
				break;
			}

			ans += dist(nums[1], nums[0]);
			ans += dist(nums[1], nums[2]);
			ans += dist(nums[3], nums[2]);
			System.out.println(ans);
		}
	}

	static int dist(int a, int b) {
		int sum = 0;
		for (int i = a; i != b; i = (i + 1) % 40, sum++);
		return sum * 9;
	}
}