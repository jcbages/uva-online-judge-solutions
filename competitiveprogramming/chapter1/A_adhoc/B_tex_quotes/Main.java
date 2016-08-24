import java.io.*;

class Main {
	final static int N = 0, Y = 1;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int s = N;
		String line;
		while ((line = in.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '"' && s == N) {
					s = Y;
					System.out.print("``");
				} else if (line.charAt(i) == '"' && s == Y) {
					s = N;
					System.out.print("''");
				} else {
					System.out.print(line.charAt(i));
				}
			}
			System.out.println("");
		}
	}
}