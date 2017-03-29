import java.io.*;

class Main {
	static char[] opLetter, opNumber;

	public static void main(String... args) throws IOException {
		opLetter = new char[] {
			'A', '-', '-', '-', '3', '-', '-', 'H', 'I',
			'L', '-', 'J', 'M', '-', 'O', '-', '-', '-',
			'2', 'T', 'U', 'V', 'W', 'X', 'Y', '5'
		};

		opNumber = new char[] {
			'1', 'S', 'E', '-', 'Z', '-', '-', '8', '-'
		};

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			boolean mirror = isMirror(line);
			boolean palindrome = isPalindrome(line);
			if (mirror && palindrome) {
				System.out.printf("%s -- is a mirrored palindrome.\n", line);
			} else if (mirror) {
				System.out.printf("%s -- is a mirrored string.\n", line);
			} else if (palindrome) {
				System.out.printf("%s -- is a regular palindrome.\n", line);
			} else {
				System.out.printf("%s -- is not a palindrome.\n", line);
			}
			System.out.println();
		}
	}

	static boolean isMirror(String line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			sb.append(op(line.charAt(i)));
		}
		return sb.reverse().toString().equals(line);
	}

	static char op(char c) {
		if ('A' <= c && c <= 'Z') {
			return opLetter[c - 'A'];
		} else {
			return opNumber[c - '1'];
		}
	}

	static boolean isPalindrome(String line) {
		for (int i = 0; i < line.length() / 2; i++) {
			if (line.charAt(i) != line.charAt(line.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}