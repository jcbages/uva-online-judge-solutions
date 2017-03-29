#include <cmath>
#include <iostream>
using namespace std;

int months, records, month, last, cnt;
double down, loan, car, percent, monthly, percentages[105];

int main() {
	// while nonnegative duration
	while (cin >> months && months >= 0) {
		// read data
		cin >> down >> loan >> records;
		last = -1;
		for (int i = 0; i < records; i++) {
			cin >> month >> percent;
			// add non given months
			percentages[month] = percent;
			for (int j = month - 1; j > last; j--)
				percentages[j] = percentages[last];
			last = month;
		}
		// add missing later months
		for (int j = last + 1; j <= months; j++)
			percentages[j] = percentages[last];
		// get answer
		monthly = loan / months;
		car = (loan + down) * (1 - percentages[0]);
		for (cnt = 1; cnt <= months && car <= loan; cnt++) {
			loan -= monthly;
			car *= (1 - percentages[cnt]);
		}
		cout << cnt-1 << (cnt-1 == 1 ? " month" : " months") << endl;
	}
}
