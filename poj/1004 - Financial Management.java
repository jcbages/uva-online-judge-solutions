import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		double sum = 0.0;
		for (int i = 0; i < 12; i++) {
			sum += sc.nextDouble();
		}
		System.out.printf("$%.2f\n", sum / 12.0);
	}
}