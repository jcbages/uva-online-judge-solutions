import java.io.*;
import java.util.Stack;

class Main {
	static StringBuilder line;
	static int ans, n;

	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String l;
		while ((l = in.readLine()) != null) {
			ans = 0;
			n = l.length();
			line = new StringBuilder(l.replace("?", "0"));
			getAns(0, 0, 0);
			System.out.println(ans);
		}
	}

	static boolean getAns(int i, int cnt, int tmp) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(new Node(i, cnt, tmp));
		while (!stack.empty()) {
			Node s = stack.pop();
			if (s.i == n) {
				if (s.cnt == 0) {
					ans = s.tmp;
				}
				return true;
			}
			if (line.charAt(s.i) == '0') {
				if (s.cnt < 2) {
					stack.push(new Node(s.i+1, 0, s.tmp+1));
				} else {
					stack.push(new Node(s.i+1, s.cnt-1, s.tmp));
				}
			} else { /* if (line.charAt(s.i) == '1') { */
				if (s.cnt == 0) {
					stack.push(new Node(s.i+1, 2, s.tmp));
				} else {
					stack.push(new Node(s.i+1, s.cnt+1, s.tmp));
				}
			}
		}
		return false;
	}

	static class Node {
		int i, cnt, tmp;

		public Node(int i, int cnt, int tmp) {
			this.i = i;
			this.cnt = cnt;
			this.tmp = tmp;
		}
	}
}