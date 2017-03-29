import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLong()) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			System.out.println(Math.abs(A - B));
		}
	}
}