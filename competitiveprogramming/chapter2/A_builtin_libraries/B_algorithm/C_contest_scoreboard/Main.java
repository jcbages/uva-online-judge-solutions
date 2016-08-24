import java.io.*;
import java.util.*;

class Main {
	final static int T = 100, P = 9, PENALTY = 20;

	public static void main(String... args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);
			int N = Integer.parseInt(in.readLine()), i = 0;
			in.readLine();
			while (i < N) {
				Contestant[] contestants = new Contestant[T];
				for (int m = 0; m < T; m++)
					contestants[m] = new Contestant(m+1);
				String line;
				while ((line = in.readLine()) != null && line.length() > 1) {
					String[] vals = line.split(" ");
					int contestant = Integer.parseInt(vals[0]);
					int problem = Integer.parseInt(vals[1]);
					int actualTime = Integer.parseInt(vals[2]);
					char status = vals[3].charAt(0);
					if (status == 'C' && contestants[contestant-1].solved[problem-1] == 0) {
						contestants[contestant-1].timePenalty +=
								contestants[contestant-1].problems[problem-1] *
								PENALTY +
								actualTime;
						contestants[contestant-1].solved[problem-1] = 1;
						contestants[contestant-1].points += 1;
					} else if (status == 'I') {
						contestants[contestant-1].problems[problem-1] += 1;
					}
					contestants[contestant-1].submissions += 1;
				}
				Arrays.sort(contestants);
				for (int j = 0; j < T; j++) {
					if (contestants[j].submissions > 0) {
						String ans = String.format(
							"%d %d %d",
							contestants[j].number,
							contestants[j].points,
							contestants[j].timePenalty
						);
						System.out.println(ans);
					}
				}
				if (i < N-1)
					System.out.println("");
				i++;
				//System.out.println("i is -> " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static class Contestant implements Comparable<Contestant> {
		int number;
		int[] problems;
		int[] solved;
		int submissions;
		int points;
		int timePenalty;

		public Contestant(int number) {
			this.number = number;
			this.submissions = 0;
			this.points = 0;
			this.timePenalty = 0;
			this.problems = new int[P];
			this.solved = new int[P];
			for (int i = 0; i < P; i++)
				this.problems[i] = this.solved[i] = 0;
		}

		@Override
		public int compareTo(Contestant that) {
			if (this.points - that.points != 0)
				return -(this.points - that.points);
			if (this.timePenalty - that.timePenalty != 0)
				return this.timePenalty - that.timePenalty;
			else
				return this.number - that.number;
		}
	}
}
