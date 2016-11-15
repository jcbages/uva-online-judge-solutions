#include <algorithm>
#include <cmath>
#include <iostream>
#include <set>
#include <string>
using namespace std;

struct anagram_compare {
	const bool operator() (const string& a, const string& b) {
		for (int i = 0; i < min(a.length(), b.length()); i++) {
			if (tolower(a[i]) == tolower(b[i])) { // upper/lower
				if (a[i] != b[i]) {
					return a[i] < b[i];
				}
			} else { // different letters
				return tolower(a[i]) < tolower(b[i]);
			}
		}
		return a.length() < b.length();
	}
};

int N;
string str;
set<string, anagram_compare> uniq;

int main() {
	while (cin >> N) {
		// add each anagram
		for (int i = 0; i < N; i++) {
			uniq.clear();
			cin >> str;
			sort(str.begin(), str.end());
			do {
				uniq.insert(str);
			} while (next_permutation(str.begin(), str.end()));
			// print the sorted set
			for (set<string>::iterator it = uniq.begin(); it != uniq.end(); it++) {
				cout << *it << "\n";
			}
		}
	}
}
