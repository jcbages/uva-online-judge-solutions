import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1, T = sc.nextInt(); t <= T; t++) {
			String line = sc.next();
			
			StringBuilder goodLine = new StringBuilder();
			for (int i = 0; i < line.length(); i++)
				if (goodLine.length() == 0 || goodLine.charAt(goodLine.length()-1) != line.charAt(i))
					goodLine.append(line.charAt(i));
			char[] string = goodLine.toString().toCharArray();
			
			int n = string.length;
			boolean[] pancakes = new boolean[n];
			for (int i = 0; i < n; i++)
				pancakes[i] = (string[i] == '+');

			int ans = Integer.MAX_VALUE;
			for (int i = n-1; i >= -1; i--)
				ans = Math.min(ans, getAns(pancakes.clone(), i, n));
			System.out.printf("Case #%d: %d\n", t, ans);
		}
	}

	static int getAns(boolean[] pancakes, int x, int n) {
		int ans = 0;
		for (int i = n-1; i >= 0; i--) {
			if (!pancakes[i] && i > x) {
				if (pancakes[0]) {
					pancakes[0] = !pancakes[0];
					ans++;
				}
				swap(pancakes, i);
				ans++;
			} else if (pancakes[i] && i <= x) {
				if (!pancakes[0]) {
					pancakes[0] = !pancakes[0];
					ans++;
				}
				swap(pancakes, i);
				ans++;
			}
		}

		boolean hasNeg = false;
		for (int i = 0; i < n && !hasNeg; i++)
			hasNeg = !pancakes[i];
		if (hasNeg)
			ans++;

		return ans;
	}

	static void swap(boolean[] pancakes, int i) {
		boolean[] opp = new boolean[i+1];
		for (int j = 0; j <= i; j++)
			opp[j] = !pancakes[j];
		for (int j = 0; j <= i; j++)
			pancakes[j] = opp[i-j];
	}
}