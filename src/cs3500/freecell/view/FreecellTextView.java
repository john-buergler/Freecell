package cs3500.freecell.view;

import java.io.IOException;

import cs3500.freecell.model.FreecellModelState;

/**
 * An instance of FreecellView in the text format.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModelState<?> model;
  private Appendable dest;

  /**
   * The constructor for a FreecellModel in text form.
   * @param model the game that is being displayed.
   */
  public FreecellTextView(FreecellModelState<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
  }

  public FreecellTextView(FreecellModelState<?> model, Appendable dest) {
    this.dest = dest;
    this.model = model;
  }

  @Override
  public String toString() {
    if (model.getNumCascadePiles() == -1) {
      return "";
    }
    else {
      return getFoundationString(model) +
              getOpenString(model) +
              getCascadeString(model);
    }
  }

  @Override
  public void renderBoard() throws IOException {
    dest.append(this.toString() + "\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    dest.append(message);
  }

  //Gets the string of all foundation piles.
  private <T> String getFoundationString(FreecellModelState<T> m) {
    Integer foundCount = 1;
    String foundString = "";
    for (int i = 0; i < 4; i++) {
      foundString = foundString + "F" + foundCount.toString() + ":";
      for (int j = 0; j < m.getNumCardsInFoundationPile(i); j++) {
        if (m.getNumCardsInFoundationPile(i) - 1 == j) {
          foundString = foundString +  " " + m.getFoundationCardAt(i, j).toString();
        }
        else {
          if (m.getFoundationCardAt(i, j) != null) {
            foundString = foundString +  " " + m.getFoundationCardAt(i, j).toString() + ",";
          }
        }
      }
      foundString = foundString + "\n";
      foundCount++;
    }
    return foundString;
  }

  //Gets the string of all open piles.
  private <T> String getOpenString(FreecellModelState<T> m) {
    Integer openCount = 1;
    String openString = "";
    for (int i = 0; i < m.getNumOpenPiles(); i++) {
      openString = openString + "O" + openCount.toString() + ":";
      if (m.getNumCardsInOpenPile(i) == 1) {
        openString = openString + " " +  m.getOpenCardAt(i).toString();
      }
      openString = openString + "\n";
      openCount++;
    }
    return openString;
  }

  //Gets the string of all cascade piles.
  private <T> String getCascadeString(FreecellModelState<T> m) {
    Integer cascadeCount = 1;
    String cascadeString = "";
    for (int i = 0; i < m.getNumCascadePiles(); i++) {
      cascadeString = cascadeString + "C" + cascadeCount.toString() + ":";
      for (int j = 0; j < m.getNumCardsInCascadePile(i); j++) {
        if (j == m.getNumCardsInCascadePile(i) - 1) {
          cascadeString = cascadeString + " " + m.getCascadeCardAt(i, j).toString();
        } else {
          if (m.getCascadeCardAt(i, j) != null) {
            cascadeString = cascadeString + " " + m.getCascadeCardAt(i, j).toString() + ",";
          }
        }
      }
      if (i != m.getNumCascadePiles() - 1) {
        cascadeCount++;
        cascadeString = cascadeString + "\n";
      }
    }
    return cascadeString;
  }
}
