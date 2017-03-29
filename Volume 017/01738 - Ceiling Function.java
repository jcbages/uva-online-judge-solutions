import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			Node[] trees = new Node[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					int number = sc.nextInt();
					if (trees[i] == null) trees[i] = new Node(number);
					else trees[i].add(number);
				}
			}

			int ans = 0;
			for (int i = 0; i < n; i++) {
				boolean unique = true;
				for (int j = i+1; j < n && unique; j++)
					unique = !equals(trees[i], trees[j]);
				if (unique) ans++;
			}
			System.out.println(ans);
		}
	}

	static boolean equals(Node a, Node b) {
		if (a == null && b != null) return false;
		if (a != null && b == null) return false;
		if (a == null && b == null) return true;
		return equals(a.left, b.left) && equals(a.right, b.right);
	}

	static class Node {
		int number;
		Node left, right;

		public Node(int number) {
			this.number = number;
			this.left = null;
			this.right = null;
		}

		public void add(int number) {
			if (number < this.number) {
				if (left == null) left = new Node(number);
				else left.add(number);
			} else {
				if (right == null) right = new Node(number);
				else right.add(number);
			}
		}
	}
}