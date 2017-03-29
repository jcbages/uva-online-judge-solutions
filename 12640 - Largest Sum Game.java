import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int[] nums = new int[vals.length];
			for (int i = 0; i < vals.length; i++) {
				nums[i] = Integer.parseInt(vals[i]);
			}
			int ans = getAns(nums);
			System.out.println(ans);
		}
	}

	static int getAns(int[] nums) {
		int max = 0, curr = 0;
		for (int n : nums) {
			max = Math.max(curr, max);
			curr = (n > curr+n) ? n : curr+n;
		}
		return Math.max(curr, max);
	}
}