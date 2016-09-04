import java.io.*;
import java.util.*;

class Main {
	static Map<String, Set<String>> G1, G2;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		String id1, id2;
		while (!(id1 = in.readLine()).equals("END")) {
			G1 = new Hashtable<String, Set<String>>();
			G2 = new Hashtable<String, Set<String>>();

			add(G1, in);
			id2 = in.readLine();
			add(G2, in);

			// check nodes
			boolean validNodes = true;
			for (String key : G1.keySet()) {
				if (G2.get(key) == null) {
					validNodes = false;
					break;
				}
			}

			if (!validNodes) {
				System.out.printf("NO: %s is not a more detailed version of %s\n", id2, id1);
				continue;
			}

			// check conns
			boolean validConns = true;
			for (String u : G1.keySet()) {
				Set<String> visited = bfs(u);
				for (String v : G1.get(u)) {
					if (!visited.contains(v)) {
						validConns = false;
						break;
					}
				}
				if (!validConns) {
					break;
				}
			}

			if (!validConns) {
				System.out.printf("NO: %s is not a more detailed version of %s\n", id2, id1);
			} else {
				System.out.printf("YES: %s is a more detailed version of %s\n", id2, id1);
			}
		}
	}

	static Set<String> bfs(String s) {
		Set<String> visited = new HashSet<String>();
		visited.add(s);
		Queue<String> Q = new LinkedList<String>();
		Q.add(s);
		while (!Q.isEmpty()) {
			String u = Q.poll();
			for (String v : G2.get(u)) {
				if (!visited.contains(v)) {
					visited.add(v);
					if (G1.get(v) == null) {
						Q.add(v);
					}
				}
			}
		}
		return visited;
	}

	static void add(Map<String, Set<String>> G, BufferedReader in) throws IOException {
		String road;
		while (!(road = in.readLine()).equals("* * *")) {
			String[] vals = road.split(" ");
			if (G.get(vals[0]) == null)
				G.put(vals[0], new HashSet<String>());
			if (G.get(vals[1]) == null)
				G.put(vals[1], new HashSet<String>());
			G.get(vals[0]).add(vals[1]);
			G.get(vals[1]).add(vals[0]);
		}
	}
}