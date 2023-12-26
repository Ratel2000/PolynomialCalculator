public class OrderedPair {
    // Data members
    private final int power;
    private final double coefficient;

    // Constructor
    public OrderedPair(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    // Copy constructor
    public OrderedPair(OrderedPair op) {
        power = op.power;
        coefficient = op.coefficient;
    }

    public int getPower() {
        return power;
    }

    public double getCoefficient() {
        return coefficient;
    }

}
