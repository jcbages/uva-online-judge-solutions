import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while (!(line = in.readLine()).equals("*")) {
			String[] vals = line.split(" ");
			char letter = vals[0].toLowerCase().charAt(0);
			boolean valid = true;
			for (int i = 1; i < vals.length && valid; i++) {
				valid = vals[i].toLowerCase().charAt(0) == letter;
			}
			System.out.println(valid ? "Y" : "N");
		}
	}
}