import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			Set<String> set = new HashSet<String>();
			for (int i = 0; i < line.length(); i++) {
				for (int j = i + 1; j < line.length() + 1; j++) {
					String test = line.substring(i, j);
					if (isPalindrome(test)) {
						set.add(test);
					}
				}
			}
			System.out.printf("The string '%s' contains %d palindromes.\n", line, set.size());
		}
	}

	static boolean isPalindrome(String test) {
		for (int i = 0; i < test.length() / 2; i++) {
			if (test.charAt(i) != test.charAt(test.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}