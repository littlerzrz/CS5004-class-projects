package chess;

/**
 * This is a class representing the pawn in chess
 */
public class Pawn implements ChessPiece {

  private int row;
  private int column;
  private Color color;

  /**
   * Constructs a pawn piece
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  color of the piece
   * @throws IllegalArgumentException when the position credentials are off the board
   */
  public Pawn(int row, int column, Color color) throws IllegalArgumentException {
    this.row = row;
    this.column = column;
    this.color = color;
    if (!isValidPosition(new int[]{row, column})) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public boolean canMove(int destRow, int destCol) {
    int direction = movableDirection();
    if (destCol != column) {
      return false;
    }
    if (!isValidPosition(new int[] {destRow, destCol})) {
      return false;
    }
    if (isInitialPosition()) {
      return destRow == row + direction || destRow == row + direction * 2;
    }
    return destRow == row + direction;
  }


  /**
   * A method to get the direction index of the piece, 1 as upward and -1 as downward
   *
   * @return the direction index
   */
  private int movableDirection() {
    return color == Color.WHITE ? 1 : -1;
  }

  @Override
  public boolean canKill(ChessPiece chessPiece) {
    int direction = movableDirection();
    int rowDiff = chessPiece.getRow() - row;
    int colDiff = chessPiece.getColumn() - column;
    boolean isEnemy = chessPiece.getColor() != color;
    return isEnemy && rowDiff == direction && Math.abs(colDiff) == 1;
  }

  @Override
  public boolean isValidPosition(int[] pos) {
    if (color == Color.WHITE) {
      return pos[0] > 0 && pos[0] < 8 && pos[1] >= 0 && pos[1] < 8;
    }
    return pos[0] >= 0 && pos[0] < 7 && pos[1] >= 0 && pos[1] < 8;
  }


  /**
   * Determines if the pawn is at its initial position
   *
   * @return whether if the pawn is at its initial position
   */
  private boolean isInitialPosition() {
    if (color == Color.WHITE) {
      return row == 1;
    }
    return row == 6;
  }
}
