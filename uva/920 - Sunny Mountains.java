import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		for (int c = 0; c < C; c++) {
			int N = sc.nextInt();
			Point[] points = new Point[N];
			for (int n = 0; n < N; n++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				points[n] = new Point(x, y);
			}
			Arrays.sort(points);

			double ans = 0.0;
			int maxY = 0;
			for (int i = 1; i < N; i++) {
				Point p1 = points[i], p2 = points[i - 1];
				if (p1.y > maxY) {
					ans += len(p1, p2, maxY);
					maxY = p1.y;
				}
			}

			System.out.printf("%.2f\n", ans);
		}
	}

	static double len(Point p1, Point p2, int maxY) {
		double m = (double) (p1.y - p2.y) / (p1.x - p2.x);
		double b = p1.y - m * p1.x;
		double maxX = (maxY - b) / m;

		double c1 = p1.y - maxY;
		double c2 = maxX - p1.x;
		return Math.sqrt(c1 * c1 + c2 * c2);
	}

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x; this.y = y;
		}

		@Override
		public int compareTo(Point that) {
			return that.x - this.x;
		}

		@Override
		public String toString() {
			return "(" + this.x + ", " + this.y + ")";
		}
	}
}