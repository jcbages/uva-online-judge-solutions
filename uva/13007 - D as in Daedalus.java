import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			int N = Integer.parseInt(vals[0]);
			int M = Integer.parseInt(vals[1]);
			int ans = 0;
			for (int i = 0; i < M; i++) {
				vals = in.readLine().split(" ");
				int[] round = new int[N];
				int B = Integer.parseInt(vals[0]);
				for (int j = 0; j < N; j++) {
					round[j] = Integer.parseInt(vals[j+1]);
				}
				ans += getAns(round, N, B);
			}
			System.out.println(ans);
		}
	}

	static int getAns(int[] round, int N, int B) {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			sum += round[i];
		}

		if (sum > B) {
			return 0;
		} else if (round[0] + sum > B) {
			return bestChoice(B - sum);
		} else {
			return bestChoice(B - sum) - round[0];
		}
	}

	static int bestChoice(int n) {
		int[] choices = new int[] { 1, 10, 100, 1000, 10000 };
		int i = 0;
		while (i < choices.length && choices[i] <= n) i++;
		return (i-1 < 0) ? 0 : choices[i-1];
	}
}