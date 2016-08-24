import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int T = sc.nextInt(), ans = 0;
			for (int i = 0; i < 5; i++) if (sc.nextInt() == T) ans += 1;
			System.out.println(ans);
		}
	}
}
