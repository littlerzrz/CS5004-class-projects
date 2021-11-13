package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {
  private Pawn whitePawn12;
  private Pawn blackPawn56;
  private Pawn whitePawn33;
  private Pawn blackPawn62;

  @Before
  public void setUp() {
    this.whitePawn12 = new Pawn(1, 2, Color.WHITE);
    this.whitePawn33 = new Pawn(3, 3, Color.WHITE);
    this.blackPawn56 = new Pawn(5, 6, Color.BLACK);
    this.blackPawn62 = new Pawn(6, 2, Color.BLACK);
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
      Pawn pawn = new Pawn(row, column, color);
      fail("Should have thrown an exception here!");
    } catch (IllegalArgumentException e) {
    }
  }
  /**
   * Tests the getter of row
   */
  @Test
  public void getRow() {
    assertEquals(1, whitePawn12.getRow());
    assertEquals(5, blackPawn56.getRow());
    assertEquals(3, whitePawn33.getRow());
    assertEquals(6, blackPawn62.getRow());
  }

  /**
   * Tests the getter of column
   */
  @Test
  public void getColumn() {
    assertEquals(2, whitePawn12.getColumn());
    assertEquals(6, blackPawn56.getColumn());
    assertEquals(3, whitePawn33.getColumn());
    assertEquals(2, blackPawn62.getColumn());
  }

  /**
   * Tests the getter for color
   */
  @Test
  public void getColor() {
    assertEquals(Color.WHITE, whitePawn12.getColor());
    assertEquals(Color.WHITE, whitePawn33.getColor());
    assertEquals(Color.BLACK, blackPawn56.getColor());
    assertEquals(Color.BLACK, blackPawn62.getColor());
  }

  /**
   * Tests the can move method and see if it returns the proper result
   */
  @Test
  public void canMove() {
//     white pawn can move up 1 or 2 rows at initial position
    assertTrue(whitePawn12.canMove(2, 2));
    assertTrue(whitePawn12.canMove(3, 2));
//     white pawn can not move to position that is not on the same column or move downward
    assertFalse(whitePawn12.canMove(4,4));
    assertFalse(whitePawn12.canMove(4,3));

//     black pawn can move down 1 or 2 rows at initial position
    assertTrue(blackPawn62.canMove(5,2));
    assertTrue(blackPawn62.canMove(4,2));
//     black pawn can not move to position that is not on the same column or move upward
    assertFalse(blackPawn62.canMove(1,1));
    assertFalse(blackPawn62.canMove(7,2));

//     white pawn can only move up 1 row when not at initial position
    assertTrue(whitePawn33.canMove(4, 3));
    assertFalse(whitePawn33.canMove(5, 3));
    assertFalse(whitePawn33.canMove(4,5));

//     black pawn can only move down 1 row when not at initial position
    assertTrue(blackPawn56.canMove(4,6));
    assertFalse(blackPawn56.canMove(3,6));
    assertFalse(blackPawn56.canMove(4,5));
  }

  /**
   * Tests whether if the can kill method is working properly
   */
  @Test
  public void canKill() {
    Queen blackQueen44 = new Queen(4,4, Color.BLACK);
    Queen whiteQueen47 = new Queen(4,7, Color.WHITE);
    assertTrue(whitePawn33.canKill(blackQueen44));
    assertFalse(whitePawn12.canKill(whiteQueen47));
    assertFalse(whitePawn12.canKill(blackQueen44));
    assertTrue( blackPawn56.canKill(whiteQueen47));
    assertFalse(blackPawn62.canKill(whiteQueen47));
    assertFalse(blackPawn62.canKill(whiteQueen47));
  }
}