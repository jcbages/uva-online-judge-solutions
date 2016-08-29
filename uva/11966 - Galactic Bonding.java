import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			double D = sc.nextDouble();
			double[][] points = new double[N][2];
			ArrayList<Integer>[] G = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				points[i][0] = sc.nextDouble();
				points[i][1] = sc.nextDouble();
				G[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					double dist = d(points[i], points[j]);
					if (dist <= D || Math.abs(dist) <= 1e-9) {
						G[i].add(j);
					}
				}
			}
			int ans = 0;
			boolean[] marks = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (!marks[i]) {
					dfs(G, marks, i);
					ans++;
				}
			}
			System.out.printf("Case %d: %d\n", t+1, ans);
		}
	}

	static void dfs(ArrayList<Integer>[] G, boolean[] marks, int u) {
		marks[u] = true;
		for (int v : G[u]) {
			if (!marks[v]) {
				dfs(G, marks, v);
			}
		}
	}

	static double d(double[] a, double[] b) {
		double dx = (a[0] - b[0]) * (a[0] - b[0]);
		double dy = (a[1] - b[1]) * (a[1] - b[1]);
		return Math.sqrt(dx + dy);
	}
}