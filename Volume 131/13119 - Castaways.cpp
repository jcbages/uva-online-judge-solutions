#include <algorithm>
#include <cmath>
#include <iostream>
using namespace std;

const int MAX = 257;
int N, rect, tri, s1, s2, max_rect, max_tri, arr[MAX], sorted[MAX];

int best_rect_area() {
	for (int k = MAX-1; k > 0;) {
		if (sorted[k] > 1) {
			if (s1 == -1) {
				s1 = k;
				sorted[s1] -= 2;
			} else {
				s2 = k;
				sorted[s1] += 2;
				return s1 * s2;
			}
		} else {
			k--;
		}
	}
	if (s1 != -1)
		sorted[s1] += 2;
	s1 = s2 = -1;
	return 0;
}

bool is_triangle(int i, int j, int k) {
	return i + j > k && i + k > j && j + k > i;
}

int tri_area(int i, int j, int k) {
	double s = (i + j + k) / 2.0;
	return (int) sqrt(s * (s-i) * (s-j) * (s-k));
}

void try_best(int i, int j, int k) {
	if (is_triangle(i, j, k)) {
		sorted[k]--;
		s1 = s2 = -1;
		rect = best_rect_area();
		tri = tri_area(i, j, k);
		if (tri + rect > max_tri + max_rect) {
			max_tri = tri;
			max_rect = rect;
		}
		sorted[k]++;
	}
}

int main() {
	while (cin >> N) {
		fill_n(sorted, MAX, 0);
		fill_n(arr, MAX, 0);
		// read and sort data
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
			sorted[arr[i]]++;
		}
		// get best answer for every tuple
		s1 = s2 = -1;
		max_tri = 0; max_rect = best_rect_area();
		for (int i = 1; i < MAX; i++) {
			if (sorted[i] > 0) {
				sorted[i]--; // start using side
				for (int j = i; j < MAX; j++) {
					if (sorted[j] > 0) {
						sorted[j]--; // start using side
						// get best rectangle info
						s1 = s2 = -1;
						rect = best_rect_area();
						if (rect > 0)
							sorted[s1] -= 2; sorted[s2] -= 2;
						// get best area without rect sides (s1, s2)
						for (int k = MAX-1; k >= j; k--) {
							if (sorted[k] > 0 && is_triangle(i, j, k)) {
								tri = tri_area(i, j, k);
								if (tri + rect > max_tri + max_rect) {
									max_tri = tri;
									max_rect = rect;
								}
							}
						}
						// try to get best with s1 or s2
						if (rect > 0) {
							sorted[s1] += 2; sorted[s2] += 2;
							int tmp_s1 = s1, tmp_s2 = s2;
							try_best(i, j, tmp_s1);
							try_best(i, j, tmp_s2);
						}
						sorted[j]++; // stop using side
					}
				}
				sorted[i]++; // stop using side
			}
		}
		cout << max_tri + max_rect << endl;
	}
}