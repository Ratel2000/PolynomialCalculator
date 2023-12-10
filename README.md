# Polynomial Operations in Java

![Java](https://img.shields.io/badge/language-Java-orange.svg)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

This Java project provides a versatile `Polynom` class for performing operations on polynomials. The class supports addition, subtraction, derivative calculation, and provides precision handling for floating-point coefficients.

## Features

- Polynomial addition
- Polynomial subtraction
- Polynomial derivative calculation
- String representation of polynomials
- Precision handling for floating-point coefficients

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Ratel2000/PolynomialCalculator.git
   
Open the project in your preferred Java development environment.

Use the Polynom class in your own code to perform polynomial operations.
### Example Usage
```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Example usage of the Polynom class
        ArrayList<OrderedPair> polynomList = new ArrayList<>();
        polynomList.add(new OrderedPair(2, 3.0));
        polynomList.add(new OrderedPair(1, -1.5));
        polynomList.add(new OrderedPair(0, 4.2));

        Polynom polynomial = new Polynom(polynomList);

        System.out.println("Original Polynomial: " + polynomial);
        System.out.println("Derivative: " + Polynom.derivative(polynomial));
    }
}
```
## **Contributing**

If you'd like to contribute to this project, please follow these guidelines:

- Fork the repository.
- Create a new branch for your feature: `git checkout -b feature-name`
- Commit your changes: `git commit -m 'Add some feature'`
- Push to the branch: `git push origin feature-name`
- Create a pull request.
  ## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

