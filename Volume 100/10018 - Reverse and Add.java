import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int n = 0; n < N; n++) {
			long P = sc.nextLong(), iter = 0;
			do {
				StringBuilder sb = new StringBuilder();
				sb.append(P);
				P += Integer.parseInt(sb.reverse().toString());
				iter++;
			} while (!isPalindrome(P));
			System.out.printf("%d %d\n", iter, P);
		}
	}

	static boolean isPalindrome(long P) {
		String str = Long.toString(P);
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}