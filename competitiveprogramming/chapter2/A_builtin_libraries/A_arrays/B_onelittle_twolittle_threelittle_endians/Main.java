import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int a = Integer.parseInt(line), b = 0;
			for (int i = 0; i < 4; i++) {
				b |= getChar(a, i);
			}
			String ans = String.format("%d converts to %d", a, b);
			System.out.println(ans);
		}
	}

	static int getChar(int n, int i) {
		n >>= (3-i) * 8;
		n  &= 255;
		n <<= i * 8;
		return n;
	}
}