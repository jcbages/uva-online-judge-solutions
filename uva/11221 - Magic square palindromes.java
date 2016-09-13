import java.io.*;

class Main {
	static char[][] matrix;
	static int K;
	static String line;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			line = in.readLine();
			line = line.replaceAll("[\\p{Punct}\\p{Blank}]", "");	
			K = (int) Math.sqrt(line.length());
			if (K * K != line.length()) {
				System.out.printf("Case #%d:\n", t + 1);
				System.out.println("No magic :(");
				continue;
			}


			int k = 0;
			matrix = new char[K][K];
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++) {
					matrix[i][j] = line.charAt(k++);
				}
			}

			System.out.printf("Case #%d:\n", t + 1);

			boolean a = read1().toString().equals(line);
			boolean b = read2().toString().equals(line);
			boolean c = read1().reverse().toString().equals(line);
			boolean d = read2().reverse().toString().equals(line);
			if (a && b && c && d) {
				System.out.println(K);
			} else {
				System.out.println("No magic :(");
			}
		}
	}

	static StringBuilder read1() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				sb.append(matrix[i][j]);
			}
		}
		return sb;
	}

	static StringBuilder read2() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < K; j++) {
			for (int i = 0; i < K; i++) {
				sb.append(matrix[i][j]);
			}
		}
		return sb;
	}
}