import java.io.*;
import java.util.*;

class Main {
	static int N;
	static boolean[][] G, I;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			G = new boolean[N][N];
			for (int u = 0; u < N; u++) {
				String[] vals = in.readLine().split(" ");
				for (int v = 0; v < N; v++) {
					G[u][v] = (u != v && vals[v].equals("1"));
				}
			}
			
			I = new boolean[N][N];
			bfs();

			System.out.printf("Case %d:\n", t + 1);
			
			char[] arr = new char[2 * N + 1];
			Arrays.fill(arr, '-');
			arr[0] = arr[2 * N] = '+';
			String ends = String.valueOf(arr);
			
			StringBuilder sb = new StringBuilder();
			sb.append(ends + "\n");
			for (int u = 0; u < N; u++) {
				for (int v = 0; v < N; v++) {
					sb.append("|");
					sb.append(I[v][u] ? "Y" : "N");
				}
				sb.append("|\n");
				sb.append(ends + "\n");
			}
			System.out.print(sb);
		}
	}

	static void bfs() {
		int[] visited = new int[N];
		Arrays.fill(visited, -1);
		visited[0] = 0;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(0);
		while (Q.size() > 0) {
			int u = Q.poll();
			I[u][u] = true; // Cause I can be visited, I am dominated by myself.
			getDominants(u, visited);
			for (int v = 0; v < N; v++) {
				if (G[u][v] && visited[v] == -1) {
					visited[v] = visited[u] + 1;
					Q.add(v);
				}
			}
		}
	}

	static void getDominants(int v, int[] visited) {
		if (v == 0) {
			return;
		}

		int count = 0;
		int[] intersection = new int[N];
		for (int u = 0; u < N; u++) {
			if (G[u][v] && visited[u] >= visited[v] - 1) {
				if (visited[u] == visited[v]) {
					visited[v] = -1;
					getDominants(u, visited);
					visited[v] = visited[u];
				}
				count++;
				for (int d = 0; d < N; d++) {
					if (I[u][d]) {
						intersection[d]++;
					}
				}
			}
		}
		for (int d = 0; d < N; d++) {
			if (v != d) {
				I[v][d] = (intersection[d] == count);
			}
		}
	}
}
