package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for a game of freecell, creates the deck and starts the game.
 */
public final class SimpleFreecellModel extends AbstractFreecellModel {

  public SimpleFreecellModel() {
    super();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    if (source == PileType.FOUNDATION) {
      throw new IllegalArgumentException("Cannot move foundation cards");
    }
    List<FreecellCard> sourcePile = new ArrayList<FreecellCard>();
    FreecellCard grabbedcard = null;

    if (source == PileType.CASCADE) {
      sourcePile = cascadePiles.get(pileNumber).cards;
      grabbedcard = getCascadeCardAt(pileNumber, cardIndex);
    }
    if (source == PileType.OPEN) {
      sourcePile = openPiles.get(pileNumber).cards;
      grabbedcard = getOpenCardAt(pileNumber);
    }

    switch (destination) {
      case FOUNDATION:
        List<FreecellCard> pile = foundationPiles.get(destPileNumber).cards;
        if (pile.isEmpty() && grabbedcard.value != 1) {
          throw new IllegalArgumentException("Aces must be first in foundation!");
        }
        if (pile.isEmpty() && grabbedcard.value == 1) {
          sourcePile.remove(grabbedcard);
          pile.add(grabbedcard);
        }
        else {
          if (pile.get(pile.size() - 1).value == grabbedcard.value - 1 &&
                  pile.get(pile.size() - 1).suit.equals(grabbedcard.suit)) {
            sourcePile.remove(grabbedcard);
            pile.add(grabbedcard);
          } else {
            throw new IllegalArgumentException("Move cannot be done");
          }
        }
        break;
      case CASCADE:
        List<FreecellCard> cascadePile = cascadePiles.get(destPileNumber).cards;
        if (cascadePile.isEmpty()) {
          sourcePile.remove(grabbedcard);
          cascadePile.add(grabbedcard);
        }
        if (cascadePile.get(cascadePile.size() - 1).value == grabbedcard.value + 1 &&
                !(cascadePile.get(cascadePile.size() - 1).color.equals(grabbedcard.color))) {
          sourcePile.remove(grabbedcard);
          cascadePile.add(grabbedcard);
        }
        else {
          throw new IllegalArgumentException("Move cannot be done");
        }
        break;
      case OPEN:
        List<FreecellCard> openPile = openPiles.get(destPileNumber).cards;
        if (openPile.isEmpty()) {
          sourcePile.remove(grabbedcard);
          openPile.add(grabbedcard);
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


