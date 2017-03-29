import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			PriorityQueue<Query> queries = new PriorityQueue<Query>();
			queries.add(new Query(line, 1));
			while (!(line = in.readLine()).equals("#")) {
				queries.add(new Query(line, 1));
			}

			if (queries.size() == 0) {
				continue;
			}

			int K = Integer.parseInt(in.readLine());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < K; i++) {
				Query q = queries.poll();
				q.mul++;
				
				queries.add(q);
				sb.append(q.number + "\n");
			}
			System.out.print(sb);
		}
	}

	static class Query implements Comparable<Query> {
		int number, period, mul;

		public Query(String line, int mul) {
			String[] vals = line.split(" ");
			this.mul = mul;
			this.number = Integer.parseInt(vals[1]);
			this.period = Integer.parseInt(vals[2]);
		}

		@Override
		public int compareTo(Query that) {
			int diff1 = (this.period * this.mul) - (that.period * that.mul);
			int diff2 = this.number - that.number;
			return (diff1 == 0) ? diff2 : diff1;
		}
	}
}