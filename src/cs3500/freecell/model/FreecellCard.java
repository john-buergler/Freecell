package cs3500.freecell.model;

/**
 * Represents a card in a game of freecell.
 *
 */
public class FreecellCard {
  int value;
  String suit;
  int index;
  String color;

  /**
   * Constructor for a FreecellCard.
   * @param value represents the value of a card.
   * @param suit represents the suit of a card.
   */
  public FreecellCard(int value, String suit) {
    if (value > 13 || value < 1) {
      throw new IllegalArgumentException("Card value is between 1 and 13!");
    }
    if (!(suit.equals("♣")) && !(suit.equals("♠")) && !(suit.equals("♦")) && !(suit.equals("♥"))) {
      throw new IllegalArgumentException("invalid suit!");
    }
    if (suit.equals("♣") ||
        suit.equals("♠")) {
      this.color = "black";
    }
    else {
      this.color = "red";
    }
    this.value = value;
    this.suit = suit;
  }

  boolean checkValue(FreecellCard topcard) {
    return topcard.value == this.value - 1;
  }

  @Override
  public String toString() {
    if (this.value == 11) {
      return "J" + this.suit;
    }
    if (this.value == 12) {
      return "Q" + this.suit;
    }
    if (this.value == 13) {
      return "K" + this.suit;
    }
    if (this.value == 1) {
      return "A" + this.suit;
    } else {


      String cardValue = String.valueOf(value);
      return cardValue + this.suit;
    }
  }

  public int getIndex() {
    return this.index;
  }
}
