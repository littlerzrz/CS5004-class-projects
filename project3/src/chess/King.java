package chess;

/**
 * This is a class representing the king in chess
 */
public class King extends StepPiece {
  public King(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public int[][] movableDirections() {
    int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
    return directions;
  }
}
