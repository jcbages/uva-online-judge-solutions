import java.util.Arrays;

class Main {
	final static int N = 1000000;

	public static void main(String... args) {
		int[] arr = new int[N];
		Arrays.fill(arr, -1);
		for (int i = 0; i < N; i++) {
			int val = d(i+1)-1;
			if (val < N) {
				arr[val] = i+1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (arr[i] == -1) {
				sb.append(i+1);
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

	static int d(int n) {
		int ans = n;
		while (n > 0) {
			ans += (n % 10);
			n /= 10;
		}
		return ans;
	}
}