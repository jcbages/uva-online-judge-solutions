import java.util.Scanner;

class Main {
	static final int N = 7;
	static final int M = 6;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int[][] vals = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					vals[i][j] = sc.nextInt();
				}
			}

			String ans = getAns(vals);
			System.out.println(ans);
		}
	}

	static String getAns(int[][] vals) {
		boolean[] used = new boolean[N];
		for (int i = 0; i < N; i++) {
			used[i] = true;
			if (tryGames(used, vals, i, -1, -1, 0)) {
				return "YES";
			}
			used[i] = false;
		}
		return "NO";
	}

	static boolean tryGames(boolean[] used, int[][] vals, int c, int z, int p, int n) {
		if (n == M) {
			return match(vals, c, p, z, n);
		} else {
			for (int i = 0; i < N; i++) {
				if (!used[i]) {
					int ct = indexOf(vals[i], vals[c][n]);
					if (p == -1 || match(vals, c, p, i, n)) {
						used[i] = true;
						z = (n == 0) ? i : z;
						if (tryGames(used, vals, c, z, i, n+1)) {
							return true;
						}
						used[i] = false;
					}
				}
			}
			return false;
		}
	}

	static int indexOf(int[] arr, int n) {
		for (int i = 0; i < M; i++) {
			if (arr[i] == n) {
				return i;
			}
		}
		return -1;
	}

	static boolean match(int[][] vals, int c, int p, int i, int n) {
		int ctp = indexOf(vals[p], vals[c][n-1]);
		int cti = indexOf(vals[i], vals[c][n%M]);
		int left = (ctp == 0) ? M-1 : ctp-1;
		int right = (cti == M-1) ? 0 : cti+1;
		return vals[p][left] == vals[i][right];
	}
}