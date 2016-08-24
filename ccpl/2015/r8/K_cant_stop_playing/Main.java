import java.io.*;
import java.util.*;

public class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(in.readLine());
			String[] vals = in.readLine().split(" ");

			int[] nums = new int[n];
			for (int j = 0; j < n; j++) {
				nums[j] = Integer.parseInt(vals[j]);
			}

			String ans = getAns(n, nums);
			System.out.println(ans);
		}
	}

	static String getAns(int n, int[] nums) {
		StringBuilder ans = new StringBuilder("r");
		LinkedList<Integer> game = new LinkedList<Integer>();
		game.add(nums[0]);
		for (int i = 1; i < n; i++) {
			makeMovement();
		}
	}
}