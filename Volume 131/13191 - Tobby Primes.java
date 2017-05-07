import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Main {
	static Random rand = new Random();
	static ArrayList<Integer> primes;
	static ArrayList<Long> ans;

	static void getPrimes() {
		primes = new ArrayList<Integer>();
		final int max = 1000005;
		boolean[] flags = new boolean[max];
		for (int i = 2; i < max; i++) {
			if (!flags[i]) {
				for (int j = i; j < max; j += i) {
					flags[j] = true;
				}
				primes.add(i);
			}
		}
	}

	static long pollardRho(BigInteger n) {
		BigInteger x = BigInteger.valueOf(rand.nextLong()).mod(n);
		BigInteger y = x;
		BigInteger d;

		long i = 1;
		long k = 2;

		while (true) {
			i++;
			x = ((x.multiply(x)).subtract(BigInteger.ONE)).mod(n);
			d = (y.subtract(x)).gcd(n);
			if (d.compareTo(BigInteger.ONE) != 0 && d.compareTo(n) != 0) {
				return d.longValue();
			}
			if (i == k) {
				y = x;
				k = 2*k;
			}
		}
	}

	static void getFactors(long n) {
		for (int i = 0; i < primes.size() && n > 1;) {
			if (n % primes.get(i) == 0) {
				ans.add((long) primes.get(i));
				n /= primes.get(i);
			} else {
				i++;
			}
		}

		if (n > 1) {
			BigInteger val = BigInteger.valueOf(n);
			if (val.isProbablePrime(10)) {
				ans.add(n);
			} else {
				long divisor = pollardRho(val);
				if (divisor > n/divisor) {
					ans.add(n/divisor);
					ans.add(divisor);
				} else {
					ans.add(divisor);
					ans.add(n/divisor);
				}
			}
		}
	}

	public static void main(String... args) throws IOException {
		// Generate primes
		getPrimes();

		// Init fast IO
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();

		// Read number of test cases
		int T = Integer.parseInt(in.readLine());

		// Iterate foreach test case
		for (int t = 0; t < T; t++) {
			// Init the answer vector
			ans = new ArrayList<Long>();

			// Read the n value
			long n = Long.parseLong(in.readLine());

			// Get the factors of n
			getFactors(n);

			// Append the answer to the stringbuilder
			sb.append(ans.get(0));
			for (int i = 1; i < ans.size(); i++) {
				sb.append(' ');
				sb.append(ans.get(i));
			}
			sb.append('\n');
		}

		// Print the final stringbuilder
		System.out.print(sb);
	}
}
