import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int i = sc.nextInt();
			int j = sc.nextInt();
			if (i > j) {
				int k = i;
				i = j;
				j = k;
			}
			int ans = 1;
			while (i + 1 != j || j % 2 != 0) {
				i = (i % 2 == 0) ? i/2 : (i + 1)/2;
				j = (j % 2 == 0) ? j/2 : (j + 1)/2;
				ans += 1;
			}
			System.out.println(ans);
		}
	}
}