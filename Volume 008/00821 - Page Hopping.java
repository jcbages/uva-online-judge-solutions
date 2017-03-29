import java.io.*;
import java.util.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		int num = 1;
		while (!(line = in.readLine()).equals("0 0")) {
			String ans = getAns(line, num);
			System.out.println(ans);
			num++;
		}
	}

	static String getAns(String line, int num) {
		Hashtable<Integer, ArrayList<Integer>> graph = getGraph(line);
		Hashtable<Integer, ArrayList<Integer>> allBFS = getAllBFS(graph);
		double avgClicks = getAvgClicks(allBFS);
		String ans = String.format("Case %d: average length between pages = %.3f clicks", num, avgClicks);
		return ans;
	}

	static double getAvgClicks(Hashtable<Integer, ArrayList<Integer>> allBFS) {
		double ans = 0.0; int count = 0;
		Enumeration<Integer> V = allBFS.keys();
		while (V.hasMoreElements()) {
			int v = V.nextElement();
			ArrayList<Integer> BFS = allBFS.get(v);
			count += BFS.size()-1;
			for (int distance : BFS) {
				ans += distance;
			}
		}
		return ans / count;
	}

	static Hashtable<Integer, ArrayList<Integer>> getAllBFS(Hashtable<Integer, ArrayList<Integer>> graph) {
		Hashtable<Integer, ArrayList<Integer>> ans = new Hashtable<Integer, ArrayList<Integer>>();
		Enumeration<Integer> V = graph.keys();
		while (V.hasMoreElements()) {
			int v = V.nextElement();
			ans.put(v, getBFS(v, graph));
		}
		return ans;
	}

	static ArrayList<Integer> getBFS(int s, Hashtable<Integer, ArrayList<Integer>> graph) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Hashtable<Integer, Integer> mark = new Hashtable<Integer, Integer>();
		mark.put(s, 0);
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);
		while (Q.peek() != null) {
			int v = Q.poll();
			int distance = mark.get(v);
			ans.add(distance);
			ArrayList<Integer> E = graph.get(v);
			for (int e : E) {
				if (mark.get(e) == null) {
					mark.put(e, distance+1);
					Q.add(e);
				}
			}
		}
		return ans;
	}

	static Hashtable<Integer, ArrayList<Integer>> getGraph(String line) {
		Hashtable<Integer, ArrayList<Integer>> ans = new Hashtable<Integer, ArrayList<Integer>>();
		String[] V = line.split(" ");
		int i = 0, j = 1;
		while (!V[i].equals("0")) {
			int v1 = Integer.parseInt(V[i]), v2 = Integer.parseInt(V[j]);
			if (ans.get(v1) == null) ans.put(v1, new ArrayList<Integer>());
			if (ans.get(v2) == null) ans.put(v2, new ArrayList<Integer>());
			ans.get(v1).add(v2);
			i += 2; j += 2;
		}
		return ans;
	}
}