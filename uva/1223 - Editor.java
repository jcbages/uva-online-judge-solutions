import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			String line = in.readLine();
			Integer[] prefix = new Integer[line.length()];
			for (int i = 0; i < line.length(); i++)
				prefix[i] = i;
			Arrays.sort(prefix, new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b) {
					return line.substring(a).compareTo(line.substring(b));
				}
			});
			int ans = 0;
			for (int i = 0; i < line.length() - 1; i++) {
				int index = 0;
				while (true) {
					int i0 = prefix[i] + index;
					int i1 = prefix[i+1] + index;
					if (i0 >= line.length()) break;
					if (i1 >= line.length()) break;
					if (line.charAt(i0) != line.charAt(i1)) break;
					index++;
				}
				ans = Math.max(ans, index);
			}
			System.out.println(ans);
		}
	}
}