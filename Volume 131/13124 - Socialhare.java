import java.io.*;
import java.util.Arrays;

class Main {
	final static int MAX = 26;
	final static int ROW_BY_ROW = 0;
	final static int COL_BY_COL = 1;
	final static int DIAG_LEFT_TO_RIGHT = 2;
	final static int DIAG_RIGHT_TO_LEFT = 3;

	static int R, C, W, ans;
	static char[][] matrix;
	static Word[] words;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			String[] vals = line.split(" ");
			R = Integer.parseInt(vals[0]);
			C = Integer.parseInt(vals[1]);
			W = Integer.parseInt(vals[2]);
			ans = 0;

			matrix = new char[R][C];
			for (int i = 0; i < R; i++) {
				matrix[i] = in.readLine().toCharArray();
			}

			words = new Word[W];
			for (int i = 0; i < W; i++) {
				words[i] = new Word(in.readLine());
			}

			// seach each word one by one
			for (Word word : words) {
				int k = word.length;

				// row by row
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						check(word, i, j, k, ROW_BY_ROW);
					}
					word.reset();
				}

				// col by col
				for (int j = 0; j < C; j++) {
					for (int i = 0; i < R; i++) {
						check(word, i, j, k, COL_BY_COL);
					}
					word.reset();
				}

				// diag left to right
				for (int ii = 0; ii < R; ii++) {
					for (int i = ii, j = 0; i < R && j < C; i++, j++) {
						check(word, i, j, k, DIAG_LEFT_TO_RIGHT);
					}
					word.reset();
				}
				for (int jj = 1; jj < C; jj++) {
					for (int i = 0, j = jj; i < R && j < C; i++, j++) {
						check(word, i, j, k, DIAG_LEFT_TO_RIGHT);
					}
					word.reset();
				}

				// diag right to left
				for (int ii = 0; ii < R; ii++) {
					for (int i = ii, j = C - 1; i < R && j >= 0; i++, j--) {
						check(word, i, j, k, DIAG_RIGHT_TO_LEFT);
					}
					word.reset();
				}
				for (int jj = C - 2; jj >= 0; jj--) {
					for (int i = 0, j = jj; i < R && j >= 0; i++, j--) {
						check(word, i, j, k, DIAG_RIGHT_TO_LEFT);
					}
					word.reset();
				}

			}

			System.out.println(ans);
		}
	}

	static void check(Word word, int i, int j, int k, int traversal) {
		if (!word.found) {
			// sub current char (enqueue last)
			word.actual[matrix[i][j]-'a']--;
			// try to add out char (dequeue first)
			int sub = getSubtract(word, i, j, k, traversal);
			if (sub != -1) {
				word.actual[sub]++;
			}
			// check if found
			for (int freq : word.actual) {
				if (freq != 0) {
					return;
				}
			}
			word.found = true;
			ans++;
		}
	}

	static int getSubtract(Word word, int i, int j, int k, int traversal) {
		switch (traversal) {
			case ROW_BY_ROW:
				return j-k >= 0 ? matrix[i][j-k]-'a' : -1;
			case COL_BY_COL:
				return i-k >= 0 ? matrix[i-k][j]-'a' : -1;
			case DIAG_LEFT_TO_RIGHT:
				return i-k >= 0 && j-k >= 0 ? matrix[i-k][j-k]-'a' : -1;
			case DIAG_RIGHT_TO_LEFT:
				return i-k >= 0 && j+k < C ? matrix[i-k][j+k]-'a' : -1;
			default:
				return -1;
		}
	}

	static class Word {
		boolean found;
		String word;
		int[] original, actual;
		int originalPending, actualPending, length;

		public Word(String line) {
			word = line;
			length = line.length();
			found = false;
			original = new int[MAX];
			actual = new int[MAX];
			for (int i = 0; i < line.length(); i++) {
				int c = line.charAt(i) - 'a';
				original[c]++;
				actual[c]++;
			}
		}

		void reset() {
			for (int i = 0; i < MAX; i++) {
				actual[i] = original[i];
			}
			actualPending = originalPending;
		}
	}
}