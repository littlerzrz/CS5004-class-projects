package chess;

/**
 * This is a class representing the queen in chess
 */
public class Queen extends SlidePiece {

  public Queen(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public int[][] movableDirections() {
    int[][] result = new int[HORIZONTAL_AND_VERTICAL_DIRS.length + DIAGONAL_DIRS.length][];
    System.arraycopy(HORIZONTAL_AND_VERTICAL_DIRS, 0, result,0, HORIZONTAL_AND_VERTICAL_DIRS.length);
    System.arraycopy(DIAGONAL_DIRS, 0, result, HORIZONTAL_AND_VERTICAL_DIRS.length, DIAGONAL_DIRS.length);
    return result;
  }
}
