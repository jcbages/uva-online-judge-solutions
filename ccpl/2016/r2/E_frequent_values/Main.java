import java.io.*;

class Main {
	final static int MAX = 2000000;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()).equals("0")) {
			String[] vals = line.split(" ");
			int n = Integer.parseInt(vals[0]);
			int q = Integer.parseInt(vals[1]);
			
			int[] arr = new int[n];
			vals = in.readLine().split(" ");
			for (int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(vals[i]);

			int[] rmq = getRMQ(arr, n);
			for (int x = 0; x < q; x++) {
				vals = in.readLine().split(" ");
				int i = Integer.parseInt(vals[0]);
				int j = Integer.parseInt(vals[1]);
				System.out.println(getQuery(rmq, i, j, n));
			}
		}

		static int[] getRMQ(int[] arr, int n) {
			int[] rmq = new int[MAX];
		}

		static int getQuery(int[] rmq, int i, int j, int n) {
			return 0;
		}
	}
}