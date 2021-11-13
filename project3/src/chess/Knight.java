package chess;

/**
 * This is a class representing the knight in chess
 */
public class Knight extends StepPiece {
  public Knight(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public int[][] movableDirections() {
    int[][] directions = {{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2}};
    return directions;
  }
}
