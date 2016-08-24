import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(in.readLine());
			int[] walls = getWalls(in.readLine());
			String ans = getAns(walls, i);
			System.out.println(ans);
		}
	}

	static String getAns(int[] walls, int num) {
		int high = 0, low = 0, me = walls[0];
		for (int i = 1; i < walls.length; i++) {
			int next = walls[i];
			if (next > me) high++;
			if (next < me) low++;
			me = next;
		}
		return String.format("Case %d: %d %d", num, high, low);
	}

	static int[] getWalls(String line) {
		String[] vals = line.split(" ");
		int[] ans = new int[vals.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = Integer.parseInt(vals[i]);
		}
		return ans;
	}
}