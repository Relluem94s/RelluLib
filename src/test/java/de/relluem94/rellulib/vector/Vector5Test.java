package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector5Test {
    @Test
    public void testVector5ConstructorAndAccessorsWithInteger() {
        Vector5<Integer> vector = new Vector5<>(1, 2, 3, 4, 5);

        assertNotNull(vector, "Vector5 should not be null");
        assertEquals(1, vector.x(), "x should be 1");
        assertEquals(2, vector.y(), "y should be 2");
        assertEquals(3, vector.z(), "z should be 3");
        assertEquals(4, vector.w(), "w should be 4");
        assertEquals(5, vector.v(), "v should be 5");
    }

    @Test
    public void testVector5ConstructorAndAccessorsWithDouble() {
        Vector5<Double> vector = new Vector5<>(1.5, 2.5, 3.5, 4.5, 5.5);

        assertNotNull(vector, "Vector5 should not be null");
        assertEquals(1.5, vector.x(), "x should be 1.5");
        assertEquals(2.5, vector.y(), "y should be 2.5");
        assertEquals(3.5, vector.z(), "z should be 3.5");
        assertEquals(4.5, vector.w(), "w should be 4.5");
        assertEquals(5.5, vector.v(), "v should be 5.5");
    }

    @Test
    public void testToShortStringWithInteger() {
        Vector5<Integer> vector = new Vector5<>(1, 2, 3, 4, 5);
        String expected = "1, 2, 3, 4, 5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToShortStringWithDouble() {
        Vector5<Double> vector = new Vector5<>(1.5, 2.5, 3.5, 4.5, 5.5);
        String expected = "1.5, 2.5, 3.5, 4.5, 5.5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToStringWithInteger() {
        Vector5<Integer> vector = new Vector5<>(1, 2, 3, 4, 5);
        String expected = "x: 1, y: 2, z: 3, w: 4, v: 5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }

    @Test
    public void testToStringWithDouble() {
        Vector5<Double> vector = new Vector5<>(1.5, 2.5, 3.5, 4.5, 5.5);
        String expected = "x: 1.5, y: 2.5, z: 3.5, w: 4.5, v: 5.5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }
}