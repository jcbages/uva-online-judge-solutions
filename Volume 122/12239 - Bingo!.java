import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int N = sc.nextInt(), B = sc.nextInt();
			if (N == 0 && B == 0) break;
			
			int[] numbers = new int[B];
			for (int i = 0; i < B; i++) {
				numbers[i] = sc.nextInt();
			}

			boolean valid = true;
			for (int i = 0; i < N + 1 && valid; i++) {
				valid = false;
				outerloop:
				for (int a : numbers) {
					for (int b : numbers) {
						if (Math.abs(a - b) == i) {
							valid = true;
							break outerloop;
						}
					}
				}
			}

			System.out.println(valid ? "Y" : "N");
		}
	}
}