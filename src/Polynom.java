import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;

// Class representing a polynomial
public class Polynom {

    // Small constant for handling precision errors
    private static final double EPSILON = 1e-10;

    // Number of decimal places to round to
    private static final int DECIMAL_PRECISION = 10;

    // List to store the ordered pairs of powers and coefficients
    private ArrayList<OrderedPair> _polynom = new ArrayList<>();

    // Constructor to create a polynomial from a list of ordered pairs
    public Polynom(ArrayList<OrderedPair> polynom) {
        _polynom = polynom;
    }

    // Getter method to retrieve the list of ordered pairs
    public ArrayList<OrderedPair> getPolynom() {
        return _polynom;
    }

    // Constructor to create a polynomial from arrays of powers and coefficients
    public Polynom(int[] powers, double[] coefficients) {
        // Check if the arrays have the same length
        if (powers.length != coefficients.length)
            throw new IllegalArgumentException("The arrays are not the same length!!!");

        // Create ordered pairs and add them to the polynomial
        for (int i = 0; i < powers.length; i++) {
            _polynom.add(new OrderedPair(powers[i], coefficients[i]));
        }

        // Sort the polynomial by power
        sortByPower(_polynom);
    }

    // Helper method to sort the polynomial by power
    private static void sortByPower(ArrayList<OrderedPair> polynom) {
        // Define a custom comparator using Comparator.comparing
        Comparator<OrderedPair> powerComparator = Comparator.comparing(OrderedPair::getPower);
        polynom.sort(powerComparator);
    }

    // Method to add two polynomials
    public Polynom plus(Polynom param) {
        ArrayList<OrderedPair> p = new ArrayList<>();

        // Separate the polynomials into smaller and bigger based on size
        ArrayList<OrderedPair> smallerPolynom;
        ArrayList<OrderedPair> biggerPolynom;
        if (this.getPolynom().size() <= param.getPolynom().size()) {
            smallerPolynom = this.getPolynom();
            biggerPolynom = param.getPolynom();
        } else {
            smallerPolynom = param.getPolynom();
            biggerPolynom = this.getPolynom();
        }

        int i = 0, j = 0;
        // Merge the ordered pairs while summing coefficients with the same power
        while (i < smallerPolynom.size() && j < biggerPolynom.size()) {
            OrderedPair op1 = smallerPolynom.get(i);
            OrderedPair op2 = biggerPolynom.get(j);

            if (op1.getPower() == op2.getPower()) {
                OrderedPair sum = plus(op1, op2);
                double roundedCoefficient = roundCoefficient(sum.getCoefficient()); // Adjust the decimal places
                if (Math.abs(roundedCoefficient) > Polynom.EPSILON) {
                    p.add(new OrderedPair(op1.getPower(), roundedCoefficient));
                }
                i++;
                j++;
            } else if (op1.getPower() < op2.getPower()) {
                p.add(op1);
                i++;
            } else {
                p.add(op2);
                j++;
            }
        }

        // Add remaining ordered pairs from both polynomials
        while (i < smallerPolynom.size()) {
            p.add(smallerPolynom.get(i));
            i++;
        }
        while (j < biggerPolynom.size()) {
            p.add(biggerPolynom.get(j));
            j++;
        }

        return new Polynom(p);
    }

    // Helper method to round the coefficient to a specified precision
    private static double roundCoefficient(double coefficient) {
        BigDecimal bd = new BigDecimal(Double.toString(coefficient));
        bd = bd.setScale(Polynom.DECIMAL_PRECISION, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // Method to subtract one polynomial from another
    public Polynom minus(Polynom param) {
        // Negate the coefficients of the second polynomial and add them
        ArrayList<OrderedPair> negatedCoefficients = new ArrayList<>();
        for (OrderedPair op : param.getPolynom()) {
            negatedCoefficients.add(new OrderedPair(op.getPower(), -op.getCoefficient()));
        }

        return this.plus(new Polynom(negatedCoefficients));
    }

    // Method to add two ordered pairs
    public OrderedPair plus(OrderedPair op1, OrderedPair op2) {
        return new OrderedPair(op1.getPower(), op1.getCoefficient() + op2.getCoefficient());
    }

    // Method to compute the derivative of a polynomial
    public static Polynom derivative(Polynom param) {
        ArrayList<OrderedPair> derivativeOfParam = new ArrayList<>();
        // Compute the derivative of each ordered pair and add to the result
        for (OrderedPair op : param.getPolynom()) {
            int power = op.getPower();
            double coefficient = op.getCoefficient();
            if (coefficient * power != 0) {
                double roundedCoefficient = roundCoefficient(coefficient * power);
                derivativeOfParam.add(new OrderedPair(power - 1, roundedCoefficient));
            }
        }
        return new Polynom(derivativeOfParam);
    }

    // Method to convert the polynomial to a string representation
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getPolynom().isEmpty()) {
            return "0";
        }
        for (OrderedPair op : _polynom) {
            String term = "+";
            if (op.getPower() == 1) {
                term += op.getCoefficient() + "x";
            } else if (op.getPower() == 0) {
                term += op.getCoefficient();
            } else {
                term += op.getCoefficient() + "x^" + op.getPower();
            }
            stringBuilder.insert(0, term);
        }
        if (stringBuilder.length() > 0 && stringBuilder.toString().charAt(0) == '+') {
            stringBuilder.deleteCharAt(0);
        }

        return stringBuilder.toString();
    }

    // Method to check if two polynomials are equal
    @Override
    public boolean equals(Object o) {
        if (o instanceof Polynom) {
            Polynom polynom = (Polynom) o;
            return this.minus(polynom).getPolynom().isEmpty();
        }
        return false;
    }
}
