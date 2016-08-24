import java.util.*;
import java.math.BigInteger;

class Main {
	final static int N = 500;
	final static BigInteger MIN16 = new BigInteger("1000000000000001", 2);
	final static BigInteger MIN32 = new BigInteger("10000000000000000000000000000001", 2);
	final static BigInteger THREE = BigInteger.valueOf(3);
	final static BigInteger SIX = BigInteger.valueOf(6);

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1, T = sc.nextInt(); t <= T; t++) {
			int N = sc.nextInt();
			int J = sc.nextInt();
			System.out.printf("Case #%d:\n", t);

			BigInteger actual = (N == 16) ? MIN16 : MIN32;
			while (J > 0) {
				actual = actual.add(SIX);
				boolean isInvalid = false;
				for (int i = 2; i <= 10 && !isInvalid; i++) {
					BigInteger val = new BigInteger(actual.toString(2), i);
					BigInteger mod = new BigInteger(THREE.toString(2), i);
					isInvalid = val.mod(mod).compareTo(BigInteger.ZERO) != 0;
				}
				if (isInvalid)
					continue;
				System.out.print(actual.toString(2));
				for (int i = 2; i <= 10; i++) {
					BigInteger K = new BigInteger(THREE.toString(2), i);
					System.out.printf(" %s", K);
				}
				System.out.println();
				J--;
			}
		}
	}
}
