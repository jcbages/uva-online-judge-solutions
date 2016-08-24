import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			Point[] points = new Point[N];
			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				points[i] = new Point(x, y);
			}
			int[] ans = getAns(points, N);
			System.out.printf("Case #%d:\n", t+1);
			for (int i = 0; i < N; i++) {
				System.out.println(ans[i]);
			}
		}
	}

	static int[] getAns(Point[] points, int N) {
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			Point p0 = points[i];
		}
		return ans;
	}

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double angle(Point that) {
			int dx = that.x - this.x;
			int dy = that.y - this.y;
			double ans = Math.toDegrees(Math.atan2(dy, dx)));
			return (ans + 360.0) % 360.0;
		}

		@Override
		public int compareTo(Point that) {
			int cmp = this.angle(that);
		}
	}
}