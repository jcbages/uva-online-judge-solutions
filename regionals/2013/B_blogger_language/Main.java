import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			String T = vals[1];
			String P = in.readLine();
			Integer[][] q = new Integer[N][2];
			for (int i = 0; i < N; i++) {
				vals = in.readLine().split(" ");
				q[i][0] = Integer.parseInt(vals[0])-1;
				q[i][1] = Integer.parseInt(vals[1])-1;
			}
			Arrays.sort(q, new Comparator<Integer[]>() {
				@Override
				public int compare(Integer[] a, Integer[] b) {
					int x = a[0] - b[0];
					int y = a[1] - b[1];
					return (x == 0) ? y : x;
				}
			});
			int ans = getAns(P, P.toLowerCase(), T, T.toLowerCase(), N, q);
			System.out.println(ans);
		}
	}

	static int getAns(String P, String NP, String T, String NT, int N, Integer[][] q) {
		Tuple[] aux = getAuxDS(N, q);
		int last = 0, ans = 0;
		while (last != -1) {
			last = NP.indexOf(NT, last);
			if (last == -1) break;
			ans = Math.max(cmp(P, T, last, aux), ans);
			last += 1;
		}
		return ans;
	}

	static Tuple[] getAuxDS(int N, Integer[][] q) {
		// Calc size
		int size = 0;
		for (int i = 0; i < N; i++) {
			size = Math.max(q[i][1], size);
		}
		// Get unique numbers
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			set.add(q[i][0]);
			if (q[i][1]+1 < size) set.add(q[i][1]+1);
		}
		Integer[] nums = set.toArray(new Integer[set.size()]);
		Arrays.sort(nums, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
		// Initialize tuples
		Tuple[] ans = new Tuple[nums.length];
		for (int i = 0; i < nums.length; i++) {
			ans[i] = new Tuple(nums[i], 0);
		}
		// Get origins and ends
		boolean[] orig = new boolean[size];
		boolean[] ends = new boolean[size];
		for (int i = 0; i < N; i++) {
			orig[q[i][0]] = true;
			if (q[i][1]+1 < size) ends[q[i][1]+1] = true;
		}
		// Sum orig
		int last = 0;
		for (int i = 0; i < nums.length; i++) {
			if (orig[ans[i].pos]) last += 1;
			ans[i].num += last;
		}
		// Sub ends
		int amount = 0;
		for (int i = 0; i < nums.length; i++) {
			if (ends[ans[i].pos]) amount += 1;
			ans[i].num -= amount;
		}

		for (Tuple t : ans) System.out.print("("+t.pos+","+t.num+"), "); System.out.println();

		// return new Tuple[] {
		// 	new Tuple(0, 1),
		// 	new Tuple(3, 2),
		// 	new Tuple(5, 3),
		// 	new Tuple(7, 2),
		// 	new Tuple(14, 1),
		// };
		return ans;
	}

	static int getCnt(Tuple[] aux, int i) {
		int lo = 0, hi = aux.length-1, ans = 0;
		while (lo <= hi) {
			int mid = (lo + hi) >>> 1;
			if (aux[mid].pos <= i) {
				ans = mid;
				lo = mid+1;
			} else { /* if (aux[mid].pos > i) */
				hi = mid-1;
			}
		}
		return aux[ans].num;
	}

	static int cmp(String P, String T, int last, Tuple[] aux) {
		int ans = 0;
		for (int i = 0; i < T.length(); i++) {
			int cnt = getCnt(aux, i+last);
			char PC = P.charAt(i+last);
			if (cnt % 2 != 0) {
				if (Character.isUpperCase(PC)) {
					PC = Character.toLowerCase(PC);
				} else {
					PC = Character.toUpperCase(PC);
				}
			}
			if (PC != T.charAt(i)) {
				ans += 1;
			}
		}
		return ans;
	}

	static class Tuple {
		int pos, num;

		public Tuple(int pos, int num) {
			this.pos = pos;
			this.num = num;
		}
	}
}