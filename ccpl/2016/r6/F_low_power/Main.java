import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			// Read data
			String[] vals = line.split(" ");
			int n = Integer.parseInt(vals[0]);
			int k = Integer.parseInt(vals[1]);
			vals = in.readLine().split(" ");
			int[] batteries = new int[2 * n * k];
			for (int i = 0; i < 2 * n * k; i++)
				batteries[i] = Integer.parseInt(vals[i]);
			Arrays.sort(batteries);
			// Handle two special cases
			if (n == 1 && k == 1) {
				System.out.println(batteries[1] - batteries[0]);
				continue;
			} else if (n <= 1 && k <= 1) {
				System.out.println(0);
				continue;
			}
			// Get differences
			int[] differences = new int[2 * n * k - 1];
			for (int i = 0; i < 2 * n * k - 1; i++)
				differences[i] = batteries[i+1] - batteries[i];
			// Get answer
			int d1 = getMin(differences, n, 0);
			int d2 = getMin(differences, n, 1);
			System.out.println(Math.min(d1, d2));
		}
	}

	static int getMin(int[] differences, int n, int i) {
		int d = differences[i], cnt = 0;
		while (i < differences.length && cnt < 2 * n) {
			d = Math.max(d, differences[i]);
			i += 2;
			cnt++;
		}
		return (cnt < 2 * n) ? Integer.MAX_VALUE : d;
	}
}