import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int B = sc.nextInt();
			int N = sc.nextInt();
			int[] MB = new int[B];
			for (int i = 0; i < B; i++) {
				MB[i] = sc.nextInt();
			}
			int ans = getAns(MB, B, N);
			System.out.printf("Case #%d: %d\n", t+1, ans);
		}
	}

	static int getAns(int[] MB, int B, int N) {
		long[] val = search(MB, N); // 0-> mid, 1-> cand
		int[] left = new int[B];
		for (int i = 0; i < B; i++) {
			int mod = (int) (val[0] % MB[i]);
			left[i] = (mod != 0) ? (MB[i] - mod) : 0;
		}
		while (true) {
			int n = min(left);
			for (int i = 0; i < B; i++) {
				left[i] -= n;
				if (left[i] == 0) {
					left[i] = MB[i];
					val[1] -= 1;
					if (val[1] == 0) {
						return i+1;
					}
				}
			}
		}
	}

	static long[] search(int[] arr, int N) {
		long lo = 0, hi = Long.parseLong("100000000000000");
		long mid = 0, cand = 0;
		while (lo <= hi) {
			mid = (lo + hi) >>> 1;
			cand = N - process(arr, mid);
			if (cand > 0 && cand < 10000) {
				break;
			} else if (cand <= 0) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return new long[] { mid, cand };
	}

	static long process(int[] arr, long n) {
		long ans = 0;
		for (int m : arr) {
			ans += n / m;
			ans += (n % m != 0) ? 1 : 0;
		}
		return ans;
	}

	static int min(int[] arr) {
		int ans = Integer.MAX_VALUE;
		for (int m : arr) {
			ans = Math.min(m, ans);
		}
		return ans;
	}
}