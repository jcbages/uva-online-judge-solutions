import java.util.*;

class Main {
	final static int TITLE_LIM = 101, TEAM_LIMIT = 31;

	public static void main(String... args) {
		try {
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				String title = sc.next() + sc.nextLine();
				int T = sc.nextInt();
				Team[] teams = new Team[T];
				for (int j = 0; j < T; j++)
					teams[j] = new Team(sc.next() + sc.nextLine());
				int G = sc.nextInt();
				for (int j = 0; j < G; j++) {
					String[] vals = (sc.next() + sc.nextLine()).split("[#|@]");
					String name1 = vals[0];
					int score1 = Integer.parseInt(vals[1]);
					int score2 = Integer.parseInt(vals[2]);
					String name2 = vals[3];

					int i1 = indexOf(teams, name1, T);
					int i2 = indexOf(teams, name2, T);

					if (score1 > score2) {
						teams[i1].points += 3;
						teams[i1].wins   += 1;
						teams[i2].points += 0;
						teams[i2].losses += 1;	
					} else if (score1 == score2) {
						teams[i1].points += 1;
						teams[i1].ties   += 1;
						teams[i2].points += 1;
						teams[i2].ties   += 1;
					} else {
						teams[i1].points += 0;
						teams[i1].losses += 1;
						teams[i2].points += 3;
						teams[i2].wins   += 1;
					}
					teams[i1].played  += 1;
					teams[i1].scored  += score1;
					teams[i1].against += score2;
					teams[i2].played  += 1;
					teams[i2].scored  += score2;
					teams[i2].against += score1;
				}
				Arrays.sort(teams);
				System.out.println(title);
				for (int j = 0; j < T; j++) {
					String ans = String.format(
						"%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)",
						j+1,
						teams[j].name,
						teams[j].points,
						teams[j].played,
						teams[j].wins,
						teams[j].ties,
						teams[j].losses,
						teams[j].scored - teams[j].against,
						teams[j].scored,
						teams[j].against
					);
					System.out.println(ans);
				}
				if (i < N-1)
					System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	static int indexOf(Team[] teams, String name, int T) {
		for (int i = 0; i < T; i++)
			if (teams[i].name.equals(name))
				return i;
		return -1;
	}

	static class Team implements Comparable<Team> {
		String name;
		int points;
		int played;
		int wins;
		int ties;
		int losses;
		int scored;
		int against;

		public Team(String name) {
			this.name = name;
			this.points = 0;
			this.played = 0;
			this.wins = 0;
			this.ties = 0;
			this.losses = 0;
			this.scored = 0;
			this.against = 0;
		}

		@Override
		public int compareTo(Team that) {
			if (this.points - that.points != 0) {
				return -(this.points - that.points);
			} if (this.wins - that.wins != 0) {
				return -(this.wins - that.wins);
			} if ((this.scored - this.against) - (that.scored - that.against) != 0) {
				return -((this.scored - this.against) - (that.scored - that.against));
			} if (this.scored - that.scored != 0) {
				return -(this.scored - that.scored);
			} if (this.played - that.played != 0) {
				return this.played - that.played;
			} else {
				String l1 = this.name.toLowerCase();
				String l2 = that.name.toLowerCase();
				int ans = l1.compareTo(l2);
				return ans;
			}
		}
	}
}