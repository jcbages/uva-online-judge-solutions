import java.io.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) throws IOExceptionI {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String line;
		while ((line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			ArrayList<Integer>[] subway = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				subway[i] = new ArrayList<Integer>();
				String[] vals = in.readLine().split(" ");
				for (int j = 0; j < vals.length; j++) {
					int v = Integer.parseInt(vals[j]);
					if (subway[i].indexOf(v) == -1) subway[i].add(v);
				}
			}
			for (int i = 0; i < N; i++) Collections.sort(subway[i]);
			String ans = getAns(subway, N);
			System.out.println(ans);
		}
	}

	static String getAns(ArrayList<Integer>[] subway, int N) {
		// TODO
		return "Y";
	}
}