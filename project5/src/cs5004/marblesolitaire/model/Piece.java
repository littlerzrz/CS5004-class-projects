package cs5004.marblesolitaire.model;
import java.util.Objects;

/**
 * Class representation of a single marble piece element on the board
 */
public class Piece {
  private int row;
  private int col;
  private final PieceMark mark;

  /**
   * Constructs the piece element with row and column, and a mark of O
   * @param row row on the board for this piece
   * @param col col on the board for this piece
   */
  public Piece(int row, int col) {
    this.row = row;
    this.col = col;
    this.mark = PieceMark.O;
  }

  /**
   * Constructs the piece with row, column, and mark
   * @param row row on the board for this piece
   * @param col col on the board for this piece
   * @param mark the specified mark
   */
  public Piece(int row, int col, PieceMark mark) {
    this.row = row;
    this.col = col;
    this.mark = mark;
  }


  @Override
  public String toString() {
    return mark.toString();
  }

  /**
   * Checks if the piece is empty
   * @return if the piece is empty
   */
  public boolean isEmpty() {
    return mark == PieceMark.EMPTY;
  }

  /**
   * Checks if the piece is with mark O
   * @return if the piece is with mark O
   */
  public boolean isO() {
    return mark == PieceMark.O;
  }

  /**
   * Checks if the piece is null(invalid)
   * @return if the piece is null
   */
  public boolean isNull() {
    return mark == PieceMark.NULL;
  }

  /**
   * Getter for row
   * @return row
   */
  public int getRow() {
    return row;
  }

  /**
   * Getter for col
   * @return col
   */
  public int getCol() {
    return col;
  }
}
