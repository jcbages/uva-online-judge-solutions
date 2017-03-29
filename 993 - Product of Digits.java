import java.util.*;
import java.math.*;

public class Main {
	static Hashtable<Integer, String> map;

	public static void main(String... args) {
		Scanner s = new Scanner(System.in);
		
		int T = s.nextInt();
		for (int i = 0; i < T; i++) {
			map = new Hashtable<Integer, String>();

			int N = s.nextInt();
			String ans = getAns(N);
			System.out.println(ans);
		}
	}

	static String getAns(int N) {
		if (map.get(N) == null) {
			if (N < 10) {
				map.put(N, N + "");
			} else {
				ArrayList<String> cands = new ArrayList<String>();
				for (int i = 2; i < 10; i++) {
					if (N % i == 0) {
						String subAns = getAns(N / i);
						if (!subAns.equals("-1")) {
							cands.add(i + getAns(N / i));
						}
					}
				}
				map.put(N, getMin(cands));
			}
		}
		return map.get(N);
	}

	static String getMin(ArrayList<String> cands) {
		if (cands.size() == 0) {
			return "-1";
		} else {
			BigInteger min = new BigInteger(cands.get(0));
			for (String c : cands) {
				BigInteger cand = new BigInteger(c);
				min = (min.compareTo(cand) > 0) ? cand : min;
			}
			return min.toString();
		}
	}
}