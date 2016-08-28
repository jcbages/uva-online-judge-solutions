import java.io.*;

class Main {
	final static int LEFT = 0, RIGHT = 1;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			long[][] points = new long[N][2];
			for (int i = 0; i < N; i++) {
				String[] vals = in.readLine().split(" ");
				points[i][0] = Long.parseLong(vals[0]);
				points[i][1] = Long.parseLong(vals[1]);
			}
			long[] ans = getAns(points, N);
			System.out.println(ans[0] + " " + ans[1]);
		}
	}

	static long[] getAns(long[][] points, int N) {
		int i = 0, j = 0; // i == j
		long[] candidates = new long[N]; // Best candidate for each point
		long volume = tot(points, N, i, j), total = tot(points, N, 0, N-1);
		while (i < N) {

			/* Calculate best choice for Carla */
			while (true) {
				long addV = add(volume, points, i, n(j, N), N);
				long remV = (i == j) ? tot(points, N, i, p(j, N)) : rej(volume, points, i, j, N);

				long cAdd = Math.min(addV, total-addV);
				long cVol = Math.min(volume, total-volume);
				long cRem = Math.min(remV, total-remV);
				
				if (
					(cAdd < cVol && cVol > cRem) ||
					(cAdd <= cVol && cVol > cRem) ||
					(cAdd < cVol && cVol >= cRem)
				) {
					break;
				} else if (cAdd >= cVol) {
					volume = addV;
					j = n(j, N);
				} else {
					volume = remV;
					j = p(j, N);
				}

			}

			/* Save best and proceed */
			candidates[i] = volume;
			volume = rem(volume, points, i, j, N);
			i += 1;
		}

		/* Calculate answer */
		long ans = 0;
		for (long c : candidates) {
			long cc = Math.max(total-c, c);
			ans = Math.max(cc, ans);
		}

		/* Return answer */
		return new long[] {
			Math.max(total-ans, ans),
			Math.min(total-ans, ans)
		};
	}

	/* Get prev point */
	static int p(int i, int N) { return (i > 0) ? i-1 : N-1; }

	/* Get next point */
	static int n(int i, int N) { return (i + 1) % N; }

	static long tot(long[][] points, int N, int i, int j) {
		long ans = 0, pos, neg;
		int k = i;
		while (k != j) {
			pos = points[k][0] * points[n(k, N)][1]; // x1 * y2
			neg = points[k][1] * points[n(k, N)][0]; // y1 * x2
			ans = ans + (pos - neg);
			k = n(k, N);
		}
		pos = points[j][0] * points[i][1]; // x1 * y2
		neg = points[j][1] * points[i][0]; // y1 * x2
		ans = ans + (pos - neg);
		return ans;
	}

	static long add(long volume, long[][] points, int i, int j, int N) {
		/* Base cases */
		if (j == i) return 0; // if i == j, area is 0 e.j. (0, 0)
		if (j == n(i, N)) return 0; // if is n point, area is 0 e.j. (0, 1)
		if (j == n(n(i, N), N)) return tot(points, N, i, j); // if is nn point, calc tot e.j. (0, 2)
		/* General case */
		long pos, neg;
		/* Rem prev close (p(j), i) */
		pos = points[p(j, N)][0] * points[i][1]; // x1 * y2
		neg = points[p(j, N)][1] * points[i][0]; // y1 * x2
		volume = volume - (pos - neg);
		/* Add next point (p(j), j) */
		pos = points[p(j, N)][0] * points[j][1]; // x1 * y2
		neg = points[p(j, N)][1] * points[j][0]; // y1 * x2
		volume = volume + (pos - neg);
		/* Add next close (j, i) */
		pos = points[j][0] * points[i][1]; // x1 * y2
		neg = points[j][1] * points[i][0]; // y1 * x2
		volume = volume + (pos - neg);
		return volume;
	}

	static long rej(long volume, long[][] points, int i, int j, int N) {
		/* Base cases */

		/* General case */
		long pos, neg;
		/* Rem prev close (j, i) */
		pos = points[j][0] * points[i][1]; // x1 * y2
		neg = points[j][1] * points[i][0]; // y1 * x2
		volume = volume - (pos - neg);
		/* Rem prev point (p(j), j) */
		pos = points[p(j, N)][0] * points[j][1]; // x1 * y2
		neg = points[p(j, N)][1] * points[j][0]; // y1 * x2
		volume = volume - (pos - neg);
		/* Add next close (p(j), i) */
		pos = points[p(j, N)][0] * points[i][1]; // x1 * y2
		neg = points[p(j, N)][1] * points[i][0]; // y1 * x2
		volume = volume + (pos - neg);
		return volume;
	}

	static long rem(long volume, long[][] points, int i, int j, int N) {
		/* Base cases */
		int k = n(i, N);
		if (j == k) return 0; // if i == j, area is 0 e.j. (0, 0)
		if (j == n(k, N)) return 0; // if is n point, area is 0 e.j. (0, 1)
		if (j == n(n(k, N), N)) return tot(points, N, k, j); // if is nn point, calc tot e.j. (0, 2)
		/* General case */
		long pos, neg;
		/* Rem prev close (j, i) */
		pos = points[j][0] * points[i][1]; // x1 * y2
		neg = points[j][1] * points[i][0]; // y1 * x2
		volume = volume - (pos - neg);
		/* Rem prev point (i, n(i)) */
		pos = points[i][0] * points[n(i, N)][1]; // x1 * y2
		neg = points[i][1] * points[n(i, N)][0]; // y1 * x2
		volume = volume - (pos - neg);
		/* Add next close (j, n(i)) */
		pos = points[j][0] * points[n(i, N)][1]; // x1 * y2
		neg = points[j][1] * points[n(i, N)][0]; // y1 * x2
		volume = volume + (pos - neg);
		return volume;
	}
}