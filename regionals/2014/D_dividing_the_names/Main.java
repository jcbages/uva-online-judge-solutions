import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			String[] words = new String[2 * N];
			for (int i = 0; i < 2 * N; i++) {
				words[i] = in.readLine();
			}
			Arrays.sort(words);
			String[] g1 = new String[N];
			String[] g2 = new String[N];
			int ig1 = 0, ig2 = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (i % 2 == 0) {
					g1[ig1++] = words[i];
				} else {
					g2[ig2++] = words[i];
				}
			}
			long ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans += getDiff(g1[i], g2[j]);
				}
			}
			System.out.println(ans);
		}
	}

	static long getDiff(String a, String b) {
		long ans = 0;
		int i = 0, j = 0;
		while (i < a.length() || j < b.length()) {
			if (a.charAt(i) == b.charAt(j)) {
				ans++;
			} else {
				break;
			}
			i++;
			j++;
		}
		System.out.println(a + " - " + b + " : " + (ans + 1));
		return ans + 1;
	}
}