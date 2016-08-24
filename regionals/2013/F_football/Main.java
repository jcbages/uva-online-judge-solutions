import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int G = sc.nextInt();
			int[] matches = new int[N];
			for (int i = 0; i < N; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				matches[i] = b - a;
			}
			Arrays.sort(matches);
			int ans = getAns(matches, N, G);
			System.out.println(ans);
		}
	}

	static int getAns(int[] matches, int N, int G) {
		int ans = 0;
		// Add winners and ties
		for (int i = 0; i < N; i++) {
			if (matches[i] < 0) {
				ans += 3;
			} else if (matches[i] == 0) {
				if (G > 0) {
					G -= 1;
					ans += 3;
				} else {
					ans += 1;
				}
			} else if (matches[i] > 0) {
				if (G >= matches[i] + 1) {
					G -= matches[i] + 1;
					ans += 3;
				} else if (G >= matches[i]) {
					G -= matches[i];
					ans += 1;
				}
			}
		}
		return ans;
	}
}