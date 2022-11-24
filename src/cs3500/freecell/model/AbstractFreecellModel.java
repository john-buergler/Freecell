package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class AbstractFreecellModel implements FreecellModel<FreecellCard> {
  protected ArrayList<Pile> openPiles;
  protected ArrayList<Pile> cascadePiles;
  protected ArrayList<Pile> foundationPiles;
  protected boolean gameStarted;

  public AbstractFreecellModel() {
    openPiles = new ArrayList<Pile>();
    cascadePiles = new ArrayList<Pile>();
    foundationPiles = new ArrayList<Pile>();
    gameStarted = false;
  }

  public List<FreecellCard> getDeck() {
    ArrayList<FreecellCard> cards = new ArrayList<FreecellCard>();
    ArrayList<String> suits =
            new ArrayList<String>(Arrays.asList("♥", "♦", "♠", "♣"));
    for (int i = 0; i < suits.size(); i++) {
      ArrayList<Integer> values =
              new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
      for (int j = 0; j < values.size(); j++) {
        cards.add(new FreecellCard(values.get(j), suits.get(i)));
      }
    }
    return cards;
  }

  @Override
  public void startGame(List<FreecellCard> deck, int numCascadePiles, int numOpenPiles,
                        boolean shuffle) {

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("Number of Cascade piles from 4-8, " +
              "Number of open piles must be from 1-4");
    }

    if (findDuplicates(deck) || deck.size() != 52) {
      throw new IllegalArgumentException("invalid deck!");
    }

    openPiles = new ArrayList<Pile>();
    cascadePiles = new ArrayList<Pile>();
    foundationPiles = new ArrayList<Pile>();

    if (shuffle) {
      Collections.shuffle(deck);
    }
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new Pile(PileType.OPEN, new ArrayList<FreecellCard>()));
    }
    for (int i = 0; i < numCascadePiles; i++) {
      Pile indexPile = new Pile(PileType.CASCADE, new ArrayList<FreecellCard>());
      cascadePiles.add(indexPile);
      for (int j = 0; j < deck.size(); j++) {
        if (j % numCascadePiles == i) {
          indexPile.cards.add(deck.get(j));
          deck.get(j).index = indexPile.cards.size() - 1;
        }
      }
    }
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new Pile(PileType.FOUNDATION, new ArrayList<FreecellCard>()));
    }
    this.gameStarted = true;
  }

  public abstract void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                            int destPileNumber);

  private boolean findDuplicates(List<FreecellCard> deck) {
    int count = 0;
    for (FreecellCard card : deck) {
      for (FreecellCard card2 : deck) {
        if (card.equals(card2)) {
          count++;
        }
      }
    }
    return count != deck.size();
  }

  @Override
  public boolean isGameOver() {
    int count = 0;
    for (Pile pile : foundationPiles) {
      if (pile.cards.size() == 13) {
        count++;
      }
    }
    return count == 4;
  }

  public ArrayList<Pile> getCascadePiles() {

    return cascadePiles;
  }

  public ArrayList<Pile> getOpenPiles() {

    return openPiles;
  }

  public ArrayList<Pile> getFoundationPiles() {

    return foundationPiles;
  }

  @Override
  public int getNumCardsInFoundationPile(int index) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (index + 1 > foundationPiles.size() || index < 0) {
      throw new IllegalArgumentException("invalid pile");
    }
    else {
      return foundationPiles.get(index).cards.size();
    }
  }

  @Override
  public int getNumCascadePiles() {
    int count = 0;
    if (!gameStarted) {
      return -1;
    } else {
      return cascadePiles.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index) {
    int num = 0;
    int count = 0;
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (index + 1 > cascadePiles.size() || index < 0) {
      throw new IllegalArgumentException("invalid pile");
    }
    if (cascadePiles.get(index).cards.size() == 0) {
      return 0;
    }
    else {
      return cascadePiles.get(index).cards.size();
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index) {
    int count = 0;
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (index + 1 > openPiles.size() || index < 0) {
      throw new IllegalArgumentException("invalid pile");
    }
    if (openPiles.get(index).cards.isEmpty()) {
      return 0;
    }
    else {
      return openPiles.get(index).cards.size();
    }
  }

  @Override
  public int getNumOpenPiles() {
    int count = 0;
    if (!gameStarted) {
      return -1;
    } else {
      return openPiles.size();
    }
  }

  @Override
  public FreecellCard getFoundationCardAt(int pileIndex, int cardIndex) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (pileIndex > foundationPiles.size() - 1 ||
            foundationPiles.get(pileIndex).cards.isEmpty() ||
            cardIndex + 1 > foundationPiles.get(pileIndex).cards.size() ||
            cardIndex < 0 || pileIndex < 0) {
      throw new IllegalArgumentException("invalid pile");
    }
    else {
      return foundationPiles.get(pileIndex).cards.get(cardIndex);
    }
  }

  @Override
  public FreecellCard getCascadeCardAt(int pileIndex, int cardIndex) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (cardIndex < 0 || pileIndex < 0 ||
            pileIndex >= cascadePiles.size() ||
            cardIndex >= cascadePiles.get(pileIndex).cards.size() ||
            cascadePiles.get(pileIndex).cards.isEmpty()) {
      throw new IllegalArgumentException("Invalid Pile or Card.");
    }
    else {
      return cascadePiles.get(pileIndex).cards.get(cardIndex);
    }
  }

  @Override
  public FreecellCard getOpenCardAt(int pileIndex) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
    if (pileIndex > openPiles.size() - 1 || pileIndex < 0) {
      throw new IllegalArgumentException("invalid pile");
    }
    if (openPiles.get(pileIndex).cards.isEmpty()) {
      return null;
    }
    else {
      return openPiles.get(pileIndex).cards.get(0);
    }
  }


}
