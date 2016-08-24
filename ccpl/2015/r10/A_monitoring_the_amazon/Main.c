#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct Node {
	int x, y, mark;
	struct Node *e1, *e2;
} Node;

void mkGraph(Node *nodes, int N);
void mkDFS(Node *root);
int cmp(Node *a, Node *b);
int getDist(Node *a, Node *b);
char *getAns(Node *nodes, int N);

int main() {
	while (1) {
		int N, i;
		scanf("%d", &N);
		if (N == 0) break;
		Node *nodes = malloc(N * sizeof(Node));
		for (i = 0; i < N; i++) {
			scanf("%d %d", &nodes[i].x, &nodes[i].y);
			nodes[i].mark = 0;
		}
		char *ans = getAns(nodes, N);
		printf("%s\n", ans);
		free(nodes);
	}
	return 0;
}

void mkGraph(Node *nodes, int N) {
	int i;
	for (i = 0; i < N; i++) {
		int j;
		Node *e1 = &nodes[0], *e2 = &nodes[1];
		int d1 = getDist(&nodes[i], e1);
		int d2 = getDist(&nodes[i], e2);
		for (j = 0; j < N; j++) {
			if (i != j) {
				int dist = getDist(&nodes[i], &nodes[j]);
				if (dist < d1) {
					e1 = &nodes[j];
					d1 = dist;
				} else if (dist < d2) {
					e2 = &nodes[j];
					d2 = dist;
				} else if (dist == d1 && cmp(&nodes[j], e1) < 0) {
					e1 = &nodes[j];
					d1 = dist;
				} else if (dist == d2 && cmp(&nodes[j], e2) < 0) {
					e2 = &nodes[j];
					d2 = dist;
				}
			}
		}
		nodes[i].e1 = e1;
		nodes[i].e2 = e2;
	}
}

void mkDFS(Node *root) {
	root->mark = 1;
	if (root->e1->mark == 0)
		mkDFS(root->e1);
	if (root->e2->mark == 0)
		mkDFS(root->e2);
}

int cmp(Node *a, Node *b) {
	int diffX = a->x - b->x;
	int diffY = a->y - b->y;
	return (diffX != 0) ? diffX : diffY;
}

int getDist(Node *a, Node *b) {
	int v1 = (int) pow(a->x - b->x, 2);
	int v2 = (int) pow(a->y - b->y, 2);
	return v1 + v2;
}

char *getAns(Node *nodes, int N) {
	if (N <= 3) {
		return "All stations are reachable.";
	} else {
		mkGraph(nodes, N);
		mkDFS(&nodes[0]);
		int i;
		for (i = 0; i < N; i++) {
			if (nodes[i].mark == 0) {
				return "There are stations that are unreachable.";
			}
		}
		return "All stations are reachable.";
	}
}