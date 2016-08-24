import java.io.*;
import java.util.*;

class Main {
	static final int NOT = 0, UP = 1, DOWN = 2, BOTH = 3;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int r = Integer.parseInt(vals[0]);
			int c = Integer.parseInt(vals[1]);
			int s = Integer.parseInt(vals[2]);

			Node[][] shield = new Node[r][c];
			for (int i = 0; i < r; i++) {
				line = in.readLine();
				for (int j = 0; j < c; j++) {
					boolean enabled = line.charAt(j) == '#';
					int exit = (i == 0) ? UP : (i == r-1) ? DOWN : NOT;
					shield[i][j] = new Node(enabled, exit);
				}
			}

			int[] shots = new int[s];
			for (int i = 0; i < s; i++) {
				shots[i] = Integer.parseInt(in.readLine());
			}

			String ans = getAns(shield, shots, r, c, s);
			System.out.println(ans);
		}
	}

	static String getAns(Node[][] shield, int[] shots, int r, int c, int s) {
		// Initialize Union-Find
		Hashtable<Node, LinkedList<Node>> UF = new Hashtable<Node, LinkedList<Node>>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!shield[i][j].enabled) {
					makeSet(shield[i][j], UF);
				}
			}
		}
		// Connect initial components
		//boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!shield[i][j].enabled) {
					// Check neighbors
					if (i > 0 && !shield[i-1][j].enabled) {
						if (union(shield[i][j].head, shield[i-1][j].head, UF)) {
							return "0";
						}
					}
					if (i < r-1 && !shield[i+1][j].enabled) {
						if (union(shield[i][j].head, shield[i+1][j].head, UF)) {
							return "0";
						}
					}
					if (j > 0 && !shield[i][j-1].enabled) {
						if (union(shield[i][j].head, shield[i][j-1].head, UF)) {
							return "0";
						}
					}
					if (j < c-1 && !shield[i][j+1].enabled) {
						if (union(shield[i][j].head, shield[i][j+1].head, UF)) {
							return "0";
						}
					}
				}
			}
		}
		// Get actual "last"
		int[] up = new int[c];
		int[] down = new int[c];
		for (int j = 0; j < c; j++) {
			boolean found = false;
			for (int i = 0; i < r && !found; i++) {
				if (shield[i][j].enabled) {
					up[j] = i;
					found = true;
				}
			}
			if (!found) {
				return "0";
			}
			found = false;
			for (int i = r-1; i >= 0 && !found; i--) {
				if (shield[i][j].enabled) {
					down[j] = i;
					found = true;
				}
			}
		}
		// Perform shots
		for (int nj : shots) {
			// Get Node coords and disable
			int j = Math.abs(nj) - 1;
			int i = (nj < 0) ? down[j]-- : up[j]++;
			shield[i][j].enabled = false;
			makeSet(shield[i][j], UF);
			// If open path, broken
			if (up[j] > down[j]) {
				return Integer.toString(nj);
			}
			// Check neighbors
			if (i > 0 && !shield[i-1][j].enabled) {
				if (union(shield[i][j].head, shield[i-1][j].head, UF)) {
					return Integer.toString(nj);
				}
			}
			if (i < r-1 && !shield[i+1][j].enabled) {
				if (union(shield[i][j].head, shield[i+1][j].head, UF)) {
					return Integer.toString(nj);
				}
			}
			if (j > 0 && !shield[i][j-1].enabled) {
				if (union(shield[i][j].head, shield[i][j-1].head, UF)) {
					return Integer.toString(nj);
				}
			}
			if (j < c-1 && !shield[i][j+1].enabled) {
				if (union(shield[i][j].head, shield[i][j+1].head, UF)) {
					return Integer.toString(nj);
				}
			}
		}
		// Perfect shield!
		return "X";
	}

	static boolean dfs(Node[][] shield, int i, int j, boolean[][] visited, Hashtable<Node, LinkedList<Node>> UF, int r, int c) {
		visited[i][j] = true;
		if (i > 0 && !shield[i-1][j].enabled && !visited[i-1][j]) {
			if (union(shield[i][j].head, shield[i-1][j].head, UF)) {
				return true;
			}
			if (dfs(shield, i-1, j, visited, UF, r, c)) {
				return true;
			}
		}
		if (i < r-1 && !shield[i+1][j].enabled && !visited[i+1][j]) {
			if (union(shield[i][j].head, shield[i+1][j].head, UF)) {
				return true;
			}
			if (dfs(shield, i+1, j, visited, UF, r, c)) {
				return true;
			}
		}
		if (j > 0 && !shield[i][j-1].enabled && !visited[i][j-1]) {
			if (union(shield[i][j].head, shield[i][j-1].head, UF)) {
				return true;
			}
			if (dfs(shield, i, j-1, visited, UF, r, c)) {
				return true;
			}
		}
		if (j < c-1 && !shield[i][j+1].enabled && !visited[i][j+1]) {
			if (union(shield[i][j].head, shield[i][j+1].head, UF)) {
				return true;
			}
			if (dfs(shield, i, j+1, visited, UF, r, c)) {
				return true;
			}
		}
		return false;
	}

	static void makeSet(Node n, Hashtable<Node, LinkedList<Node>> UF) {
		UF.put(n, new LinkedList<Node>());
		UF.get(n).add(n);
		n.head = n;
	}

	static boolean union(Node a,  Node b, Hashtable<Node, LinkedList<Node>> UF) {
		LinkedList<Node> la = UF.get(a);
		LinkedList<Node> lb = UF.get(b);
		if (la.size() > lb.size()) {
			for (Node n : lb) {
				n.head = a;
				if ((a.exit = getExit(a.exit, n.exit)) == BOTH) {
					return true;
				}
			}
			return false;
		} else {
			for (Node n : la) {
				n.head = b;
				if ((b.exit = getExit(b.exit, n.exit)) == BOTH) {
					return true;
				}
			}
			return false;
		}
	}

	static int getExit(int a, int b) {
		if (a == b) {
			return a;
		} else {
			if (b == NOT) {
				return a;
			} else if (a == NOT) {
				return b;
			} else {
				return BOTH;
			}
		}
	}

	static Node find(Node n, Hashtable<Node, LinkedList<Node>> UF) {
		return n.head;
	}

	static class Node {
		boolean enabled;
		int exit;
		Node head;

		public Node(boolean enabled, int exit) {
			this.enabled = enabled;
			this.exit = exit;
			this.head = null;
		}
	}
}