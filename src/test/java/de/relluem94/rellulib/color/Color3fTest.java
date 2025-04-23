package de.relluem94.rellulib.color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Color3fTest {

    private static Color3f color3f;

    @BeforeAll
    static void setup(){
        color3f = new Color3f(10, 20, 30);
    }

    @Test
    void getColor() {
        Assertions.assertEquals(color3f, color3f.getColor());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("r" + 10f + ", g" + 20f + ", b" + 30f, color3f.toString());
    }

    @Test
    void toIntString() {
        Assertions.assertEquals(10f + "," + 20f + "," + 30f, color3f.toIntString());
    }

    @Test
    void toArray() {
        Assertions.assertEquals(10, color3f.toArray()[0]);
        Assertions.assertEquals(20, color3f.toArray()[1]);
        Assertions.assertEquals(30, color3f.toArray()[2]);
    }

    @Test
    void testConstructFromColor(){
        Color3f newColor = new Color3f(color3f);
        Assertions.assertEquals(color3f.r, newColor.r);
        Assertions.assertEquals(color3f.g, newColor.g);
        Assertions.assertEquals(color3f.b, newColor.b);
    }
}