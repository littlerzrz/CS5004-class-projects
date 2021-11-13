package project02;

import java.util.NoSuchElementException;

/**
 * This is a class that represents a Rectangle in the 2 dimensional system
 */
public class Rectangle {

  private int x;
  private int y;
  private int w;
  private int h;

  /**
   * Constructs a Rectangle object and initialize it with its x,y, w, h
   *
   * @param x x coordinate of the rectangle
   * @param y y coordinate of the rectangle
   * @param w width of the rectangle
   * @param h height of the rectangle
   */
  public Rectangle(int x, int y, int w, int h) throws IllegalArgumentException {
    this.x = x;
    this.y = y;
    if (h <= 0 || w <= 0) {
      throw new IllegalArgumentException("Width and height cannot be negative!");
    } else {
      this.w = w;
      this.h = h;
    }
  }

  /**
   * A method that determines the overlap of the rectangle and another rectangle. This method should
   * return true if this rectangle overlaps with other, false otherwise. Rectangles that touch each
   * other are not considered to be overlapping.
   *
   * @param other another rectangle
   * @return whether the two rectangles overlap with each other or not
   */
  public Boolean overlap(Rectangle other) {
    // determines the center differences between the two rectangles
    int centerXDiff = Math.abs(x + w/2 - (other.x + other.w/2));
    int centerYDiff = Math.abs(y + h/2 - (other.y + other.h/2));
    int halfWidthSum = (other.w + w) / 2;
    int halfHeightSum = (other.h + h) / 2;
    // if the center differences are both smaller than the half width and half height sum, then the rectangles overlap
    return centerXDiff < halfWidthSum && centerYDiff < halfHeightSum;
  }

  /**
   * A method that determines the intersection part of this rectangle and another rectangle. This
   * method should return a Rectangle object that represents the overlap of the two rectangles. If
   * no intersection exists, it should throw a NoSuchElementException with a helpful message.
   *
   * @param other another rectangle
   * @return a new rectangle representing the overlap of the two rectangles
   */
  public Rectangle intersection(Rectangle other) throws NoSuchElementException {
    if (!this.overlap(other)) {
      throw new NoSuchElementException("There is no intersection between!");
    }
    int resX = Math.max(x, other.x);
    int resW = Math.abs(Math.min(getBottomRightX(), getBottomRightX(other)) - resX);

    int resY = Math.max(y, other.y);
    int resH = Math.abs(Math.min(getTopLeftY(), getTopLeftY(other)) - resY);

    return new Rectangle(resX, resY, resW, resH);
  }

  /**
   * Helper to get the bottom right x coordinate of the rectangle
   *
   * @param rec rectangle
   * @return the bottom right x coordinate of the rectangle
   */
  private int getBottomRightX(Rectangle rec) {
    return rec.x + rec.w;
  }

  /**
   * Overload to get the bottom right x of this rectangle
   *
   * @return the bottom right x of this rectangle
   */
  private int getBottomRightX() {
    return getBottomRightX(this);
  }

  /**
   * Helper to get the top left y coordinate of the rectangle
   *
   * @param rec rectangle
   * @return the top left y coordinate of the rectangle
   */
  private int getTopLeftY(Rectangle rec) {
    return rec.y + rec.h;
  }

  /**
   * Overload to get the top left y of this rectangle
   *
   * @return the top left y of this rectangle
   */
  private int getTopLeftY() {
    return getTopLeftY(this);
  }

  /**
   * A method that determines the union part of this rectangle and another rectangle. This method
   * returns a Rectangle object that represents the union of this rectangle and the other rectangle.
   * The union is the smallest rectangle that contains both rectangles. Note that unlike the
   * intersection, the union always exists.
   *
   * @param other another rectangle
   * @return a new rectangle representing the union of the two rectangles
   */
  public Rectangle union(Rectangle other) {
    int bottomRightX = getBottomRightX(this);
    int topLeftY = getTopLeftY(this);
    int otherBR = getBottomRightX(other);
    int otherTL = getTopLeftY(other);

    int newX = Math.min(x, other.x);
    int newY = Math.min(y, other.y);
    int newBottomRight = Math.max(bottomRightX, otherBR);
    int newTopLeft = Math.max(topLeftY, otherTL);
    int newW = Math.abs(newBottomRight - newX);
    int newH = Math.abs(newTopLeft - newY);
    return new Rectangle(newX, newY, newW, newH);
  }

  /**
   * Helper to get the area
   *
   * @return the area of the rectangle
   */
  private int area() {
    return w * h;
  }

  /**
   * A method that determines the area of the intersection rectangle divided by the area of the
   * union rectangle. If this value cannot be obtained, it should throw a NoSuchElementException
   * with a helpful message.
   *
   * @param other another rectangle
   * @return the area of the intersection rectangle divided by the area of the union rectangle
   */
  public float intersectionOverUnion(Rectangle other) throws NoSuchElementException {
    if (!this.overlap(other)) {
      throw new NoSuchElementException("There is no intersection between!");
    }
    int intersectionRec = intersection(other).area();
    int unionRec = union(other).area();
    return (float)intersectionRec / unionRec;
  }

  /**
   * A method that formats the object to a string
   *
   * @return a formatted string
   */
  @Override
  public String toString() {
    return
        "x:" + x +
            ", y:" + y +
            ", w:" + w +
            ", h:" + h;
  }
}
