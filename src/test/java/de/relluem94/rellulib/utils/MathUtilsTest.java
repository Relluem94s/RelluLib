package de.relluem94.rellulib.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {
    @Test
    void testGetRemainder() {
        assertEquals(1, MathUtils.getRemainder(10, 3));
        assertEquals(-1, MathUtils.getRemainder(-10, 3));
        assertEquals(1, MathUtils.getRemainder(10, -3));
        assertEquals(0, MathUtils.getRemainder(10, 10));
        assertEquals(0, MathUtils.getRemainder(0, 10));
        assertThrows(ArithmeticException.class, () -> MathUtils.getRemainder(10, 0));
    }

    @Test
    void testAlmostEqual() {
        assertTrue(MathUtils.almostEqual(1.0000001, 1.0000002, 0.000001));
        assertFalse(MathUtils.almostEqual(1.0, 1.2, 0.0001));
        assertTrue(MathUtils.almostEqual(2.0, 2.0, 0.000001));
    }

    @Test
    void testIsOdd() {
        assertTrue(MathUtils.isOdd(1));
        assertTrue(MathUtils.isOdd(3));
        assertTrue(MathUtils.isOdd(-5));
        assertFalse(MathUtils.isOdd(2));
        assertFalse(MathUtils.isOdd(0));
        assertFalse(MathUtils.isOdd(-4));
    }

    @Test
    void testIsEven() {
        assertTrue(MathUtils.isEven(2));
        assertTrue(MathUtils.isEven(0));
        assertTrue(MathUtils.isEven(-4));
        assertFalse(MathUtils.isEven(1));
        assertFalse(MathUtils.isEven(3));
        assertFalse(MathUtils.isEven(-5));
    }
}