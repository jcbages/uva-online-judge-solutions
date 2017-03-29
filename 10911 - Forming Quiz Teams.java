import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		int num = 1;
		while (!(line = in.readLine()).equals("0")) {
			Hashtable<String, Double> memo = new Hashtable<String, Double>();
			int N = Integer.parseInt(line);
			Integer[][] coords = new Integer[2 * N][2];
			for (int i = 0; i < 2 * N; i++) {
				String[] vals = in.readLine().split(" ");
				coords[i][0] = Integer.parseInt(vals[1]);
				coords[i][1] = Integer.parseInt(vals[2]);
			}
			double val = getAns(coords, memo);
			String ans = String.format("Case %d: %.2f", num, val);
			System.out.println(ans);
			num++;
		}
	}

	static double getAns(Integer[][] coords, Hashtable<String, Double> memo) {
		Arrays.sort(coords, new Comparator<Integer[]>() {
			public int compare(Integer[] int1, Integer[] int2) {
				Integer x1 = int1[0], x2 = int2[0], y1 = int1[1], y2 = int2[1];
				if (x1.compareTo(x2) > 0) return  1;
				if (x1.compareTo(x2) < 0) return -1;
				if (y1.compareTo(y2) > 0) return  1;
				if (y1.compareTo(y2) < 0) return -1;
				return 0;
			}
		});

		String key = stringify(coords);
		if (memo.get(key) != null) {

			return memo.get(key);
		}
		if (coords.length == 2) {
			memo.put(key, getDistance(coords[0], coords[1]));
			return memo.get(key);
		}
		double[] candidates = new double[coords.length-1];
		for (int i = 1; i < coords.length; i++) {
			double thisDistance = getDistance(coords[0], coords[i]);
			Integer[][] subcoords = getSubcoords(coords, i);
			candidates[i-1] = thisDistance + getAns(subcoords, memo);
		}
		double ans = Integer.MAX_VALUE;
		for (double cand : candidates)
			if (cand < ans) ans = cand;
		memo.put(key, ans);
		return memo.get(key);
	}

	static String stringify(Integer[][] coords) {
		StringBuilder ans = new StringBuilder();
		for (Integer[] coord : coords) {
			String part = "(" + coord[0] + "," + coord[1] + ")-";
			ans.append(part);
		}
		return ans.toString();
	}

	static Integer[][] getSubcoords(Integer[][] coords, int n) {
		Integer[][] ans = new Integer[coords.length-2][2];
		boolean found = false;
		for (int i = 1; i < coords.length; i++)
			if (i == n) found = true;
			else {
				if (!found) { ans[i-1] = coords[i]; }
				if (found)  { ans[i-2] = coords[i]; }
			}
		return ans;
	}

	static double getDistance(Integer[] coord1, Integer[] coord2) {
		int x = coord1[0] - coord2[0], y = coord1[1] - coord2[1];
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}