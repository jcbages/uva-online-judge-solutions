import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			StringBuilder a = new StringBuilder(sc.next());
			StringBuilder b = new StringBuilder(sc.next());
			int sum = Integer.parseInt(a.reverse().toString()) +
					Integer.parseInt(b.reverse().toString());
			StringBuilder sb = new StringBuilder(Integer.toString(sum));
			int ans = Integer.parseInt(sb.reverse().toString());
			System.out.println(ans);
		}
	}
}