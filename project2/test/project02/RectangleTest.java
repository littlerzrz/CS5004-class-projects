package project02;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.Rect;

public class RectangleTest {

  Rectangle rec1;
  Rectangle rec2;
  Rectangle rec3;
  Rectangle rec4;
  Rectangle rec5;
  Rectangle rec6;


  @Before
  public void setUp() {
    this.rec1 = new Rectangle(5, 4, 10, 15);
    this.rec2 = new Rectangle(-10, 5, 30, 20);
    this.rec3 = new Rectangle(-5, -20, 25, 25);
    this.rec4 = new Rectangle(3, 2, 10, 30);
    this.rec5 = new Rectangle(-20, -30, 80, 90);
    this.rec6 = new Rectangle(-30, -40, 5, 5);
  }

  /**
   * Tests whether the constructor throws an exception with invalid width or height
   */
  @Test
  public void invalidConstructorTest() {
    invalidConstructorHelper(3, 4, -10, 4);
    invalidConstructorHelper(-10, 3, 1, -2);
    invalidConstructorHelper(0,0,0,0);
    invalidConstructorHelper(1,3,1,0);
    invalidConstructorHelper(14,-10,0,1);
  }

  /**
   * A helper method to eliminate redundant code
   *
   * @param x x of the rectangle
   * @param y y of the rectangle
   * @param w width of the rectangle
   * @param h height of the rectangle
   */
  private void invalidConstructorHelper(int x, int y, int w, int h) {
    try {
      Rectangle rec = new Rectangle(x, y, w, h);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * Tests if the overlap method is computing properly
   */
  @Test
  public void overlap() {
    //overlaps
    assertTrue(rec1.overlap(rec2));
    assertTrue(rec1.overlap(rec5));
    assertTrue(rec1.overlap(rec1));
    assertTrue(rec2.overlap(rec4));
    //touch but not overlap
    assertFalse(rec2.overlap(rec3));
    //no overlap at all
    assertFalse(rec1.overlap(rec6));
    assertFalse(rec6.overlap(rec3));
  }

  /**
   * Tests if the intersection method is working properly. The method should throw a
   * NoSuchElementException when no there is no overlap between the two rectangles
   */
  @Test
  public void intersection() {
    assertEquals("x:5, y:5, w:10, h:14", rec1.intersection(rec2).toString());
    assertEquals("x:5, y:4, w:10, h:15", rec1.intersection(rec5).toString());
    noIntersectionHelper(rec2, rec3);
    noIntersectionHelper(rec1, rec6);
    noIntersectionHelper(rec6, rec3);
  }

  /**
   * Helper method testing with no overlaps to expect the exception
   *
   * @param recA rectangleA
   * @param recB rectangleB
   */
  private void noIntersectionHelper(Rectangle recA, Rectangle recB, String type) {
    try {
      if (type.equals("IOU")) {
        float res = recA.intersectionOverUnion(recB);
      } else {
        Rectangle rec = recA.intersection(recB);
      }
      fail("An exception should have been thrown");
    } catch (NoSuchElementException e) {
    }
  }

  /**
   * Overload of the noIntersectionHelper to work without the input type
   */
  private void noIntersectionHelper(Rectangle recA, Rectangle recB) {
    noIntersectionHelper(recA, recB, "other");
  }

  /**
   * Tests if the union method is correctly returning the union region rectangle
   */
  @Test
  public void union() {
    assertEquals("x:-10, y:4, w:30, h:21", rec1.union(rec2).toString());
    assertEquals("x:-5, y:-20, w:25, h:39", rec3.union(rec1).toString());
    assertEquals("x:-10, y:-20, w:30, h:45", rec2.union(rec3).toString());
    assertEquals("x:-20, y:-30, w:80, h:90", rec5.union(rec1).toString());
    assertEquals("x:-30, y:-40, w:50, h:45", rec6.union(rec3).toString());

  }

  /**
   * Tests if the method is correctly computing the result of the IOU
   */
  @Test
  public void intersectionOverUnion() {
    assertEquals(0.2222, rec1.intersectionOverUnion(rec2), 0.001f);
    assertEquals(0.0208, rec1.intersectionOverUnion(rec5), 0.001f);
    assertEquals(0.0833, rec2.intersectionOverUnion(rec5), 0.001f);
    noIntersectionHelper(rec2, rec3, "IOU");
    noIntersectionHelper(rec1, rec6, "IOU");
    noIntersectionHelper(rec3, rec6, "IOU");
  }

  /**
   * Tests whether the method returns a valid formatted string
   */
  @Test
  public void testToString() {
    assertEquals("x:5, y:4, w:10, h:15",rec1.toString());
    assertEquals("x:-10, y:5, w:30, h:20",rec2.toString());
    assertEquals("x:-5, y:-20, w:25, h:25",rec3.toString());
    assertEquals("x:3, y:2, w:10, h:30",rec4.toString());
    assertEquals("x:-20, y:-30, w:80, h:90",rec5.toString());
    assertEquals("x:-30, y:-40, w:5, h:5",rec6.toString());
  }
}