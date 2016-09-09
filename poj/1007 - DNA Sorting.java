import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[] strings = new String[m];
		for (int i = 0; i < m; i++) {
			strings[i] = sc.next();
		}
		Arrays.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return unsortedness(a) - unsortedness(b);
			}
		});
		for (String s : strings) {
			System.out.println(s);
		}
	}

	static int unsortedness(String s) {
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) > s.charAt(j)) {
					ans++;
				}
			}
		}
		return ans;
	}
}