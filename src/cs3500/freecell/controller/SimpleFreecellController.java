package cs3500.freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.freecell.model.FreecellCard;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;

/**
 * The controller for the freecell game. Handles the moving of cards and throws exception
 * messages when needed.
 */
public class SimpleFreecellController implements FreecellController {
  private FreecellModel<FreecellCard> model;
  private Readable rd;
  private Appendable ap;

  /**
   * An instance of a controller to play the freecell game.
   * @param model takes in a Freecell Model, which holds the state of the game.
   * @param rd The readable is the input given by the user. Used to make moves.
   * @param ap The output given by the system. Renders the board and errors.
   */
  public SimpleFreecellController(FreecellModel<FreecellCard> model, Readable rd, Appendable ap) {
    if (model == null || rd == null || ap == null || model.getDeck().isEmpty()) {
      throw new IllegalArgumentException("The Freecell controller cannot have null fields.");
    }
    this.model = model;
    this.rd = rd;
    this.ap = ap;

  }

  @Override
  public void playGame(List deck, int numCascades, int numOpens, boolean shuffle) {
    if (deck == null || deck.isEmpty()) {
      throw new IllegalArgumentException("Deck cannot be empty nor null");
    }

    Scanner scan = new Scanner(this.rd);
    FreecellTextView text = new FreecellTextView(model, ap);
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
      text.renderBoard();
    } catch (IOException | IllegalArgumentException ioe) {
      try {
        text.renderMessage("Could not start game.");
        return;
      } catch (IOException ioe2) {
        throw new IllegalStateException("Failed at failing the game. Bad");
      }

    }

    try {
      PileType srcPile = null;
      int pileIdx = -1;
      int cardIdx = -1;
      int destPileIdx = -1;
      PileType destPile = null;

      while (!model.isGameOver()) {

        String a;

        if (scan.hasNext()) {
          a = scan.next();
        } else {
          throw new IllegalStateException("need something to read");
        }

        if (a.substring(0, 1).equals("Q") ||
                a.substring(0, 1).equals("q")) {
          try {
            text.renderMessage("Game quit prematurely.");
          } catch (IOException ioe) {
            throw new IllegalArgumentException("Quitting failed.");
          }
          return;
        }

        if (srcPile == null && pileIdx == -1) {
          try {
            String pileString = a.substring(0, 1);
            srcPile = getPileType(pileString);
          } catch (IOException ioe) {
            text.renderMessage("Invalid source pile type" + '\n');
          }

          if (srcPile != null) {
            try {
              Integer.parseInt(a.substring(1));
              if (validPile(Integer.parseInt(a.substring(1)), srcPile, numCascades, numOpens)) {
                pileIdx = Integer.parseInt(a.substring(1)) - 1;
              }
              else {
                try {
                  text.renderMessage("invalid pile number, input a valid one:" + '\n');
                  pileIdx = -1;
                  continue;
                } catch (IOException e) {
                  throw new IllegalStateException("failed");
                }
              }
            } catch (NumberFormatException nfe) {
              text.renderMessage("input a pile number!" + '\n');
            }
            continue;
          }
          continue;
        }

        try {
          if (srcPile != null && pileIdx != -1 && cardIdx == -1) {
            if (validCard(Integer.parseInt(a.substring(0, 1)) - 1, srcPile, pileIdx)) {
              int cardValue = Integer.parseInt(a.substring(0, 1)) - 1;
              cardIdx = cardValue;
              continue;
            } else {
              text.renderMessage("invalid card number, enter another card index" + '\n');
            }
          }
        } catch (NumberFormatException nfe) {
          text.renderMessage("Re-enter card index, non-numerical value detected" + '\n');
          destPile = null;
          destPileIdx = -1;
          cardIdx = -1;
          continue;
        }

        if (srcPile != null &&
                pileIdx != -1 &&
                cardIdx != -1 &&
                destPileIdx == -1 &&
                destPile == null) {

          try {
            String pileString = a.substring(0, 1);
            destPile = getPileType(pileString);
          } catch (IOException ioe5) {
            text.renderMessage("Invalid destination pile type" + '\n');
          }

          if (destPile != null) {
            if (validPile(Integer.parseInt(a.substring(1)), destPile, numCascades, numOpens)) {
              destPileIdx = Integer.parseInt(a.substring(1)) - 1;
            }
            else {
              try {
                text.renderMessage("invalid pile number, input a valid one:" + '\n');
                pileIdx = -1;
              } catch (IOException e) {
                throw new IllegalStateException("failed");
              }
            }
          }
        }

        if (destPile != null &&
                pileIdx != -1 &&
                srcPile != null &&
                cardIdx != -1 &&
                destPileIdx != -1) {
          try {
            model.move(srcPile, pileIdx, cardIdx, destPile, destPileIdx);
            text.renderBoard();
          } catch (IOException | IllegalArgumentException | NullPointerException ioe) {
            text.renderMessage("Invalid move. Try again." + '\n');
          }
          srcPile = null;
          pileIdx = -1;
          cardIdx = -1;
          destPileIdx = -1;
          destPile = null;
        }
        continue;
      }
      text.renderMessage("Game over.");
    } catch (IOException ioe) {
      throw new IllegalStateException("Game failed");
    }
  }

  private boolean validCard(int parseInt, PileType srcPile, int pileIdx) throws IOException {
    switch (srcPile) {
      case CASCADE:
        return parseInt <= model.getNumCardsInCascadePile(pileIdx) && parseInt >= 0;
      case OPEN:
        return parseInt != 1;
      case FOUNDATION:
        return true;
      default:
        throw new IOException("wack card index");
    }
  }

  private boolean validPile(int parseInt,
                            PileType srcPile,
                            int numCascades,
                            int numOpen) throws IOException {
    switch (srcPile) {
      case CASCADE:
        return parseInt <= numCascades && parseInt > 0;
      case OPEN:
        return parseInt <= numOpen && parseInt > 0;
      case FOUNDATION:
        return parseInt <= 4 && parseInt > 0;
      default:
        throw new IOException();
    }
  }


  private PileType getPileType(String pilestring) throws IOException {
    switch (pilestring) {
      case "C":
        return PileType.CASCADE;
      case "O":
        return PileType.OPEN;
      case "F":
        return PileType.FOUNDATION;
      default:
        throw new IOException("Invalid Pile");
    }
  }
}