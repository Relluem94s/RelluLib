package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {
    @Test
    public void testVector3ConstructorAndAccessorsWithInteger() {
        Vector3<Integer> vector = new Vector3<>(1, 2, 3);

        assertNotNull(vector, "Vector3 should not be null");
        assertEquals(1, vector.x(), "x should be 1");
        assertEquals(2, vector.y(), "y should be 2");
        assertEquals(3, vector.z(), "z should be 3");
    }

    @Test
    public void testVector3ConstructorAndAccessorsWithDouble() {
        Vector3<Double> vector = new Vector3<>(1.5, 2.5, 3.5);

        assertNotNull(vector, "Vector3 should not be null");
        assertEquals(1.5, vector.x(), "x should be 1.5");
        assertEquals(2.5, vector.y(), "y should be 2.5");
        assertEquals(3.5, vector.z(), "z should be 3.5");
    }

    @Test
    public void testToShortStringWithInteger() {
        Vector3<Integer> vector = new Vector3<>(1, 2, 3);
        String expected = "1, 2, 3";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToShortStringWithDouble() {
        Vector3<Double> vector = new Vector3<>(1.5, 2.5, 3.5);
        String expected = "1.5, 2.5, 3.5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToStringWithInteger() {
        Vector3<Integer> vector = new Vector3<>(1, 2, 3);
        String expected = "x: 1, y: 2, z: 3";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }

    @Test
    public void testToStringWithDouble() {
        Vector3<Double> vector = new Vector3<>(1.5, 2.5, 3.5);
        String expected = "x: 1.5, y: 2.5, z: 3.5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }
}