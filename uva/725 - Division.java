import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N, caseNum = 0;
		while ((N = sc.nextInt()) != 0) {
			if (caseNum > 0) {
				System.out.println();
			}

			ArrayList<String[]> ans = new ArrayList<String[]>();
			for (int fghij = 1234; fghij <= 99999; fghij++) {
				int abcde = N * fghij;
				if (isCorrect(abcde, fghij)) {
					ans.add(new String[] { str(abcde), str(fghij) });
				}
			}
			Collections.sort(ans, new Comparator<String[]>() {
				@Override
				public int compare(String[] a, String[] b) {
					int d1 = a[0].compareTo(b[0]);
					int d2 = a[1].compareTo(b[1]);
					return d1 == 0 ? d2 : d1;
				}
			});

			if (ans.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String[] ss : ans) {
					sb.append(ss[0] + " / " + ss[1] + " = " + N);
					sb.append("\n");
				}
				System.out.print(sb);
			} else {
				System.out.printf("There are no solutions for %d.\n", N);
			}
			caseNum++;
		}
	}

	static boolean isCorrect(int abcde, int fghij) {
		boolean[] used = new boolean[10];
		boolean ans = check(abcde, used) && check(fghij, used);
		for (boolean b : used) {
			ans = ans && b;
		}
		return ans;
	}

	static boolean check(int n, boolean[] used) {
		String s = str(n);
		if (s.length() != 5) {
			return false;
		}

		boolean ans = (s.length() == 5);
		for (int i = 0; i < s.length() && ans; i++) {
			ans = ans && !used[s.charAt(i) - '0'];
			used[s.charAt(i) - '0'] = true;
		}
		return true;
	}

	static String str(int n) {
		String s = Integer.toString(n);
		if (s.length() == 4) {
			s = "0" + s;
		}
		return s;
	}
}