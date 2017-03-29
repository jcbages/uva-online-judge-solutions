import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			String[] vals = in.readLine().split(" ");
			PriorityQueue<Long> PQ = new PriorityQueue<Long>();
			for (int i = 0; i < N; i++) PQ.add(Long.parseLong(vals[i]));
			long ans = 0;
			while (PQ.size() >= 2) {
				long v1 = PQ.poll();
				long v2 = PQ.poll();
				ans += v1 + v2;
				PQ.add(v1 + v2);
			}
			System.out.println(ans);
		}
	}
}