import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigInteger;

public class Main {

	public static void main(String... args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			String ans = getAns(line);
			System.out.println(ans);
		}
	}

	static String getAns(String line) {
		if (line.length() == 0) {
			return "1/1";
		} else {
			BigInteger[] left  = new BigInteger[2];
			BigInteger[] right = new BigInteger[2];
			BigInteger[] ans   = new BigInteger[2];

			if (line.charAt(0) == 'L') {
				left[0]  = BigInteger.ZERO; left[1]  = BigInteger.ONE;
				right[0] = BigInteger.ONE;  right[1] = BigInteger.ONE;
			} else {	
				left[0]  = BigInteger.ONE;  left[1]  = BigInteger.ONE;
				right[0] = BigInteger.ONE; right[1] = BigInteger.ZERO;
			}

			for (int i = 1; i < line.length(); i++) {
				ans[0] = left[0].add(right[0]);
				ans[1] = left[1].add(right[1]);
				if (line.charAt(i) == 'L') {
					right = ans;
				} else {
					left  = ans;
				}
			}

			ans[0] = left[0].add(right[0]);
			ans[1] = left[1].add(right[1]);

			return ans[0] + "/" + ans[1];
		}
	}
}