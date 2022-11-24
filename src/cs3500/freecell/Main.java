package cs3500.freecell;

import java.io.InputStreamReader;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.MultiMoveModel;

/**
 * Main method runs the Freecell Game, a text based version where you type pile, card index,
 * and then destination pile to move a card.
 */
public class Main {
  static FreecellController ctrl;

  /**
   * The method that handles actually running the game.
   * @param args these are the inputs given by the user as they play.
   */
  public static void main(String[] args) {
    FreecellModel game = new MultiMoveModel();
    ctrl = new SimpleFreecellController(game, new InputStreamReader(System.in),
            System.out);
    ctrl.playGame(game.getDeck(), 8, 1, false);
  }
}
