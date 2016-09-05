import java.io.*;
import java.util.*;

class Main {
	static LinkedList<Integer>[] G, H;
	static int[][] dG, dH;
	static int n;

	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while ((n = Integer.parseInt(in.readLine())) != 0) {
			G = new LinkedList[n];
			add(G, in);
			H = new LinkedList[n];
			add(H, in);

			dG = new int[n][n];
			dH = new int[n][n];

			String[] vals = in.readLine().split(" ");
			int A = Integer.parseInt(vals[0]);
			int B = Integer.parseInt(vals[1]);

			for (int i = 0; i < n; i++) {
				bfs(G, dG, i);
				bfs(H, dH, i);
			}


			boolean valid = true;
			for (int u = 0; u < n && valid; u++) {
				for (int v = 0; v < n && valid; v++) {
					valid = (dH[u][v] >= 0) && (dH[u][v] <= A * dG[u][v] + B);
				}
			}

			System.out.println(valid ? "Yes" : "No");
		}
	}

	static void bfs(LinkedList<Integer>[] G, int[][] dis, int s) {
		for (int i = 0; i < n; i++) {
			dis[s][i] = -1;
		}
		dis[s][s] = 0;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);
		while (!Q.isEmpty()) {
			int u = Q.poll();
			for (int v : G[u]) {
				if (dis[s][v] == -1) {
					dis[s][v] = dis[s][u] + 1;
					Q.add(v);
				}
			}
		}
	}

	static void add(LinkedList<Integer>[] G, BufferedReader in) throws IOException {
		for (int i = 0; i < n; i++) {
			G[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			String[] vals = in.readLine().split(" ");
			int u = Integer.parseInt(vals[0]) - 1;
			for (int j = 1; j < vals.length; j++) {
				int v = Integer.parseInt(vals[j]) - 1;
				G[u].add(v);
			}
		}
	}
}