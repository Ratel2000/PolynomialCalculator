import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.round;

// Class representing a polynomial
public class Polynom {

    // List to store the ordered pairs of powers and coefficients
    private ArrayList<OrderedPair> _polynom = new ArrayList<>();

    // Constructor to create a polynomial from a list of ordered pairs
    private Polynom(ArrayList<OrderedPair> polynom) {
        for (OrderedPair op : polynom) {
            if (op.getCoefficient() != 0.0)
                this._polynom.add(new OrderedPair(op.getPower(), op.getCoefficient()));
        }
        sortByPower(_polynom);
        Collections.reverse(_polynom);
    }

    // Constructor to create a polynomial from arrays of powers and coefficients
    public Polynom(int[] powers, double[] coefficients) throws IllegalArgumentException {
        // Check if the arrays have the same length
        if (powers.length != coefficients.length)
            throw new IllegalArgumentException("The arrays are not the same length!!!");

        // Create ordered pairs and add them to the polynomial
        for (int i = 0; i < powers.length; i++) {
            if (coefficients[i] != 0)
                _polynom.add(new OrderedPair(powers[i], coefficients[i]));
        }
        // Sort the polynomial by power
        sortByPower(_polynom);
        Collections.reverse(_polynom);
        _polynom = this.roundCof().getPolynom();
    }

    // Getter method to retrieve the list of ordered pairs
    public ArrayList<OrderedPair> getPolynom() {
        return _polynom;
    }

    // Helper method to sort the polynomial by power
    private static void sortByPower(ArrayList<OrderedPair> polynom) {
        // Define a custom comparator using Comparator.comparing
        Comparator<OrderedPair> powerComparator = Comparator.comparing(OrderedPair::getPower);
        polynom.sort(powerComparator);
    }

    // Method to add two ordered pairs
    private OrderedPair plus(OrderedPair op1, OrderedPair op2) {
        return new OrderedPair(op1.getPower(), op1.getCoefficient() + op2.getCoefficient());
    }

    // Method to add two polynomials
    public Polynom plus(Polynom param) {
        ArrayList<OrderedPair> pSum = new ArrayList<>();

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
                pSum.add(new OrderedPair(plus(op1, op2)));
                i++;
                j++;
            } else if (op1.getPower() > op2.getPower()) {
                pSum.add(op1);
                i++;
            } else {
                pSum.add(op2);
                j++;
            }
        }

        // Add remaining ordered pairs from both polynomials
        while (i < smallerPolynom.size()) {
            pSum.add(smallerPolynom.get(i));
            i++;
        }
        while (j < biggerPolynom.size()) {
            pSum.add(biggerPolynom.get(j));
            j++;
        }

        return new Polynom(pSum).roundCof();
    }

    // Helper method to round the coefficient to a specified precision
    private Polynom roundCof() {
        ArrayList<OrderedPair> exactPolynomial = new ArrayList<>();
        for (OrderedPair op : _polynom) {
            // Number of decimal places to round to
            double roundingPrecision = 1e7;
            double rc = round(op.getCoefficient() * roundingPrecision) / roundingPrecision;
            exactPolynomial.add(new OrderedPair(op.getPower(), rc));
        }
        return new Polynom(exactPolynomial);

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

    // Method to compute the derivative of a polynomial
    public Polynom derivative() {
        ArrayList<OrderedPair> derivativeOfParam = new ArrayList<>();
        // Compute the derivative of each ordered pair and add to the result
        for (OrderedPair op : this.getPolynom()) {
            int power = op.getPower();
            double coefficient = op.getCoefficient();
            if (power != 0) {
                derivativeOfParam.add(new OrderedPair(power - 1, coefficient));
            }
        }
        return new Polynom(derivativeOfParam).roundCof();
    }

    // Method to convert the polynomial to a string representation
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getPolynom().isEmpty()) {
            return "0";
        }
        for (OrderedPair op : _polynom) {
            String term = "";
            if (op.getCoefficient() > 0)
                term = "+";
            if (op.getPower() == 0) {
                term += op.getCoefficient();
            } else if (op.getCoefficient() == 1.0 || op.getCoefficient() == -1.0) {
                if (op.getCoefficient() == -1.0)
                    term += "-";
                if (op.getPower() == 1) {
                    term += "x";
                } else {
                    term += "x^" + op.getPower();
                }
            } else if (op.getPower() == 1) {
                term += op.getCoefficient() + "x";
            } else {
                term += op.getCoefficient() + "x^" + op.getPower();
            }
            stringBuilder.append(term);
        }
        if (stringBuilder.toString().charAt(0) == '+') {
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
