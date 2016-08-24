import java.io.*;
import java.util.ArrayList;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int n = Integer.parseInt(vals[0]);
			int b = Integer.parseInt(vals[1]);
			int c = vals[2].charAt(0) - 'a';

			Bloc[] blocs = new Bloc[b];
			for (int i = 0; i < b; i++) {
				vals = in.readLine().split(" ");
				int v = Integer.parseInt(vals[0]);
				String pref = vals[1];
				blocs[i] = new Bloc(v, pref);
			}

			ArrayList<Integer>[] G = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				G[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					int[] winners = getWinners(blocs, b, i, j);
					if (winners.length == 2) {
						G[i].add(j);
						G[j].add(i);
					} else if (winners[0] == i) {
						G[i].add(j);
					} else {
						G[j].add(i);
					}
				}
			}

			boolean possible = true;
			for (int u = 0; u < n; u++) {
				boolean[] seen = new boolean[n];
				dfs(G, u, seen);
				for (boolean s : seen) {
					possible = possible && s;
				}
			}
			String ans = possible ? "Y" : "N";
			System.out.println(ans);
		}
	}

	static void dfs(ArrayList<Integer>[] G, int s, boolean[] seen) {
		seen[s] = true;
		for (int v : G[s]) {
			if (!seen[v]) {
				dfs(G, v, seen);
			}
		}
	}

	static int[] getWinners(Bloc[] blocs, int b, int i, int j) {
		int v1 = 0;
		int v2 = 0;
		for (Bloc bloc : blocs) {
			boolean found = false;
			for (int k = 0; k < bloc.pref.length() && !found; k++) {
				int c = bloc.pref.charAt(k) - 'a';
				if (i == c) {
					v1 += bloc.v;
					found = true;
				} else if (j == c) {
					v2 += bloc.v;
					found = true;
				}
			}
		}
		
		if (v1 == v2) {
			return new int[] { i, j };
		} else if (v1 > v2) {
			return new int[] { i };
		} else {
			return new int[] { j };
		}
	}

	static class Bloc {
		int v;
		String pref;

		public Bloc(int v, String pref) {
			this.v = v;
			this.pref = pref;
		}
	}
}