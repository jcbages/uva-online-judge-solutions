import java.io.*;
import java.util.*;
import java.math.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		Hashtable<String, BigInteger> memo = new Hashtable<String, BigInteger>();

		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line);
			String ans = getAns(n, 0, 0, memo).toString();
			System.out.println(ans);
		}
	}

	/* pos 0, 1, or 2. dir 0 for bottom, 1 for top */
	static BigInteger getAns(int n, int pos, int dir, Hashtable<String, BigInteger> memo) {
		String key = n + "-" + pos + "-" + dir;
		if (memo.get(key) != null) {
			return memo.get(key);
		}
		if (n == 0) {
			memo.put(key, BigInteger.ONE);
			return memo.get(key);
		} else {
			if (pos == 0) {
				BigInteger val = getAns(n-1, 1, 0, memo).add(getAns(n-1, 2, 0, memo));
				memo.put(key, val);
				return memo.get(key);
			} else if (pos == 1) {
				if (dir == 0) {
					BigInteger val = getAns(n-1, 0, 1, memo);
					memo.put(key, val);
					return memo.get(key);
				} else {
					BigInteger val = getAns(n-1, 2, 0, memo);
					memo.put(key, val);
					return memo.get(key);
				}
			} else /* if (pos == 2) */ {
				BigInteger val = getAns(n-1, 1, 1, memo).add(getAns(n-1, 0, 1, memo));
				memo.put(key, val);
				return memo.get(key);
			}
		}
	}
}