import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int tot = 0, n = Integer.parseInt(line);
			int[] nums = new int[n];
			String[] vals = in.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(vals[i]);
				tot += nums[i];
			}
			
			ArrayList<Integer> cand = new ArrayList<Integer>();
			for (int i = 1000; i >= 3; i--)
				if (tot % i == 0)
					cand.add(tot / i);

			int acc = 0;
			int[] cumm = new int[2 * n + 1];
			for (int i = 0; i < 2 * n + 1; i++) {
				cumm[i] = acc;
				acc += nums[i % n];
			}

			int ans = getAns(nums, cumm, cand, n);
			System.out.println(ans);
		}
	}

	@SuppressWarnings("unchecked")
	static int getAns(int[] nums, int[] cumm, ArrayList<Integer> cand, int n) {
		// Initialize everything
		ArrayList<Integer>[] dst = new ArrayList[n];
		ArrayList<Integer>[] idx = new ArrayList[n];
		ArrayList<Boolean>[] mrk = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			dst[i] = new ArrayList<Integer>();
			idx[i] = new ArrayList<Integer>();
			mrk[i] = new ArrayList<Boolean>();
			for (int j = 0; j < cand.size(); j++) {
				int k = Arrays.binarySearch(cumm, cumm[i] + cand.get(j));
				if (k >= 0) {
					dst[i].add(cand.get(j));
					idx[i].add(k % n);
					mrk[i].add(false);
				}
			}
		}

		// Calculate answer
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < dst[i].size(); j++) {
				if (!mrk[i].get(j)) {
					int k = calcPoly(i, j, dst, idx, mrk);
					mrk[i].set(j, true);
					if (k > 2)
						return k;
				}
			}
		}
		return -1;
	}

	static int calcPoly(int i, int j, ArrayList<Integer>[] dst, ArrayList<Integer>[] idx, ArrayList<Boolean>[] mrk) {
		int ans = 1, u = idx[i].get(j);
		while (u != i) {
			int v = Collections.binarySearch(dst[u], dst[i].get(j));
			if (v >= 0 && !mrk[u].get(v)) {
				mrk[u].set(v, true);
				u = idx[u].get(v);
			} else {
				return -1;
			}
			ans++;
		}
		return ans;
	}
}