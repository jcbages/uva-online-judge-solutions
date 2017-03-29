import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int N = Integer.parseInt(in.readLine());
		for (int x = 0; x < N; x++) {
			String[] dictionary = in.readLine().split(" ");
			String[] target = in.readLine().split(" ");
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < target.length; i++) {
				ans.append(getWord(target[i], dictionary));
				if (i < target.length-1) ans.append(" ");
			}
			System.out.println(ans);
		}
	}

	static String getWord(String word, String[] dictionary) {
		String ans = word;
		for (String dict : dictionary) {
			if (dict.charAt(0) != word.charAt(0)) {
				continue;
			}
			if (dict.charAt(dict.length()-1) != word.charAt(word.length()-1)) {
				continue;
			}
			int[] count = new int[26];
			for (int i = 0; i < word.length(); i++) {
				count[word.charAt(i) - 'a'] += 1;
			}
			for (int i = 0; i < dict.length(); i++) {
				count[dict.charAt(i) - 'a'] -= 1;
			}
			boolean valid = true;
			for (int i = 0; i < 26 && valid; i++) {
				valid = count[i] == 0;
			}
			if (valid) {
				return dict;
			}
		}
		return ans;
	}
}