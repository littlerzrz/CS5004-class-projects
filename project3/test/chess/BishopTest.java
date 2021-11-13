package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the bishop chess piece
 */
public class BishopTest {

  private Bishop whiteBishop50;
  private Bishop blackBishop27;
  private Bishop whiteBishop45;
  private Bishop blackBishop31;

  @Before
  public void setUp() {
    this.whiteBishop50 = new Bishop(5, 0, Color.WHITE);
    this.whiteBishop45 = new Bishop(4, 5, Color.WHITE);
    this.blackBishop27 = new Bishop(2, 7, Color.BLACK);
    this.blackBishop31 = new Bishop(3, 1, Color.BLACK);
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
      Bishop bishop = new Bishop(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }
  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(5, whiteBishop50.getRow());
    assertEquals(2, blackBishop27.getRow());
    assertEquals(4, whiteBishop45.getRow());
    assertEquals(3, blackBishop31.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(0, whiteBishop50.getColumn());
    assertEquals(7, blackBishop27.getColumn());
    assertEquals(5, whiteBishop45.getColumn());
    assertEquals(1, blackBishop31.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whiteBishop45.getColor());
    assertEquals(Color.WHITE, whiteBishop50.getColor());
    assertEquals(Color.BLACK, blackBishop27.getColor());
    assertEquals(Color.BLACK, blackBishop31.getColor());
  }
  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
    assertTrue(whiteBishop50.canMove(1, 4));
    assertFalse(whiteBishop50.canMove(4,2));
    assertTrue(blackBishop27.canMove(7,2));
    assertFalse(blackBishop27.canMove(1,5));
    assertTrue(whiteBishop45.canMove(2, 3));
    assertFalse(whiteBishop45.canMove(6,4));
    assertTrue(blackBishop31.canMove(4,0));
    assertFalse(blackBishop31.canMove(3,2));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    Knight blackKnight14 = new Knight(1,4, Color.BLACK);
    Knight whiteKnight40 = new Knight(4,0, Color.WHITE);
    assertTrue(whiteBishop50.canKill(blackKnight14));
    assertFalse(whiteBishop45.canKill(whiteKnight40));
    assertFalse(whiteBishop45.canKill(blackKnight14));
    assertTrue(blackBishop31.canKill(whiteKnight40));
    assertFalse(blackBishop27.canKill(whiteKnight40));
    assertFalse(blackBishop27.canKill(whiteKnight40));
  }

}