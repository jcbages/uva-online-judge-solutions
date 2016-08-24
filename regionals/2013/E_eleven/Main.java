import java.io.*

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			char[] N = line.toCharArray();
			int[] count = new int[11]; // count each mod [0, 10]
			for (int i = 0; i < N.length; i++) {
				int[] newcount = new int[11];
				for (int j = 0; j < N.length; j++) {
					int digit = N[j] - '0';
					if (digit > 0 && i % 2 != 0)
						digit = 11 - digit;
					for (int k = 0; k < count.length; k++) {
						int newvalue = (digit + k) % 11;
					}
				}
			}
		}
	}
}