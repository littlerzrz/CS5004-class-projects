package chess;

/**
 * This is a class representing the rock in chess
 */
public class Rook extends SlidePiece {

  public Rook(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public int[][] movableDirections() {
    return HORIZONTAL_AND_VERTICAL_DIRS;
  }
}
