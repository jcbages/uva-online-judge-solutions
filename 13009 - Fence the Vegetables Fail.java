import java.io.*;
import java.util.*;

class Main {
	final static int FENCE = 1, PLANT = 2;

	static long[] B1, B2;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int P = Integer.parseInt(vals[0]);
			int V = Integer.parseInt(vals[1]);
			ArrayList<Segment> segments = new ArrayList<Segment>();
			Set<Integer> yvalues = new HashSet<Integer>();
			
			// Add plants as horizontal segments
			for (int i = 0; i < P; i++) {
				vals = in.readLine().split(" ");
				int x = Integer.parseInt(vals[0]);
				int y = Integer.parseInt(vals[1]);
				segments.add(new Segment(x, y, y, i+1, PLANT));
				yvalues.add(y);
			}

			// Add fence's vertical segments vertices
			int prevX = -1, prevY = -1;
			int firstX = -1, firstY = -1;
			int lastX = -1, lastY = -1;
			for (int i = 0; i < V; i++) {
				vals = in.readLine().split(" ");
				int x = Integer.parseInt(vals[0]);
				int y = Integer.parseInt(vals[1]);
				if (prevX == x) {
					int y1 = Math.min(prevY, y);
					int y2 = Math.max(prevY, y);
					segments.add(new Segment(x, y1, y2, 0, FENCE));
				}
				prevX = x;
				prevY = y;
				yvalues.add(y);
				if (i == 0) {
					firstX = x;
					firstY = y;
				} else if (i == V-1) {
					lastX = x;
					lastY = y;
				}
			}
			if (firstX == lastX) {
				int x = firstX;
				int y1 = Math.min(firstY, lastY);
				int y2 = Math.max(firstY, lastY);
				segments.add(new Segment(x, y1, y2, 0, FENCE));
			}

			// Compress coordinates
			compressCoordinates(segments, yvalues);

			// Make BIT
			B1 = new long[1000000];
			B2 = new long[1000000];

			// Calculate and print answer
			long ans = 0;
			for (Segment s : segments) {
				if (s.type == PLANT) {
					long collide = query(s.y1, s.y1);
					if (collide % 2 == 0) {
						ans += (long) s.weight;
					}
				} else if (s.type == FENCE) {
					update(s.y1 + 1, s.y2, 1);
				}
			}
			System.out.println(ans);
		}
	}

	static void compressCoordinates(ArrayList<Segment> segments, Set<Integer> yvalues) {
		// Sort points in increasing order
		ArrayList<Integer> newvalues = new ArrayList<Integer>();
		for (int y : yvalues)
			newvalues.add(y);
		Collections.sort(newvalues);

		// Convert each point to it's new coordinate
		for (Segment s : segments) {
			s.y1 = Collections.binarySearch(newvalues, s.y1);
			s.y2 = Collections.binarySearch(newvalues, s.y2);
		}
		Collections.sort(segments);
	}

	static void update(long[] ft, int p, long v) {
		for (; p <= ft.length - 1; p += p & (-p))
			ft[p] += v;
	}

	static void update(int a, int b, long v) {
		update(B1, a, v);
		update(B1, b + 1, -v);
		update(B2, a, v * (a - 1));
		update(B2, b + 1, -v * b);
	}

	static long query(long[] ft, int b) {
		long sum = 0;
		for (; b > 0; b -= b & (-b))
			sum += ft[b];
		return sum;
	}

	static long query(int b) {
		return query(B1, b) * b - query(B2, b);
	}

	static long query(int a, int b) {
		return query(b) - query(a - 1);
	}

	static class Segment implements Comparable<Segment> {
		int x, y1, y2, weight, type;

		public Segment(int x, int y1, int y2, int weight, int type) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
			this.weight = weight;
			this.type = type;
		}

		@Override
		public int compareTo(Segment that) {
			int dx = this.x - that.x;
			int dy1 = this.y1 - that.y1;
			int dy2 = this.y2 - that.y2;
			if (dx == 0) {
				if (dy1 == 0) {
					return dy2;
				}
				return dy1;
			}
			return dx;
		}
	}
}