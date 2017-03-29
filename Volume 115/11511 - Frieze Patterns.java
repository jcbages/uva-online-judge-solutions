import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N;
		while ((N = sc.nextInt()) != 0) {
			int i = sc.nextInt() - 1;
			int j = (sc.nextInt() - 1) % (N + 1);
			int[][] matrix = new int[N][j + 1];
			for (int y = 0; y < j + 1; y++) {
				matrix[0][y] = matrix[N - 1][y] = 1;
			}
			for (int x = 0; x < N; x++) {
				matrix[x][0] = sc.nextInt();
			}
			for (int y = 1; y < j + 1; y++) {
				for (int x = 1; x < N - 1; x++) {
					matrix[x][y] = (matrix[x-1][y] * matrix[x+1][y-1] + 1) / matrix[x][y-1];
				}
			}
			System.out.println(matrix[i][j]);
		}
	}
}