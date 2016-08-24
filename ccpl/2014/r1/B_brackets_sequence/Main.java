import java.io.*;
import java.util.*;

public class Main {
	final static int AVAILABLE = -2; /* Available to pair */
	final static int NEWPAIRED = -1; /* Paired with a new one */

	static char[] line;
	static int[] paired;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			in.readLine();
			line = in.readLine().toCharArray();
			paired = new int[line.length];
			for (int j = 0; j < line.length; j++)
				paired[j] = AVAILABLE;
			String ans = getAns(0);
			if (i > 0) System.out.println();
			System.out.println(ans);
		}
	}

	static String getAns(int i) {
		if (i == line.length) {
			StringBuilder ans = new StringBuilder();
			for (int j = 0; j < line.length; j++) {
				if (paired[j] == NEWPAIRED) {
					if (line[j] == '(' || line[j] == ')') {
						ans.append("()");
					} else {
						ans.append("[]");
					}
				} else {
					ans.append(line[j]);
				}
			}
			return ans.toString();
		}

		if (paired[i] != AVAILABLE) {
			return getAns(i+1);
		} else if (line[i] == ']' || line[i] == ')') {
			paired[i] = NEWPAIRED;
			String ans = getAns(i+1);
			paired[i] = AVAILABLE;
			return ans;
		} else {
			ArrayList<String> answers = new ArrayList<String>();
			
			paired[i] = NEWPAIRED;
			answers.add(getAns(i+1));
			paired[i] = AVAILABLE;

			for (int j = i+1; j < line.length; j++) {
				if (paired[j] == AVAILABLE) {
					if (sameType(line[i], line[j])) {
						paired[i] = j;
						paired[j] = i;
						answers.add(getAns(i+1));
						paired[i] = AVAILABLE;
						paired[j] = AVAILABLE;
					}
				} else if (paired[j] != NEWPAIRED) {
					if (line[j] == ')' || line[j] == ']') {
						break;
					} else {
						j = paired[j];
					}
				}
			}

			int ans = 0, best = answers.get(ans).length();
			for (int j = 0; j < answers.size(); j++) {
				if (answers.get(j).length() < best) {
					ans = j;
					best = answers.get(ans).length();
				}
			}
			return answers.get(ans);
		}
	}

	static boolean sameType(char a, char b) {
		boolean v1 = (a == '(' && b == ')');
		boolean v2 = (a == '[' && b == ']');
		return v1 || v2;
	}
}