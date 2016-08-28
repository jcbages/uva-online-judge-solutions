import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int n, m, r, c, i, ans;
			String[] wall;
			while(sc.hasNext()) {
				n = sc.nextInt();
				m = sc.nextInt();
				r = sc.nextInt();
				c = sc.nextInt();
				if (n + m + r + c == 0) break;

				wall = new String[n];
				for (i = 0; i < n; i++) {
					wall[i] = sc.next();
				}

				ans = getAns(n, m, r, c, wall);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int getAns(int n, int m, int r, int c, String[] wall) {
		int ans = 0, i, j;
		String blank[] = new String[n];

		for (i = 0; i < n; i++) {
			StringBuilder col = new StringBuilder();
			for (j = 0; j < m; j++) col.append('0');
			blank[i] = col.toString();
		}

		for (i = 0; i < n-r+1; i++) {
			for (j = 0; j < m-c+1; j++) {
				if (blank[i].charAt(j) != wall[i].charAt(j)) {
					paint(r, c, i, j, blank);
					ans++;
				}
			}
		}

		return compare(n, m, wall, blank) ? ans : -1;
	}

	static void paint(int r, int c, int x, int y, String[] blank) {
		int rlim = r+x, clim = c+y, i, j;
		for (i = x; i < rlim; i++) {
			StringBuilder col = new StringBuilder(blank[i]);
			for (j = y; j < clim; j++) {
				char ch = (blank[i].charAt(j) == '1') ? '0' : '1';
				col.replace(j, j+1, ch+"");
			}
			blank[i] = col.toString();
		}
	}

	static boolean compare(int n, int m, String[] wall, String[] blank) {
		int i, j;
		boolean ans = true;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (wall[i].charAt(j) != blank[i].charAt(j)) {
					ans = false;
				}
			}
		}
		return ans;
	}
}