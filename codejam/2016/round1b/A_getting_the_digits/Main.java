import java.util.*;

class Main {
	final static char[][] digits = new char[][] {
		{ 'Z', 'E', 'R', 'O' },
		{ 'O', 'N', 'E' },
		{ 'T', 'W', 'O' },
		{ 'T', 'H', 'R', 'E', 'E' },
		{ 'F', 'O', 'U', 'R' },
		{ 'F', 'I', 'V', 'E' },
		{ 'S', 'I', 'X' },
		{ 'S', 'E', 'V', 'E', 'N' },
		{ 'E', 'I', 'G', 'H', 'T' },
		{ 'N', 'I', 'N', 'E' }
	};

	final static char[] unique = new char[] {
		'Z', 'W', 'U', 'X', 'G', 'O', 'T', 'F', 'S', 'N'
	};

	final static int[] d = new int[] {
		0, 2, 4, 6, 8, 1, 3, 5, 7, 9
	};

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			char[] line = sc.next().toCharArray();
			boolean[] check = new boolean[line.length];

			ArrayList<Integer> ans = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				while (hasLetter(line, check, unique[i])) {
					ans.add(findNumber(line, check, d[i]));
				}
			}
			Collections.sort(ans);

			StringBuilder sb = new StringBuilder("Case #" + t + ": ");
			for (int i : ans) sb.append(i);
			System.out.println(sb.toString());
		}
	}

	static boolean hasLetter(char[] line, boolean[] check, char c) {
		for (int i = 0; i < line.length; i++)
			if (line[i] == c && !check[i])
				return true;
		return false;
	}

	static int findNumber(char[] line, boolean[] check, int d) {
		for (int i = 0; i < digits[d].length; i++) {
			boolean found = false;
			for (int j = 0; j < line.length && !found; j++) {
				if (line[j] == digits[d][i] && !check[j]) {
					check[j] = true;
					found = true;
				}
			}
		}
		return d;
	}
}