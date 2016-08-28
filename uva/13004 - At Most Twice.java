import java.io.*;

class Main {
	final static int DIGITS = 10;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String ans = getAns(line);
			System.out.println(ans);
		}
	}

	static String getAns(String line) {
		StringBuilder aux = new StringBuilder(line);
		int[] counter = new int[DIGITS];
		
		boolean finished = false;
		while (!finished) {
			finished = true;
			int position = -1;
			for (int i = 0; i < aux.length() && finished; i++) {
				int n = aux.charAt(i) - '0';
				counter[n] += 1;
				if (counter[n] > 2) {
					finished = false;
					position = i;
				}
			}

			if (!finished) {
				int biggest = 9, reps = 0;
				long num = Long.parseLong(aux.toString());
				num -= (long) Math.pow(10, aux.length() - position - 1);
				aux = new StringBuilder(Long.toString(num));
				for (int i = position+1; i < aux.length(); i++) {
					if (reps == 2) {
						biggest -= 1;
						reps = 0;
					}
					aux.replace(i, i+1, Integer.toString(biggest));
					reps += 1;
				}
				resetCounter(counter);
			}
		}

		return aux.toString();
	}

	static void resetCounter(int[] counter) {
		for (int i = 0; i < DIGITS; i++) {
			counter[i] = 0;
		}
	}
}