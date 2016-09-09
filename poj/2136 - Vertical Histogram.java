import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			int[] freq = new int[26];
			for (int i = 0; i < 4; i++) {
				String s = sc.next();
				for (int j = 0; j < s.length(); j++) {
					char c = s.charAt(j);
					if ('A' <= c && c <= 'Z') {
						freq[c - 'A']++;
					}
				}
			}

			for (int i = 300; i > 0; i--) {
				int last = -1;
				for (int j = 0; j < 26; j++) {
					if (freq[j] >= i) {
						last = j;
					}
				}

				if (last > -1) {
					for (int j = 0; j < last; j++) {
						System.out.print(freq[j] >= i ? "* " : "  ");
					}
					System.out.println("*");
				}
			}

			for (int i = 0; i < 26; i++) {
				char letter = (char) ('A' + i);
				System.out.print(letter);
				if (i < 25) System.out.print(" ");
			}
			System.out.println();
		}
	}
}