import java.util.*;
import java.io.*;

class Main {
	public static void main(String... args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			in.readLine();
			ArrayList<Item> items = new ArrayList<Item>();
			String[] poss = in.readLine().split("[ \t]");
			String[] vals = in.readLine().split("[ \t]");
			for (int j = 0; j < poss.length; j++) {
				int pos = Integer.parseInt(poss[j]);
				String val = vals[j];
				items.add(new Item(pos, val));
			}
			Collections.sort(items);
			for (int j = 0; j < items.size(); j++)
				System.out.println(items.get(j).val);
			if (i < N-1)
				System.out.println("");
		}
	}

	static class Item implements Comparable<Item> {
		int pos;
		String val;

		public Item(int pos, String val) {
			this.pos = pos;
			this.val = val;
		}

		@Override
		public int compareTo(Item that) {
			return this.pos - that.pos;
		}
	}
}