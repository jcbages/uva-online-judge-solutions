#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
using namespace std;

string str;
vector<string> vect, ans;
int cnt1[52], cnt2[52];
bool is_ananagram;

bool anagrams(string& a, string& b) {
	// reset arrays
	for (int i = 0; i < 52; i++) {
		cnt1[i] = cnt2[i] = 0;
	}
	// count freqs
	for (int i = 0; i < a.length(); i++) {
		cnt1[islower(a[i]) ? a[i]-'a' : a[i]-'A']++;
	}
	for (int i = 0; i < b.length(); i++) {
		cnt2[islower(b[i]) ? b[i]-'a' : b[i]-'A']++;
	}
	// check if both are anagrams
	for (int i = 0; i < 52; i++) {
		if (cnt1[i] != cnt2[i]) {
			return false;
		}
	}
	return true;
}

int main() {
	// until a line containing #
	while (cin >> str && str[0] != '#') {
		// add words to vector
		vect.push_back(str);
	}
	// check ananagrams
	for (int i = 0; i < vect.size(); i++) {
		is_ananagram = true;
		for (int j = 0; j < vect.size() && is_ananagram; j++) {
			if (i != j) {
				is_ananagram = !anagrams(vect[i], vect[j]);
			}
		}
		if (is_ananagram) {
			ans.push_back(vect[i]);
		}
	}
	// print sorted answer
	sort(ans.begin(), ans.end());
	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << "\n";
	}
}