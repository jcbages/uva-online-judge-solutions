import java.io.*;
import java.util.*;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			String[] vals = in.readLine().split(" ");
			int N = Integer.parseInt(vals[0]);
			int M = Integer.parseInt(vals[1]);
			int L = Integer.parseInt(vals[2]);

			Agency[] agencies = new Agency[L];
			for (int j = 0; j < L; j++) {
				String agVals[] = in.readLine().split("[:,]");
				String name = agVals[0];
				int A = Integer.parseInt(agVals[1]);
				int B = Integer.parseInt(agVals[2]);
				agencies[j] = new Agency(name, A, B);
			}

			String ans = getAns(N, M, L, i+1, agencies);
			System.out.println(ans);
		}
	}

	static String getAns(int N, int M, int L, int T, Agency[] agencies) {
		for (Agency a : agencies) {
			a.min = getMinCost(N, M, a);
		}
		Arrays.sort(agencies);

		StringBuilder ans = new StringBuilder("Case " + T + "\n");
		for (Agency a : agencies) {
			ans.append(a.name + " " + a.min + "\n");
		}
		return ans.substring(0, ans.length() - 1);
	}

	static int getMinCost(int N, int M, Agency a) {
		if (N == M) return 0;
		int half = N / 2;
		int ans = 0;
		if (half >= M) {
			ans = Math.min(a.A * (N - half), a.B) + getMinCost(half, M, a);
		} else {
			ans = a.A + getMinCost(N-1, M, a);
		}
		return ans;
	}

	static class Agency implements Comparable<Agency> {
		String name;
		int A, B, min;

		public Agency(String name, int A, int B) {
			this.name = name;
			this.A = A;
			this.B = B;
			this.min = -1;
		}

		@Override
		public int compareTo(Agency that) {
			int diff = this.min - that.min;
			if (diff == 0) {
				return this.name.compareTo(that.name);
			} else {
				return diff;
			}
		}
	}
}