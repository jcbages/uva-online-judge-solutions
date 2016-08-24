import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int R = sc.nextInt(), C = sc.nextInt();
			int[][] poly = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					poly[i][j] = sc.nextInt();
				}
			}
			long ans = getAns(poly, R, C);
			System.out.println(ans);
		}
	}

	static long getAns(int[][] p, int R, int C) {
		/* Base answer */
		long ans = 5;

		/* dfs for top */
		boolean[][] marks = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!marks[i][j]) {
					ans += 1;
					bfs(marks, p, R, C, i, j);
				}
			}
		}

		/* From i=1 to i=R-1 */
		for (int i = 1; i <= R-1; i++) {
			int lo = 0, hi = 0;
			for (int j = 0; j < C; j++) {
				if (p[i][j] > p[i-1][j]) {
					if (p[i][j] <= lo || p[i-1][j] >= hi) ans += 1;
					lo = p[i-1][j];
					hi = p[i][j];
				} else {
					lo = 0;
					hi = 0;
				}
			}
		}

		/* From i=R-2 to i=0 */
		for (int i = R-2; i >= 0; i--) {
			int lo = 0, hi = 0;
			for (int j = 0; j < C; j++) {
				if (p[i][j] > p[i+1][j]) {
					if (p[i][j] <= lo || p[i+1][j] >= hi) ans += 1;
					lo = p[i+1][j];
					hi = p[i][j];
				} else {
					lo = 0;
					hi = 0;
				}
			}
		}

		/* From j=1 to j=C-1 */
		for (int j = 1; j <= C-1; j++) {
			int lo = 0, hi = 0;
			for (int i = 0; i < R; i++) {
				if (p[i][j] > p[i][j-1]) {
					if (p[i][j] <= lo || p[i][j-1] >= hi) ans += 1;
					lo = p[i][j-1];
					hi = p[i][j];
				} else {
					lo = 0;
					hi = 0;
				}
			}
		}

		/* From j=C-2 to j=0 */
		for (int j = C-2; j >= 0; j--) {
			int lo = 0, hi = 0;
			for (int i = 0; i < R; i++) {
				if (p[i][j] > p[i][j+1]) {
					if (p[i][j] <= lo || p[i][j+1] >= hi) ans += 1;
					lo = p[i][j+1];
					hi = p[i][j];
				} else {
					lo = 0;
					hi = 0;
				}
			}
		}

		return ans;
	}

	static void bfs(boolean[][] marks, int[][] p, int R, int C, int i, int j) {
		Queue<Pair> Q = new LinkedList<Pair>();
		Q.add(new Pair(i, j));
		marks[i][j] = true;
		while (Q.size() > 0) {
			Pair x = Q.poll();
			if (x.i-1 >= 0 && !marks[x.i-1][x.j] && p[x.i-1][x.j] == p[x.i][x.j]) { marks[x.i-1][x.j] = true; Q.add(new Pair(x.i-1, x.j)); }
			if (x.i+1 <  R && !marks[x.i+1][x.j] && p[x.i+1][x.j] == p[x.i][x.j]) { marks[x.i+1][x.j] = true; Q.add(new Pair(x.i+1, x.j)); }
			if (x.j-1 >= 0 && !marks[x.i][x.j-1] && p[x.i][x.j-1] == p[x.i][x.j]) { marks[x.i][x.j-1] = true; Q.add(new Pair(x.i, x.j-1)); }
			if (x.j+1 <  C && !marks[x.i][x.j+1] && p[x.i][x.j+1] == p[x.i][x.j]) { marks[x.i][x.j+1] = true; Q.add(new Pair(x.i, x.j+1)); }
		}
	}

	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}