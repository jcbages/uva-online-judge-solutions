import java.io.*;

public class Main {
	final static int JACK = 1;
	final static int JIM  = -1;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int N;
		while ((N = Integer.parseInt(in.readLine())) != 0) {
			String[] vals = in.readLine().split(" ");

			int[] Pn = new int[N];
			for (int i = 0; i < N; i++) {
				Pn[i] = Integer.parseInt(vals[i]);
			}

			String ans = getAns(N, Pn);
			System.out.println(ans);
		}
	}

	static String getAns(int N, int[] Pn) {
		int actual = Pn[0];
		for (int i = 1; i < N; i++) {
			actual = actual ^ Pn[i];
		}
		return (actual == 0) ? "No" : "Yes";
	}
}