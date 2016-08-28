import java.io.*;
import java.math.BigInteger;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int n = line.length();
			BigInteger number = new BigInteger(line);
			String square = number.multiply(number).toString();
			if (number.equals(BigInteger.ZERO) || number.equals(BigInteger.ONE)) {
				System.out.println("Not an Automorphic number.");
			} else if (square.indexOf(line, square.length() - n) != -1) {
				System.out.printf("Automorphic number of %d-digit.\n", n);
			} else {
				System.out.println("Not an Automorphic number.");
			}
		}
	}
}