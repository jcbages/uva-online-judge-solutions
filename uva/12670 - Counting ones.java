import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLong()) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			long ans = getAns(A, B);
			System.out.println(ans);
		}
	}

	static long getAns(long A, long B) {
		return count(B) - count(A-1);
	}

	static long count(long n) {
		long ans = 0, acc = 1;
		while (acc <= n) {
			long a = n - acc + 1;
			long b = 2 * acc;
			ans += a / b * acc;
			ans += Math.min(a % b, acc);
			acc = 2 * acc;
		}
		return ans;
	}
}