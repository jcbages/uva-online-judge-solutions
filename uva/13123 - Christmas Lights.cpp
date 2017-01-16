#include <iostream>
#include <cstring>
#include <string>
using namespace std;

const int MAX = 1001000;
const int CHUNKS = 1001;

char lights[MAX];
int ones[CHUNKS], swaps[CHUNKS];
string chain;

inline int query(int i) {
	return (lights[i] + swaps[i / CHUNKS]) & 1;
}

void update(int a, int b) {
	// update left offset
	for (; a % CHUNKS != 0 && a <= b; a++) {
		ones[a / CHUNKS] += (query(a) ? -1 : 1);
		lights[a]++;
	}

	// update complete chunks
	for (; a + CHUNKS <= b; a += CHUNKS) {
		swaps[a / CHUNKS]++;
		ones[a / CHUNKS] = CHUNKS - ones[a / CHUNKS];
	}

	// update right offset
	for (; a <= b; a++) {
		ones[a / CHUNKS] += (query(a) ? -1 : 1);
		lights[a]++;
	}
}

int find_left_one(int i) {
	// check in same chunk
	int chunk = i / CHUNKS;
	for (int ii = i; ii >= 0 && ii / CHUNKS == chunk; ii--) {
		if (query(ii)) {
			return ii;
		}
	}

	// check in different chunk
	for (chunk--; chunk >= 0 && ones[chunk] == 0; chunk--)
		;
	if (chunk < 0) {
		return i; // nothing found
	}
	for (int ii = CHUNKS * (chunk + 1) - 1;; ii--) {
		if (query(ii)) {
			return ii;
		}
	}
}

int find_right_one(int i) {
	// check in same chunk
	int chunk = i / CHUNKS;
	for (int ii = i; ii < MAX && ii / CHUNKS == chunk; ii++) {
		if (query(ii)) {
			return ii;
		}
	}

	// check in different chunk
	for (chunk++; chunk < CHUNKS && ones[chunk] == 0; chunk++)
		;
	if (chunk >= CHUNKS) {
		return i; // nothing found
	}
	for (int ii = CHUNKS * chunk;; ii++) {
		if (query(ii)) {
			return ii;
		}
	}
}

int main() {
	bool first_found;
	int T, K, M, i, j, k, val, offset, ai, bi;
	cin >> T;
	while (T--) {
		// read the test case info
		cin >> K >> M;
		memset(lights, 0, sizeof(char) * MAX);	// clean the lights
		memset(ones, 0, sizeof(int) * CHUNKS);	// clean the ones
		memset(swaps, 0, sizeof(int) * CHUNKS);	// clean the swaps

		// convert HEX to lights and update ones
		cin >> chain;
		for (i = chain.length() - 1, j = MAX - 1; i >= 0; i--) {
			val = chain[i] + (chain[i] >= 'A' ? 10 - 'A' : '0');
			for (k = 0; k < 4; k++, j--, val /= 2) {
				ones[j / CHUNKS] += val & 1;
				lights[j] += val & 1;
			}
		}

		// make the M seconds switches
		offset = MAX - K;
		while (M--) {
			cin >> ai >> bi;
			update(
				find_left_one(ai + offset - 1),
				find_right_one(bi + offset - 1)
			);
		}

		// convert lights to HEX and print
		first_found = false;
		for (i = 0; i < MAX; i += 4) {
			// calculate HEX value
			val = 0;
			for (j = 0; j < 4; j++) {
				val = 2 * val + query(i + j);
			}
			
			// print the value if its the case
			first_found = first_found || val;
			if (first_found) {
				cout << (char) (val + (val < 10 ? '0' : 'A' - 10));
			}
		}
		if (!first_found) {
			cout << '0';
		}
		cout << '\n';
	}
}
