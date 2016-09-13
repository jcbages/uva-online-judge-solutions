import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int N = Integer.parseInt(in.readLine());
		for (int n = 0; n < N; n++) {
			String line = in.readLine().replace(":", "");
			int hour = Integer.parseInt(line.substring(0, 2));
			int mins = Integer.parseInt(line.substring(2));
			do {
				if (mins == 59) {
					hour = (hour + 1) % 24;
				}
				mins = (mins + 1) % 60;
			} while (!isPalindrome(hour, mins));
			System.out.println(toString(hour, mins));
		}
	}

	static String toString(int hour, int mins) {
		String str = Integer.toString(hour * 100 + mins);
		for (; str.length() < 4;) {
			str = "0" + str;
		}
		return str.substring(0, 2) + ":" + str.substring(2);
	}

	static boolean isPalindrome(int hour, int mins) {
		String str = Integer.toString(hour * 100 + mins);
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}