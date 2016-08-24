import java.util.*;

class Main {
	final static int LIMIT = 10000000;

	public static void main(String... args) {
		ArrayList<Integer> primes = getPrimes();
		Scanner sc = new Scanner(System.in);
		while (true) {
			long n = sc.nextLong();
			if (n == 1)
				break;
			int ans = getAns(n, primes);
			System.out.println(ans);
		}
	}

	static ArrayList<Integer> getPrimes() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		boolean[] candidates = new boolean[LIMIT+1];
		for (int i = 2; i <= LIMIT; i++) {
			if (!candidates[i]) {
				for (int j = i; j <= LIMIT; j += i) {
					candidates[j] = true;
				}
				ans.add(i);
			}
		}
		return ans;
	}

	static int getAns(long n, ArrayList<Integer> primes) {
		int ans = 0, i = 0;
		while (n > 1 && i < primes.size()) {
			if (n % primes.get(i) == 0) {
				ans++;
				n /= primes.get(i);
			} else {
				i++;
			}
		}
		if (n != 1)
			ans++;
		return ans;
	}
}