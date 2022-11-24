import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellCard;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.MultiMoveModel;
import cs3500.freecell.model.Pile;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test cases for the freecell model.
 */
public class FreecellModelTest {
  FreecellModel freecell = new SimpleFreecellModel();

  String stringDeck = "[A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, " +
                      "A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, " +
                      "A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, " +
                      "A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣]";

  String unshuffledGameString = "F1: \n" +
          "F2: \n" +
          "F3: \n" +
          "F4: \n" +
          "O1: \n" +
          "O2: \n" +
          "O3: \n" +
          "O4: \n" +
          "C1: A♥, 9♥, 4♦, Q♦, 7♠, 2♣, 10♣, \n" +
          "C2: 2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣, \n" +
          "C3: 3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣, \n" +
          "C4: 4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣, \n" +
          "C5: 5♥, K♥, 8♦, 3♠, J♠, 6♣, \n" +
          "C6: 6♥, A♦, 9♦, 4♠, Q♠, 7♣, \n" +
          "C7: 7♥, 2♦, 10♦, 5♠, K♠, 8♣, \n" +
          "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣, \n";

  ////////////////// Tests for a SimpleFreecellModel AKA non-multimove ////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetDeck() {

    List<FreecellCard> deck = freecell.getDeck();
    String stringGetDeck = deck.toString();
    assertEquals(stringDeck, stringGetDeck);
  }

  @Test
  public void testStartGame() {
    SimpleFreecellModel freecell2 = new SimpleFreecellModel();

    freecell2.startGame(freecell2.getDeck(), 8, 4, false);
    Pile firstPile = freecell2.getOpenPiles().get(0);
    Pile firstCascadePile = freecell2.getCascadePiles().get(0);
    Pile secondCascadePile = freecell2.getCascadePiles().get(1);
    Pile thirdCascadePile = freecell2.getCascadePiles().get(2);
    Pile fourthCascadePile = freecell2.getCascadePiles().get(3);
    Pile fifthCascadePile = freecell2.getCascadePiles().get(4);
    Pile sixthCascadePile = freecell2.getCascadePiles().get(5);
    Pile seventhCascadePile = freecell2.getCascadePiles().get(6);
    Pile lastCascadePile = freecell2.getCascadePiles().get(7);
    firstPile.getType().equals(PileType.OPEN);
    assertEquals(new ArrayList<FreecellCard>() , firstPile.getCards());
    firstCascadePile.getType().equals(PileType.CASCADE);
    assertEquals("[A♥, 9♥, 4♦, Q♦, 7♠, 2♣, 10♣]" , firstCascadePile.getCards().toString());
    assertEquals("[2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣]" , secondCascadePile.getCards().toString());
    assertEquals("[3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣]" , thirdCascadePile.getCards().toString());
    assertEquals("[4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣]" , fourthCascadePile.getCards().toString());
    assertEquals("[5♥, K♥, 8♦, 3♠, J♠, 6♣]" , fifthCascadePile.getCards().toString());
    assertEquals("[6♥, A♦, 9♦, 4♠, Q♠, 7♣]" , sixthCascadePile.getCards().toString());
    assertEquals("[7♥, 2♦, 10♦, 5♠, K♠, 8♣]" , seventhCascadePile.getCards().toString());
    assertEquals("[8♥, 3♦, J♦, 6♠, A♣, 9♣]" , lastCascadePile.getCards().toString());
    assertEquals("4♦", freecell2.getCascadeCardAt(0, 2).toString());
  }

  @Test
  public void testMove() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, false);
    FreecellCard testCard = new FreecellCard(12, "♥");
    FreecellCard testCard2 = new FreecellCard(1, "♥");
    ArrayList<FreecellCard> testCards = new ArrayList<FreecellCard>();
    testCards.add(testCard2);
    testCards.add(testCard);
    Pile firstPile = freecell3.getOpenPiles().get(0);
    Pile firstCascadePile = freecell3.getCascadePiles().get(0);
    Pile secondCascadePile = freecell3.getCascadePiles().get(1);
    Pile thirdCascadePile = freecell3.getCascadePiles().get(2);
    Pile fourthCascadePile = freecell3.getCascadePiles().get(3);
    Pile fifthCascadePile = freecell3.getCascadePiles().get(4);
    Pile sixthCascadePile = freecell3.getCascadePiles().get(5);
    Pile seventhCascadePile = freecell3.getCascadePiles().get(6);
    Pile lastCascadePile = freecell3.getCascadePiles().get(7);
    Pile foundationPile = freecell3.getFoundationPiles().get(0);
    Pile randomPile = new Pile(PileType.CASCADE, testCards);
    assertEquals(6, firstCascadePile.getCards().get(firstCascadePile.getCards().size() -
                 1).getIndex());
    freecell3.getCascadePiles().add(randomPile);
    assertEquals("[]", firstPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals("[10♣]", firstPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 3);
    assertEquals("Q♥",
                 fourthCascadePile.getCards().get(fourthCascadePile.getCards().size()
                                                  - 1).toString());
    assertEquals("[A♥]", randomPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    assertEquals("[A♥]", foundationPile.getCards().toString());
  }

  @Test(expected = Exception.class)
  public void testGetNumCardsInCascadePileException() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    assertEquals(7, freecell3.getNumCardsInCascadePile(0));

    freecell3.startGame(freecell3.getDeck(), 8, 4, true);
    freecell3.getNumCardsInCascadePile(9);
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, true);

    assertEquals(7, freecell3.getNumCardsInCascadePile(0));
    assertEquals(7, freecell3.getNumCardsInCascadePile(2));
    assertEquals(6, freecell3.getNumCardsInCascadePile(5));
  }

  @Test
  public void testGetNumCardsInFoundationPile() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, true);

    assertEquals(0, freecell3.getNumCardsInFoundationPile(0));
    assertEquals(0, freecell3.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGetNumCascadePile() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, true);
    assertEquals(8, freecell3.getNumCascadePiles());
    freecell3.startGame(freecell3.getDeck(), 7, 4, true);
    assertEquals(7, freecell3.getNumCascadePiles());
  }
  // Due to the functionality of getNumOpenPiles being exactly the same,
  // there is no need to test it as well.

  @Test
  public void testGetNumCardsInOpenPile() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, true);
    FreecellCard exCard = new FreecellCard(10, "♣");
    Pile openfull = new Pile(PileType.OPEN, new ArrayList<FreecellCard>());
    openfull.getCards().add(exCard);
    freecell3.getOpenPiles().add(openfull);

    assertEquals(0, freecell3.getNumCardsInOpenPile(0));
    assertEquals(1, freecell3.getNumCardsInOpenPile(4));
  }

  @Test
  public void testGetCascadeCardAt() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, false);
    assertEquals("A♥", freecell3.getCascadeCardAt(0, 0).toString());
    assertEquals("4♦", freecell3.getCascadeCardAt(0, 2).toString());
    assertEquals("2♥", freecell3.getCascadeCardAt(1, 0).toString());
  }

  @Test
  public void testGetFoundationCardAt() {
    FreecellCard exCard = new FreecellCard(10, "♣");
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, false);
    freecell3.getFoundationPiles().get(1).getCards().add(exCard);
    assertEquals("10♣", freecell3.getFoundationCardAt(1, 0).toString());

  }

  @Test
  public void testGetOpenCardAt() {
    SimpleFreecellModel freecell3 = new SimpleFreecellModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, false);
    FreecellCard exCard = new FreecellCard(10, "♣");
    Pile openfull = new Pile(PileType.OPEN, new ArrayList<FreecellCard>());
    openfull.getCards().add(exCard);
    freecell3.getOpenPiles().add(openfull);
    assertEquals("10♣", freecell3.getOpenCardAt(4).toString());
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////// Tests for a MultiMoveFreecellModel //////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////

  @Test
  public void testCascadeToCascadeNormalMultiMove() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 8, 4, false);

    FreecellCard testCard = new FreecellCard(12, "♥");
    FreecellCard testCard2 = new FreecellCard(1, "♥");
    FreecellCard testCard3 = new FreecellCard(2, "♥");
    FreecellCard testCard4 = new FreecellCard(3, "♠");
    FreecellCard testCard5 = new FreecellCard(4, "♥");
    FreecellCard testCard6  = new FreecellCard(5, "♠");

    ArrayList<FreecellCard> testCards = new ArrayList<FreecellCard>();
    testCards.add(testCard2);
    testCards.add(testCard);

    Pile firstPile = freecell3.getOpenPiles().get(0);
    Pile firstCascadePile = freecell3.getCascadePiles().get(0);
    Pile secondCascadePile = freecell3.getCascadePiles().get(1);
    Pile thirdCascadePile = freecell3.getCascadePiles().get(2);
    Pile fourthCascadePile = freecell3.getCascadePiles().get(3);
    Pile fifthCascadePile = freecell3.getCascadePiles().get(4);
    Pile sixthCascadePile = freecell3.getCascadePiles().get(5);
    Pile seventhCascadePile = freecell3.getCascadePiles().get(6);
    Pile lastCascadePile = freecell3.getCascadePiles().get(7);
    Pile foundationPile = freecell3.getFoundationPiles().get(0);

    Pile randomPile = new Pile(PileType.CASCADE, testCards);
    Pile randomPile2 = new Pile(PileType.CASCADE, new ArrayList<FreecellCard>());
    randomPile2.getCards().add(testCard6);

    assertEquals(6, firstCascadePile.getCards().get(firstCascadePile.getCards().size() -
            1).getIndex());
    freecell3.getCascadePiles().add(randomPile);
    assertEquals("[]", firstPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals("[10♣]", firstPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 3);
    assertEquals("Q♥",
            fourthCascadePile.getCards().get(fourthCascadePile.getCards().size()
                    - 1).toString());
    assertEquals("[A♥]", randomPile.getCards().toString());
    freecell3.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    assertEquals("[A♥]", foundationPile.getCards().toString());

    testCards.add(testCard5);
    testCards.add(testCard4);
    testCards.add(testCard3);

    freecell3.getCascadePiles().add(randomPile2);

    freecell3.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 9);
    assertEquals("[5♠, 4♥, 3♠, 2♥]", randomPile2.getCards().toString());
    assertEquals("[]", randomPile.getCards().toString());
  }

  @Test
  public void testMoveFromOpenToFoundation() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.OPEN, 0);
    assertEquals("[A♠]", freecell3.getOpenPiles().get(0).getCards().toString());
    freecell3.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    assertEquals("[]", freecell3.getOpenPiles().get(0).getCards().toString());
    assertEquals("[A♠]", freecell3.getFoundationPiles().get(0).getCards().toString());

  }

  @Test
  public void testMultiMove1Open() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    freecell3.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 0);
    freecell3.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 2);
    freecell3.move(PileType.CASCADE, 16, 1, PileType.CASCADE, 1);
    freecell3.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 16);
    assertEquals("[4♦, 3♠, 2♥]", freecell3.getCascadePiles().get(16).getCards().toString());
  }

  @Test
  public void testMultiMoveFromCascadeToOpen() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 1, 1, PileType.OPEN, 0);
    assertEquals("[2♠]", freecell3.getOpenPiles().get(0).getCards().toString());
  }

  @Test
  public void testMultiMoveFromCascadeToFoundation() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    assertEquals("[A♠]", freecell3.getFoundationPiles().get(0).getCards().toString());
  }

  @Test
  public void testShuffle() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    List<FreecellCard> deckb4 = freecell3.getDeck();
    freecell3.startGame(freecell3.getDeck(), 26, 1, true);
    assertNotEquals(freecell3.getDeck(), deckb4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovetoIncorrectCardValue() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    freecell3.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 0);
    freecell3.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 2);
    freecell3.move(PileType.CASCADE, 16, 1, PileType.CASCADE, 1);
    freecell3.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 17);

  }

  @Test
  public void testFreecellModelCreator() {
    FreecellModelCreator cre = new FreecellModelCreator();
    FreecellModel game = cre.create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertTrue(game instanceof SimpleFreecellModel);
    FreecellModel game2 = cre.create(FreecellModelCreator.GameType.MULTIMOVE);
    assertTrue(game2 instanceof MultiMoveModel);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpen() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCascade() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(),  2, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToFullOpenPile() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(),  26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.OPEN, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeckDupe() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    FreecellCard testCard = new FreecellCard(12, "♥");
    FreecellCard testCard2 = new FreecellCard(2, "♥");
    List<FreecellCard> deck = freecell3.getDeck();
    deck.remove(testCard2);
    deck.add(testCard);
    freecell3.startGame(deck, 2, 1, false);
  }



  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeck53Cards() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    FreecellCard testCard = new FreecellCard(12, "♥");
    List<FreecellCard> deck = freecell3.getDeck();
    deck.add(testCard);
    freecell3.startGame(deck, 2, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovetoIncorrectCardSuit() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    freecell3.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 0);
    freecell3.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 2);
    freecell3.move(PileType.CASCADE, 16, 1, PileType.CASCADE, 1);
    freecell3.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyCards() {
    FreecellCard testCard3 = new FreecellCard(2, "♥");
    FreecellCard testCard4 = new FreecellCard(3, "♠");
    FreecellCard testCard5 = new FreecellCard(4, "♥");
    FreecellCard testCard6  = new FreecellCard(5, "♠");


    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 52, 1, false);

    ArrayList<FreecellCard> testCards = new ArrayList<FreecellCard>();

    testCards.add(testCard6);
    testCards.add(testCard5);
    testCards.add(testCard4);
    testCards.add(testCard3);

    Pile testPile = new Pile(PileType.CASCADE, testCards);
    freecell3.getCascadePiles().add(testPile);

    freecell3.move(PileType.CASCADE, 52, 0, PileType.CASCADE, 5);

    // Note that Cascade pile index 5 has a 6 of Hearts, and therefore this move is only invalid
    // because the stack is higher than the multiCount.

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingAnInvalidStack() {

    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingAPoorlyOrderedStack() {

    MultiMoveModel freecell3 = new MultiMoveModel();

    FreecellCard testCard = new FreecellCard(4, "♠");

    freecell3.startGame(freecell3.getDeck(), 26, 2, false);

    freecell3.getCascadePiles().get(17).getCards().add(testCard);

    freecell3.move(PileType.CASCADE, 5, 1, PileType.OPEN, 0);
    freecell3.move(PileType.CASCADE, 17, 1, PileType.CASCADE, 5);
  }

  @Test
  public void testTheSameAsAboveWithWellOrderedStack() {

    MultiMoveModel freecell3 = new MultiMoveModel();

    FreecellCard testCard = new FreecellCard(4, "♥");

    freecell3.startGame(freecell3.getDeck(), 26, 2, false);

    freecell3.getCascadePiles().get(17).getCards().add(testCard);

    freecell3.move(PileType.CASCADE, 5, 1, PileType.OPEN, 0);
    freecell3.move(PileType.CASCADE, 17, 1, PileType.CASCADE, 5);

    assertEquals("[6♥, 5♣, 4♥]", freecell3.getCascadePiles().get(5).getCards().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMultipleToOpenPile() {

    FreecellCard testCard = new FreecellCard(12, "♥");
    FreecellCard testCard2 = new FreecellCard(1, "♥");
    FreecellCard testCard3 = new FreecellCard(2, "♥");
    FreecellCard testCard4 = new FreecellCard(3, "♠");
    FreecellCard testCard5 = new FreecellCard(4, "♥");
    FreecellCard testCard6  = new FreecellCard(5, "♠");


    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 52, 1, false);

    ArrayList<FreecellCard> testCards = new ArrayList<FreecellCard>();

    testCards.add(testCard6);
    testCards.add(testCard5);
    testCards.add(testCard4);
    testCards.add(testCard3);

    Pile testPile = new Pile(PileType.CASCADE, testCards);
    freecell3.getCascadePiles().add(testPile);

    freecell3.move(PileType.CASCADE, 52, 0, PileType.OPEN, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMultipleToFoundationPile() {

    FreecellCard testCard3 = new FreecellCard(2, "♥");
    FreecellCard testCard4 = new FreecellCard(3, "♠");
    FreecellCard testCard5 = new FreecellCard(4, "♥");
    FreecellCard testCard6  = new FreecellCard(5, "♠");


    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 52, 1, false);

    ArrayList<FreecellCard> testCards = new ArrayList<FreecellCard>();

    testCards.add(testCard6);
    testCards.add(testCard5);
    testCards.add(testCard4);
    testCards.add(testCard3);

    Pile testPile = new Pile(PileType.CASCADE, testCards);
    freecell3.getCascadePiles().add(testPile);

    freecell3.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingWrongValueToFoundation() {

    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    freecell3.move(PileType.CASCADE, 2, 1, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingWrongSuitToFoundation() {

    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    freecell3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    freecell3.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingWrongCardToCascade() {

    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    freecell3.move(PileType.CASCADE, 0, 1, PileType.CASCADE, 1);
  }

  @Test
  public void testPlayFullGame() {
    Appendable out = new StringBuilder();
    FreecellModel game = new MultiMoveModel();
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

  @Test
  public void testGameOverWhenNot() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    assertFalse(freecell3.isGameOver());
  }

  @Test
  public void testRestartGame() {
    MultiMoveModel freecell3 = new MultiMoveModel();
    freecell3.startGame(freecell3.getDeck(), 26, 1, false);
    assertTrue(freecell3.getCascadePiles().size() == 26);
    freecell3.startGame(freecell3.getDeck(), 52, 1, false);
    assertTrue(freecell3.getCascadePiles().size() == 52);
  }

  @Test
  public void testCorrectDeal() {
    MultiMoveModel freecell2 = new MultiMoveModel();

    freecell2.startGame(freecell2.getDeck(), 8, 4, false);
    Pile firstPile = freecell2.getOpenPiles().get(0);
    Pile firstCascadePile = freecell2.getCascadePiles().get(0);
    Pile secondCascadePile = freecell2.getCascadePiles().get(1);
    Pile thirdCascadePile = freecell2.getCascadePiles().get(2);
    Pile fourthCascadePile = freecell2.getCascadePiles().get(3);
    Pile fifthCascadePile = freecell2.getCascadePiles().get(4);
    Pile sixthCascadePile = freecell2.getCascadePiles().get(5);
    Pile seventhCascadePile = freecell2.getCascadePiles().get(6);
    Pile lastCascadePile = freecell2.getCascadePiles().get(7);
    firstPile.getType().equals(PileType.OPEN);
    assertEquals(new ArrayList<FreecellCard>() , firstPile.getCards());
    firstCascadePile.getType().equals(PileType.CASCADE);
    assertEquals("[A♥, 9♥, 4♦, Q♦, 7♠, 2♣, 10♣]" , firstCascadePile.getCards().toString());
    assertEquals("[2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣]" , secondCascadePile.getCards().toString());
    assertEquals("[3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣]" , thirdCascadePile.getCards().toString());
    assertEquals("[4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣]" , fourthCascadePile.getCards().toString());
    assertEquals("[5♥, K♥, 8♦, 3♠, J♠, 6♣]" , fifthCascadePile.getCards().toString());
    assertEquals("[6♥, A♦, 9♦, 4♠, Q♠, 7♣]" , sixthCascadePile.getCards().toString());
    assertEquals("[7♥, 2♦, 10♦, 5♠, K♠, 8♣]" , seventhCascadePile.getCards().toString());
    assertEquals("[8♥, 3♦, J♦, 6♠, A♣, 9♣]" , lastCascadePile.getCards().toString());
    assertEquals("4♦", freecell2.getCascadeCardAt(0, 2).toString());
  }




}
