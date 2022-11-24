package cs3500.freecell.model;

/**
 * Creates a model of Freecell, either SingleMove or MultiMove.
 * MultiMove allows the shortcut of moving multiple cards depending on how many open piles there
 * are.
 * Single move is normal Freecell, one card at a time.
 */
public class FreecellModelCreator {

  /**
   * A GameType for the game. Either Singlemove or Multimove, as described above.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Creates the Freecell game of the given game type.
   * @param type GameType to decide whether the game includes the multimove shortcut.
   * @return a FreecellModel depending on gametype.
   */
  public static FreecellModel create(GameType type) {
    if (type == GameType.SINGLEMOVE) {
      return new SimpleFreecellModel();
    }
    else  {
      return new MultiMoveModel();
    }
  }
}
