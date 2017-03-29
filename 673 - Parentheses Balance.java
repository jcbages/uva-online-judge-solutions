import java.io.*;
import java.util.*;

class Main {
	final static int MAX_LENGTH = 130;

	public static void main(String... args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);
			int N = Integer.parseInt(in.readLine());
			for (int i = 0; i < N; i++) {
				String line = in.readLine();
				String ans = getAns(line);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static String getAns(String line) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(' || line.charAt(i) == '[') {
				stack.push(line.charAt(i));
			} else {
				if (stack.empty() || noMatch(stack.peek(), line.charAt(i))) {
					return "No";
				} else {
					stack.pop();
				}
			}
		}
		if (stack.empty()) return "Yes";
		else return "No";
	}

	static boolean noMatch(char a, char b) {
		return (a == '(' && b == ']') || (a == '[' && b == ')');
	}
}