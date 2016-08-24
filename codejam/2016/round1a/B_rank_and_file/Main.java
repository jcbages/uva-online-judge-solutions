import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 0, T = sc.nextInt(); t < T; t++) {
			int N = sc.nextInt();
			int[] count = new int[2501];
			for (int i = 0; i < N * (2 * N - 1); i++)
				count[sc.nextInt()]++;
			System.out.printf("Case #%d:", t+1);
			for (int i = 0; i < 2501; i++)
				if (count[i] % 2 != 0)
					System.out.printf(" %d", i);
			System.out.println();
		}
	}
}