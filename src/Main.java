import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize Scanner for user input.
        Scanner scanner = new Scanner(System.in);

        // Get the size of the first array from the user.
        int size1 = getSizeFromUser(scanner);

        // Get values for the first int and double arrays from the user.
        int[] expArr1 = getExpArrayValuesFromUser("first int", size1, scanner);
        double[] coffsArr1 = getCoffArrayValuesFromUser("first double", size1, scanner);

        // Get the size of the second array from the user.
        int size2 = getSizeFromUser(scanner);
        // Get values for the second int and double arrays from the user.
        int[] expArr2 = getExpArrayValuesFromUser("second int", size2, scanner);
        double[] coffsArr2 = getCoffArrayValuesFromUser("second double", size2, scanner);

        // Display the entered arrays.
        displayArray("First int", expArr1);
        displayArray("First double", coffsArr1);
        displayArray("Second int", expArr2);
        displayArray("Second double", coffsArr2);

        // Close the scanner to prevent resource leaks.
        scanner.close();

        // Create polynomials using the entered arrays.
        Polynom polynom1 = new Polynom(expArr1, coffsArr1);
        Polynom polynom2 = new Polynom(expArr2, coffsArr2);

        // Display polynomials and perform operations.
        System.out.println("p(x) = " + polynom1);
        System.out.println("q(x) = " + polynom2);
        System.out.println("Precision of operations is 7 digits after the decimal point");
        System.out.println("(p+q)(x) = " + polynom1 + " + " + polynom2 + " = " + polynom1.plus(polynom2));
        System.out.println("(p-q)(x) = " + polynom1 + " - " + polynom2 + " = " + polynom1.minus(polynom2));
        System.out.println("p'(x) = " + polynom1.derivative());
        System.out.println("q'(x) = " + polynom2.derivative());
        System.out.println("is p(x) = q(x) ? " + polynom1.equals(polynom2));
    }

    // Helper method to get the size of the array from the user.
    private static int getSizeFromUser(Scanner scanner) {
        System.out.print("Enter the size of the array: ");
        return scanner.nextInt();
    }

    // Helper method to get values for an int array from the user.
    private static int[] getExpArrayValuesFromUser(String arrayType, int size, Scanner scanner) {
        System.out.println("Enter values for the " + arrayType + "exponents array:");
        int[] array = new int[size];
        // Get values for the array from the user.
        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for index " + i + ": ");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    // Helper method to get values for a double array from the user.
    private static double[] getCoffArrayValuesFromUser(String arrayType, int size, Scanner scanner) {
        System.out.println("Enter values for the " + arrayType + "coefficients array:");
        double[] array = new double[size];
        // Get values for the array from the user.
        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for index " + i + ": ");
            array[i] = scanner.nextDouble();
        }
        return array;
    }

    // Helper method to display an int array.
    private static void displayArray(String arrayType, int[] array) {
        System.out.println(arrayType + " array: " + java.util.Arrays.toString(array));
    }

    // Helper method to display a double array.
    private static void displayArray(String arrayType, double[] array) {
        System.out.println(arrayType + " array: " + java.util.Arrays.toString(array));
    }
}
