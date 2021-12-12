package Hangman.View;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * This represents the interface for the Hangman game's view
 */
public interface HView {

  /**
   * Make the view visible. This is usually called after the view is constructed
   */
  void makeVisible();

  /**
   * Gets the guess typed by the user
   *
   * @return the guess input string
   */
  String getGuess();

  /**
   * Appends the game log to the view
   *
   * @param log game log to append
   */
  void appendGameLog(String log);

  /**
   * Updates the display word
   *
   * @param word word for display
   */
  void updateWordDisplay(String word);

  /**
   * Updates the canvas drawing of the hangman
   *
   * @param stage stage of the hangman (guesses remaining)
   */
  void updateCanvas(int stage);

  /**
   * Updates the missed characters display
   * @param missedChars missed chars to be displayed
   */
  void updateMissedDisplay(List<Character> missedChars);

  /**
   * Sets the guess listener for the view
   * @param actionEvent the event to listen to
   */
  void setGuessListener(ActionListener actionEvent);
}
