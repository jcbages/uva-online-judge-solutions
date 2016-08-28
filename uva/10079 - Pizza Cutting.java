import java.io.*;
import java.math.*;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		BigInteger N = new BigInteger(in.readLine());
		while (N.compareTo(BigInteger.ZERO) >= 0) {
			BigInteger nPlusOne = N.add(BigInteger.ONE);
			BigInteger ans = N.multiply(nPlusOne);
			ans = ans.divide(BigInteger.valueOf(2));
			ans = ans.add(BigInteger.ONE);

			System.out.println(ans);

			N = new BigInteger(in.readLine());
		}
	}
}