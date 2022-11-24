package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The model class for a MultiMove model of freecell. This allows for the shortcut to move multiple
 * cards at once depending on the number of piles that are open.
 */
public final class MultiMoveModel extends AbstractFreecellModel {

  /**
   * The constructor for the multimove model of Freecell.
   */
  public MultiMoveModel() {
    super();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    if (source == PileType.FOUNDATION) {
      throw new IllegalArgumentException("Cannot move foundation cards");
    }
    List<FreecellCard> sourcePile = new ArrayList<FreecellCard>();
    ArrayList<FreecellCard> grabbedcards = new ArrayList<FreecellCard>();

    if (source == PileType.OPEN) {
      sourcePile = openPiles.get(pileNumber).cards;
      grabbedcards.add(getOpenCardAt(pileNumber));
    }

    if (source == PileType.CASCADE) {
      sourcePile = cascadePiles.get(pileNumber).cards;
      if (checkMulti(sourcePile, cardIndex) &&
              wellOrdered(sourcePile.subList(cardIndex, sourcePile.size()))) {
        List<FreecellCard> cardStack = sourcePile.subList(cardIndex, sourcePile.size());
        for (FreecellCard card : cardStack) {
          grabbedcards.add(card);
        }
      }
      else {
        throw new IllegalArgumentException("Cannot move that many cards or the cards aren't " +
                "well ordered");
      }
    }
    processDestination(sourcePile, grabbedcards, destination, destPileNumber);
  }

  private boolean wellOrdered(List<FreecellCard> subList) {
    int value = subList.get(0).value;
    String color = subList.get(0).color;
    boolean b = false;
    for (FreecellCard card : subList) {
      if (card.value == value &&
              card.color.equals(color) &&
              subList.indexOf(card) == subList.size() - 1) {
        b = true;
      }
      if (card.value == value &&
              card.color.equals(color) &&
              subList.indexOf(card) != subList.size() - 1) {
        value = value - 1;
        if (color.equals("black")) {
          color = "red";
        }
        else {
          color = "black";
        }
      }
    }
    return b;
  }

  private boolean checkMulti(List<FreecellCard> sourcePile, int cardIndex) {
    return sourcePile.size() - cardIndex <= this.multiCount();
  }

  private int multiCount() {
    int cas = 0;
    int open = 1;
    for (int i = 0; i < getNumOpenPiles(); i++) {
      if (openPiles.get(i).cards.isEmpty()) {
        open++;
      }
    }
    for (int i = 0; i < getNumCascadePiles(); i++) {
      if (cascadePiles.get(i).cards.isEmpty()) {
        cas++;
      }
    }
    return (int) (open * Math.pow(2, cas));
  }

  private void processDestination(List<FreecellCard> sourcePile,
                                  ArrayList<FreecellCard> grabbedcards,
                                  PileType destination,
                                  int destPileNumber) {
    switch (destination) {
      case FOUNDATION:
        List<FreecellCard> pile = foundationPiles.get(destPileNumber).cards;
        if (pile.isEmpty() && grabbedcards.get(0).value != 1) {
          throw new IllegalArgumentException("Aces must be first in foundation!");
        }
        if (pile.isEmpty() && grabbedcards.get(0).value == 1) {
          pile.addAll(grabbedcards);
          sourcePile.removeAll(grabbedcards);
        }
        else {
          if (pile.get(pile.size() - 1).value == grabbedcards.get(0).value - 1 &&
                  pile.get(pile.size() - 1).suit.equals(grabbedcards.get(0).suit)) {
            pile.addAll(grabbedcards);
            sourcePile.removeAll(grabbedcards);
          } else {
            throw new IllegalArgumentException("Move cannot be done");
          }
        }
        break;
      case CASCADE:
        List<FreecellCard> cascadePile = cascadePiles.get(destPileNumber).cards;
        if (cascadePile.isEmpty()) {
          cascadePile.addAll(grabbedcards);
          sourcePile.removeAll(grabbedcards);
        }
        else {
          if (cascadePile.get(cascadePile.size() - 1).value == grabbedcards.get(0).value + 1 &&
                  !(cascadePile.get(cascadePile.size() - 1)
                          .color.equals(grabbedcards.get(0).color))) {
            cascadePile.addAll(grabbedcards);
            sourcePile.removeAll(grabbedcards);
          } else {
            throw new IllegalArgumentException("Move cannot be done");
          }
        }
        break;
      case OPEN:
        List<FreecellCard> openPile = openPiles.get(destPileNumber).cards;
        if (openPile.isEmpty() && grabbedcards.size() <= 1) {
          openPile.addAll(grabbedcards);
          sourcePile.removeAll(grabbedcards);
        }
        else {
          throw new IllegalArgumentException("Open piles can only hold one card.");
        }
        break;
      default: //No action because no other case.
        break;
    }
  }
}
