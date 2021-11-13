package chess;

import java.util.ArrayList;

/**
 * This is an abstract class for chess pieces that can slide either horizontal/vertically or
 * diagonally, or both;
 */
public abstract class SlidePiece extends DirectKillPiece {

  public static final int[][] HORIZONTAL_AND_VERTICAL_DIRS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
  public static final int[][] DIAGONAL_DIRS = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

  public SlidePiece(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public ArrayList<int[]> movablePositions() {
    int[][] directions = movableDirections();
    ArrayList<int[]> positions = new ArrayList<>();
    for (int[] dir : directions) {
      positions.addAll(growPositionsToBoardEnd(dir));
    }
    return positions;
  }

  /**
   * Grows the directions all the way till the board's end
   *
   * @param dir direction coordinates
   * @return a list of positions on a specific direction
   */
  private ArrayList<int[]> growPositionsToBoardEnd(int[] dir) {
    int[] pos = {getRow(), getColumn()};
    ArrayList<int[]> positions = new ArrayList<>();
    while (isValidPosition(pos)) {
      int x = pos[0] + dir[0];
      int y = pos[1] + dir[1];
      int[] newPos = {x, y};
      positions.add(newPos);
      pos = newPos;
    }
    return positions;
  }
}
