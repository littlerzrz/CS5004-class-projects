package chess;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for the king chess piece
 */
public class KingTest {

  private King whiteKing40;
  private King blackKing47;
  private King whiteKing65;
  private King blackKing33;

  @Before
  public void setUp() {
    this.whiteKing40 = new King(4, 0, Color.WHITE);
    this.blackKing47 = new King(4, 7, Color.BLACK);
    this.whiteKing65 = new King(6, 5, Color.WHITE);
    this.blackKing33 = new King(3, 3, Color.BLACK);
  }

  /**
   * Tests the piece with invalid credentials
   */
  @Test
  public void invalidPieceCheck() {
    invalidCheckHelper(20, 30, Color.BLACK);
    invalidCheckHelper(-1, 2, Color.WHITE);
    invalidCheckHelper(0, -2, Color.BLACK);
  }

  /**
   * Helper check method for invalid positions
   *
   * @param row    row of piece
   * @param column column of piece
   * @param color  color of piece
   */
  private void invalidCheckHelper(int row, int column, Color color) {
    try {
      King king = new King(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }
  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(4, whiteKing40.getRow());
    assertEquals(4, blackKing47.getRow());
    assertEquals(6, whiteKing65.getRow());
    assertEquals(3, blackKing33.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(0, whiteKing40.getColumn());
    assertEquals(7, blackKing47.getColumn());
    assertEquals(5, whiteKing65.getColumn());
    assertEquals(3, blackKing33.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whiteKing40.getColor());
    assertEquals(Color.WHITE, whiteKing65.getColor());
    assertEquals(Color.BLACK, blackKing47.getColor());
    assertEquals(Color.BLACK, blackKing33.getColor());
  }

  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
    assertTrue(whiteKing40.canMove(3, 0));
    assertFalse(whiteKing40.canMove(10,10));
    assertTrue(blackKing47.canMove(5,6));
    assertFalse(blackKing47.canMove(3,1));
    assertTrue(whiteKing65.canMove(7, 6));
    assertFalse(whiteKing65.canMove(6,3));
    assertTrue(blackKing33.canMove(2,2));
    assertFalse(blackKing33.canMove(1,7));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    Rook blackRook64 = new Rook(6,4, Color.BLACK);
    Rook whiteRook34 = new Rook(3,4, Color.WHITE);
    assertTrue(whiteKing65.canKill(blackRook64));
    assertFalse(whiteKing65.canKill(whiteRook34));
    assertFalse(whiteKing40.canKill(blackRook64));
    assertTrue(blackKing33.canKill(whiteRook34));
    assertFalse(blackKing47.canKill(whiteRook34));
    assertFalse(blackKing47.canKill(whiteRook34));
  }

}