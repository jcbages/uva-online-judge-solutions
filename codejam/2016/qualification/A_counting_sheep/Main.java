import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1, T = sc.nextInt(); t <= T; t++) {
			long N = sc.nextLong();
			String ans = getAns(N);
			System.out.printf("Case #%d: %s\n", t, ans);
		}
	}

	static String getAns(long n) {
		long N = n;
		if (N == 0)
			return "INSOMNIA";
		boolean[] digits = new boolean[10];
		int cnt = 0;
		String v = null;
		while (cnt < 10) {
			v = Long.toString(N);
			for (int j = 0; j < v.length(); j++) {
				if (!digits[v.charAt(j) - '0']) {
					digits[v.charAt(j) - '0'] = true;
					cnt++;
				}
			}
			N += n;
		}
		return v;
	}
}