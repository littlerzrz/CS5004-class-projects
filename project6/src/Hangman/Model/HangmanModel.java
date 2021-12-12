package Hangman.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * This class is a model implementation for the Hangman game
 */
public class HangmanModel implements HModel {

  private int remainingGuesses;
  private Status gameState;
  private final String secretWord;
  private final boolean[] wordState;
  private final HashSet<Character> attemptedChars;
  private final HashSet<Character> wrongGuesses;

  /**
   * Builder to construct the complex model and avoid unintentional mutations of the model. The
   * builder gives us options of having both generated random secret word for playing the game and
   * having self-determined secret word that weill be convenient for testing.
   */
  public static class Builder {

    String secret;

    /**
     * Sets up the secret word when needed
     * @param secret intended secret word
     * @return the builder itself
     */
    public Builder setSecretWord(String secret) {
      this.secret = secret.toLowerCase();
      return this;
    }

    /**
     * Generates the secret word for the game based on the list of words provided
     * @param wordList the list of word to generate secret from
     * @return the builder itself
     */
    public Builder generateSecretWord(ArrayList<String> wordList) {
      int randomIdx = new Random().nextInt(wordList.size());
      this.secret = wordList.get(randomIdx).toLowerCase();
      return this;
    }

    /**
     * Builds the model
     * @return the constructed model
     */
    public HModel build() {
      return new HangmanModel(secret);
    }
  }

  /**
   * Private constructor of the model, setting up all the standards and basics of the hangman game
   *
   * @param secretWord the secret word to be guessed
   */
  private HangmanModel(String secretWord) {
    this.remainingGuesses = 8;
    this.attemptedChars = new HashSet<>();
    this.wrongGuesses = new HashSet<>();
    this.gameState = Status.Playing;
    this.secretWord = secretWord;
    wordState = new boolean[secretWord.length()];
  }


  @Override
  public boolean makeGuess(String guess) throws IllegalArgumentException, IllegalStateException {
    guess = guess.toLowerCase();
    boolean isWhole = isWholeGuess(guess);
    boolean result;

    if (gameState != Status.Playing) {
      throw new IllegalStateException("Game is Over! Cannot make more guesses!");
    }
    if (!guess.matches("[a-zA-Z]+") || (guess.length() > 1 && !isWhole)) {
      throw new IllegalArgumentException("Invalid format!");
    }
    if (isWhole) {
      result = guessWholeWord(guess);
    } else if (alreadyAttempted(guess.charAt(0))) {
      throw new IllegalArgumentException("Already attempted!");
    } else {
      result = guessSingleCharacter(guess.charAt(0));
    }
    checkWin();
    return result;
  }

  /**
   * Determines if the character is already attempted or not
   *
   * @param letter the attempted character
   * @return if the character is attempted or not
   */
  private boolean alreadyAttempted(char letter) {
    return attemptedChars.contains(letter);
  }

  /**
   * Determine whether this guess is a whole word guess
   *
   * @param guess the guessing word
   * @return whether if it is a whole guess
   */
  private boolean isWholeGuess(String guess) {
    return guess.length() == secretWord.length();
  }

  /**
   * Processes a whole word guess
   *
   * @param guess guessed whole word
   */
  private boolean guessWholeWord(String guess) {
    if (!guess.equals(secretWord)) {
      remainingGuesses--;
      return false;
    } else {
      Arrays.fill(wordState, true);
      return true;
    }
  }

  /**
   * Processes a single character guess
   *
   * @param guess guessed character
   */
  private boolean guessSingleCharacter(char guess) {
    attemptedChars.add(guess);
    boolean missed = true;
    for (int i = 0; i < secretWord.length(); i++) {
      if (secretWord.charAt(i) == guess) {
        wordState[i] = true;
        missed = false;
      }
    }
    if (missed) {
      remainingGuesses--;
      wrongGuesses.add(guess);
      return false;
    }
    return true;
  }

  /**
   * Method to check if the user has won or not
   */
  private void checkWin() {
    boolean allMatch = true;
    for (boolean b : wordState) {
      if (!b) {
        allMatch = false;
        break;
      }
    }
    if (remainingGuesses >= 0 && allMatch) {
      gameState = Status.Won;
    } else if (remainingGuesses < 1) {
      gameState = Status.Lose;
    } else {
      gameState = Status.Playing;
    }
  }

  @Override
  public List<Character> getWrongGuesses() {
    return wrongGuesses.stream().toList();
  }

  @Override
  public int getRemainingGuesses() {
    return remainingGuesses;
  }

  @Override
  public Status gameStatus() {
    return gameState;
  }

  @Override
  public boolean[] getWordState() {
    return wordState;
  }

  @Override
  public String getSecretWord() {
    return secretWord;
  }

}

