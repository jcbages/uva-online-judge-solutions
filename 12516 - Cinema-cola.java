import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int R = Integer.parseInt(vals[0]);
			int C = Integer.parseInt(vals[1]);
			if (R == 0 && C == 0) {
				break;
			}

			boolean[][] cinema = new boolean[R][C + 1];
			int P = Integer.parseInt(in.readLine());
			for (int i = 0; i < P; i++) {
				vals = in.readLine().split(" ");
				int[] ij = coords(vals[0]);
				int m = vals[1].charAt(0) == '+' ? 1 : 0;
				cinema[ij[0]][ij[1] + m] = true;
			}

			boolean[][] friends = new boolean[R][C];
			int Z = Integer.parseInt(in.readLine());
			for (int i = 0; i < Z; i++) {
				int[] ij = coords(in.readLine());
				friends[ij[0]][ij[1]] = true;
			}

			boolean valid = true;
			for (int i = 0; i < R && valid; i++) {
				for (int j = 0; j < C && valid; j++) {
					if (friends[i][j]) {
						if (!cinema[i][j]) {
							cinema[i][j] = true;
						} else if (!cinema[i][j + 1]) {
							cinema[i][j + 1] = true;
						} else {
							valid = false;
						}
					}
				}
			}
			System.out.println(valid ? "YES" : "NO");
		}
	}

	static int[] coords(String pos) {
		return new int[] {
			pos.charAt(0) - 'A',
			Integer.parseInt(pos.substring(1)) - 1
		};
	}
}