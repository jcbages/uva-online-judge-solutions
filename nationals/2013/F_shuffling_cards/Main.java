import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			long R = sc.nextLong();
			int[] slots = new int[N];
			for (int i = 0; i < N; i++) {
				slots[i] = sc.nextInt();
			}
			int[] ans = getAns(slots, N, R);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(ans[i]);
				if (i < N-1) {
					sb.append(" ");
				}
			}
			System.out.println(sb);
		}
	}

	static int[] getAns(int[] slots, int N, long R) {
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			if (slots[i]-1 == i) {
				ans[i] = slots[i];
			} else {
				long rnd = getRnd(slots, i, N);
				long mod = R % rnd;
				ans[i] = getSlot(slots, i, N, mod);
			}
		}
		return ans;
	}

	static long getRnd(int[] slots, int i, int N) {
		int curr = slots[i]-1;
		long ans = 1;
		while (curr != i) {
			curr = slots[curr]-1;
			ans += 1;
		}
		return ans;
	}

	static int getSlot(int[] slots, int i, int N, long R) {
		int curr = i;
		for (long j = 0; j < R; j++) {
			curr = slots[curr]-1;
		}
		return curr+1;
	}
}
