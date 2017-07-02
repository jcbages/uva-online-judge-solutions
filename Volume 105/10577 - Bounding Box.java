import java.util.*;
import java.awt.geom.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = 1;
		while (true) {
			int n = sc.nextInt();
			if (n == 0) break;

			Point2D[] p = new Point2D[3];
			for (int i = 0; i < p.length; i++) {
				p[i] = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
			}

			Line2D[] l = new Line2D[2];
			for (int i = 0; i < 3; i++) {
				if (Math.abs(p[i].getX() - p[(i+1)%3].getX()) <= 1e-9) {
					l[0] = new Line2D.Double(p[(i+1)%3], p[(i+2)%3]);
					l[1] = new Line2D.Double(p[(i+2)%3], p[i]);
					break;
				}
			}

			if (l[0] == null) {
				l[0] = new Line2D.Double(p[0], p[1]);
				l[1] = new Line2D.Double(p[1], p[2]);
			}

			Point2D[] mid = new Point2D[2];
			for (int i = 0; i < 2; i++) {
				mid[i] = new Point2D.Double(
					(l[i].getX1()+l[i].getX2())/2.0,
					(l[i].getY1()+l[i].getY2())/2.0
				);
			}

			double[] m1 = new double[2];
			double[] m2 = new double[2];
			for (int i = 0; i < 2; i++) {
				m1[i] = (l[i].getY2()-l[i].getY1()) / (l[i].getX2()-l[i].getX1());
				if (Math.abs(m1[i]) <= 1e-9) {
					m2[i] = Double.POSITIVE_INFINITY;
				} else {
					m2[i] = (l[i].getX1()-l[i].getX2()) / (l[i].getY2()-l[i].getY1());
				}
			}

			double[] b = new double[2];
			for (int i = 0; i < 2; i++) {
				if (m2[i] != Double.POSITIVE_INFINITY) {
					b[i] = mid[i].getY() - m2[i]*mid[i].getX();
				}
			}

			Point2D point = null;
			if (m2[0] == Double.POSITIVE_INFINITY) {
				point = new Point2D.Double(mid[0].getX(), m2[1]*mid[0].getX()+b[1]);
			} else if (m2[1] == Double.POSITIVE_INFINITY) {
				point = new Point2D.Double(mid[1].getX(), m2[0]*mid[1].getX()+b[0]);
			} else {
				double x = (b[1]-b[0]) / (m2[0]-m2[1]);
				point = new Point2D.Double(x, m2[0]*x+b[0]);
			}

			Point2D curr = new Point2D.Double(p[0].getX()-point.getX(), p[0].getY()-point.getY());
			double theta = Math.toRadians(360.0 / n);
			double minX = curr.getX(), maxX = curr.getX();
			double minY = curr.getY(), maxY = curr.getY();
			for (int i = 0; i < n; i++) {
				curr = new Point2D.Double(
					curr.getX()*Math.cos(theta) - curr.getY()*Math.sin(theta),
					curr.getX()*Math.sin(theta) + curr.getY()*Math.cos(theta)
				);

				if (curr.getX() < minX) minX = curr.getX();
				if (curr.getX() > maxX) maxX = curr.getX();

				if (curr.getY() < minY) minY = curr.getY();
				if (curr.getY() > maxY) maxY = curr.getY();
			}

			String ans = String.format("%.3f", Math.abs(maxX-minX) * Math.abs(maxY-minY));
			System.out.printf("Polygon %d: %s\n", tc++, ans.replaceAll(",", "."));
		}
	}
}
