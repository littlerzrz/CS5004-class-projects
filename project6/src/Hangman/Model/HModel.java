package Hangman.Model;

import java.util.List;

public interface HModel {

  /**
   * Method to make a guess
   *
   * @param guess the guessing word or character
   * @throws IllegalArgumentException when the guess is not in the right format(not
   *                                  words/characters)
   * @throws IllegalStateException    when one cannot make more guesses
   * @return whether if the guess is correct or not
   */
  boolean makeGuess(String guess) throws IllegalArgumentException, IllegalStateException;

  /**
   * Method to return the remaining guesses
   *
   * @return remaining guesses
   */
  int getRemainingGuesses();

  /**
   * Method to get the wrong guesses
   *
   * @return wrong guesses;
   */
  List<Character> getWrongGuesses();

  /**
   * Gets the current game status
   *
   * @return current game status
   */
  Status gameStatus();

  /**
   * Gets the current guessing state on each character of the word
   *
   * @return the current guessing state
   */
  boolean[] getWordState();

  /**
   * Gets the secret word of the game
   * @return the secret word
   */
  String getSecretWord();

}
