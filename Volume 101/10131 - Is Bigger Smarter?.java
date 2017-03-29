import java.io.*;
import java.util.*;

class Main {
	final static int N = 1000;

	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			Elephant[] E = new Elephant[N];
			int n = 0;
			while (sc.hasNext()) {
				int p = sc.nextInt(), c = sc.nextInt();
				E[n] = new Elephant(p, c, n);
				n++;
			}
			getAns(E, n);
		} catch (Exception e) {
			// Nothing to do here
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static void getAns(Elephant[] old, int n) {
		int[] L = new int[n];
		int i, j, len;
		for (i = 0; i < n; i++) {
			L[i] = 1;
		}

		Elephant[] E = new Elephant[n];
		for (i = 0; i < n; i++) {
			E[i] = old[i];
		}
		Arrays.sort(E);

		len = 0;
		for (i = 0; i < n; i++) {
			for (j = i+1; j < n; j++) {
				if (E[j].p > E[i].p &&
					E[j].c < E[i].c) {
					L[j] = Math.max(L[i] + 1, L[j]);
					len = (L[j] > L[len]) ? j : len;
				}
			}
		}
		System.out.println(L[len]);

		E[len].valid = true;
		for (i = len; i >= 0; i--) {
			if (L[i] == L[len] - 1 &&
				E[i].p < E[len].p &&
				E[i].c > E[len].c) {
				len = i;
				E[len].valid = true;
			}
		}

		for (i = 0; i < n; i++) {
			if (E[i].valid) {
				System.out.println(E[i].i);
			}
		}
	}

	static class Elephant implements Comparable<Elephant> {
		int p, c, i;
		boolean valid;

		public Elephant(int p, int c, int i) {
			this.p = p;
			this.c = c;
			this.i = i;
			this.valid = false;
		}

		@Override
		public int compareTo(Elephant that) {
			int diffP = this.p - that.p, diffC = this.c - that.c;
			return (diffP != 0) ? diffP : diffC;
		}
	}
}