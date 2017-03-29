import java.util.Scanner;

class Main {
	static boolean PRINCE = false, PRINCESS = true;
	static int[] prince, princess;
	static boolean[] princeUsed, princessUsed;

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			prince = new int[3];
			princess = new int[3];

			princeUsed = new boolean[3];
			princessUsed = new boolean[3];

			princess[0] = sc.nextInt();
			princess[1] = sc.nextInt();
			princess[2] = sc.nextInt();
			prince[0] = sc.nextInt();
			prince[1] = sc.nextInt();
			if (princess[0] + princess[1] + princess[2] + prince[0] + prince[1] == 0) {
				break;
			}

			int ans = -1;
			for (int i = 1; i <= 52 && ans == -1; i++) {
				boolean valid = true;
				prince[2] = -1;
				for (int j = 0; j < 3; j++) {
					valid = valid && princess[j] != i && prince[j] != i;
				}
				if (valid) {
					prince[2] = i;
					ans = worstPlay(PRINCE, 0, -1) ? i : -1;
				}
			}
			System.out.println(ans);
		}
	}

	static boolean worstPlay(boolean turn, int lost, int prev) {
		if (lost >= 2) {
			return false;
		}

		boolean ans = true;
		for (int i = 0; i < 3; i++) {
			if (turn == PRINCE && !princeUsed[i]) {
				princeUsed[i] = true;
				ans = ans && worstPlay(!turn, lost, prince[i]);
				princeUsed[i] = false;
			}
			if (turn == PRINCESS && !princessUsed[i]) {
				princessUsed[i] = true;
				int newLost = prev < princess[i] ? lost + 1 : lost;
				ans = ans && worstPlay(!turn, newLost, -1);
				princessUsed[i] = false;
			}
		}
		return ans;
	}
}