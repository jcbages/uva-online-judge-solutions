import java.io.*;
import java.math.BigInteger;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int n = Integer.parseInt(vals[0]);
			String d = vals[1];
			String ans = getAns(n, d);
			System.out.println(ans);
		}
	}

	static String getAns(int n, String d) {
		StringBuilder ans = new StringBuilder();
		String palindrome = getFirstPalindrome(d);
		ans.append(palindrome);
		for (int i = 1; i < n; i++) {
			palindrome = getNextPalindrome(palindrome);
			ans.append("\n");
			ans.append(palindrome);
		}
		return ans.toString();
	}

	static String getFirstPalindrome(String d) {
		int n = d.length();
		String half = d.substring(0, n/2);
		
		StringBuilder aux = new StringBuilder(half);
		String palindrome = null;
		if (n % 2 == 0) {
			palindrome = aux.toString() + aux.reverse().toString();
		} else {
			char extra = d.charAt(n/2);
			palindrome = aux.toString() + extra + aux.reverse().toString();
		}

		BigInteger a = new BigInteger(palindrome);
		BigInteger b = new BigInteger(d);
		while (a.compareTo(b) <= 0) {
			palindrome = getNextPalindrome(palindrome);
			a = new BigInteger(palindrome);
		}

		return palindrome;
	}

	static String getNextPalindrome(String d) {
		int n = d.length();
		String half = (n % 2 == 0) ? d.substring(0, n/2) : d.substring(0, n/2+1);
		StringBuilder palindrome = new StringBuilder(half);
		
		int i = (n % 2 == 0) ? n/2-1 : n/2;
		boolean acc = true;
		while (i >= 0 && acc) {
			int number = half.charAt(i) - '0' + 1;
			if (number == 10) number = 0; else acc = false;
			palindrome.replace(i, i+1, Integer.toString(number));
			i -= 1;
		}
		if (i < 0 && acc) palindrome.insert(0, 1);

		String ans = null;
		if (acc) {
			int m = palindrome.length();
			char extra = palindrome.charAt(m-1);
			palindrome.replace(m-1, m, "");
			if (n % 2 != 0) {
				ans = palindrome.toString() + palindrome.reverse().toString();
			} else {
				ans = palindrome.toString() + extra + palindrome.reverse().toString(); 
			}
		} else {
			if (n % 2 == 0) {
				ans = palindrome.toString() + palindrome.reverse().toString();
			} else {
				int m = palindrome.length();
				char extra = palindrome.charAt(m-1);
				palindrome.replace(m-1, m, "");
				ans = palindrome.toString() + extra + palindrome.reverse().toString();
			}
		}
		return ans;
	}
}