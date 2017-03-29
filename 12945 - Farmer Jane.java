import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt();
			int[][] trees = new int[N][3];
			for (int i = 0; i < N; i++) {
				trees[i][0] = sc.nextInt(); // x
				trees[i][1] = sc.nextInt(); // y
				trees[i][2] = sc.nextInt(); // w
			}
			double ans = getAns(trees, N);
			System.out.printf("%.3f\n", ans);
		}
	}

	static double getAns(int[][] trees, int N) {
		/* Get x & y */
		double x = 0.0;
		double y = 0.0;
		double w = 0.0;
		for (int i = 0; i < N; i++) {
			x += trees[i][2] * trees[i][0];
			y += trees[i][2] * trees[i][1];
			w += trees[i][2];
		}
		x /= w;
		y /= w;
		/* Calculate and return total cost */
		double ans = 0.0;
		for (int[] tree : trees) {
			ans += tree[2] * (Math.pow(tree[0] - x, 2) + Math.pow(tree[1] - y, 2));
		}
		return ans;
	}
}
