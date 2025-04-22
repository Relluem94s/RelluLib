package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector1Test {
    @Test
    public void testVector1ConstructorAndAccessorsWithInteger() {
        Vector1<Integer> vector = new Vector1<>(1);

        assertNotNull(vector, "Vector1 should not be null");
        assertEquals(1, vector.x(), "x should be 1");
    }

    @Test
    public void testVector1ConstructorAndAccessorsWithDouble() {
        Vector1<Double> vector = new Vector1<>(1.5);

        assertNotNull(vector, "Vector1 should not be null");
        assertEquals(1.5, vector.x(), "x should be 1.5");
    }

    @Test
    public void testToShortStringWithInteger() {
        Vector1<Integer> vector = new Vector1<>(1);
        String expected = "1";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToShortStringWithDouble() {
        Vector1<Double> vector = new Vector1<>(1.5);
        String expected = "1.5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToStringWithInteger() {
        Vector1<Integer> vector = new Vector1<>(1);
        String expected = "x: 1";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }

    @Test
    public void testToStringWithDouble() {
        Vector1<Double> vector = new Vector1<>(1.5);
        String expected = "x: 1.5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }
}