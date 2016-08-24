import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			String line = sc.next();
			String ans = getAns(line);
			System.out.printf("Case #%d: %s\n", t+1, ans);
		}
	}

	static String getAns(String line) {
		LinkedList<Character> list = new LinkedList<Character>();
		for (int i = 0; i < line.length(); i++) {
			if (list.size() == 0) {
				list.addFirst(line.charAt(i));
			} else {
				if (line.charAt(i) >= list.getFirst()) {
					list.addFirst(line.charAt(i));
				} else {
					list.addLast(line.charAt(i));
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		for (Character c : list) {
			ans.append(c);
		}
		return ans.toString();
	}
}