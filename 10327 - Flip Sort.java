import java.io.*;
import java.util.*;
import java.math.*;

class Main {

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			int ans = 0;
			if (N > 0) {
				int[] a = getNumbers(N, in.readLine());
				ans = getAns(N, a);
			}
			System.out.println("Minimum exchange operations : " + ans);
		}
    }

	static int getAns(int N, int[] a) {
		int ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < i; j++)
				if (a[j] > a[i]) ans++;
		return ans;
	}

	static int[] getNumbers(int N, String line) {
		String[] vals = line.split(" ");
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) ans[i] = Integer.parseInt(vals[i]);
		return ans;
	}
}