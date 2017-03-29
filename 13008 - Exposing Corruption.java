import java.io.*;
import java.util.*;

class Main {
	static final int DSP_PARTY = 0, PPP_PARTY = 1;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int D = Integer.parseInt(vals[0]);
			int P = Integer.parseInt(vals[1]);
			int R = Integer.parseInt(vals[2]);
			int B = Integer.parseInt(vals[3]);

			Hashtable<Member, ArrayList<Member>> graph = new Hashtable<Member, ArrayList<Member>>();

			Member[] DSP = addParty(graph, in.readLine().split(" "), DSP_PARTY, D);
			Member[] PPP = addParty(graph, in.readLine().split(" "), PPP_PARTY, P);

			for (int i = 0; i < R; i++) {
				vals = in.readLine().split(" ");
				Member X = DSP[Integer.parseInt(vals[0]) - 1];
				Member Y = PPP[Integer.parseInt(vals[1]) - 1];
				graph.get(X).add(Y);
				graph.get(Y).add(X);
			}

			ArrayList<Component> components = getComponents(DSP, PPP, graph);

			int ans1 = getAns(components, DSP_PARTY, B, D, P);
			int ans2 = getAns(components, PPP_PARTY, B, D, P);
			System.out.printf("%d %d\n", ans1, ans2);
		}
	}

	static int getAns(ArrayList<Component> components, int party, int B, int D, int P) {
		/* Initialize Knapsack DP matrix */
		int n = components.size();
		int[][] dp = new int[n+1][B+1];
		/* Let the magic run */
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= B; j++) {
				/* If i == 0 or j == 0, there are no components */
				/* or no money, so default is initial members	*/
				if (i == 0 || j == 0) dp[i][j] = (party == DSP_PARTY) ? D : P;
				/* If component cost is possible to pay, set value	*/
				/* as max between paying it or ignoring it 			*/
				else if (j - components.get(i-1).cost >= 0) {
					int prev = dp[i-1][j - components.get(i-1).cost];
					int lost = (party == DSP_PARTY) ? components.get(i-1).p1 : components.get(i-1).p2;
					int won = (party == DSP_PARTY) ? components.get(i-1).p2 : components.get(i-1).p1;
					int newVal = prev - lost + won;
					dp[i][j] = Math.max(newVal, dp[i-1][j]);
				}
				/* If component cost is NOT possible to pay, set */
				/* value as the max when it was ignored			 */
				else dp[i][j] = dp[i-1][j];
			}
		}
		return dp[n][B];
	}

	static Member[] addParty(Hashtable<Member, ArrayList<Member>> graph, String[] vals, int party, int n) {
		Member[] ans = new Member[n];
		for (int i = 0; i < n; i++) {
			int money = Integer.parseInt(vals[i]);
			ans[i] = new Member(party, money);
			graph.put(ans[i], new ArrayList<Member>());
		}
		return ans;
	}

	static ArrayList<Component> getComponents(Member[] DSP, Member[] PPP, Hashtable<Member, ArrayList<Member>> graph) {
		ArrayList<Component> ans = new ArrayList<Component>();
		HashSet<Member> marks = new HashSet<Member>();
		Enumeration<Member> iterator = graph.keys();
		while (iterator.hasMoreElements()) {
			Member X = iterator.nextElement();
			if (!marks.contains(X)) {
				Component component = bfs(marks, graph, X);
				ans.add(component);
			}
		}
		return ans;
	}

	static Component bfs(HashSet<Member> marks, Hashtable<Member, ArrayList<Member>> graph, Member s) {
		int p1 = 0, p2 = 0, cost = 0;
		/* Initialize queue */
		Queue<Member> Q = new LinkedList<Member>();
		Q.add(s);
		marks.add(s);
		/* Make bfs run! */
		while (!Q.isEmpty()) {
			Member X = Q.poll();
			/* Add cost and count using member X */
			if (X.party == DSP_PARTY) p1 += 1;
			if (X.party == PPP_PARTY) p2 += 1;
			cost += X.cost;
			/* Add new members to queue */
			for (Member Y : graph.get(X)) {
				if (!marks.contains(Y)) {
					marks.add(Y);
					Q.add(Y);
				}
			}
		}
		return new Component(p1, p2, cost);
	}

	static class Component {
		int p1, p2, cost;

		public Component(int p1, int p2, int cost) {
			this.p1 = p1;
			this.p2 = p2;
			this.cost = cost;
		}
	}

	static class Member {
		int party, cost;

		public Member(int party, int cost) {
			this.party = party;
			this.cost = cost;
		}
	}
}