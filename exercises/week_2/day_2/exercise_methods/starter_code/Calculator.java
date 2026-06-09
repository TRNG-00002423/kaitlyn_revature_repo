
/**
 * Week 2 Exercise — Calculator with static methods and overloads.
 *
 * Division by zero strategy (TODO — choose and implement):
 *   Option A: print error message and return Double.NaN
 *   Option B: return 0.0 and document why (not ideal for production)
 *
 * Compile: javac Calculator.java
 * Run:     java Calculator
 */
import java.util.Scanner;

public class Calculator {

    /**
     * Returns the sum of two doubles a and b.
     * 
     * @param a
     * @param b
     * @return a + b
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Returns the sum of three doubles a, b, and c. Overwrites add(double a, double
     * b).
     * 
     * @param a
     * @param b
     * @param c
     * @return a + b + c
     */
    public static double add(double a, double b, double c) {
        return a + b + c;
    }

    /**
     * Returns the result of a - b, where and b are doubles.
     * 
     * @param a
     * @param b
     * @return a - b
     */
    public static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Returns the product of double a and double b.
     * 
     * @param a
     * @param b
     * @return a * b
     */
    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Returns the quotient of a and b, or throws an ArithmeticException if b is
     * zero.
     * 
     * @param a
     * @param b
     * @return a / b
     * @throws ArithmeticException
     */
    public static double divide(double a, double b) throws ArithmeticException {
        if (b == 0.0) {
            throw new ArithmeticException("Cannot divide by 0");
        }
        return a / b;

    }

    /**
     * Returns base raised to the power of exp.
     * 
     * @param base
     * @param exp
     * @return base^exp
     */
    public static double pow(double base, double exp) {
        double result = base;
        for (int i = 1; i < exp; i++) {
            result *= base;
        }

        return result;
    }

    public static void section() {
        System.out.println("====================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a value for a: ");
        double a = Double.parseDouble(sc.next());
        System.out.print("Enter a value for b: ");
        double b = Double.parseDouble(sc.next());
        System.out.print("Enter a value for c: ");
        double c = Double.parseDouble(sc.next());

        section();
        System.out.println("a + b = " + add(a, b));

        section();
        System.out.println("a + b + c = " + add(a, b, c));

        section();
        System.out.println("a - b = " + subtract(a, b));

        section();
        System.out.println("a * b = " + multiply(a, b));

        section();
        try {
            System.out.println("a / b = " + divide(a, b));
        } catch (Exception e) {
            System.out.println("divide(a, b) threw " + e);
        }

        section();
        System.out.println("a ^ b = " + pow(a, b));

        sc.close();
    }
}