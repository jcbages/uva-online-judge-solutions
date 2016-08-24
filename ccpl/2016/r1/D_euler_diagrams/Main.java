import java.util.*;

class Main {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int W = sc.nextInt();
			int H = sc.nextInt();
			int n = sc.nextInt();
			if (W == 0 && H == 0 && n == 0)
				break;

			int[][] circles = new int[n][3];
			for (int i = 0; i < n; i++) {
				circles[i][0] = sc.nextInt();
				circles[i][1] = sc.nextInt();
				circles[i][2] = sc.nextInt();
			}

			ArrayList<Integer>[][] squares = new ArrayList[W][H];
			for (int w = 0; w < W; w++) {
				for (int h = 0; h < H; h++) {
					squares[w][h] = new ArrayList<Integer>();
					for (int i = 0; i < n; i++)
						for (int j = 0; j <= 1; j++)
							for (int k = 0; k <= 1; k++)
								if (check(w+j, h+k, circles[i], true) && !squares[w][h].contains(i+1))
									squares[w][h].add(i+1);
								else if (!check(w+j, h+k, circles[i], true) && !squares[w][h].contains(-(i+1)))
									squares[w][h].add(-(i+1));
					Collections.sort(squares[w][h]);
				}
			}

			// for (int w = 0; w < W; w++) {
			// 	for (int h = 0; h < H; h++) {
			// 		System.out.printf("Coords (%d, %d)\nList: ", w, h);
			// 		for (int i = 0; i < squares[w][h].size(); i++) {
			// 			System.out.print(squares[w][h].get(i)+" ");
			// 		}
			// 		System.out.println();
			// 	}
			// }

			boolean[] formulae = new boolean[n];
			int ans = getAns(formulae, circles, squares, W, H, n, 0);
			System.out.println(ans);
		}
	}

	static int getAns(boolean[] formulae, int[][] circles, ArrayList<Integer>[][] squares, int W, int H, int n, int i) {
		if (i < n) {
			int ans = 0;
			formulae[i] = true;
			ans += getAns(formulae, circles, squares, W, H, n, i+1);
			formulae[i] = false;
			ans += getAns(formulae, circles, squares, W, H, n, i+1);
			return ans;
		} else {
			return checkFormulae(formulae, circles, squares, W, H, n);
		}
	}

	static int checkFormulae(boolean[] formulae, int[][] circles, ArrayList<Integer>[][] squares, int W, int H, int n) {
		for (int w = 0; w < W; w++) {
			for (int h = 0; h < H; h++) {
				boolean valid = true;
				for (int i = 0; i < n && valid; i++) {
					if (formulae[i] && Collections.binarySearch(squares[w][h], i+1) < 0) {
						valid = false;
					} else if (!formulae[i] && Collections.binarySearch(squares[w][h], -(i+1)) < 0) {
						valid = false;
					}
				}
				
				if (valid) {
					return 1;
				}
			}
		}
		return 0; 
	}

	static boolean check(int w, int h, int[] c, boolean eq) {
		int ans = (w - c[0]) * (w - c[0]) + (h - c[1]) * (h - c[1]);
		return eq ? ans <= c[2] * c[2] : ans < c[2] * c[2];
	}
}