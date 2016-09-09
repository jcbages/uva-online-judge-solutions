import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		double N;
		while ((N = sc.nextDouble()) > 0.00) {
			int ans = 0, n = 2;
			double acc = 0.00;
			while (acc < N) {
				acc += (1.0 / n++);
				ans++;
			}
			System.out.println(ans + " card(s)");
		}
	}
}