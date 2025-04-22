package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    @Test
    public void testVector2ConstructorAndAccessorsWithInteger() {
        Vector2<Integer> vector = new Vector2<>(1, 2);

        assertNotNull(vector, "Vector2 should not be null");
        assertEquals(1, vector.x(), "x should be 1");
        assertEquals(2, vector.y(), "y should be 2");
    }

    @Test
    public void testVector2ConstructorAndAccessorsWithDouble() {
        Vector2<Double> vector = new Vector2<>(1.5, 2.5);

        assertNotNull(vector, "Vector2 should not be null");
        assertEquals(1.5, vector.x(), "x should be 1.5");
        assertEquals(2.5, vector.y(), "y should be 2.5");
    }

    @Test
    public void testToShortStringWithInteger() {
        Vector2<Integer> vector = new Vector2<>(1, 2);
        String expected = "1, 2";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToShortStringWithDouble() {
        Vector2<Double> vector = new Vector2<>(1.5, 2.5);
        String expected = "1.5, 2.5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToStringWithInteger() {
        Vector2<Integer> vector = new Vector2<>(1, 2);
        String expected = "x: 1, y: 2";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }

    @Test
    public void testToStringWithDouble() {
        Vector2<Double> vector = new Vector2<>(1.5, 2.5);
        String expected = "x: 1.5, y: 2.5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }
}