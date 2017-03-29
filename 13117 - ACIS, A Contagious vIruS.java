import java.io.*;
import java.awt.Point;
import java.awt.geom.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while (!(line = in.readLine()).contains("*")) {
			// read data
			int N = Integer.parseInt(line);
			Point center = readPoint(in);
			Point[] polygon = new Point[N+1];
			for (int i = 0; i < N; i++) {
				polygon[i] = readPoint(in);
			}
			polygon[N] = polygon[0];
			// get answer
			double ans = Double.POSITIVE_INFINITY;
			for (int i = 0; i < N; i++) {
				ans = Math.min(ans, center.distance(polygon[i]));
				Line2D.Double seg = new Line2D.Double(polygon[i], polygon[i+1]);
				ans = Math.min(ans, seg.ptSegDist(center));
			}
			String print = String.format("%.3f", ans).replace(",", ".");
			System.out.println(print);
		}
	}

	static Point readPoint(BufferedReader in) throws IOException {
		String[] vals = in.readLine().split(" ");
		int x = Integer.parseInt(vals[0]);
		int y = Integer.parseInt(vals[1]);
		return new Point(x, y);
	}
}