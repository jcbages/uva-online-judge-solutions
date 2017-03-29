import java.util.*;

class Main {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
			for (int i = 0; i < N; i++) {
				PQ.add(-sc.nextInt());
			}
			int ans = getAns(PQ);
			System.out.println(ans);
		}
	}

	static int getAns(PriorityQueue<Integer> PQ) {
		while (PQ.size() > 0) {
			int u = -PQ.poll();
			if (u > PQ.size()) {
				return 0;
			}
			int[] edges = new int[u];
			for (int i = 0; i < u; i++) {
				edges[i] = PQ.poll() + 1;
			}
			for (int i = 0; i < u; i++) {
				if (edges[i] < 0) {
					PQ.add(edges[i]);
				}
			}
		}
		return 1;
	}
}