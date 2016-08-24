import java.util.*;

class Main {
	final static int X = 0, Y = 1, Z = 2;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == 0 && m == 0)
				break;
			boolean[][][] G = new boolean[n+1][n+1][n+1];
			for (int i = 0; i < m; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int z = sc.nextInt();
				G[x][y][z] = true;
			}
			boolean[][][] M = new boolean[n+1][n+1][n+1];
			boolean finished = false;
			for (int x = 0; x <= n && !finished; x++)
				for (int y = 0; y <= n && !finished; y++)
					for (int z = 0; z <= n && !finished; z++)
						if (G[x][y][z] && !M[x][y][z])
							finished = bfs(G, M, x, y, z, n);
			if (finished)
				System.out.println("Benny");
			else
				System.out.println("Willy");
		}
	}

	static boolean bfs(boolean[][][] G, boolean[][][] M, int x, int y, int z, int n) {
		Queue<Node> Q = new LinkedList<Node>();
		Q.add(new Node(x, y, z));
		M[x][y][z] = true;
		while (Q.size() > 0) {
			Node u = Q.poll();
			ArrayList<Node> neighbors = getNeighbors(G, M, u, n);
			for (Node v : neighbors) {
				Q.add(v);
				M[v.x][v.y][v.z] = true;
			}
		}
		return check(G, M, X, n) && check(G, M, Y, n) && check(G, M, Z, n);
	}

	static ArrayList<Node> getNeighbors(boolean[][][] G, boolean[][][] M, Node u, int n) {
		ArrayList<Node> ans = new ArrayList<Node>();
		Node[] candidates = new Node[] {
			new Node(u.x-1, u.y, u.z),
			new Node(u.x+1, u.y, u.z),
			new Node(u.x, u.y-1, u.z),
			new Node(u.x, u.y+1, u.z),
			new Node(u.x, u.y, u.z-1),
			new Node(u.x, u.y, u.z+1),
		};
		for (Node cand : candidates)
			if (isValid(G, M, cand, n))
				ans.add(cand);
		return ans;
	}

	static boolean isValid(boolean[][][] G, boolean[][][] M, Node u, int n) {
		boolean xvalid = 0 <= u.x && u.x <= n;
		boolean yvalid = 0 <= u.y && u.y <= n;
		boolean zvalid = 0 <= u.z && u.z <= n;
		if (xvalid && yvalid && zvalid)
			return G[u.x][u.y][u.z] && !M[u.z][u.y][u.z];
		else
			return false;
	}

	static boolean check(boolean[][][] G, boolean[][][] M, int dim, int n) {
		boolean valid = false;
		int a = 1, b = n-1;
		for (int i = 0; i <= n-1; i++) {
			if (dim == X)
				valid = valid || (G[0][a][b] && M[0][a][b]);
			else if (dim == Y)
				valid = valid || (G[a][0][b] && M[a][0][b]);
			else if (dim == Z)
				valid = valid || (G[a][b][0] && M[a][b][0]);
			a++; b--;
		}
		return valid;
	}

	static class Node {
		int x, y, z;

		public Node(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}