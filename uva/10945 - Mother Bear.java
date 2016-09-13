import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while (!(line = in.readLine()).equals("DONE")) {
			line = line.replaceAll("[\\p{Punct} ]", "");
			line = line.toLowerCase();
			boolean valid = true;
			for (int i = 0; i < line.length() / 2 && valid; i++) {
				valid = line.charAt(i) == line.charAt(line.length() - i - 1);
			}
			System.out.println(valid ? "You won't be eaten!" : "Uh oh..");
		}
	}
}