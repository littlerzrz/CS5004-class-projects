package project01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains all the unit tests for various kinds of vectors
 */
class Vector3DTest {

  Vector3D vector1, vector2, vector3, vector4, vector5;

  @BeforeEach
  void setUp() {
    vector1 = new Vector3D(0.00, 0.00, 0.0000);
    vector2 = new Vector3D(-1.22, 8.55, 6.87231);
    vector3 = new Vector3D(2.426564, 2.08, 2.91123);
    vector4 = new Vector3D(-4.73, -7.1112, -3.12391);
    vector5 = new Vector3D(3.00, 4.00, 5.00);
  }

  /**
   * Tests whether the getter method for the object's x component works for all types
   */
  @Test
  void getX() {
    Assertions.assertEquals(0.00, vector1.getX());
    Assertions.assertEquals(-1.22, vector2.getX());
    Assertions.assertEquals(2.426564, vector3.getX());
    Assertions.assertEquals(-4.73, vector4.getX());
    Assertions.assertEquals(3.00, vector5.getX());
  }

  /**
   * Tests whether the getter method for the object's y component works for all types
   */
  @Test
  void getY() {
    Assertions.assertEquals(0.00, vector1.getY());
    Assertions.assertEquals(8.55, vector2.getY());
    Assertions.assertEquals(2.08, vector3.getY());
    Assertions.assertEquals(-7.1112, vector4.getY());
    Assertions.assertEquals(4.00, vector5.getY());
  }

  /**
   * Tests whether the getter method for the object's z component works for all types
   */
  @Test
  void getZ() {
    Assertions.assertEquals(0.0000, vector1.getZ());
    Assertions.assertEquals(6.87231, vector2.getZ());
    Assertions.assertEquals(2.91123, vector3.getZ());
    Assertions.assertEquals(-3.12391, vector4.getZ());
    Assertions.assertEquals(5.00, vector5.getZ());
  }
  /**
   * A helper method to verify that the objects are not changed within any of the operations
   */
  void toStringHelper() {
    Assertions.assertEquals("(0.00, 0.00, 0.00)", vector1.toString());
    Assertions.assertEquals("(-1.22, 8.55, 6.87)", vector2.toString());
    Assertions.assertEquals("(2.43, 2.08, 2.91)", vector3.toString());
    Assertions.assertEquals("(-4.73, -7.11, -3.12)", vector4.toString());
    Assertions.assertEquals("(3.00, 4.00, 5.00)", vector5.toString());
  }

  /**
   * Tests whether the objects have been created with correct numbers or not. It does this by using
   * their toString methods
   */
  @Test
  void testToString() {
    toStringHelper();
  }

  /**
   * Tests whether the method correctly computes and returns the magnitude of the vector object
   */
  @Test
  void getMagnitude() {
    Assertions.assertEquals(0, vector1.getMagnitude());
    Assertions.assertEquals(11.037, vector2.getMagnitude(), 0.001);
    Assertions.assertEquals(4.323, vector3.getMagnitude(), 0.001);
    Assertions.assertEquals(9.094, vector4.getMagnitude(), 0.001);
    Assertions.assertEquals(7.071, vector5.getMagnitude(), 0.001);
  }

  /**
   * A helper method for running a single normalization test of the vector
   *
   * @param vector         the vector to test
   * @param expectedResult the expected string result of the vector
   */
  void singleNormalizeTest(Vector3D vector, String expectedResult) {
    Vector3D normalizedVector;
    String stringVersion;
    try {
      normalizedVector = vector.normalize();
      stringVersion = normalizedVector.toString();
      assertEquals(expectedResult, stringVersion);
    } catch (IllegalStateException e) {
      fail("An exception should not have been thrown");
    }
  }

