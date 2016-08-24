import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 0, T = sc.nextInt(); t < T; t++) {
			long n = sc.nextInt();
			long m = sc.nextInt();
			long ans = sum(m) - sum(m - n) - n;
			System.out.println(ans);
		}
	}

	static long sum(long n) {
		return n * (n + 1) / 2;
	}
}