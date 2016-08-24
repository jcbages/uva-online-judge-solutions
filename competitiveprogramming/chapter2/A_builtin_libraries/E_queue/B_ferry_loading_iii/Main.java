import java.util.*;

class Main {
	final static int LEFT  = 0;
	final static int RIGHT = 1;

	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt();
			for (int i = 0; i < T; i++) {
				Queue<Integer> left  = new LinkedList<Integer>();
				Queue<Integer> right = new LinkedList<Integer>();

				String currPos = "left";
				int currTime = 0;

				int n = sc.nextInt();
				int t = sc.nextInt();
				int m = sc.nextInt();
				for (int j = 0; j < m; j++) {
					int aTime = sc.nextInt();
					String pos = sc.next() + sc.nextLine();

					if (pos.equals(currPos)) {

					}
				}
}