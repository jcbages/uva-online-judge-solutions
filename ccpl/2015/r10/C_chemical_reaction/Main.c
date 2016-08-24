#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_P 20
#define MAX_PERMS 300000

typedef struct {
	int type, heat;
} Result;

typedef struct Node {
	int num, nSize;
	struct Node *nodes;
} Node;

int cmpfunc(const void *a, const void *b);
int countUnique(int *chemicals, int n);
int *getCount(int *chemicals, int n);
void genTree(Node *root, int *count, int cSize);
void freeTree(Node *root);
void getPerms(Node *root, char *acc, char **ans, int *s);
int getRes(char *str, Result **results);
int getAns(Result **results, int *chemicals, int m, int n);

int main() {
	int T, i;
	scanf("%d", &T);
	for (i = 0; i < T; i++) {
		int j;
		int m;
		scanf("%d", &m);
		Result **results = malloc(m * sizeof(Result *));
		for (j = 0; j < m; j++) {
			int k;
			results[j] = malloc(m * sizeof(Result));
			for (k = 0; k < m; k++) {
				scanf("%d %d", &results[j][k].type, &results[j][k].heat);
			}
		}
		int n;
		scanf("%d", &n);
		int *chemicals = malloc(n * sizeof(int));
		for (j = 0; j < n; j++) {
			scanf("%d", &chemicals[j]);
		}
		qsort(chemicals, n, sizeof(int), cmpfunc);
		int ans = getAns(results, chemicals, m, n);
		printf("%d\n", ans);
		for (j = 0; j < m; j++) {
			free(results[j]);
		}
		free(results);
		free(chemicals);
		if (i < T - 1) {
			char aux[3];
			scanf("%s", aux);
		}
	}
	return 0;
}

int cmpfunc(const void *a, const void *b) {
	return *(int *) a - *(int *) b;
}

int countUnique(int *chemicals, int n) {
	int i, prev = chemicals[0], size = 1;
	for (i = 1; i < n; i++) {
		if (chemicals[i] != prev) {
			prev = chemicals[i];
			size += 1;
		}
	}
	return size;
}

int *getCount(int *chemicals, int n) {
	int size = countUnique(chemicals, n);
	int *count = malloc(size * sizeof(int));
	int i, j = 0, prev = -1;
	for (i = 0; i < n; i++) {
		if (chemicals[i] != prev) {
			prev = chemicals[i];
			count[j++] = 1;
		} else {
			count[j-1] += 1;
		}
	}
	return count;
}

void genTree(Node *root, int *count, int cSize) {
	int i, nSize = 0;
	for (i = 0; i < cSize; i++) {
		if (count[i] > 0) {
			nSize += 1;
		}
	}
	int j = 0;
	root->nSize = nSize;
	root->nodes = malloc(nSize * sizeof(Node));
	for (i = 0; i < nSize; i++) {
		root->nodes[i].num = -1;
		root->nodes[i].nSize = 0;
	}
	for (i = 0; i < cSize; i++) {
		if (count[i] > 0) {
			count[i] -= 1;
			root->nodes[j].num = i+1;
			genTree(&root->nodes[j], count, cSize);
			count[i] += 1;
			j += 1;
		}
	}
}

void freeTree(Node *root) {
	int i;
	for (i = 0; i < root->nSize; i++) {
		freeTree(&root->nodes[i]);
	}
	free(root->nodes);
}

void getPerms(Node *root, char *acc, char **ans, int *s) {
	if (root->num != -1) {
		char aux[3];
		snprintf(aux, sizeof(aux), "%d", root->num);
		strcat(acc, " ");
		strcat(acc, aux);
	}
	if (root->nSize > 0) {
		int i;
		for (i = 0; i < root->nSize; i++) {
			getPerms(&root->nodes[i], acc, ans, s);
		}
	} else {
		ans[*s] = malloc(strlen(acc) * sizeof(char));
		strcpy(ans[*s], acc);
		*s += 1;
	}
	if (root->num != -1) {
		acc[strlen(acc)-2] = '\0';
	}
}

int getRes(char *str, Result **results) {
	int n = strlen(str), i;
	Result res = results[str[1]-'0'-1][str[3]-'0'-1];
	int actual = res.type;
	int ans = res.heat;
	for (i = 5; i < n; i+=2) {
		res = results[actual-1][str[i]-'0'-1];
		actual = res.type;
		ans   += res.heat;
	}
	return ans;
}

int getAns(Result **results, int *chemicals, int m, int n) {
	int cSize = countUnique(chemicals, n);
	int *count = getCount(chemicals, n);
	Node root;
	root.num = -1;
	root.nSize = 0;
	genTree(&root, count, cSize);

	int s = 0;
	char **perms = malloc(MAX_PERMS * sizeof(char *));
	char *acc = malloc(MAX_P * sizeof(char));
	getPerms(&root, acc, perms, &s);
	
	char *min;
	int ans = 1000000000, i;
	for (i = 0; i < s; i++) {
		int res = getRes(perms[i], results);
		if (res < ans) {
			min = perms[i];
			ans = res;
		}
	}

	for (i = 0; i < s; i++) {
		free(perms[i]);
	}
	free(acc);
	freeTree(&root);
	free(count);

	return ans;
}
