import java.io.*;
import java.math.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			int Q = Integer.parseInt(vals[1]);

			String[] parents = new String[N];
			for (int i = 0; i < N; i++) {
				parents[i] = getParent(in.readLine(), N, true);
				// System.out.println("parent -> " + parents[i]);
			}

			for (int i = 0; i < Q; i++) {
				String q = getParent(in.readLine(), N, false);
				// System.out.println("quuery -> " + q);
				String ans = getAns(parents, N, q);
				System.out.println(ans);
			}
		}
	}

	static String getAns(String[] parents, int N, String query) {
		/* Make q */
		String[] sq = query.split(" ");
		int M = sq.length;
		int[] q = new int[M];
		for (int i = 0; i < M; i++) {
			q[i] = Integer.parseInt(sq[i]);
		}
		/* Generate graph */
		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			/* Make parent integers */
			String[] vals = parents[i].split(" ");
			int[] pp = new int[vals.length];
			for (int j = 0; j < vals.length; j++) {
				pp[j] = Integer.parseInt(vals[j]);
			}
			/* Search for query using bs */
			for (int j = 0; j < M; j++) {
				graph[i][j] = (Arrays.binarySearch(pp, q[j]) < 0) ? 0 : 1;
			}
		}
		/* Check every parent is valid */
		for (int i = 0; i < N; i++) {
			if (!parents[i].isEmpty()) {
				boolean valid = false;
				for (int j = 0; j < M && !valid; j++) {
					valid = graph[i][j] == 1;
				}
				if (!valid) {
					valid = parents[i].split(" ")[0].equals("0") && M < N;
				}
				if (!valid) {
					return "N";
				}
			}
		}
		/* Check if max flow equals M when 0 is included */
		int[] matchR = new int[M];
		for (int i = 0; i < M; i++) {
			matchR[i] = -1;
		}
		int result = 0;
		for (int u = 0; u < N; u++) {
			int[] seen = new int[M];
			for (int i = 0; i < M; i++) {
				seen[i] = 0;
			}
			if (bpm(graph, N, M, u, seen, matchR)) {
				result += 1;
			}
		}
		return (result == M) ? "Y" : "N";
	}

	static boolean bpm(int[][] graph, int N, int M, int u, int[] seen, int[] matchR) {
		for (int v = 0; v < M; v++) {
			if (graph[u][v] == 1 && seen[v] == 0) {
				seen[v] = 1;
				if (matchR[v] < 0 || bpm(graph, N, M, matchR[v], seen, matchR)) {
					matchR[v] = u;
					return true;
				}
			}
		}
		return false;
	}

	static String getParent(String line, int N, boolean add) {
		String[] bloodGroup = line.split(" ");
		int n = Integer.parseInt(bloodGroup[0]);
		if (n == 0) {
			return "0";
		} else {
			StringBuilder sb = new StringBuilder();
			/* Try to add a 0 */
			if (add && n < N) {
				sb.append("0 ");
			}
			/* Add every antigen type and sort it */
			int[] antigenTypes = new int[n];
			for (int i = 0; i < n; i++) {
				antigenTypes[i] = Integer.parseInt(bloodGroup[i+1]);
			}
			Arrays.sort(antigenTypes);
			/* Add sorted antigens to the answer */
			for (int i = 0; i < n; i++) {
				sb.append(antigenTypes[i]);
				if (i < n - 1) {
					sb.append(" ");
				}
			}
			return sb.toString();
		}
	}
}