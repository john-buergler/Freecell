import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellCard;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.SimpleFreecellModel;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the freecell controller.
 */
public class FreecellControllerTest {

  @Test
  public void testMoveCascadetoOpen() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 7 O1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥, 9♥, 4♦, Q♦, 7♠, 2♣\n" +
            "C2: 2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣\n" +
            "C3: 3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣\n" +
            "C4: 4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣\n" +
            "C5: 5♥, K♥, 8♦, 3♠, J♠, 6♣\n" +
            "C6: 6♥, A♦, 9♦, 4♠, Q♠, 7♣\n" +
            "C7: 7♥, 2♦, 10♦, 5♠, K♠, 8♣\n" +
            "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "Game quit prematurely.", out.toString());

  }

  @Test
  public void testMoveCascadeToFoundation() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 1 F1 Q"),
            out);
    controller.playGame(deck, 52, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "Game quit prematurely.", out.toString());
  }

  @Test
  public void testMoveMultCascadeToFoundation() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 1 F1 C2 1 F1 C3 1 F1 Q"),
            out);
    controller.playGame(deck, 52, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "F1: A♥, 2♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "F1: A♥, 2♥, 3♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "Game quit prematurely.", out.toString());
  }


  @Test
  public void testInvalidMove() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    Readable in = new StringReader("C1 2 O1 Q");
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            in,
            out);
    controller.playGame(deck, 52, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "Invalid move. Try again.\n" +
            "Game quit prematurely.", out.toString());
  }

  @Test
  public void testInvalidPileType() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("V1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "Invalid source pile type\n" +
                    "Game quit prematurely.", out.toString());

  }

  @Test
  public void testInvalidPileTypeThenMove() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("V1 C1 7 O1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "Invalid source pile type\n" +
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: 10♣\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♥, 9♥, 4♦, Q♦, 7♠, 2♣\n" +
                    "C2: 2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣\n" +
                    "C3: 3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣\n" +
                    "C4: 4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣\n" +
                    "C5: 5♥, K♥, 8♦, 3♠, J♠, 6♣\n" +
                    "C6: 6♥, A♦, 9♦, 4♠, Q♠, 7♣\n" +
                    "C7: 7♥, 2♦, 10♦, 5♠, K♠, 8♣\n" +
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "Game quit prematurely.", out.toString());
  }

  @Test
  public void testInvalidPileNumber() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C12 6 O1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "invalid pile number, input a valid one:\n" +
                    "Game quit prematurely.", out.toString());
  }

  @Test
  public void testInvalidPileNumberNegative() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C-2 6 O1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "invalid pile number, input a valid one:\n" +
                    "Game quit prematurely.", out.toString());
  }

  @Test
  public void testInvalidPileTypeDest() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 7 V1 Q"),
            out);
    controller.playGame(deck, 8, 4, false);
    assertEquals(
            "F1:\n" +
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
                    "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣\n" +
                    "Invalid destination pile type\n" +
                    "Game quit prematurely.", out.toString());

  }

  @Test
  public void testMoveFromFoundation() throws IOException {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 1 F1 F1 1 O3 Q"),
            out);
    controller.playGame(deck, 52, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: 2♥\n" +
            "C3: 3♥\n" +
            "C4: 4♥\n" +
            "C5: 5♥\n" +
            "C6: 6♥\n" +
            "C7: 7♥\n" +
            "C8: 8♥\n" +
            "C9: 9♥\n" +
            "C10: 10♥\n" +
            "C11: J♥\n" +
            "C12: Q♥\n" +
            "C13: K♥\n" +
            "C14: A♦\n" +
            "C15: 2♦\n" +
            "C16: 3♦\n" +
            "C17: 4♦\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣\n" +
            "Invalid move. Try again.\n" +
            "Game quit prematurely.", out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullDeck() {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("a"),
            out);
    controller.playGame(null, 52, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Appendable out = new StringBuilder();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(null,
            new StringReader("a"),
            out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            null,
            out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    FreecellModel game = new SimpleFreecellModel();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("a"),
            null);
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyReader() {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader(""),
            out);
    controller.playGame(deck, 52, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    Appendable out = new FailingAppendable();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("a"),
            out);
    controller.playGame(deck, 52, 4, false);
    assertEquals("Fail!", out.toString());
  }

  @Test
  public void testPlayFullGame() {
    Appendable out = new StringBuilder();
    FreecellModel game = new SimpleFreecellModel();
    List<FreecellCard> deck = game.getDeck();
    FreecellController<FreecellCard> controller = new SimpleFreecellController(game,
            new StringReader("C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 C5 1 F1 C6 1 F1 C7 1 F1 " +
                    "C8 1 F1 C9 1 F1 C10 1 F1 C11 1 F1 C12 1 F1 C13 1 F1 " +
                    "C14 1 F2 C15 1 F2 C16 1 F2 C17 1 F2 C18 1 F2 C19 1 F2 C20 1 F2 " +
                    "C21 1 F2 C22 1 F2 C23 1 F2 C24 1 F2 C25 1 F2 C26 1 F2 " +
                    "C27 1 F3 C28 1 F3 C29 1 F3 C30 1 F3 C31 1 F3 C32 1 F3 C33 1 F3 " +
                    "C34 1 F3 C35 1 F3 C36 1 F3 C37 1 F3 C38 1 F3 C39 1 F3 " +
                    "C40 1 F4 C41 1 F4 C42 1 F4 C43 1 F4 C44 1 F4 C45 1 F4 C46 1 F4 " +
                    "C47 1 F4 C48 1 F4 C49 1 F4 C50 1 F4 C51 1 F4 C52 1 F4"),
            out);
    controller.playGame(deck, 52, 4, false);
    // For the sake of not making this file 3000 lines, I put the string into a separate class.
    assertEquals(new EndGameString().endGameString, out.toString());
  }
}