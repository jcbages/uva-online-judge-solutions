import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			// Read data
			int N = Integer.parseInt(in.readLine());
			String[][] strings = new String[N][2];
			for (int i = 0; i < N; i++) {
				String[] vals = in.readLine().split(" ");
				strings[i][0] = vals[0];
				strings[i][1] = vals[1];
			}
			// Discard good ones
			ArrayList<String[]> cands = new ArrayList<String[]>();
			ArrayList<String[]> free = new ArrayList<String[]>();
			for (int i = 0; i < N; i++) {
				boolean unique1 = true;
				for (int j = 0; j < N && unique1; j++) {
					if (i == j) continue;
					unique1 = !strings[i][0].equals(strings[j][0]);
				}
				boolean unique2 = true;
				for (int j = 0; j < N && unique2; j++) {
					if (i == j) continue;
					unique2 = !strings[i][1].equals(strings[j][1]);
				}
				if (!unique1 && !unique2)
					cands.add(new String[] { strings[i][0], strings[i][1] });
				else {
					free.add(new String[] { strings[i][0], strings[i][1] });
				}
			}
			int ans = 0;
			for (int i = 0; i < cands.size(); i++) {
				boolean f1 = false;
				for (int j = 0; j < free.size() && !f1; j++)
					f1 = cands.get(i)[0].equals(free.get(j)[0]);
				boolean f2 = false;
				for (int j = 0; j < free.size() && !f2; j++)
					f2 = cands.get(i)[1].equals(free.get(j)[1]);
				if (f1 && f2) {
					ans++;
				} else if (f1 != f2) {
					free.add(new String[] { cands.get(i)[0], cands.get(i)[1] });
				}
			}
			// Base case
			// if (cands.size() <= 1) {
			// 	System.out.printf("Case #%d: %d\n", t, cands.size());
			// 	continue;
			// }
			// for (int i = 0; i < cands.size(); i++)
			// 	System.out.println(cands.get(i)[0] + " " + cands.get(i)[1]);
			// System.out.println(cands.size());
			// Get relations
			// Hashtable<String, Set<String>> ht = new Hashtable<String, Set<String>>();
			// for (int i = 0; i < cands.size(); i++) {
			// 	if (ht.get(cands.get(i)[0]) == null)
			// 		ht.put(cands.get(i)[0], new HashSet<String>());
			// 	ht.get(cands.get(i)[0]).add(cands.get(i)[1]);
			// }
			// Count unique ones
			// Set<String> set1 = new HashSet<String>();
			// for (String[] cand : cands) 
			// 	set1.add(cand[0]);
			// int m = set1.size();
			// Set<String> set2 = new HashSet<String>();
			// for (String[] cand : cands) 
			// 	set2.add(cand[1]);
			// int n = set2.size();
			// // Make the graph
			// boolean[][] bpGraph = new boolean[m][n];
			// int i = 0;
			// for (String s1 : set1) {
			// 	int j = 0;
			// 	for (String s2 : set2) {
			// 		bpGraph[i][j] = ht.get(s1).contains(s2);
			// 		j++;
			// 	}
			// 	i++;
			// }
			// Get answer
			// int ans = cands.size() - maxBPM(bpGraph, n, m);
			System.out.printf("Case #%d: %d\n", t, ans);
		}
	}

	static int maxBPM(boolean[][] bpGraph, int N, int M) {
		int[] matchR = new int[N];
		for (int i = 0; i < N; i++)
			matchR[i] = -1;
		int result = 0;
		for (int u = 0; u < M; u++) {
			boolean[] seen = new boolean[N];
			for (int i = 0; i < N; i++)
				seen[i] = false;
			if (bpm(bpGraph, u, seen, matchR, N, M))
				result++;
		}
		return result;
	}

	static boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matchR[], int N, int M) {
		for (int v = 0; v < N; v++) {
			if (bpGraph[u][v] && !seen[v]) {
				seen[v] = true;
				if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR, N, M)) {
					matchR[v] = u;
					return true;
				}
			}
		}
		return false;
	}
}