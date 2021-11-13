package chess;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is an abstract class representing piece that can directly kill enemy when it can move to the
 * position
 */
public abstract class DirectKillPiece implements ChessPiece {

  private int row;
  private int column;
  private Color color;

  /**
   * Constructs a chess piece that can directly kill enemy piece when it can move to the position
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  color of the piece
   * @throws IllegalArgumentException when the row or column is invalid
   */
  public DirectKillPiece(int row, int column, Color color) throws IllegalArgumentException {
    this.row = row;
    this.column = column;
    this.color = color;
    if (!isValidPosition()) {
      throw new IllegalArgumentException("Invalid position, try again!");
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
  public boolean canMove(int destRow, int destColumn) {
    int[] pos = {destRow, destColumn};
    ArrayList<int[]> movable = movablePositions();
    for (int[] item : movable
    ) {
      if (Arrays.equals(pos, item)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean canKill(ChessPiece chessPiece) {
    int x = chessPiece.getRow();
    int y = chessPiece.getColumn();
    boolean isEnemy = chessPiece.getColor() != color;
    return canMove(x, y) && isEnemy;
  }

  @Override
  public boolean isValidPosition(int[] pos) {
    return pos[0] >= 0 && pos[1] >= 0 && pos[0] < 8 && pos[1] < 8;
  }

  /**
   * Overload of the isValidPosition method
   *
   * @return whether if this chess' position is valid
   */
  public boolean isValidPosition() {
    return isValidPosition(new int[]{row, column});
  }

  /**
   * Gets the movable positions of the piece
   *
   * @return all movable positions of the piece
   */
  public ArrayList<int[]> movablePositions() {
    throw new UnsupportedOperationException("No implementation error!!");
  }


  /**
   * Directions the specific piece can move. Represented in 2D array with the very first position of
   * each direction
   *
   * @return movable directions for the piece
   */
  public int[][] movableDirections() {
    throw new UnsupportedOperationException("No implementation error!!");
  }

}
