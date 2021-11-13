package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the rook chess piece
 */
public class RookTest {

  private Rook whiteRook00;
  private Rook blackRook77;
  private Rook whiteRook45;
  private Rook blackRook31;

  @Before
  public void setUp() {
    this.whiteRook00 = new Rook(0, 0, Color.WHITE);
    this.whiteRook45 = new Rook(4, 5, Color.WHITE);
    this.blackRook77 = new Rook(7, 7, Color.BLACK);
    this.blackRook31 = new Rook(3, 1, Color.BLACK);
  }

  /**
   * Tests the piece with invalid credentials
   */
  @Test
  public void invalidPieceCheck() {
    invalidCheckHelper(11, 8, Color.BLACK);
    invalidCheckHelper(-1, 9, Color.WHITE);
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
      Rook rook = new Rook(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(0, whiteRook00.getRow());
    assertEquals(7, blackRook77.getRow());
    assertEquals(4, whiteRook45.getRow());
    assertEquals(3, blackRook31.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(0, whiteRook00.getColumn());
    assertEquals(7, blackRook77.getColumn());
    assertEquals(5, whiteRook45.getColumn());
    assertEquals(1, blackRook31.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whiteRook00.getColor());
    assertEquals(Color.WHITE, whiteRook45.getColor());
    assertEquals(Color.BLACK, blackRook77.getColor());
    assertEquals(Color.BLACK, blackRook31.getColor());
  }

  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
    assertTrue(whiteRook00.canMove(5, 0));
    assertFalse(whiteRook00.canMove(4, 2));
    assertTrue(blackRook77.canMove(7, 2));
    assertFalse(blackRook77.canMove(1, 5));
    assertTrue(whiteRook45.canMove(6, 5));
    assertFalse(whiteRook45.canMove(6, 4));
    assertTrue(blackRook31.canMove(0, 1));
    assertFalse(blackRook31.canMove(4, 2));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    Knight blackKnight70 = new Knight(7, 0, Color.BLACK);
    Knight whiteKnight51 = new Knight(5, 1, Color.WHITE);
    assertTrue(whiteRook00.canKill(blackKnight70));
    assertFalse(whiteRook45.canKill(whiteKnight51));
    assertFalse(whiteRook45.canKill(blackKnight70));
    assertTrue(blackRook31.canKill(whiteKnight51));
    assertFalse(blackRook77.canKill(whiteKnight51));
    assertFalse(blackRook77.canKill(whiteKnight51));
  }
}