import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt(), b = 0, ans = 0;
			if (n <= 2) {
				ans = n + 1;
			} else {
				ans = Integer.MAX_VALUE;
				for (b = 2; b * b <= n; b++) {
					if (test(getBase(n, b))) {
						ans = Math.min(b, ans);
					}
				}
				if (ans == Integer.MAX_VALUE) {
					for (int d = b-1; d > 0; d--) {
						b = n / d - 1;
						if (b > d && (b + 1) * d == n) {
							ans = Math.min(b, ans);
						}
					}
				}
			}
			System.out.println(ans);
		}
	}

	static boolean test(int[] base) {
		boolean isPalindrome = true;
		for (int i = 0; i < base.length/2 && isPalindrome; i++) {
			isPalindrome = base[i] == base[base.length-i-1];
		}
		return isPalindrome;
	}

	static int[] getBase(int n, int b) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		while (n > 1) {
			list.addFirst(n % b);
			n /= b;
		}
		if (n > 0) {
			list.addFirst(n);
		}
		int i = 0;
		int[] ans = new int[list.size()];
		for (int x : list) {
			ans[i++] = x;
		}
		return ans;
	}
}