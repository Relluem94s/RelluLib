package de.relluem94.rellulib.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Color4fTest {
    private static Color4f color4f;

    @BeforeAll
    static void setup(){
        color4f = new Color4f(10, 20, 30, 40);
    }

    @Test
    void getColor() {
        Assertions.assertEquals(color4f, color4f.getColor());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("r" + 10f + ", g" + 20f + ", b" + 30f + ", a" + 40f, color4f.toString());
    }

    @Test
    void toIntString() {
        Assertions.assertEquals(10f + "," + 20f + "," + 30f + "," + 40f, color4f.toIntString());
    }

    @Test
    void toArray() {
        Assertions.assertEquals(10, color4f.toArray()[0]);
        Assertions.assertEquals(20, color4f.toArray()[1]);
        Assertions.assertEquals(30, color4f.toArray()[2]);
        Assertions.assertEquals(40, color4f.toArray()[3]);
    }

    @Test
    void testConstructFromColor(){
        Color4f newColor = new Color4f(color4f);
        Assertions.assertEquals(color4f.r, newColor.r);
        Assertions.assertEquals(color4f.g, newColor.g);
        Assertions.assertEquals(color4f.b, newColor.b);
        Assertions.assertEquals(color4f.a, newColor.a);
    }
}