  /**
   * Tests whether the method correctly computes and returns the normalized version of the vector
   * object.It should throw an IllegalStateException object if this operation cannot be completed.
   */
  @Test
  void normalize() {
    Vector3D normalizedVector;
    String stringVersion;
    try {
      normalizedVector = vector1.normalize();
      stringVersion = normalizedVector.toString();
      fail("An exception should have been thrown");
    } catch (IllegalStateException e) {
    }
    singleNormalizeTest(vector2, "(-0.11, 0.77, 0.62)");
    singleNormalizeTest(vector3, "(0.56, 0.48, 0.67)");
    singleNormalizeTest(vector4, "(-0.52, -0.78, -0.34)");
    singleNormalizeTest(vector5, "(0.42, 0.57, 0.71)");
  }

  /**
   * Tests whether the method correctly computes and returns the addition of this vector to another
   * vector
   */
  @Test
  void add() {
    Vector3D otherVector = new Vector3D(1.31, 3.11, 5.445);
    Assertions.assertEquals("(1.31, 3.11, 5.45)", vector1.add(otherVector).toString());
    Assertions.assertEquals("(0.09, 11.66, 12.32)", vector2.add(otherVector).toString());
    Assertions.assertEquals("(3.74, 5.19, 8.36)", vector3.add(otherVector).toString());
    Assertions.assertEquals("(-3.42, -4.00, 2.32)", vector4.add(otherVector).toString());
    Assertions.assertEquals("(4.31, 7.11, 10.45)", vector5.add(otherVector).toString());
    toStringHelper();
  }

  /**
   * Tests whether the method correctly computes and returns the multiply result of this vector to
   * another vector
   */
  @Test
  void multiply() {
    Assertions.assertEquals("(0.00, 0.00, 0.00)", vector1.multiply(333.123).toString());
    Assertions.assertEquals("(-150.06, 1051.65, 845.29)", vector2.multiply(123).toString());
    Assertions.assertEquals("(181.99, 156.00, 218.34)", vector3.multiply(75).toString());
    Assertions.assertEquals("(-4.73, -7.11, -3.12)", vector4.multiply(1).toString());
    Assertions.assertEquals("(-22.63, -30.18, -37.73)", vector5.multiply(-7.545).toString());
    toStringHelper();
  }

  /**
   * Tests whether the method correctly computes and returns the multiply result of this vector to
   * another vector.
   */
  @Test
  void dotProduct() {
    Vector3D otherVector = new Vector3D(1.31, 3.11, 5.445);
    Assertions.assertEquals(0.00, vector1.dotProduct(otherVector), 0.001);
    Assertions.assertEquals(62.412, vector2.dotProduct(otherVector), 0.001);
    Assertions.assertEquals(25.499, vector3.dotProduct(otherVector), 0.001);
    Assertions.assertEquals(-45.322, vector4.dotProduct(otherVector), 0.001);
    Assertions.assertEquals(43.595, vector5.dotProduct(otherVector), 0.001);
    Assertions.assertEquals("(1.31, 3.11, 5.45)", otherVector.toString());
    toStringHelper();
  }

  void angleTestHelper(Vector3D vector, Vector3D otherVector, double expected) {
    double angle;
    try {
      angle = vector.angleBetween(otherVector);
      assertEquals(expected, angle, 0.001);
    } catch (IllegalStateException e) {
      fail("An exception should not have been thrown");
    }
  }


  /**
   * Test whether if the method correctly computes and returns the angle in degrees between the two
   * vector objects
   */
  @Test
  void angleBetween() {
    Vector3D otherVector = new Vector3D(31.31, 34.11, 9.4777);
    double angle;
    try {
      angle = vector1.angleBetween(otherVector);
      fail("An exception should have been thrown");
    } catch (IllegalStateException e) {
    }
    angleTestHelper(vector2, otherVector, 52.358);
    angleTestHelper(vector3, otherVector, 31.336);
    angleTestHelper(vector4, otherVector, 167.913);
    angleTestHelper(vector5, otherVector, 33.784);
    toStringHelper();
  }
}