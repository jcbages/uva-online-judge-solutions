import java.util.*;

class Main {

	final static int ASC = 0, DSC = 1;

	public static void main(String... args) {
		try {
			Scanner sc=new Scanner(System.in);
			for (;sc.hasNextInt();) {
				int N = sc.nextInt();
				int[] arr = new int[N];
				for (int i = 0; i < N; i++) {
					arr[i] = sc.nextInt();
				}
				int ans = getAns(N, arr);
				System.out.println(ans);
			}
		} catch (Exception e) {
			// Nothing to do here
		} finally {
			System.exit(0);
		}
	}

	static int getAns(int N, int[] arr) {
		int[] incr = LIS(N, arr);
		arr = reverse(arr, N);
		int[] decr = LIS(N, arr);
		decr = reverse(decr, N);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(2 * Math.min(incr[i], decr[i]) - 1, ans);
		}
		return ans;
	}

	static int[] LIS(int N, int[] arr) {
		int[] L = new int[N];
		int[] I = new int[N+1];

		I[0] = Integer.MIN_VALUE;
		for (int i = 1; i < N+1; i++) {
			L[i-1] = 1;
			I[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 1; i < N+1; i++) {
			int low, mid, high;
			low = 0;
			high = i;
			while (low <= high) {
				mid = (low + high) / 2;
				if (I[mid] < arr[i-1])
					low = mid + 1;
				else
					high = mid - 1;
			}
			I[low] = arr[i-1];
			L[i-1] = low;
		}

		return L;
	}

	static int[] reverse(int[] arr, int N) {
		int[] ans = new int[N];
		for (int i = 0; i < N; i++)
			ans[N-i-1] = arr[i];
		return ans;
	}
}