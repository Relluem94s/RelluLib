package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Vector4Test {

    @Test
    public void testVector4ConstructorAndAccessorsWithInteger() {
        Vector4<Integer> vector = new Vector4<>(1, 2, 3, 4);

        assertNotNull(vector, "Vector4 should not be null");
        assertEquals(1, vector.x(), "x should be 1");
        assertEquals(2, vector.y(), "y should be 2");
        assertEquals(3, vector.z(), "z should be 3");
        assertEquals(4, vector.w(), "w should be 4");
    }

    @Test
    public void testVector4ConstructorAndAccessorsWithDouble() {
        Vector4<Double> vector = new Vector4<>(1.5, 2.5, 3.5, 4.5);

        assertNotNull(vector, "Vector4 should not be null");
        assertEquals(1.5, vector.x(), "x should be 1.5");
        assertEquals(2.5, vector.y(), "y should be 2.5");
        assertEquals(3.5, vector.z(), "z should be 3.5");
        assertEquals(4.5, vector.w(), "w should be 4.5");
    }

    @Test
    public void testToShortStringWithInteger() {
        Vector4<Integer> vector = new Vector4<>(1, 2, 3, 4);
        String expected = "1, 2, 3, 4";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToShortStringWithDouble() {
        Vector4<Double> vector = new Vector4<>(1.5, 2.5, 3.5, 4.5);
        String expected = "1.5, 2.5, 3.5, 4.5";
        assertEquals(expected, vector.toShortString(), "toShortString should return correct format");
    }

    @Test
    public void testToStringWithInteger() {
        Vector4<Integer> vector = new Vector4<>(1, 2, 3, 4);
        String expected = "x: 1, y: 2, z: 3, w: 4";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }

    @Test
    public void testToStringWithDouble() {
        Vector4<Double> vector = new Vector4<>(1.5, 2.5, 3.5, 4.5);
        String expected = "x: 1.5, y: 2.5, z: 3.5, w: 4.5";
        assertEquals(expected, vector.toString(), "toString should return correct format");
    }
}