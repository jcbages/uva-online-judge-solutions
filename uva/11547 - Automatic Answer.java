import java.util.Scanner;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			long n = sc.nextLong();
			String ans = Long.toString((n * 567 / 9 + 7492) * 235 / 47 - 498);
			System.out.println(ans.length() == 1 ? "0" : ans.charAt(ans.length()-2) + "");
		}
	}
}