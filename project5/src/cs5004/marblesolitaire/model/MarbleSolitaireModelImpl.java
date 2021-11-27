package cs5004.marblesolitaire.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class representation of Marble Solitaire game model implementation
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  Piece[][] board;
  int score = 0;

  /**
   * Constructs a MarbleSolitaireModelImpl with no params initialize the game board with arm
   * thickness 3 with the empty slot at the center.
   */
  public MarbleSolitaireModelImpl() {
    setupBoard(3, 3, 3);
  }

  /**
   * Construct initialize the game board so that the arm thickness is 3 and the empty slot is at the
   * position (sRow, sCol).
   *
   * @param sRow specified initial empty position row
   * @param sCol specified initial empty position column
   * @throws IllegalArgumentException with a message "Invalid empty cell position (r,c)" If this
   *                                  specified position is invalid
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    setupBoard(3, sRow, sCol);
  }

  /**
   * Take the arm thickness as its only parameter and initialize a game board in the arm thickness
   * with the empty slot at the center
   *
   * @param armThickness arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    setupBoard(armThickness, armThickness, armThickness);
  }

  /**
   * Take the arm thickness, row and column of the empty slot in that order and initialize a game
   * board in the arm thickness with the empty slot at the specified position
   *
   * @param armThickness arm thickness of the board
   * @param sRow         specified initial empty position row
   * @param sCol         specified initial empty position column
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the
   *                                  specified position is invalid
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    setupBoard(armThickness, sRow, sCol);
  }

  /**
   * Helper method to set up the board and handle exceptions
   *
   * @param armThickness arm thickness of the board
   * @param sRow         specified initial empty position row
   * @param sCol         specified initial empty position column
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the *
   *                                  specified position is invalid
   */
  private void setupBoard(int armThickness, int sRow, int sCol) throws IllegalArgumentException {
    IllegalArgumentException invalidEmpty = new IllegalArgumentException(
        "Invalid empty cell position (r,c)");
    if (armThickness % 2 != 1 || armThickness < 3) {
      throw new IllegalArgumentException("Invalid arm thickness!");
    }
    //the dimension of the whole square
    int dimension = armThickness * 2 + 1;
    //width of the null corner space, which is also the start index of an arm
    int cornerWidth = armThickness / 2 + 1;
    //end boundary index of the arm
    int armEnd = dimension - cornerWidth;
    boolean invalidPositions = sRow < 0 || sRow >= dimension || sCol < 0 || sCol >= dimension;
    if (invalidPositions) {
      throw invalidEmpty;
    }
    board = new Piece[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if ((i >= cornerWidth && i < armEnd) || (j >= cornerWidth && j < armEnd)) {
          placePiece(i, j);
        } else {
          placeNullPiece(i, j);
        }
      }
    }
    if (isNotValid(sRow, sCol)) {
      throw invalidEmpty;
    }
    placeEmptyPiece(sRow, sCol);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!canMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Cannot move!");
    }
    placeEmptyPiece(fromRow, fromCol);
    placePiece(toRow, toCol);
    int[] medPos = medPosition(fromRow, fromCol, toRow, toCol);
    placeEmptyPiece(medPos[0], medPos[1]);
  }


  @Override
  public boolean isGameOver() {
    return Arrays.stream(board).allMatch(
        row -> Arrays.stream(row).noneMatch(piece -> canMove(piece.getRow(), piece.getCol())));
  }

  @Override
  public String getGameState() {
    return Arrays.stream(board).map(row -> Arrays.stream(row).map(Piece::toString).collect(
        Collectors.joining(" "))).collect(Collectors.joining("\n"));
  }

  @Override
  public int getScore() {
    return score;
  }

  /**
   * Helper method to check the specified position is empty or not
   *
   * @param row row of the specified position
   * @param col col of the specified position
   * @return whether if the position is empty or not
   */
  private boolean isEmpty(int row, int col) {
    return getPiece(row, col).isEmpty();
  }

  /**
   * Helper method to check the specified position has a piece or not
   *
   * @param row row of the specified position
   * @param col col of the specified position
   * @return whether if the position has a piece or not
   */
  private boolean hasPiece(int row, int col) {
    return getPiece(row, col).isO();
  }

  /**
   * Helper method to check if the position is not valid
   *
   * @param row row of the specified position
   * @param col col of the specified position
   * @return whether if the position is not valid
   */
  private boolean isNotValid(int row, int col) {
    return getPiece(row, col).isNull();
  }

  /**
   * Getter for piece on board, if the row/col is out of bound, then the method should return a null
   * piece
   *
   * @param row row of the position
   * @param col col of the position
   * @return the piece at the position
   */
  private Piece getPiece(int row, int col) {
    if (row < 0 || col < 0 || row >= board.length || col >= board.length) {
      return new Piece(-1, -1, PieceMark.NULL);
    }
    return board[row][col];
  }

  /**
   * Places a piece on a specified board position and adds the score by 1
   *
   * @param row row of position
   * @param col col of position
   */
  private void placePiece(int row, int col) {
    board[row][col] = new Piece(row, col);
    score++;
  }

  /**
   * Places a null piece on a specified board position
   *
   * @param row row of position
   * @param col col of position
   */
  private void placeNullPiece(int row, int col) {
    board[row][col] = new Piece(row, col, PieceMark.NULL);
  }

  /**
   * Places an empty piece on a specified board position and reduces score by 1
   *
   * @param row row of position
   * @param col col of position
   */
  private void placeEmptyPiece(int row, int col) {
    board[row][col] = new Piece(row, col, PieceMark.EMPTY);
    score--;
  }

  /**
   * Gets the med position between two positions
   *
   * @param fromRow row of from position
   * @param fromCol col of from position
   * @param toRow   row of to position
   * @param toCol   col of to position
   * @return position coordinates of the med between the two positions
   */
  private int[] medPosition(int fromRow, int fromCol, int toRow, int toCol) {
    return new int[]{(fromRow + toRow) / 2, (fromCol + toCol) / 2};
  }

  /**
   * Helper method to determine if the piece can move from one position to another of not
   *
   * @param fromRow row of from position
   * @param fromCol col of from position
   * @param toRow   row of to position
   * @param toCol   col of to position
   * @return if the piece can move from one position to another of not
   */
  private boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (isNotValid(fromRow, fromCol) || isNotValid(toRow, toCol)) {
      return false;
    }
    if (!hasPiece(fromRow, fromCol) || !isEmpty(toRow, toCol)) {
      return false;
    }
    int rowDiff = Math.abs(toRow - fromRow);
    int colDiff = Math.abs(toCol - fromCol);
    int[] medPosition = medPosition(fromRow, fromCol, toRow, toCol);
    boolean correctPos = (rowDiff == 2 && colDiff == 0) || (colDiff == 2 && rowDiff == 0);
    return correctPos && hasPiece(medPosition[0], medPosition[1]);
  }

  /**
   * Helper method to determine the piece can move or not
   *
   * @param row row of the position
   * @param col col of the position
   * @return piece at the position can move or not
   */
  private boolean canMove(int row, int col) {
    boolean canMoveInRow =
        canMove(row, col, row - 2, col) || canMove(row, col, row + 2,
            col);
    boolean canMoveInCol =
        canMove(row, col, row, col - 2) || canMove(row, col, row,
            col + 2);
    return canMoveInRow || canMoveInCol;
  }
}
