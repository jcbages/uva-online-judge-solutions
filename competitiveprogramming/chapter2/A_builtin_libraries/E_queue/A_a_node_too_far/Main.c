#include <stdio.h>
#include <stdlib.h>

#define LIM 1000

typedef struct {
	int a, b;
} Conn;

int indexOf(int *arr, int n, int itm);
int getAns(Conn *conns, int N, int s, int ttl, int cnt, int *nodes, int *marks);

int main() {
	int caseNum = 1;
	while (1) {
		int N, cnt = 0, i;
		scanf("%d", &N);
		if (N == 0) break;
		Conn *conns = malloc(N * sizeof(Conn));
		int *nodes = malloc(LIM * sizeof(int));
		for (i = 0; i < N; i++) {
			int a, b;
			scanf("%d %d", &a, &b);
			conns[i].a = a; conns[i].b = b;
			if (indexOf(nodes, LIM, a) == -1)
				nodes[cnt++] = a;
			if (indexOf(nodes, LIM, b) == -1)
				nodes[cnt++] = b;
		}
		while (1) {
			int s, ttl;
			scanf("%d %d", &s, &ttl);
			if (s == 0 && ttl == 0) break;
			int *marks = malloc(cnt * sizeof(int));
			for (i = 0; i < cnt; i++) marks[i] = 0;
			int ans = getAns(conns, N, s, ttl, cnt, nodes, marks);
			printf(
				"Case %d: %d nodes not reachable from node %d with TTL = %d.\n",
				caseNum++,
				cnt - ans,
				s,
				ttl
			);
			free(marks);
		}
		free(nodes);
		free(conns);
	}
	return 0;
}

int indexOf(int *arr, int n, int itm) {
	int i;
	for (i = 0; i < n; i++) {
		if (arr[i] == itm) {
			return i;
		}
	}
	return -1;
}

int getAns(Conn *conns, int N, int s, int ttl, int cnt, int *nodes, int *marks) {
	marks[indexOf(nodes, cnt, s)] = 1;
	if (ttl == 0) return 1;
	int i, ans = 1;
	for (i = 0; i < N; i++) {
		if (conns[i].a == s && marks[indexOf(nodes, cnt, conns[i].b)] == 0) {
			ans += getAns(conns, N, conns[i].b, ttl-1, cnt, nodes, marks);
		}
		if (conns[i].b == s && marks[indexOf(nodes, cnt, conns[i].a)] == 0) {
			ans += getAns(conns, N, conns[i].a, ttl-1, cnt, nodes, marks);
		}
	}
	return ans;
}