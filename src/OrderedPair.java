public class OrderedPair {
    private final int power;
    private final double coefficient;

    public OrderedPair(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

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
