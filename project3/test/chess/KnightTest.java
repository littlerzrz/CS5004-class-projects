package chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the knight chess piece
 */
public class KnightTest {

  private Knight whiteKnight10;
  private Knight blackKnight67;
  private Knight whiteKnight32;
  private Knight blackKnight62;

  @Before
  public void setUp() {
    this.whiteKnight10 = new Knight(1, 0, Color.WHITE);
    this.whiteKnight32 = new Knight(3, 2, Color.WHITE);
    this.blackKnight67 = new Knight(6, 7, Color.BLACK);
    this.blackKnight62 = new Knight(6, 2, Color.BLACK);
  }

  /**
   * Tests the piece with invalid credentials
   */
  @Test
  public void invalidPieceCheck() {
    invalidCheckHelper(30, 50, Color.BLACK);
    invalidCheckHelper(-1, 9, Color.WHITE);
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
      Knight knight = new Knight(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }
  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(1, whiteKnight10.getRow());
    assertEquals(6, blackKnight67.getRow());
    assertEquals(3, whiteKnight32.getRow());
    assertEquals(6, blackKnight62.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(0, whiteKnight10.getColumn());
    assertEquals(7, blackKnight67.getColumn());
    assertEquals(2, whiteKnight32.getColumn());
    assertEquals(2, blackKnight62.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whiteKnight10.getColor());
    assertEquals(Color.WHITE, whiteKnight32.getColor());
    assertEquals(Color.BLACK, blackKnight67.getColor());
    assertEquals(Color.BLACK, blackKnight62.getColor());
  }

  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
    assertTrue(whiteKnight10.canMove(3, 1));
    assertFalse(whiteKnight10.canMove(7,4));
    assertTrue(blackKnight67.canMove(5,5));
    assertFalse(blackKnight67.canMove(3,3));
    assertTrue(whiteKnight32.canMove(1, 3));
    assertFalse(whiteKnight32.canMove(6,4));
    assertTrue(blackKnight62.canMove(5,0));
    assertFalse(blackKnight62.canMove(1,7));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    King blackKing51 = new King(5,1, Color.BLACK);
    King whiteKing43 = new King(4,3, Color.WHITE);
    assertTrue(whiteKnight32.canKill(blackKing51));
    assertFalse(whiteKnight32.canKill(whiteKing43));
    assertFalse(whiteKnight10.canKill(blackKing51));
    assertTrue(blackKnight62.canKill(whiteKing43));
    assertFalse(blackKnight67.canKill(whiteKing43));
    assertFalse(blackKnight67.canKill(whiteKing43));
  }

}