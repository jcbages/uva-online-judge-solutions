#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define CARDS 2

typedef struct {
	char card[3];
	int num;
} Pile;

void initPiles(Pile *piles);
int compatible(char c1[], char c2[]);
int canInsert(int i, char card[], Pile *piles);
void shiftLeft(int i, Pile *piles);

int main() {
	Pile *piles = malloc(CARDS * sizeof(Pile));
	int i, insertPos, eof = 0;
	while (eof == 0) {
		for (i = 0, insertPos = 0; i < CARDS; i++, insertPos++) {
			char card[3];
			scanf("%s", card);
			if (i == 0) {
				if (card[0] == '#') {
					eof = 1;
					break;
				} else {
					initPiles(piles);
				}
			}

			/* Add in my pile */
			strcpy(piles[insertPos].card, card);
			piles[insertPos].num += 1;

			/* Try to add in other pile */
			int actualPos = insertPos;
			while (canInsert(actualPos, card, piles)) {
				if (compatible(card, piles[actualPos-3].card)) {
					strcpy(piles[actualPos-3].card, piles[actualPos].card);
					piles[actualPos-3].num += piles[actualPos].num;
					strcpy(piles[actualPos].card, "");
					piles[actualPos].num = 0;
					shiftLeft(actualPos, piles);
					actualPos -= 3;
				} else if (compatible(card, piles[actualPos-1].card)) {
					strcpy(piles[actualPos-1].card, piles[actualPos].card);
					piles[actualPos-1].num += piles[actualPos].num;
					strcpy(piles[actualPos].card, "");
					piles[actualPos].num = 0;
					shiftLeft(actualPos, piles);
					actualPos -= 1;
				}
				insertPos -= 1;
			}

			insertPos += 1;

			int j;
			for (j = 0; j < insertPos; j++)
				printf("%s-%d ", piles[i].card, piles[i].num);
			printf("\n=========================================\n");
		}
		for (i = 0; i < CARDS; i++) {
			if (piles[i].num > 0) printf("%d ", piles[i].num);
			if (i < CARDS-1) printf(" ");
		}
		printf("\n");
	}
	free(piles);
	return 0;
}

void initPiles(Pile *piles) {
	int i;
	for (i = 0; i < CARDS; i++) {
		strcpy(piles[i].card, "");
		piles[i].num = 0;
	}
}

int compatible(char c1[], char c2[]) {
	return strlen(c1) > 0 && (c1[0] == c2[0] || c1[1] == c2[1]);
}

int canInsert(int i, char card[], Pile *piles) {
	int c1 = i-3 >= 0 && compatible(card, piles[i-3].card) == 1;
	int c2 = i-1 >= 0 && compatible(card, piles[i-1].card) == 1;
	return c1 || c2;
}

void shiftLeft(int i, Pile *piles) {
	for (; i < CARDS-1; i++) {
		piles[i].num = piles[i+1].num;
		strcpy(piles[i].card, piles[i+1].card);
	}
	piles[CARDS-1].num = 0;
	strcpy(piles[CARDS-1].card, "");
}