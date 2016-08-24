import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				int N = sc.nextInt();
				if (N == 0) break;
				ArrayList<Node> nodes = new ArrayList<Node>();
				ArrayList<Connection> conns = new ArrayList<Connection>();

				for (int i = 0; i < N; i++) {
					Node a = new Node(sc.nextInt());
					Node b = new Node(sc.nextInt());
					if (index(nodes, a.num) == -1) nodes.add(a);
					if (index(nodes, b.num) == -1) nodes.add(b);
					conns.add(new Connection(a, b));
				}

				while (true) {
					int s = sc.nextInt(), ttl = sc.nextInt();
					if (s == 0 && ttl == 0) break;
					int ans = getAns(nodes, conns, s, ttl);
					System.out.println(String.format(
						"Case %d: %d nodes not reachable from node %d with TTL = %d.",
						1,
						ans,
						s,
						ttl
					));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int index(ArrayList<Node> nodes, int s) {
		for (int i = 0; i < nodes.size(); i++)
			if (nodes.get(i).num == s) return i;
		return -1;
	}

	static int getAns(ArrayList<Node> nodes, ArrayList<Connection> conns, int s, int ttl) {
		Node begin = nodes.get(index(nodes, s));
		begin.ttl = ttl;
		begin.mark = true;

		Queue<Node> q = new LinkedList<Node>();
		q.add(begin);

		while (!q.isEmpty()) {
			Node v = q.poll();
			System.out.println("v.num="+v.num+"v.mark="+v.mark);
			if (v.ttl < 0) continue;
			for (int i = 0; i < conns.size(); i++) {
				Connection conn = conns.get(i);

				if (conn.a.num == v.num && !conn.b.mark) {
					System.out.println("Adding conn "+v.num+" "+conn.b.num);
					System.out.println("conn.b.num"+conn.b.num+",conn.b.mark"+conn.b.mark);
					conn.b.mark = true;
					conn.b.ttl  = v.ttl - 1;
					q.add(conn.b);
				}

				if (conn.b.num == v.num && !conn.a.mark) {
					System.out.println("Adding conn "+v.num+" "+conn.a.num);
					System.out.println("conn.a.num"+conn.a.num+",conn.a.mark"+conn.a.mark);
					conn.a.mark = true;
					conn.a.ttl = v.ttl - 1;
					q.add(conn.a);
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			System.out.println("ttl:" + node.ttl + " " + ttl);
			ans += (node.ttl > ttl) ? 1 : 0;
			//node.ttl = -1;
			//node.mark = false;
		}
		return ans;
	}

	static class Node {
		int num, ttl;
		boolean mark;

		public Node(int num) {
			this.num = num;
			this.ttl = -1;
			this.mark = false;
		}
	}

	static class Connection {
		Node a, b;

		public Connection(Node a, Node b) {
			this.a = a;
			this.b = b;
		}
	}

}