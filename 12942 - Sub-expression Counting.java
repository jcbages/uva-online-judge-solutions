import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line, alpha, beta;
		while ((line = in.readLine()) != null) {
			alpha = processString(line);
			beta = processString(in.readLine());
			int ans = getAns(alpha, beta);
			System.out.println(ans);
		}
	}

	static int getAns(String alpha, String beta) {
		int ans = 0, last = 0;
		while (last != -1) {
			if (alpha.length() > 3) last = kmp(beta, alpha, last);
			else last = beta.indexOf(alpha, last);
			if (last != -1) { ans += 1; last += 1; }
		}
		return ans;
	}

	static int kmp(String S, String W, int last) {
		int m = last, i = 0;
		int[] T = table(W);
		while (m+i < S.length()) {
			if (W.charAt(i) == S.charAt(m+i)) {
				if (i == W.length()-1) return m;
				i += 1;
			} else {
				if (T[i] > -1) {
					m += i - T[i];
					i = T[i];
				} else {
					i = 0;
					m += 1;
				}
			}
		}
		return -1;
	}

	static int[] table(String W) {
		int pos = 2, cnd = 0;
		int[] T = new int[W.length()];
		T[0] = -1; T[1] = 0;
		while (pos < W.length()) {
			if (W.charAt(pos-1) == W.charAt(cnd)) {
				T[pos] = cnd + 1;
				cnd += 1;
				pos += 1;
			} else if (cnd > 0) {
				cnd = T[cnd];
			} else {
				T[pos] = 0;
				pos += 1;
			}
		}
		return T;
	}

	static String processString(String line) {
		StringBuilder ans = new StringBuilder();
		boolean inNumber = false;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (inNumber && (c - '0' < 0 || c - '0' > 9)) {
				inNumber = false;
				ans.append('x');
			}

			if (c == '+' || c == '-' || c == '*' || c == '/') {
				ans.append('y');
			} else if (c - 'a' >= 0 && c - 'a' <= 26) {
				ans.append('x');
			} else if (c - '0' >= 0 && c - '0' <= 9) {
				inNumber = true;
			} else {
				ans.append(c);
			}
		}
		if (inNumber) ans.append('x');
		return ans.toString();
	}
}