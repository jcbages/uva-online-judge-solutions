import java.util.*;

class Main {
	static int T, D, K, N;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			// Read test case
			D = sc.nextInt();
			K = sc.nextInt();
			N = sc.nextInt();

			// Get k position and neighbors positions
			int k = getPos(K, (K - 1) % 2 == 0);
			int cw = (k == D) ? 1 : k + 1;
			int cc = (k == 1) ? D : k - 1;

			// Get neighbors values
			boolean add = ((k - 1) % 2 == 0) == (N % 2 == 0);
			int a = getPos(cw, add);
			int b = getPos(cc, add);
			System.out.printf("Case #%d: %d %d\n", t+1, a, b);
		}
	}

	static int getPos(int k, boolean add) {
		int i = 0;
		if (add) {
			i = (k - 1 + N) % D;
		} else {
			i = (k - 1 - N) % D;
			if (i < 0)
				i += D;
		}
		i += 1;
		return i;
	}
}