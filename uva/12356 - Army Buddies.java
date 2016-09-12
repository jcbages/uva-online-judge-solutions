import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		while (true) {
			String[] vals = in.readLine().split(" ");
			int S = Integer.parseInt(vals[0]);
			int B = Integer.parseInt(vals[1]);
			if (S + B == 0) {
				break;
			}

			Soldier[] soldiers = new Soldier[S + 2]; 
			soldiers[0] = new Soldier(0, 1);
			soldiers[S + 1] = new Soldier(S, S + 1);
			for (int i = 1; i <= S; i++) {
				soldiers[i] = new Soldier(i - 1, i + 1);
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < B; i++) {
				vals = in.readLine().split(" ");
				int L = Integer.parseInt(vals[0]);
				int R = Integer.parseInt(vals[1]);
				
				String left = soldiers[L].left < 1 ? "*" : soldiers[L].left + "";
				String right = soldiers[R].right > S ? "*" : soldiers[R].right + "";
				sb.append(left + " " + right + "\n");
				
				soldiers[soldiers[L].left].right = soldiers[R].right;
				soldiers[soldiers[R].right].left = soldiers[L].left;
			}
			sb.append("-");
			System.out.println(sb);
		}
	}

	static class Soldier {
		int left, right;

		public Soldier(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}