package cs3500.freecell.model;

import java.util.List;

/**
 * Represents a pile of cards in given Freecell game.
 */
public class Pile {
  PileType type;
  List<FreecellCard> cards;

  /**
   * Constructor for the Pile of cards.
   * @param type type of pile, as in cascade, open, or foundation.
   * @param cards the list of FreecellCards in the pile.
   */
  public Pile(PileType type, List cards) {
    this.cards = cards;
    this.type = type;
  }

  public PileType getType() {
    return type;
  }

  public List<FreecellCard> getCards() {
    return this.cards;
  }

}
