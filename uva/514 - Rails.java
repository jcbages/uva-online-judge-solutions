import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				int beginBlock = sc.nextInt();
				if (beginBlock == 0) break;
				while (true) {
					int endBlock = sc.nextInt();
					if (endBlock == 0) break;
					int[] nums = new int[beginBlock]; nums[0] = endBlock;
					for (int i = 1; i < beginBlock; i++) nums[i] = sc.nextInt();
					String ans = getAns(nums, beginBlock);
					System.out.println(ans);
				}
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static String getAns(int[] nums, int N) {
		int numToPush = 1;
		int actualNum = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(numToPush++);
		while (actualNum < N) {
			if (!stack.empty() && stack.peek() == nums[actualNum]) {
				stack.pop(); 	/* pop */
				actualNum++;	/* incr actualNum */
			} else {
				if (numToPush > N) {
					return "No";
				} else {
					stack.push(numToPush++);
				}
			}
		}
		return "Yes";
	}
}