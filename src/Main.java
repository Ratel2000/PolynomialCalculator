import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = getSizeFromUser(scanner);

        int[] expArr1 = getExpArrayValuesFromUser("first int", size, scanner);
        double[] coffsArr1 = getCoffArrayValuesFromUser("first double", size, scanner);

        int[] expArr2 = getExpArrayValuesFromUser("second int", size, scanner);
        double[] coffsArr2 = getCoffArrayValuesFromUser("second double", size, scanner);

        displayArray("First int", expArr1);
        displayArray("First double", coffsArr1);
        displayArray("Second int", expArr2);
        displayArray("Second double", coffsArr2);

        scanner.close();

        Polynom polynom1 = new Polynom(expArr1, coffsArr1);
        Polynom polynom2 = new Polynom(expArr2, coffsArr2);

        System.out.println("p(x) = " + polynom1);
        System.out.println("q(x) = " + polynom2);
        System.out.println("Precision of operations is 7 digits after the decimal point");
        System.out.println("(p+q)(x) = " + polynom1 + " + " + polynom2 + " = " + polynom1.plus(polynom2));
        System.out.println("(p-q)(x) = " + polynom1 + " - " + polynom2 + " = " + polynom1.minus(polynom2));
        System.out.println("p'(x) = " + Polynom.derivative(polynom1));
        System.out.println("q'(x) = " + Polynom.derivative(polynom2));
        System.out.println("is p(x) = q(x) ? " + polynom1.equals(polynom2));
    }

    private static int getSizeFromUser(Scanner scanner) {
        System.out.print("Enter the size of the arrays: ");
        return scanner.nextInt();
    }

    private static int[] getExpArrayValuesFromUser(String arrayType, int size, Scanner scanner) {
        System.out.println("Enter values for the " + arrayType + " array:");
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for index " + i + ": ");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static double[] getCoffArrayValuesFromUser(String arrayType, int size, Scanner scanner) {
        System.out.println("Enter values for the " + arrayType + " array:");
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for index " + i + ": ");
            array[i] = scanner.nextDouble();
        }
        return array;
    }

    private static void displayArray(String arrayType, int[] array) {
        System.out.println(arrayType + " array: " + java.util.Arrays.toString(array));
    }

    private static void displayArray(String arrayType, double[] array) {
        System.out.println(arrayType + " array: " + java.util.Arrays.toString(array));
    }
}
