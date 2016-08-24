import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m = sc.nextLong();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(i + 1);
		}
		long[][] nums = new long[sb.length()][5];
		for (int i = 0; i < sb.length(); i++) {
			for (int j = 0; j < 5; j++) {
				if (i + j < sb.length()) {
					String v = sb.substring(i, i + j + 1);
					nums[i][j] = Long.parseLong(v);
				} else {
					nums[i][j] = -1;
				}
			}
		}
		for (int i = 0; i < sb.length(); i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("%5d ", nums[i][j]);
			}
			System.out.println();
		}
		char ans = getAns(nums, m, 2);
		System.out.println(ans);
	}

	static char getAns(long[][] nums, long m, int tries) {
		return 'Y';
	}
}