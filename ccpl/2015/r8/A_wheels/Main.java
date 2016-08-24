import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		long T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(in.readLine());
			Wheel[] wheels = new Wheel[n];
			for (int j = 0; j < n; j++) {
				String[] vals = in.readLine().split(" ");
				wheels[j] = new Wheel(
					Integer.parseInt(vals[0]),
					Integer.parseInt(vals[1]),
					Integer.parseInt(vals[2])
				);
			}

			// Force in wheel #1
			wheels[0].move = 1;

			String ans = getAns(wheels);
			System.out.println(ans);
		}
	}

	static String getAns(Wheel[] wheels) {
		Stack<Integer> S = new Stack<Integer>();
		S.push(0);
		while (!S.empty()) {
			int u = S.pop();
			for (int i = 0; i < wheels.length; i++) {
				if (theyTouch(wheels[i], wheels[u]) && wheels[i].move == 0) {
					wheels[i].move = -wheels[u].move;
					S.add(i);
				}
			}
		}

		StringBuilder ans = new StringBuilder();
		for (Wheel w : wheels) {
			if (w.move == 0) {
				ans.append("not moving");
			} else {
				long gcd = BigInteger.valueOf(wheels[0].r).gcd(
					BigInteger.valueOf(w.r)).longValue();
				ans.append(wheels[0].r / gcd);
				ans.append((w.r > gcd) ? "/" + (w.r / gcd) : "");
				ans.append((w.move == 1) ? " clockwise" : " counterclockwise");
			}
			ans.append("\n");
		}
		return ans.substring(0, ans.length() - 1);
	}

	static boolean theyTouch(Wheel wi, Wheel wj) {
		long distX   = (wi.x - wj.x) * (wi.x - wj.x);
		long distY   = (wi.y - wj.y) * (wi.y - wj.y);
		long rSquare = (wi.r + wj.r) * (wi.r + wj.r);
		return (distX + distY) == rSquare;
	}

	static class Wheel {
		long x, y, r;
		int move;
		public Wheel(long x, long y, long r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
}