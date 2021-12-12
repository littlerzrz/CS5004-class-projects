package Hangman.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HModelTest {
  private HModel gameHello;

  @Before
  public void setUp() {
    gameHello = new HangmanModel.Builder().setSecretWord("hello").build();
  }

  /**
   * Tests if the model can properly handle different kinds of guesses.
   */
  @Test
  public void makeGuess() {
    gameHello.makeGuess("a");
    assertEquals(7, gameHello.getRemainingGuesses());

    gameHello.makeGuess("E");
    assertEquals(7, gameHello.getRemainingGuesses());
    assertThrows(IllegalArgumentException.class, ()-> gameHello.makeGuess("E"));

    gameHello.makeGuess("great");
    assertEquals(6, gameHello.getRemainingGuesses());

    gameHello.makeGuess("L");
    assertEquals(6, gameHello.getRemainingGuesses());

    gameHello.makeGuess("hello");

    assertThrows(IllegalStateException.class,()->gameHello.makeGuess("x"));
  }

  @Test
  public void makeInvalidGuess() {
    assertThrows(IllegalArgumentException.class,()->gameHello.makeGuess("aw123980"));
    assertThrows(IllegalArgumentException.class,()->gameHello.makeGuess("$^&*&^!"));
  }


  @Test
  public void getRemainingGuesses() {
    String guesses = "QaRBcxJP";
    for (int i = 0; i < guesses.length(); i++) {
      gameHello.makeGuess(String.valueOf(guesses.charAt(i)));
      assertEquals(7-i, gameHello.getRemainingGuesses());
    }
    assertThrows(IllegalStateException.class,()->gameHello.makeGuess("v"));
  }

  @Test
  public void getWrongGuesses() {
    String guesses = "AqXbeClO";
    String wrongGuesses = "aqxbc";
    String correctGuesses = "elo";
    for (int i = 0; i < guesses.length(); i++) {
      gameHello.makeGuess(String.valueOf(guesses.charAt(i)));
    }
    for (int i = 0; i < wrongGuesses.length(); i++) {
      assertTrue(gameHello.getWrongGuesses().contains(wrongGuesses.charAt(i)));
    }
    for (int i = 0; i < correctGuesses.length(); i++) {
      assertFalse(gameHello.getWrongGuesses().contains(correctGuesses.charAt(i)));
    }
    assertEquals(5, gameHello.getWrongGuesses().size());
  }


  @Test
  public void gameStatus() {
    gameHello.makeGuess("a");
    assertEquals(Status.Playing, gameHello.gameStatus());
    gameHello.makeGuess("hello");
    assertEquals(Status.Won, gameHello.gameStatus());
  }

  @Test
  public void getWordState() {
    gameHello.makeGuess("a");
    for (boolean b : gameHello.getWordState()) {
      assertFalse(b);
    }
    gameHello.makeGuess("E");
    assertTrue(gameHello.getWordState()[1]);

    gameHello.makeGuess("x");
    gameHello.makeGuess("L");
    assertTrue(gameHello.getWordState()[2]);
    assertTrue(gameHello.getWordState()[3]);
    assertFalse(gameHello.getWordState()[0]);
    assertFalse(gameHello.getWordState()[4]);

    gameHello.makeGuess("H");
    assertTrue(gameHello.getWordState()[0]);

    gameHello.makeGuess("O");
    assertTrue(gameHello.getWordState()[4]);
  }
}