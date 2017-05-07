#include <cstdlib>
#include <iostream>
#include <sstream>
using namespace std;
#define MAX 5003
#define MOD 1000000007
#define sum(a, b) ((((a) % MOD) + ((b) % MOD)) % MOD)
#define mul(a, b) ((((a) % MOD) * ((b) % MOD)) % MOD)

int main() {
	long long divs[MAX], max = 0;
	for (long long i = 1; i < MAX; i++) {
		long long sum = 0;
		for (long long j = 1; j < i; j++) {
			sum += (i % j == 0) ? 1 : 0;
		}
		divs[i] = sum;
		max = (sum > max) ? sum : max;
	}
	max += 5;
	
	long long combs[MAX][max], val;
	combs[1][1] = 1;
	for (long long n = 2; n < MAX; n++) {
		for (long long k = 1; k <= (max-1 < n ? max-1 : n); k++) {
			val = sum(combs[n-1][k], combs[n-1][k-1]);
			combs[n][k] = (k == 1) ? n : (k >= n) ? 1 : val;
		}
	}

	long long **graphs = (long long**) malloc(sizeof(long long*)*MAX);
	long long **arcs = (long long**) malloc(sizeof(long long*)*MAX);
	for (long long i = 0; i < MAX; i++) {
		*(graphs+i) = (long long*) malloc(sizeof(long long)*max);
		*(arcs+i) = (long long*) malloc(sizeof(long long)*max);
	}

	for (long long k = 1; k < max; k++) {
		graphs[1][k] = 1;
		arcs[1][k] = 0;
	}

	long long comb, numarcs;
	for (long long n = 2; n < MAX; n++) {
		for (long long k = 1; k < max; k++) {
			if (divs[n] > k) {
				comb = combs[divs[n]][k];
				numarcs = k;
			} else {
				comb = 1;
				numarcs = divs[n];
			}
			graphs[n][k] = mul(graphs[n-1][k], comb);
			arcs[n][k] = sum(arcs[n-1][k], numarcs);
		}
	}

	stringstream s;
	long long T, N, K;
	cin >> T;
	for (long long t = 0; t < T; t++) {
		cin >> N >> K;
		K = (K < max-1) ? K : max-1;
		s << arcs[N][K] << ' ' << graphs[N][K] << '\n';
	}
	cout << s.str();

	for (long long i = 0; i < MAX; i++) {
		free(*(graphs+i));
		free(*(arcs+i));
	}
	free(graphs);
	free(arcs);
}
