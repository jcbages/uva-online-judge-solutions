import java.io.*;

/*
nums[0] = p
nums[1] = a
nums[2] = b
nums[3] = c
nums[4] = d
nums[5] = n
*/

public class Main {

	static double[] sin, cos;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int[] nums = new int[6];
			for (int i = 0; i < 6; i++) {
				nums[i] = Integer.parseInt(vals[i]);
			}
			double ans = getAns(nums);
			System.out.println(ans);
		}
	}

	static double getAns(int[] nums) {
		double ans = 0;
		double max = getPrice(nums, 1);
		double act = 0;
		for (int k = 1; k <= nums[5]; k++) {
			act = getPrice(nums, k);
			max = Math.max(act, max);
			ans = Math.max(max - act, ans);
		}
		return ans;
	}

	static double getPrice(int[] nums, int k) {
		int v1 = nums[1] * k + nums[2];
		int v2 = nums[3] * k + nums[4];
		double d1 = Math.sin(v1 % (2 * Math.PI));
		double d2 = Math.cos(v2 % (2 * Math.PI));
		return nums[0] * (d1 + d2 + 2);
	}
}