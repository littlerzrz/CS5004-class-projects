package cs5004.marblesolitaire.model;

public enum PieceMark {
  O("O"), NULL(" "), EMPTY("_");
  private final String name;
  PieceMark(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
