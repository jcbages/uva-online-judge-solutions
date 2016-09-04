import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N;
		while ((N = sc.nextInt()) != 0) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			int max = 0, curr = 0;
			for (int i = 0; i < N; i++) {
				curr = Math.max(curr + arr[i], arr[i]);
				max = Math.max(max, curr);
			}
			if (max > 0) {
				System.out.printf("The maximum winning streak is %d.\n", max);
			} else {
				System.out.println("Losing streak.");
			}
		}
	}
}