import java.io.*;
import java.util.*;

class Main {

	final static String[][] vals = {
		{ "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
		{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
		{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
		{ "M", "MM", "MMM" }
	};

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String ans = "";
			try {
				Integer.parseInt(line);
				ans = getAns(line, true);
			} catch (NumberFormatException e) {
				ans = getAns(line, false);
			}
			System.out.println(ans);
		}
	}

	static String getAns(String line, boolean isInteger) {
		return isInteger ? toRoman(line) : toInteger(line);
	}

	static String toRoman(String line) {
		int n = line.length();
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int j = line.charAt(i) - '0';
			if (j > 0)
				ans.append(vals[n-i-1][j-1]);
		}
		return ans.toString();
	}

	static String toInteger(String line) {
		String ans = null;
		for (int i = 1; i < 4000 && ans == null; i++) {
			String s = Integer.toString(i);
			if (toRoman(s).equals(line))
				ans = s;
		}
		return ans;
	}
}
