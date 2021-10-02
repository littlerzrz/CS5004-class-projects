package project01;

/**
 * Represents a vector in 3D space that has three components: x, y, z; The project01.Vector3D class involves
 * different operations;
 */

public class Vector3D {

  private double x;
  private double y;
  private double z;

  /**
   * Constructs a project01.Vector3D object and initializes it to the x, y, and z component
   *
   * @param x the x coordinate of this vector
   * @param y the y coordinate of this vector
   * @param z the z coordinate of this vector
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Get the x coordinate of the vector
   *
   * @return the x coordinate of the vector
   */
  public double getX() {
    return x;
  }

  /**
   * Get the y coordinate of the vector
   *
   * @return the y coordinate of the vector
   */
  public double getY() {
    return y;
  }

  /**
   * Get the z coordinate of the vector
   *
   * @return the z coordinate of the vector
   */
  public double getZ() {
    return z;
  }

  /**
   * Returns a string that describes this vector.
   *
   * @return a formatted string
   */
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f, %.2f)", x, y, z);
  }

  /**
   * Computes and returns the magnitude of the vector
   *
   * @return the magnitude
   */
  public double getMagnitude() {
    double squareSum = x * x + y * y + z * z;
    double magnitude = Math.sqrt(squareSum);
    return magnitude;
  }

  /**
   * Helper method for the normalize method, computes and returns the coordinate divided by the
   * magnitude
   *
   * @param cord coordinate for the normalization
   * @return the calculated new coordinate
   * @throws IllegalStateException when this operation cannot be completed
   */
  private double getNewCoordinate(double cord) throws IllegalStateException{
    double magnitude = getMagnitude();
    if (cord == 0) {
      throw new IllegalStateException("Normalization cannot be completed, try a different object");
    }
    return cord / magnitude;
  }

  /**
   * Computes and returns a normalized version of the vector
   * @return the normalized version of the vector
   */
  public Vector3D normalize() {
    double newX = getNewCoordinate(x);
    double newY = getNewCoordinate(y);
    double newZ = getNewCoordinate(z);
    return new Vector3D(newX, newY, newZ);
  }

  /**
   * Computes and returns result of adding this vector to another vector
   * @param otherVector another vector
   * @return result of adding this vector to another vector
   */
  public Vector3D add(Vector3D otherVector) {
    double otherX = otherVector.getX(), otherY = otherVector.getY(), otherZ = otherVector.getZ();
    double newX = x + otherX, newY = y + otherY, newZ = z+ otherZ;
    return new Vector3D(newX, newY, newZ);
  }

  /**
   * Computes and returns the result of multiplying this vector by a constant
   * @param factor a constant
   * @return the result of multiplying this vector by a constant
   */
  public Vector3D multiply(double factor) {
    return new Vector3D(factor * x, factor * y, factor * z);
  }

  /**
   * Computes and returns the dot product of this vector and another vector
   * @param otherVector another vector object for computation
   * @return returns the dot product of this vector and another vector
   */
  public double dotProduct(Vector3D otherVector) {
    double otherX = otherVector.getX(), otherY = otherVector.getY(), otherZ = otherVector.getZ();
    return otherX * x + otherY * y + otherZ * z;
  }

  /**
   * Computes and returns the angle between two vectors in degrees
   * @param otherVector another vector object
   * @return returns the angle between two vectors in degrees
   * @throws IllegalStateException when this operation cannot be completed.
   */
  public double angleBetween(Vector3D otherVector) throws IllegalStateException {
    double product = dotProduct(otherVector);
    double otherMag = otherVector.getMagnitude();
    double mag = getMagnitude();

    if (mag == 0 || otherMag == 0) {
      throw new IllegalStateException("Something went wrong, try again.");
    }
    double cosine = product / (mag * otherMag);
    return Math.toDegrees(Math.acos(cosine));
  }
}
