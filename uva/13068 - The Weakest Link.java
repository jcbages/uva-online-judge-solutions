import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int N = Integer.parseInt(in.readLine());
		for (int n = 0; n < N; n++) {
			char[] txt = in.readLine().toCharArray();
			int ans = getAns(txt, txt.length);
			System.out.println(ans);
		}
	}

	static int getAns(char[] txt, int n) {
		Suffix[] suffixes = new Suffix[n];
		for (int i = 0; i < n; i++) {
			int r1 = txt[i] - 'a';
			int r2 = txt[(i+1) % n] - 'a';
			suffixes[i] = new Suffix(i, r1, r2);
		}
		Arrays.sort(suffixes);

		int[] ind = new int[n];
		for (int k = 4; k < 2*n; k *= 2) {
			int rank = 0;
			int prank = suffixes[0].rank[0];
			suffixes[0].rank[0] = rank;
			ind[suffixes[0].index] = 0;

			for (int i = 1; i < n; i++) {
				if (suffixes[i].rank[0] == prank && suffixes[i].rank[1] == suffixes[i-1].rank[1]) {
					prank = suffixes[i].rank[0];
					suffixes[i].rank[0] = rank;
				} else {
					prank = suffixes[i].rank[0];
					suffixes[i].rank[0] = ++rank;
				}
				ind[suffixes[i].index] = i;
			}

			for (int i = 0; i < n; i++) {
				int nexti = (suffixes[i].index + k/2) % n;
				suffixes[i].rank[1] = suffixes[ind[nexti]].rank[0];
			}

			Arrays.sort(suffixes);
		}

		return suffixes[0].index + 1;
	}

	static class Suffix implements Comparable<Suffix> {
		int index;
		int[] rank;

		public Suffix(int index, int r1, int r2) {
			this.index = index;
			this.rank = new int[] { r1, r2 };
		}

		@Override
		public int compareTo(Suffix that) {
			if (this.rank[0] == that.rank[0])
				return (this.rank[1] - that.rank[1]);
			else
				return (this.rank[0] - that.rank[0]);
		}
	}
}