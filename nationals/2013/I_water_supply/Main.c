#include <stdio.h>
#include <stdlib.h>

int getAns(int **graph, int N);
void dfs(int **graph, int N, int o, int s, int *mark);

int main() {
	int N, M, i, j;
	while (scanf("%d %d", &N, &M) != EOF) {
		/* Initialize graph as a matrix */ 
		int **graph = malloc((N+1) * sizeof(int *));
		for (i = 0; i < N+1; i++) {
			graph[i] = malloc((N+1) * sizeof(int));
			for (j = 0; j < N+1; j++) {
				graph[i][j] = 0;
			}
		}
		/* Read and add every edge */
		for (i = 0; i < M; i++) {
			int a, b;
			scanf("%d %d", &a, &b);
			graph[a][b] = 1;
		}
		/* Get and print ans */
		int ans = getAns(graph, N);
		printf("%d\n", ans);
		/* Release used memory */
		for (i = 0; i < N; i++) {
			free(graph[i]);
		}
		free(graph);
	}
	return 0;
}

int getAns(int **graph, int N) {
	/* Variables for iteration */
	int i, j;
	/* Make the initial DFS (original graph) */
	int *mark = malloc((N+1) * sizeof(int));
	for (i = 0; i < N+1; i++) {
		mark[i] = 0;
	}
	dfs(graph, N, -1, 0, mark);
	/* Calculate worst-case ans, that is,   */
	/* how many nodes it can not get from 0 */
	int ans = 0;
	for (i = 0; i < N+1; i++) {
		if (!mark[i]) {
			ans += 1;
		}
	}
	/* Make 0 have only edges to the nodes */
	/* it can not get initially			   */
	for (i = 1; i < N+1; i++) {
		graph[0][i] = !mark[i];
	}

	/* Reset previously used marks */
	for (i = 0; i < N+1; i++) {
		mark[i] = 0;
	}
	/* Try to find paths from other	 */
	/* unreachable vertices to low	 */
	/* the worst-case answer		 */
	mark[0] = 1;
	for (i = 0; i < N+1; i++) {
		if (graph[0][i] && !mark[i]) {
			dfs(graph, N, i, i, mark);
		}
	}
	for (i = 0; i < N+1; i++) {
		if (graph[0][i] && mark[i]) {
			ans -= 1;
		}
	}
	/* Release memory used */
	free(mark);
	/* Finally return the best ans */
	return ans;
}

void dfs(int **graph, int N, int o, int s, int *mark) {
	if (s != o) {
		mark[s] = 1;
	}
	int i;
	for (i = 0; i < N+1; i++) {
		if (graph[s][i] && !mark[i]) {
			dfs(graph, N, o, i, mark);
		}
	}
}
