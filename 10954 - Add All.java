import java.io.*;
import java.util.*;
import java.math.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while (!(line = in.readLine()).equals("0")) {
			int N = Integer.parseInt(line);
			int[] numbers = getNumbers(in.readLine());
			String ans = getAns(N, numbers);
			System.out.println(ans);
		}
	}

	static String getAns(int N, int[] numbers) {
		BigInteger ans = BigInteger.valueOf(0);
		PriorityQueue<BigInteger> pq = new PriorityQueue<BigInteger>();
		for (int num : numbers) pq.add(BigInteger.valueOf(num));
		while (pq.size() >= 2) {
			BigInteger n1 = pq.poll(), n2 = pq.poll();
			ans = ans.add(n1); ans = ans.add(n2);
			pq.add(n1.add(n2));
		}
		return ans + "";
	}

	static int[] getNumbers(String line) {
		String[] vals = line.split(" ");
		int[] ans = new int[vals.length];
		for (int i = 0; i < vals.length; i++) {
			ans[i] = Integer.parseInt(vals[i]);
		}
		return ans;
	}
}