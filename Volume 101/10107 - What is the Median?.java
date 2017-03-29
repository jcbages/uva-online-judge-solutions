import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> p1 = new PriorityQueue<Integer>();
		PriorityQueue<Integer> p2 = new PriorityQueue<Integer>();
		while (sc.hasNextInt()) {
			int x = sc.nextInt(), a = 0, b = 0;
			// inv: a <= b
			
			// get a & b
			if (!p1.isEmpty()) {
				if (!p2.isEmpty()) {
					a = -p1.peek();
					b = p2.peek();
				} else {
					a = b = -p1.peek();
				}
			} else if (!p2.isEmpty()) {
				a = b = p2.peek();
			}

			// add x to p1/p2
			if (x <= b) {
				p1.add(-x);
			} else {
				p2.add(x);
			}

			// balance pqs
			while (Math.abs(p1.size() - p2.size()) > 1) {
				if (p1.size() < p2.size()) {
					p1.add(-p2.poll()); // add p2 head to p1
				} else {
					p2.add(-p1.poll()); // add p1 head to p2
				}
			}

			// print median
			int median;
			if (p1.size() == p2.size()) {
				median = (-p1.peek() + p2.peek()) / 2;
			} else {
				if (p1.size() < p2.size()) {
					median = p2.peek();
				} else {
					median = -p1.peek();
				}
			}
			System.out.println(median);
		}
	}
}