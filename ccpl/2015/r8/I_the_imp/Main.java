import java.io.*;
import java.util.*;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			String[] vals = in.readLine().split(" ");
			int n = Integer.parseInt(vals[0]);
			int k = Integer.parseInt(vals[1]);

			Item[] items = new Item[n];
			for (int j = 0; j < n; j++) {
				String[] itemVals = in.readLine().split(" ");
				int v = Integer.parseInt(itemVals[0]);
				int c = Integer.parseInt(itemVals[1]);
				items[j] = new Item(v, c);
			}
			Arrays.sort(items);

			int ans = getAns(n, k, items);
			System.out.println(ans);
		}
	}

	static int getAns(int n, int k, Item[] items) {
		int[][] dp = initMatrix(n, k, items);

		// Iterate from (k>0) and (n>0)
		for (int j = 1; j <= k; j++) {
			for (int i = 1; i <= n; i++) {
				// If remove this, we will pay the cost.
				int remThis = -items[i-1].c + dp[i-1][j-1];

				// If we buy this, we obtain the earn.
				int buyThis = items[i-1].v - items[i-1].c;

				// Imp decision will be the one that minimize
				// the earnings.
				int impDecision = Math.min(buyThis, remThis);

				// Ignoring item will let to the prev calc.
				int ignoreItem  = dp[i-1][j];

				// So we save the max between imp or ignore.
				dp[i][j] = Math.max(impDecision, ignoreItem);
			}
		}

		return dp[n][k];

	}

	static int[][] initMatrix(int n, int k, Item[] items) {
		int dp[][] = new int[n+1][k+1];

		// There are no items (n=0).
		for (int i = 0; i <= k; i++) {
			dp[0][i] = 0;
		}

		// There are no spells (k=0) and (n>0).
		for (int i = 1; i <= n; i++) {
			int prevEarn = dp[i-1][0];
			int thisEarn = items[i-1].v - items[i-1].c;
			dp[i][0] = Math.max(prevEarn, thisEarn);
		}

		return dp;
	}

	static class Item implements Comparable<Item> {
		int v, c;

		public Item(int v, int c) {
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Item that) {
			int diffV = that.v - this.v;
			int diffC = that.c - this.c;
			return (diffV == 0) ? diffC : diffV;
		}
	}
}