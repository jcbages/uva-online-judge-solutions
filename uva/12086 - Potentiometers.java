import java.io.*;

class Main {
	static int[] tree, resistances;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int testCase = 1;
		while (true) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0)
				break;
			resistances = new int[N];
			tree = new int[getSize(N)];
			for (int i = 0; i < N; i++)
				resistances[i] = Integer.parseInt(in.readLine());
			create(1, 0, N-1);
			StringBuilder sb = new StringBuilder();
			if (testCase > 1)
				sb.append("\n");
			sb.append("Case " + testCase + ":\n");
			while (true) {
				String[] vals = in.readLine().split(" ");
				if (vals[0].equals("END")) {
					System.out.print(sb.toString());
					break;
				} else if (vals[0].equals("M")) {
					int l = Integer.parseInt(vals[1]) - 1;
					int r = Integer.parseInt(vals[2]) - 1;
					sb.append(query(1, 0, N-1, l, r) + "\n");
				} else if (vals[0].equals("S")) {
					int i = Integer.parseInt(vals[1]) - 1;
					int v = Integer.parseInt(vals[2]);
					update(1, 0, N-1, i, v);
				}
			}
			testCase++;
		}
	}

	static int getSize(int N) {
		int log2 = (int) (Math.log(N) / Math.log(2));
		int pow = (int) Math.pow(2, log2+1);
		return 2 * pow - 1;
	}

	static void create(int node, int start, int end) {
		if (start == end) {
			tree[node-1] = resistances[start];
		} else {
			int mid = (start + end) >>> 1;
			create(2*node, start, mid);
			create(2*node+1, mid+1, end);
			tree[node-1] = tree[2*node-1] + tree[2*node];
		}
	}

	static void update(int node, int start, int end, int idx, int val) {
		if (start == end) {
			resistances[idx] = val;
			tree[node-1] = val;
		} else {
			int mid = (start + end) >>> 1;
			if (start <= idx && idx <= mid)
				update(2*node, start, mid, idx, val);
			else
				update(2*node+1, mid+1, end, idx, val);
			tree[node-1] = tree[2*node-1] + tree[2*node];
		}
	}

	static int query(int node, int start, int end, int l, int r) {
		if (r < start || end < l) {
			return 0;
		} else if (l <= start && end <= r) {
			return tree[node-1];
		} else {
			int mid = (start + end) >>> 1;
			int p1 = query(2*node, start, mid, l, r);
			int p2 = query(2*node+1, mid+1, end, l, r);
			return p1 + p2;
		}
	}
}