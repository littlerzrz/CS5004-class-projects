package polynomial;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PolynomialImpl implements Polynomial {

  private final String TERM_REGEX = "([+-]?[\\d]*)([x]?)\\^?(\\d*)";

  private TreeMap<Integer, Integer> terms = new TreeMap<>(
      Comparator.reverseOrder());

  /**
   * Constructs the PolynomialImpl
   */
  public PolynomialImpl() {
  }

  /**
   * Constructs the PolynomialImpl with String inputs
   *
   * @param str the string that needs to be turned to polynomial
   * @throws IllegalArgumentException if the string is not in the correct format with terms
   *                                  separated by space
   */
  public PolynomialImpl(String str) throws IllegalArgumentException {
    String[] strArr = str.split(" ");
    for (String termStr : strArr) {
      if (!termStr.matches(TERM_REGEX)) {
        throw new IllegalArgumentException(
            "Invalid input string! Terms need to be separated by space and negative power is not allowed!");
      }
      addTermWithString(termStr);
    }
  }

  /**
   * Parses the term string and add it to the list
   *
   * @param termStr term string
   */
  private void addTermWithString(String termStr) {
    final Pattern pattern = Pattern.compile(TERM_REGEX);
    final Matcher matcher = pattern.matcher(termStr);
    if (matcher.find()) {
      String coeStr = matcher.group(1);
      String xStr = matcher.group(2);
      String powStr = matcher.group(3);

      coeStr = coeStr.matches("[+-]") ? coeStr + "1" : !coeStr.equals("") ? coeStr : "0";
      powStr = !powStr.equals("") ? powStr : xStr.equals("x") ? "1" : "0";

      int coefficient = Integer.parseInt(coeStr);
      int power = Integer.parseInt(powStr);
      addTerm(coefficient, power);
    }
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other.getClass() != this.getClass()) {
      throw new IllegalArgumentException("Make sure the polynomial is the same concrete type!");
    }
    PolynomialImpl result = new PolynomialImpl();
    terms.forEach((power, coefficient) -> result.addTerm(coefficient, power));
    ((PolynomialImpl) other).terms.forEach(
        (power, coefficient) -> result.addTerm(coefficient, power));
    return result;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative!");
    }
    if (terms.containsKey(power)) {
      int newCo = terms.get(power) + coefficient;
      if (newCo == 0) {
        terms.remove(power);
      }
      terms.put(power,newCo);
    } else {
      terms.put(power, coefficient);
    }
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (poly.getClass() != this.getClass()) {
      return false;
    }
    return toString().equals(poly.toString());
  }

  @Override
  public double evaluate(double x) {
    double result = 0;
    for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
      Integer power = entry.getKey();
      Integer coefficient = entry.getValue();
      result += coefficient * Math.pow(x, power);
    }
    return result;
  }

  @Override
  public int getCoefficient(int power) {
    if (!terms.containsKey(power)) {
      return 0;
    }
    return terms.get(power);
  }

  @Override
  public int getDegree() {
    if (terms.isEmpty()) {
      return 0;
    }
    return terms.firstKey();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (terms.isEmpty()) {
      return "0";
    }
    terms.forEach(((power, coefficient) -> stringBuilder.append(termToString(coefficient, power))));
    String result = stringBuilder.toString();
    result = result.charAt(0) == '+' ? result.substring(1) : result;
    return result.substring(0, result.length() - 1);
  }

  /**
   * Converts a single term with coefficient and power into string
   *
   * @param coefficient the coefficient of the term
   * @param power       the power of the term
   * @return the formatted single term string
   */
  private String termToString(int coefficient, int power) {
    StringBuilder stringBuilder = new StringBuilder();
    if (coefficient > 0) {
      stringBuilder.append("+");
    }
    stringBuilder.append(coefficient);
    if (power > 0) {
      stringBuilder.append("x^").append(power);
    }
    stringBuilder.append(" ");
    return stringBuilder.toString();
  }
}
