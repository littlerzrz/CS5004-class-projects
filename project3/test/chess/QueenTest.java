package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the queen chess piece
 */
public class QueenTest {

  private Queen whiteQueen30;
  private Queen blackQueen37;
  private Queen whiteQueen46;
  private Queen blackQueen22;

  @Before
  public void setUp() {
    this.whiteQueen30 = new Queen(3, 0, Color.WHITE);
    this.whiteQueen46 = new Queen(4, 6, Color.WHITE);
    this.blackQueen37 = new Queen(3, 7, Color.BLACK);
    this.blackQueen22 = new Queen(2, 2, Color.BLACK);
  }

  /**
   * Tests the piece with invalid credentials
   */
  @Test
  public void invalidPieceCheck() {
    invalidCheckHelper(11, 8, Color.BLACK);
    invalidCheckHelper(-1, 7, Color.WHITE);
    invalidCheckHelper(0, -5, Color.BLACK);
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
      Queen queen = new Queen(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(3, whiteQueen30.getRow());
    assertEquals(3, blackQueen37.getRow());
    assertEquals(4, whiteQueen46.getRow());
    assertEquals(2, blackQueen22.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(0, whiteQueen30.getColumn());
    assertEquals(7, blackQueen37.getColumn());
    assertEquals(6, whiteQueen46.getColumn());
    assertEquals(2, blackQueen22.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whiteQueen30.getColor());
    assertEquals(Color.WHITE, whiteQueen46.getColor());
    assertEquals(Color.BLACK, blackQueen37.getColor());
    assertEquals(Color.BLACK, blackQueen22.getColor());
  }

  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
    assertTrue(whiteQueen30.canMove(3, 4));
    assertFalse(whiteQueen30.canMove(4, 2));
    assertTrue(blackQueen37.canMove(6, 4));
    assertFalse(blackQueen37.canMove(1, 4));
    assertTrue(whiteQueen46.canMove(2, 4));
    assertFalse(whiteQueen46.canMove(6, 3));
    assertTrue(blackQueen22.canMove(7, 7));
    assertFalse(blackQueen22.canMove(1, 4));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    King blackKing24 = new King(2, 4, Color.BLACK);
    King whiteKing66 = new King(6, 6, Color.WHITE);
    assertTrue(whiteQueen46.canKill(blackKing24));
    assertFalse(whiteQueen30.canKill(whiteKing66));
    assertFalse(whiteQueen30.canKill(blackKing24));
    assertTrue(blackQueen22.canKill(whiteKing66));
    assertFalse(blackQueen37.canKill(whiteKing66));
    assertFalse(blackQueen37.canKill(whiteKing66));
  }

}