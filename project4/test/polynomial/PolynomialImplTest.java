package polynomial;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for PolynomialImpl
 */
public class PolynomialImplTest {

  private PolynomialImpl poly0;
  private PolynomialImpl poly1;
  private PolynomialImpl poly2;
  private PolynomialImpl poly3;
  private PolynomialImpl poly5;

  @Before
  public void setUp() {
    poly0 = new PolynomialImpl();
    poly1 = new PolynomialImpl("4x^3 +3x^1 -5");
    poly2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    poly3 = new PolynomialImpl("-50x^3 +x^2 +3");
    poly5 = new PolynomialImpl("3");
  }

  /**
   * Tests to make sure the invalid credentials won't form the polynomial
   */
  @Test
  public void InvalidConstructorTest() {
    String[] strArr = new String[]{"-3x^4-2x^5", "4x^3 +3x^-1 -5", "-50x^3+0x^2+3", "5x^1 -3x^3 +2.1x^5 +12x^1"};
    for (String str : strArr)
      invalidTestHelper(() -> {
        PolynomialImpl poly = new PolynomialImpl(str);
      });
  }

  /**
   * Higher order helper method for invalid tests
   * @param block block of code to execute
   */
  private void invalidTestHelper(Runnable block) {
    try {
      block.run();
      fail("An exception should been thrown");
    } catch (IllegalArgumentException ignored) {
    }
  }


  /**
   * Tests the add method and make sure the method computes and returns the correct value
   */
  @Test
  public void add() {
    Polynomial poly12 = poly1.add(poly2);
    Polynomial poly23 = poly2.add(poly3);
    Polynomial poly31 = poly3.add(poly1);
    Polynomial poly1N = poly1.add(new PolynomialImpl());
    assertEquals("4x^3 +3x^1 -5", poly1N.toString());
    assertEquals("-2x^5 -3x^4 +4x^3 +14x^1 -10", poly12.toString());
    assertEquals("-2x^5 -3x^4 -50x^3 +1x^2 +11x^1 -2", poly23.toString());
    assertEquals("-46x^3 +1x^2 +3x^1 -2", poly31.toString());
  }

  /**
   * Tests the add method with invalid type of polynomial, expect to get exceptions
   */
  @Test
  public void invalidAdd() {
    PolynomialImpl2 diffPoly1 = new PolynomialImpl2("1x^2 +3x^1 -2");
    PolynomialImpl2 diffPoly2 = new PolynomialImpl2();
    PolynomialImpl[] arr = new PolynomialImpl[]{poly1, poly2, poly3};
    for (PolynomialImpl polynomial : arr) {
      invalidTestHelper(()-> {
        Polynomial invalid = polynomial.add(diffPoly1);
      });
      invalidTestHelper(()-> {
        Polynomial invalid = polynomial.add(diffPoly2);
      });
    }
  }

  /**
   * Tests if the methods correctly adds term into the polynomial
   */
  @Test
  public void addTerm() {

    poly1.addTerm(3,1);
    assertEquals("4x^3 +6x^1 -5", poly1.toString());
    poly1.addTerm(-4,5);
    assertEquals("-4x^5 +4x^3 +6x^1 -5", poly1.toString());

    poly2.addTerm(-14,2);
    assertEquals("-2x^5 -3x^4 -14x^2 +11x^1 -5", poly2.toString());
    poly2.addTerm(1,4);
    assertEquals("-2x^5 -2x^4 -14x^2 +11x^1 -5", poly2.toString());

    invalidTestHelper(()-> poly3.addTerm(1,-1));
    invalidTestHelper(()-> poly3.addTerm(4,-8));
  }

  /**
   * Tests if the method is checking the two polynomials correctly
   */
  @Test
  public void isSame() {
    PolynomialImpl poly4 = new PolynomialImpl("4x^3 +3x -5");
    PolynomialImpl emptyPoly1 = new PolynomialImpl();
    PolynomialImpl emptyPoly2 = new PolynomialImpl();
    assertTrue(poly1.isSame(poly4));
    assertTrue(poly4.isSame(poly1));
    assertFalse(poly1.isSame(poly2));
    assertFalse(poly2.isSame(poly4));
    assertFalse(poly1.isSame(emptyPoly2));
    assertTrue(emptyPoly1.isSame(emptyPoly2));
    assertTrue(poly5.isSame(new PolynomialImpl("3")));
    for (int i = 0; i < 500; i++) {
      int MAX = 30;
      Random random1 = new Random();
      int randomCo = random1.nextInt(MAX) * (random1.nextBoolean() ? -1 : 1);
      int randomCo2 = new Random().nextInt(MAX);
      emptyPoly1.addTerm(randomCo, i);
      emptyPoly2.addTerm(randomCo-randomCo2, i);
      emptyPoly2.addTerm(randomCo2, i);
      assertTrue(emptyPoly1.isSame(emptyPoly2));
    }
  }

  /**
   * Tests if the method is computing and returning the evaluation result correctly
   */
  @Test
  public void evaluate() {
    assertEquals(2.0,poly1.evaluate(1), 0.01);
    assertEquals(-651.056,poly1.evaluate(-5.4), 0.01);
    assertEquals(256.43,poly2.evaluate(-3.1), 0.01);
    assertEquals(-7315378.86,poly3.evaluate(52.7), 0.01);
  }

  /**
   * Tests if the method is getting the right coefficient
   */
  @Test
  public void getCoefficient() {
    assertEquals(0, poly0.getCoefficient(0));
    assertEquals(4, poly1.getCoefficient(3));
    assertEquals(3, poly1.getCoefficient(1));
    assertEquals(-5, poly1.getCoefficient(0));
    assertEquals(-2, poly2.getCoefficient(5));
    assertEquals(-50, poly3.getCoefficient(3));
  }

  /**
   * Tests if the method is getting the right degree
   */
  @Test
  public void getDegree() {
    assertEquals(0, poly0.getDegree());
    assertEquals(3, poly1.getDegree());
    assertEquals(5, poly2.getDegree());
    assertEquals(3, poly3.getDegree());
  }
}

/**
 * A different Polynomial implementation to test some methods that require the same type
 */
class PolynomialImpl2 implements Polynomial {

  public PolynomialImpl2() {
  }
  public PolynomialImpl2(String str) {
  }
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    return null;
  }
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
  }
  @Override
  public boolean isSame(Polynomial poly) {
    return false;
  }
  @Override
  public double evaluate(double x) {
    return 0;
  }
  @Override
  public int getCoefficient(int power) {
    return 0;
  }
  @Override
  public int getDegree() {
    return 0;
  }
}