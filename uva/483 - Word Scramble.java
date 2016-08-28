import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			for (int j = 0; j < vals.length; j++) {
				for (int i = vals[j].length()-1; i >= 0; i--)
					System.out.print(vals[j].charAt(i));
				if (j < vals.length-1) System.out.print(" ");
			}
			System.out.println("");
		}
	}
}