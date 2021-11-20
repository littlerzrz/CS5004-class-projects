/**
 * Do not modify this file. This file should compile correctly with your code!
 * You DO NOT need to submit this file.
 */
public class TypeChecks {

  /**
   * The contents of this method are meaningless.
   * They are only here to ensure that your code compiles properly.
   */
  public static void main(String[] args) {
    helper(new cs5004.marblesolitaire.model.MarbleSolitaireModelImpl());
    helper(new cs5004.marblesolitaire.model.MarbleSolitaireModelImpl(2, 2));
    helper(new cs5004.marblesolitaire.model.MarbleSolitaireModelImpl(5));
    helper(new cs5004.marblesolitaire.model.MarbleSolitaireModelImpl(3, 0, 4));
  }

  private static void helper(cs5004.marblesolitaire.model.MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
