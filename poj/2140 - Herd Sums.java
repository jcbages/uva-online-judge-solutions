import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong(), ans = 1;
		for (long i = 1;; i++) {
			long sum = (i * (i + 1)) / 2;
			if (sum >= N) break;
			if ((N + sum) % (i + 1) == 0) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}