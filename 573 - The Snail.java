import java.util.*;

class Main {
	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int H, U, D, F, i, t, v;
			while (sc.hasNext()) {
				H = sc.nextInt();
				U = sc.nextInt();
				D = sc.nextInt();
				F = sc.nextInt();
				if (H == 0) break;
				i = t = 0;
				v = U * F;
				while (true) {
					t += Math.max(U * 100 - v * i, 0);
					if (t < 0 || t > H * 100) break;
					t -= D * 100;
					if (t < 0 || t > H * 100) break;
					i++;
				}
				if (t < 0)	System.out.println("failure on day " + (i+1));
				else		System.out.println("success on day " + (i+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}