#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
enum result {
  LIBERTY = 0,
  DEATH = 1,
};

struct drawer {
  int cardNum;
  int hasBeenOpened;
};

struct drawer *drawerSet;

void initialize(int prisoners) {
  int i = 0;
  int j = 0;
  int card = 0;
  int unique = 0;

  drawerSet = ((struct drawer *)malloc(prisoners * sizeof(struct drawer))) - 1;

  card = rand() % prisoners + 1;
  drawerSet[1].cardNum = card;
  drawerSet[1].hasBeenOpened = 0;

  for (i = 1 + 1; i < prisoners + 1; i++) {
    unique = 0;
    while (unique == 0) {
      for (j = 0; j < i; j++) {
        if (drawerSet[j].cardNum == card) {
          card = rand() % prisoners + 1;
          break;
        }
      }
      if (j == i) {
        unique = 1;
      }
    }
    drawerSet[i].cardNum = card;
    drawerSet[i].hasBeenOpened = 0;
  }
}

void closeAllDrawers(int prisoners) {
  int i = 0;
  for (i = 1; i < prisoners + 1; i++) {
    drawerSet[i].hasBeenOpened = 0;
  }
}

int libertyOrDeathAtRandom(int prisoners, int chances) {
  int i = 0;
  int j = 0;
  int chosenDrawer = 0;

  for (i = 1; i < prisoners + 1; i++) {
    int foundCard = 0;
    for (j = 0; j < chances; j++) {
      do {
        chosenDrawer = rand() % prisoners + 1;
      } while (drawerSet[chosenDrawer].hasBeenOpened == 1);

      if (drawerSet[chosenDrawer].cardNum == i) {
        foundCard = 1;
        break;
      }
      drawerSet[chosenDrawer].hasBeenOpened = 1;
    }
    closeAllDrawers(prisoners);
    if (foundCard == 0) {
      return DEATH;
    }
  }

  return LIBERTY;
}

int libertyOrDeathPlanned(int prisoners, int chances) {
  int i = 0;
  int j = 0;
  int chosenDrawer = 0;
  for (i = 1; i < prisoners + 1; i++) {
    chosenDrawer = i;
    int foundCard = 0;
    for (j = 0; j < chances; j++) {
      drawerSet[chosenDrawer].hasBeenOpened = 1;

      if (drawerSet[chosenDrawer].cardNum == i) {
        foundCard = 1;
        break;
      }
      if (chosenDrawer == drawerSet[chosenDrawer].cardNum) {
        do {
          chosenDrawer = rand() % prisoners + 1;
        } while (drawerSet[chosenDrawer].hasBeenOpened == 1);
      } else {
        chosenDrawer = drawerSet[chosenDrawer].cardNum;
      }
    }

    closeAllDrawers(prisoners);
    if (foundCard == 0) {
      return DEATH;
    }
  }

  return LIBERTY;
}

int main(int argc, char **argv) {
  int prisoners = 0;
  int chances = 0;
  unsigned long long int trials = 0;
  unsigned long long int i = 0;
  unsigned long long int count = 0;
  char *end = NULL;

  if (argc != 4) {
    return printf("Usage : %s <Number of prisoners> <Number of chances> <Number of trials>", argv[0]);
  }

  prisoners = atoi(argv[1]);
  chances = atoi(argv[2]);
  trials = strtoull(argv[3], &end, 10);

  srand(time(NULL));

  printf("Running random trials...");
  for (i = 0; i < trials; i += 1L) {
    initialize(prisoners);

    count += libertyOrDeathAtRandom(prisoners, chances) == DEATH ? 0 : 1;
  }

  printf("\n\nGames Played : %llu\nGames Won : %llu\nChances : %lf %% \n\n", trials, count, (100.0 * count) / trials);

  count = 0;

  printf("Running strategic trials...");
  for (i = 0; i < trials; i += 1L) {
    initialize(prisoners);

    count += libertyOrDeathPlanned(prisoners, chances) == DEATH ? 0 : 1;
  }

  printf("\n\nGames Played : %llu\nGames Won : %llu\nChances : %lf %% \n\n", trials, count, (100.0 * count) / trials);
  return 0;
}
