import java.io.*;
import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int N = Integer.parseInt(in.readLine());
		for (int n = 0; n < N; n++) {
			if (n > 0) in.readLine();

			char[][] board = new char[3][3];
			for (int i = 0; i < 3; i++) {
				board[i] = in.readLine().toCharArray();
			}

			boolean xwin = win('X', board);
			boolean ywin = win('O', board);

			int xcount = count('X', board);
			int ycount = count('O', board);

			String ans = "yes";
			if (xwin && ywin) ans = "no";
			if (xcount < ycount || xcount > ycount + 1) ans = "no";
			if (xwin && xcount != ycount + 1) ans = "no";
			if (ywin && ycount != xcount) ans = "no";
			System.out.println(ans);
		}
	}

	static int count(char c, char[][] board) {
		int ans = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == c) ans++;
		return ans;
	}

	static boolean win(char c, char[][] board) {
		boolean winning = true;
		for (int i = 0; i < 3; i++) {
			winning = true;
			for (int j = 0; j < 3 && winning; j++) {
				winning = (board[i][j] == c);
			}
			if (winning) return winning;
		}

		for (int i = 0; i < 3; i++) {
			winning = true;
			for (int j = 0; j < 3 && winning; j++) {
				winning = (board[j][i] == c);
			}
			if (winning) return winning;
		}

		winning = true;
		for (int i = 0; i < 3 && winning; i++) {
			winning = (board[i][i] == c);
		}
		if (winning) return winning;

		winning = true;
		for (int i = 0; i < 3 && winning; i++) {
			winning = (board[2-i][i] == c);
		}
		if (winning) return winning;

		return false;
	}	
}