import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Main {
	final static BigInteger MAX = new BigInteger("1000000000000000");
	final static BigInteger MIN = new BigInteger("2");
	final static BigInteger[] k = new BigInteger[27];

	public static void main(String... args) throws IOException {
		k[0] = k[1] = BigInteger.ONE;
		for (int i = 2; i < k.length; i++) {
			k[i] = k[i-1].multiply(BigInteger.valueOf(i));
		}

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		
		int T = Integer.parseInt(in.readLine());
		for (int test = 0; test < T; test++) {
			long m = Long.parseLong(in.readLine());
			BigInteger bm = BigInteger.valueOf(m);
			
			ArrayList<Tuple> ans = new ArrayList<Tuple>();
			for (int i = 1; i < k.length; i++) {
				BigInteger mul = bm.multiply(k[i]);
				BigInteger val = getVal(mul, i);
				if (val != null) {
					BigInteger bi = BigInteger.valueOf(i);
					BigInteger sub = val.subtract(bi);
					ans.add(new Tuple(val.toString(), bi.toString()));
					if (sub.compareTo(BigInteger.valueOf(k.length)) >= 0) {
						ans.add(new Tuple(val.toString(), sub.toString()));
					}
				}
			}
			Collections.sort(ans);
			
			StringBuilder sb = new StringBuilder();
			sb.append(ans.size() + "\n");
			for (Tuple tuple : ans) {
				sb.append("(" + tuple.a + "," + tuple.b + ")");
				sb.append(" ");
			}
			System.out.println(sb.substring(0, sb.length()-1));
		}
	}

	static BigInteger getVal(BigInteger mul, int i) {
		BigInteger lo = MIN, hi = MAX;
		while (lo.compareTo(hi) <= 0) {
			BigInteger mid = (lo.add(hi)).divide(MIN);
			int cmp = f(mid, i).compareTo(mul);
			if (cmp == 0) {
				return mid;
			} else if (cmp < 0) {
				lo = mid.add(BigInteger.ONE);
			} else {
				hi = mid.subtract(BigInteger.ONE);
			}
		}
		return null;
	}

	static BigInteger f(BigInteger mid, int i) {
		BigInteger actual = mid;
		BigInteger ans = BigInteger.ONE;
		for (int j = 0; j < i; j++) {
			ans = ans.multiply(actual);
			actual = actual.subtract(BigInteger.ONE);
		}
		return ans;
	}

	static class Tuple implements Comparable<Tuple> {
		String a, b;

		public Tuple(String a, String b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Tuple that) {
			int da = new BigInteger(this.a).compareTo(new BigInteger(that.a));
			int db = new BigInteger(this.b).compareTo(new BigInteger(that.b));
			return (da == 0) ? db : da;
		}
	}
}