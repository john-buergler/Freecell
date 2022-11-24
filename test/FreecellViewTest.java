import org.junit.Test;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Text view of the Freecell Game.
 */
public class FreecellViewTest {

  String unshuffledGameString = "F1:\n" +
          "F2:\n" +
          "F3:\n" +
          "F4:\n" +
          "O1:\n" +
          "O2:\n" +
          "O3:\n" +
          "O4:\n" +
          "C1: A♥, 9♥, 4♦, Q♦, 7♠, 2♣, 10♣\n" +
          "C2: 2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣\n" +
          "C3: 3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣\n" +
          "C4: 4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣\n" +
          "C5: 5♥, K♥, 8♦, 3♠, J♠, 6♣\n" +
          "C6: 6♥, A♦, 9♦, 4♠, Q♠, 7♣\n" +
          "C7: 7♥, 2♦, 10♦, 5♠, K♠, 8♣\n" +
          "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣";

  @Test
  public void toStringTest() {
    FreecellModel testGame = new SimpleFreecellModel();
    FreecellModel testGame2 = new SimpleFreecellModel();
    testGame.startGame(testGame.getDeck(), 8, 4, false);
    testGame2.startGame(testGame.getDeck(), 4, 2, true);
    FreecellView startedGameState = new FreecellTextView(testGame);
    FreecellView startedGameState2 = new FreecellTextView(testGame2);
    assertEquals(unshuffledGameString, startedGameState.toString());
  }
}
