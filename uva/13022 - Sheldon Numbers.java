import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		ArrayList<Long> sheldonNumbers = getSheldonNumbers();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split("[ \t]+");
			long X = Long.parseLong(vals[0]);
			long Y = Long.parseLong(vals[1]);

			int ans = 0;
			for (long number : sheldonNumbers) {
				if (X <= number && number <= Y) {
					ans++;
				}
			}
			System.out.println(ans);
		}
	}

	static ArrayList<Long> getSheldonNumbers() {
		ArrayList<Long> ans = new ArrayList<Long>();
		for (int n = 1; n <= 63; n++) {
			char[] bits = new char[n];
			for (int i = 1; i <= bits.length; i++) {
				for (int j = 1; i + j <= bits.length; j++) {
					if (fillArr(bits, i, j)) {
						ans.add(Long.parseLong(String.valueOf(bits), 2));
					}
				}
			}
			fillArr(bits, 100, 0);
			ans.add(Long.parseLong(String.valueOf(bits), 2));
		}
		Collections.sort(ans);
		return ans;
	}

	static boolean fillArr(char[] bits, int n, int m) {
		try {
			for (int i = 0; i < bits.length;) {
				for (int j = 0; j < n; j++) {
					bits[i++] = '1';
				}

				if (i < bits.length) {
					for (int j = 0; j < m; j++) {
						bits[i++] = '0';
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}