package cs5004.marblesolitaire.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for the Marble Solitaire Model Implementation
 */
public class MarbleSolitaireModelImplTest {

  private MarbleSolitaireModelImpl arm3;
  private MarbleSolitaireModelImpl arm5;
  private MarbleSolitaireModelImpl arm7;
  private MarbleSolitaireModelImpl arm3initial23;
  private MarbleSolitaireModelImpl arm5initial65;


  /**
   * Sets up the model board with different arm thicknesses and initial empty slots
   */
  @Before
  public void setUp() {
    arm3 = new MarbleSolitaireModelImpl();
    arm5 = new MarbleSolitaireModelImpl(5);
    arm7 = new MarbleSolitaireModelImpl(7);
    arm3initial23 = new MarbleSolitaireModelImpl(2, 3);
    arm5initial65 = new MarbleSolitaireModelImpl(5, 6, 5);
  }

  /**
   * Tests the constructor with different invalid credentials and expect these invalid credentials
   * to throw exceptions
   */
  @Test
  public void invalidConstructors() {
    int[] invalidArms = {0, 4, 6, 8, 10, -2, -5};
    int[][] invalidInitialsFor3 = {{0, 0}, {0, 1}, {10, 10}, {-1, -1}};
    int[][] invalidInitialsFor5 = {{0, 2}, {0, 10}, {8, 2}, {10, 10}, {-1, -1}};
    for (int i : invalidArms) {
      invalidTestHelper(() -> {
        MarbleSolitaireModelImpl invalidArm = new MarbleSolitaireModelImpl(i);
      });
    }
    for (int[] pos : invalidInitialsFor3) {
      invalidTestHelper(() -> {
        MarbleSolitaireModelImpl invalid3 = new MarbleSolitaireModelImpl(pos[0], pos[1]);
      });
    }
    for (int[] pos : invalidInitialsFor5) {
      invalidTestHelper(() -> {
        MarbleSolitaireModelImpl invalid5 = new MarbleSolitaireModelImpl(5, pos[0], pos[1]);
      });
    }
  }

  /**
   * Higher order helper method for invalid tests
   *
   * @param block block of code to execute
   */
  private void invalidTestHelper(Runnable block) {
    try {
      block.run();
      fail("An exception should been thrown");
    } catch (IllegalArgumentException ignored) {
    }
  }

  /**
   * Tests if the move method is working correctly, moving the marble piece from one position to the
   * empty position and taking away the piece in between
   */
  @Test
  public void move() {
    arm3.move(3, 1, 3, 3);
    assertEquals(
        """
                O O O   \s
                O O O   \s
            O O O O O O O
            O _ _ O O O O
            O O O O O O O
                O O O   \s
                O O O   \s""",
        arm3.getGameState());
    arm3.move(1, 2, 3, 2);
    assertEquals(
        """
                O O O   \s
                _ O O   \s
            O O _ O O O O
            O _ O O O O O
            O O O O O O O
                O O O   \s
                O O O   \s""",
        arm3.getGameState());
  }

  /**
   * Tests moves that are illegal
   */
  @Test
  public void invalidMove() {
    invalidTestHelper(() -> arm3.move(4, 4, 3, 3));
    invalidTestHelper(() -> arm3.move(0, 2, 1, 3));
    invalidTestHelper(() -> arm3.move(0, 0, 0, 3));
    invalidTestHelper(() -> arm5.move(0, 8, 0, 6));
    invalidTestHelper(() -> arm5.move(4, 5, 2, 5));
    invalidTestHelper(() -> arm7.move(7, 7, -1, 0));
  }

  /**
   * Tests if the isGameOver method is working appropriately.
   */
  @Test
  public void isGameOver() {
    assertFalse(arm3.isGameOver());
    arm3.move(5, 3, 3, 3);
    assertFalse(arm3.isGameOver());
    arm3.move(2, 3, 4, 3);
    assertFalse(arm3.isGameOver());
    arm3.move(0, 3, 2, 3);
    assertFalse(arm3.isGameOver());
    arm3.move(3, 1, 3, 3);
    assertFalse(arm3.isGameOver());
    arm3.move(3, 4, 3, 2);
    assertFalse(arm3.isGameOver());
    arm3.move(3, 6, 3, 4);
    assertTrue(arm3.isGameOver());
  }

  /**
   * Tests if the getGameState is getting the correct game state within different moves.
   */
  @Test
  public void getGameState() {
    assertEquals(
        """
                O O O   \s
                O O O   \s
            O O O O O O O
            O O O _ O O O
            O O O O O O O
                O O O   \s
                O O O   \s""",
        arm3.getGameState());
    arm3.move(5, 3, 3, 3);
    assertEquals(
        """
                O O O   \s
                O O O   \s
            O O O O O O O
            O O O O O O O
            O O O _ O O O
                O _ O   \s
                O O O   \s""",
        arm3.getGameState());
    arm3.move(2, 3, 4, 3);
    assertEquals(
        """
                O O O   \s
                O O O   \s
            O O O _ O O O
            O O O _ O O O
            O O O O O O O
                O _ O   \s
                O O O   \s""",
        arm3.getGameState());
    arm5.move(3, 5, 5, 5);
    assertEquals(
        """
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s
            O O O O O _ O O O O O
            O O O O O _ O O O O O
            O O O O O O O O O O O
            O O O O O O O O O O O
            O O O O O O O O O O O
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s""",
        arm5.getGameState());
  }

  /**
   * Tests if the getScore is getting the correct score
   */
  @Test
  public void getScore() {
    assertEquals(32, arm3.getScore());
    arm3.move(5, 3, 3, 3);
    assertEquals(31, arm3.getScore());
    arm3.move(2, 3, 4, 3);
    assertEquals(30, arm3.getScore());
    arm3.move(0, 3, 2, 3);
    arm3.move(3, 1, 3, 3);
    arm3.move(3, 4, 3, 2);
    arm3.move(3, 6, 3, 4);
    assertEquals(26, arm3.getScore());
    assertEquals(84, arm5.getScore());
    assertEquals(160, arm7.getScore());
    assertEquals(32, arm3initial23.getScore());
    assertEquals(84, arm5initial65.getScore());
  }
}