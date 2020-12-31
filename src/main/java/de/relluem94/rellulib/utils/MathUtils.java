package de.relluem94.rellulib.utils;

public class MathUtils {

    /**
     *
     * @param dividend
     * @param divisor
     * @return remainder
     */
    public static Integer getRemainder(int dividend, int divisor) {
        return dividend % divisor;
    }

    /**
     *
     * @param a
     * @param b
     * @param eps
     * @return
     */
    public static boolean almostEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

    /**
     *
     * @param number
     * @return boolean
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     *
     * @param number
     * @return boolean
     */
    public static boolean isEven(int number) {
        return !isOdd(number);
    }
}
