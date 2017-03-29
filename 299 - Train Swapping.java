import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int L = sc.nextInt();
				int[] carriages = new int[L];
				for (int j = 0; j < L; j++)
					carriages[j] = sc.nextInt();
				int swaps = getSwaps(carriages, L);
				String ans = String.format("Optimal train swapping takes %d swaps.", swaps);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int getSwaps(int[] carriages, int L) {
		int ans = 0;
		for (int i = L-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (carriages[j] > carriages[j+1]) {
					int tmp = carriages[j];
					carriages[j] = carriages[j+1];
					carriages[j+1] = tmp;
					ans += 1;
				}
			}
		}
		return ans;
	}
}