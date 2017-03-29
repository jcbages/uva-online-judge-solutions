import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			long ans = 0;
			long[] ends = new long[3];
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if ('0' <= c && c <= '9') {
					int num = (c - '0') % 3;
					calculateEnds(ends, num);
					ans += ends[0];
				} else {
					ends[0] = ends[1] = ends[2] = 0;
				}
			}
			System.out.println(ans);
		}
	}

	static void calculateEnds(long[] ends, int num) {
		long[] newEnds = new long[3];
		for (int i = 0; i < 3; i++)
			newEnds[(i + num) % 3] = ends[i];
		for (int i = 0; i < 3; i++)
			ends[i] = newEnds[i];
		ends[num]++;
	}
}
