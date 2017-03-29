import java.util.*;

class Main {
	static int B;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while ((B = sc.nextInt()) != 0) {
			ArrayList<Vertex> vertices = new ArrayList<Vertex>();
			for (int i = 0; i < B; i++) {
				Vertex v1 = new Vertex(sc.nextInt(), sc.nextInt(), i);
				Vertex v2 = new Vertex(sc.nextInt(), sc.nextInt(), i);
				v1.op = v2;
				v2.op = v1;
				vertices.add(v1);
				vertices.add(v2);
			}
			Collections.sort(vertices);

			int n = vertices.size(), ans = 0;
			for (int i = 0; i < n; i++) {
				Vertex v = vertices.get(i);
				if (!v.addable) {
					continue;
				} else if (v.op.addable) { // start, add!
					v.addable = false;
				} else { // end, remove!
					for (int j = 0; j < n; j++) {
						Vertex w = vertices.get(j);
						if (w.compareTo(v) > 0) { // its on right, stop!
							break;
						}
						w.addable = w.op.addable = false;
					}
					ans++;
				}
			}

			System.out.println(ans);
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int x, y, glass;
		boolean addable;
		Vertex op;

		public Vertex(int x, int y, int glass) {
			this.x = x;
			this.y = y;
			this.addable = true;
			this.glass = glass;
		}

		@Override
		public int compareTo(Vertex that) {
			return this.x * that.y - that.x * this.y;
		}
	}
}