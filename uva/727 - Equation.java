import java.io.*;
import java.util.*;

class Main {
	final static int MAX_LENGTH = 51;

	public static void main(String... args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);
			int N = Integer.parseInt(in.readLine());
			in.readLine(); // Read blank line.
			for (int i = 0; i < N; i++) {
				String line = getEq(in);
				getAns(line);
				if (i < N-1) System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static String getEq(BufferedReader in) throws Exception {
		StringBuilder ans = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null && line.length() > 0) {
			ans.append(line);
		}
		return ans.toString();
	}

	static void getAns(String line) {
		int n = line.length();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < n; i++) {
			char c = line.charAt(i);
			if (isDigit(c)) {
				System.out.print(c);
			} else if (isOperator(c)) {
				release(stack, c);
				stack.push(c);
			} else if (c == '(') {
				stack.push(c);
			} else /* c == ')' */ {
				release(stack, '+'); /* + because all ops will print */
				stack.pop();
			}
		}
		release(stack, '+');  /* + because all ops will print */
		System.out.println("");
	}

	static void release(Stack<Character> stack, char c) {
		while (!stack.empty() && isValid(stack.peek(), c)) {
			System.out.print(stack.pop());
		}
	}

	static boolean isValid(char a, char b) {
		if (a == '*' ||a == '/') {
			return true;
		} else if (a == '+' || a == '-') {
			return b == '+' || b == '-';
		} else {
			return false;
		}
	}

	static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
}