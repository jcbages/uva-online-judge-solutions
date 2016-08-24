import java.io.*;
import java.util.ArrayList;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		String line;
		while ((line = in.readLine()) != null) {
			String t = in.readLine();

			ArrayList<String> pn = new ArrayList<String>();
			int n = Integer.parseInt(line);
			for (int i = 0; i < n; i++) {
				pn.add(in.readLine());
			}

			String ans = getAns(t, pn);
			System.out.println(ans);
		}
	}

	static String getAns(String t, ArrayList<String> pn) {
		StringBuilder ans = new StringBuilder();
		for (String p : pn) {
			boolean match = getMatch(t, p);
			ans.append(match ? "yes\n" : "no\n");
		}
		return ans.substring(0, ans.length() - 1);
	}

	static boolean getMatch(String t, String p) {
		int lastIndex = 0, currIndex = -1;
		String[] tokens = p.split("\\*");
		for (String token : tokens) {
			if (token.length() > 0) {
				if (token.length() > 1) {
					currIndex = match(t, token, lastIndex);
				} else {
					currIndex = t.indexOf(token, lastIndex);
				}

				if (currIndex == -1) {
					return false;
				} else {
					lastIndex = currIndex + token.length();
				}
			}
		}
		return true;
	}

	static int match(String S, String W, int m) {
		int i = 0;
		int[] T = getTable(W);

		while (m + i < S.length()) {
			if (W.charAt(i) == S.charAt(m + i)) {
				if (i == W.length() - 1) {
					return m;
				}
				i++;
			} else {
				if (T[i] > -1) {
					m += i - T[i];
					i = T[i];
				} else {
					m++;
					i = 0;
				}
			}
		}

		return -1;
	}

	static int[] getTable(String W) {
		int[] T = new int[W.length()];
		int pos = 2, cnd = 0;

		T[0] = -1; T[1] = 0;

		while (pos < W.length()) {
			if (W.charAt(pos - 1) == W.charAt(cnd)) {
				cnd++; T[pos] = cnd; pos++;
			} else if (cnd > 0) {
				cnd = T[cnd];
			} else {
				T[pos] = 0; pos++;
			}
		}

		return T;
	}
}