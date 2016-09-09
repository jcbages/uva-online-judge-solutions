import java.util.Scanner;

class Main {
	static int[][] matrix, dp;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {	
			int N = sc.nextInt();
			matrix = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < i + 1; j++) {
					matrix[i][j] = sc.nextInt();
					dp[i][j] = i == N - 1 ? matrix[i][j] : -1;
				}
			}
			int ans = matrix[0][0] + Math.max(getDp(1, 0), getDp(1, 1));
			System.out.println(ans);
		}
	}

	static int getDp(int i, int j) {
		if (dp[i][j] == -1) {
			dp[i][j] = matrix[i][j] + Math.max(getDp(i + 1, j), getDp(i + 1, j + 1));
		}
		return dp[i][j];
	}
}