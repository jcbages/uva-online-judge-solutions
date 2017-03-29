import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals1 = line.split(" ");
			String[] vals2 = in.readLine().split(" ");
			boolean valid = true;
			for (int i = 0; i < vals1.length && valid; i++) {
				valid = !vals1[i].equals(vals2[i]);
			}
			System.out.println(valid ? "Y" : "N");
		}
	}
}