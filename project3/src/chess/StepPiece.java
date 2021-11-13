package chess;

import java.util.ArrayList;

/**
 * This is an abstract class for chess pieces that can only step to several positions
 */
public abstract class StepPiece extends DirectKillPiece {

  public StepPiece(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public ArrayList<int[]> movablePositions() {
    int[][] directions = movableDirections();
    ArrayList<int[]> positions = new ArrayList<>();
    for (int[] dir : directions
    ) {
      int x = getRow() + dir[0];
      int y = getColumn() + dir[1];
      int[] pos = {x, y};
      if (isValidPosition(pos)) {
        positions.add(pos);
      }
    }
    return positions;
  }
}
