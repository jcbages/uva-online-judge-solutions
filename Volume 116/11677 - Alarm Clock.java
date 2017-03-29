import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int h1 = sc.nextInt();
			int m1 = sc.nextInt();
			int h2 = sc.nextInt();
			int m2 = sc.nextInt();
			int ans = 0;
			if (h1 == 0 && m1 == 0 && h2 == 0 && m2 == 0)
				break;
			while (h1 != h2 || m1 != m2) {
				m1++;
				if (m1 == 60) {
					m1 = 0;
					h1++;
				}
				if (h1 == 24) {
					h1 = 0;
				}
				ans++;
			}
			System.out.println(ans);
		}
	}
}