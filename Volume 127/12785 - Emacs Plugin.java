import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line);
			String t = in.readLine();
			for (int i = 0; i < n; i++) {
				int last = -1;
				String[] patterns = in.readLine().split("\\*");
				String ans = "yes";
				for (String p : patterns) {
					if (p.length() == 0) {
						continue;
					} else if (p.length() < 10) {
						last = t.indexOf(p, last+1);
					} else {
						last = kmp(t, p, last+1);
					}
					if (last == -1) {
						ans = "no";
						break;
					}
					last += p.length() - 1;
				}
				System.out.println(ans);
			}
		}
	}

	static int kmp(String t, String p, int last) {
		int i = 0;
		int[] T = table(p);
		while (last + i < t.length()) {
			if (p.charAt(i) == t.charAt(last + i)) {
				if (i == p.length() - 1) {
					return last;
				}
				i += 1;
			} else {
				if (T[i] > -1) {
					last += i - T[i];
					i = T[i];
				} else {
					i = 0;
					last += 1;
				}
			}
		}
		return -1;
	}

	static int[] table(String p) {
		int[] T = new int[p.length()];
		int pos = 2;
		int cnd = 0;
		T[0] = -1;
		T[1] = 0;
		while (pos < p.length()) {
			if (p.charAt(pos-1) == p.charAt(cnd)) {
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
}