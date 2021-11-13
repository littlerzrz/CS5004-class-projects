package chess;

/**
 * This class represents the bishop in chess
 */
public class Bishop extends SlidePiece {

  public Bishop(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public int[][] movableDirections() {
    return DIAGONAL_DIRS;
  }
}
