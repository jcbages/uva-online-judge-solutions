import java.util.*;

class Main {
	public static void main(String... args) {
		String[] answers = new String[] {
			"stack", "queue", "priority queue"
		};

		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			
			boolean[] ds = new boolean[3];
			Arrays.fill(ds, true);
			
			Stack<Integer> stack = new Stack<Integer>();
			Queue<Integer> queue = new LinkedList<Integer>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			
			for (int i = 0; i < n; i++) {
				int operation = sc.nextInt();
				int number = sc.nextInt();
				if (operation == 1) {
					if (ds[0])
						stack.push(number);
					if (ds[1])
						queue.add(number);
					if (ds[2])
						pq.add(-number);
				} else {
					if (ds[0] && (stack.empty() || stack.pop() != number))
						ds[0] = false;
					if (ds[1] && (queue.isEmpty() || queue.poll() != number))
						ds[1] = false;
					if (ds[2] && (pq.isEmpty() || pq.poll() != -number))
						ds[2] = false;
				}
			}

			int count = 0, last = 0;
			for (int i = 0; i < 3; i++) {
				if (ds[i]) {
					count++;
					last = i;
				}
			}

			if (count == 0) {
				System.out.println("impossible");
			} else if (count > 1) {
				System.out.println("not sure");
			} else {
				System.out.println(answers[last]);
			}
		}
	}
}