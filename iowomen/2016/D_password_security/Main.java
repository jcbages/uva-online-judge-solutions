import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			// Read test case
			int N = Integer.parseInt(in.readLine());
			String[] P = in.readLine().split(" ");

			// Process test case
			String ans = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			boolean valid = false;
			for (int i = 0; i < 10000 && !valid; i++) {
				if (i > 0)
					ans = shuffle(ans);
				valid = true;
				for (int j = 0; j < N && valid; j++)
					valid = valid && ans.indexOf(P[j]) == -1;
			}
			if (!valid)
				ans = "IMPOSSIBLE";
			System.out.printf("Case #%d: %s\n", t+1, ans);
		}
	}

	static String shuffle(String str) {
		// Generate arraylist of letters and shuffle
		ArrayList<Character> arr = new ArrayList<Character>();
		for (int i = 0; i < 26; i++)
			arr.add(str.charAt(i));
		Collections.shuffle(arr);

		// Generate and return corresponding string
		StringBuilder ans = new StringBuilder();
		for (char c : arr)
			ans.append(c);
		return ans.toString();
	}
}