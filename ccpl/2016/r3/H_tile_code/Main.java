import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			int[] dp1 = new int[n]; // 2x1 tiles
			dp1[0] = 1; dp1[1] = 3;
			int[] dp2 = new int[n]; // 1x2 tiles
			dp2[0] = 0; dp2[1] = 1;
			int[] dp3 = new int[n]; // 2x2 tiles
			dp3[0] = 0; dp3[1] = 1;
			for (int i = 2; i < n; i++) {
				dp1[i] = dp1[i-1] + dp2[i-2] + dp3[i-2];
				dp2[i] = dp2[i-2] + dp3[i-2];
				dp3[i] = dp3[i-2];
			}
			System.out.println(dp1[n-1] + dp2[n-1] + dp3[n-1]);
		}
	}
}