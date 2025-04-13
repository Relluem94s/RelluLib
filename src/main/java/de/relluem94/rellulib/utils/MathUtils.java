package de.relluem94.rellulib.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MathUtils {

    private MathUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param dividend int
     * @param divisor int
     * @return remainder
     */
    @Contract(pure = true)
    public static @NotNull Integer getRemainder(int dividend, int divisor) {
        return dividend % divisor;
    }

    /**
     *
     * @param a double
     * @param b double
     * @param eps double
     * @return boolean
     */
    public static boolean almostEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

    /**
     *
     * @param number int
     * @return boolean
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     *
     * @param number int
     * @return boolean
     */
    public static boolean isEven(int number) {
        return !isOdd(number);
    }
}
