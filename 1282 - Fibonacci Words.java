import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Main {
	static int MAX = 101;
	static BigInteger[] fibtable = new BigInteger[MAX];
	static String[] fibpattern;

	public static void main(String... args) throws IOException {
		fibtable[0] = BigInteger.ONE; fibtable[1] = BigInteger.ONE;
		for (int i = 2; i < MAX; i++) fibtable[i] = fibtable[i-1].add(fibtable[i-2]);

		int size = 2;
		ArrayList<String> patterns = new ArrayList<String>();
		patterns.add("0"); patterns.add("1");
		for (int i = 2; patterns.get(size - 1).length() <= 100000; i++) {
			patterns.add(patterns.get(i - 1) + patterns.get(i - 2));
			size++;
		}
		fibpattern = new String[patterns.size()];
		patterns.toArray(fibpattern);

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int caseNumber = 1;
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line);
			String pattern = in.readLine();
			long ans = getans(pattern, n);
			System.out.printf("Case %d: %d\n", caseNumber++, ans);
		}
	}

	static long getans(String pattern, int n) {
		long[] table = new long[n + 1];
		BigInteger len = BigInteger.valueOf(pattern.length());
		for (int i = 0; i <= n; i++) {
			if (fibtable[i].compareTo(len) < 0) {
				table[i] = 0;
			} else if (fibtable[i].compareTo(len) == 0) {
				table[i] = fibpattern[i].indexOf(pattern) + 1;
			} else {
				table[i] = getoccurences(pattern, len, i, table); 
			}
		}
		return table[n];
	}

	static long getoccurences(String pattern, BigInteger len, int n, long[] table) {
		String string = "";
		if (n < fibpattern.length) {
			string = fibpattern[n];
		} else {
			string = getlast(pattern, len, n - 1) + getfirst(pattern, len, n - 2);
		}
		int init = string.indexOf(pattern, 0);
		long ans = 0;
		while (init != -1) {
			ans++;
			init = string.indexOf(pattern, init + 1);
		}
		return (n < fibpattern.length) ? ans : ans + table[n - 1] + table[n - 2];
	}

	static String getlast(String pattern, BigInteger len, int n) {
		if (n <= 1) return "";
		if (n >= fibpattern.length) return getlast(pattern, len, n - 2);
		if (fibtable[n - 2].compareTo(len) < 0) {
			int init = fibpattern[n].length() - pattern.length() + 1;
			return (init >= 0) ? fibpattern[n].substring(init) : fibpattern[n];
		}
		return getlast(pattern, len, n - 2);
	}

	static String getfirst(String pattern, BigInteger len, int n) {
		if (n <= 1) return "";
		if (n >= fibpattern.length) return getfirst(pattern, len, n - 1);
		if (fibtable[n - 1].compareTo(len) < 0) {
			int end = Math.min(fibpattern[n].length(), pattern.length() - 1);
			return fibpattern[n].substring(0, end);
		}
		return getfirst(pattern, len, n - 1);
	}
}