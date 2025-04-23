package de.relluem94.rellulib.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Color4iTest {
    private static Color4i color4i;

    @BeforeAll
    static void setup(){
        color4i = new Color4i(10, 20, 30, 40);
    }

    @Test
    void getColor() {
        Assertions.assertEquals(color4i, color4i.getColor());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("r" + 10 + ", g" + 20 + ", b" + 30 + ", a" + 40, color4i.toString());
    }

    @Test
    void toIntString() {
        Assertions.assertEquals(10 + "," + 20 + "," + 30 + "," + 40, color4i.toIntString());
    }

    @Test
    void toArray() {
        Assertions.assertEquals(10, color4i.toArray()[0]);
        Assertions.assertEquals(20, color4i.toArray()[1]);
        Assertions.assertEquals(30, color4i.toArray()[2]);
        Assertions.assertEquals(40, color4i.toArray()[3]);
    }

    @Test
    void testConstructFromColor(){
        Color4i newColor = new Color4i(color4i);
        Assertions.assertEquals(color4i.r, newColor.r);
        Assertions.assertEquals(color4i.g, newColor.g);
        Assertions.assertEquals(color4i.b, newColor.b);
        Assertions.assertEquals(color4i.a, newColor.a);
    }
}