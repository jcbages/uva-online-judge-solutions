import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			if (i > 0) {
				System.out.println();
			}
			System.out.printf("Scenario #%d:\n", i + 1);

			int u = sc.nextInt(), n = 2 * u + 1;
			int matrix[][] = new int[n][n];
			for (int x = -u; x <= u; x++) {
				for (int y = -u; y <= u; y++) {
					matrix[x + u][y + u] = Math.abs(x) + Math.abs(y) + u;
				}
			}

			for (int z = -u; z <= u ; z++) {
				System.out.printf("slice #%d:\n", z + u + 1);

				int k = z < 0 ? -1 : 1;
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < n; y++) {
						System.out.print(matrix[x][y] > u ? "." : matrix[x][y]);
						matrix[x][y] += k;
					}
					System.out.println();
				}

			}
		}
	}
}