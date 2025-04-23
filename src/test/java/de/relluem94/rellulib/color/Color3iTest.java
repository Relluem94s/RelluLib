package de.relluem94.rellulib.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Color3iTest {
    private static Color3i color3i;

    @BeforeAll
    static void setup(){
        color3i = new Color3i(10, 20, 30);
    }

    @Test
    void getColor() {
        Assertions.assertEquals(color3i, color3i.getColor());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("r" + 10 + ", g" + 20 + ", b" + 30, color3i.toString());
    }

    @Test
    void toIntString() {
        Assertions.assertEquals(10 + "," + 20 + "," + 30, color3i.toIntString());
    }

    @Test
    void toArray() {
        Assertions.assertEquals(10, color3i.toArray()[0]);
        Assertions.assertEquals(20, color3i.toArray()[1]);
        Assertions.assertEquals(30, color3i.toArray()[2]);
    }

    @Test
    void testConstructFromColor(){
        Color3i newColor = new Color3i(color3i);
        Assertions.assertEquals(color3i.r, newColor.r);
        Assertions.assertEquals(color3i.g, newColor.g);
        Assertions.assertEquals(color3i.b, newColor.b);
    }
}