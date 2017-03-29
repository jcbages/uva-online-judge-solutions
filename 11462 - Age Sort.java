import java.io.*;

class Main {
	final static int AGES = 99;

	public static void main(String... args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);
			while (true) {
				int n = Integer.parseInt(in.readLine());
				if (n == 0) break;
				int[] ages = new int[AGES];
				String[] vals = in.readLine().split(" ");
				for (int i = 0; i < n; i++) {
					int age = Integer.parseInt(vals[i]);
					ages[age-1] += 1;
				}
				StringBuilder ans = new StringBuilder();
				for (int i = 0; i < AGES; i++) {
					for (int j = 0; j < ages[i]; j++) {
						if (i > 0 || j > 0) ans.append(" ");
						ans.append(i+1);
					}
				}
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}