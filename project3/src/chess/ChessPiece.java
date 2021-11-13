package chess;

/**
 * This is an interface for chess piece
 */
public interface ChessPiece {

  /**
   * Getter for current row
   * @return current row of the chess piece
   */
  int getRow();

  /**
   * Getter for current column
   * @return current column of the chess piece
   */
  int getColumn();

  /**
   * Getter for color
   * @return color of the piece
   */
  Color getColor();

  /**
   * Determine if a chess piece can move to a given cell
   * @param x x coordinate of the intended move
   * @param y y coordinate of the intended move
   * @return if the chess piece can move to the intended position
   */
  boolean canMove(int x, int y);

  /**
   * Determine if it can kill a provided piece starting from where it currently is
   * @param chessPiece the chess piece provided to determine whether if the current piece can kill
   * @return whether if the provided piece can be killed by this chess piece
   */
  boolean canKill(ChessPiece chessPiece);

  /**
   * Check if the position is still on the board
   * @param pos position
   * @return whether the position is on the board or not
   */
  boolean isValidPosition(int[] pos);
}
