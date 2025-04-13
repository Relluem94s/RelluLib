package de.relluem94.rellulib.utils;

import de.relluem94.rellulib.color.Color3f;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.color.Color4i;
import de.relluem94.rellulib.vector.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeUtilsTest {

    @Test
    public void testToVector1f_Valid() {
        Vector1<Float> result = TypeUtils.toVector1f("1.5");
        assertEquals(1.5f, result.x(), 0.00001);
    }

    @Test
    public void testToVector1f_Invalid() {
        Vector1<Float> result = TypeUtils.toVector1f("1.5,2.5");
        assertEquals(0.0f, result.x(), 0.00001);
    }

    @Test
    public void testToVector2f_Valid() {
        Vector2<Float> result = TypeUtils.toVector2f("1.1, 2.2");
        assertEquals(1.1f, result.x(), 0.00001);
        assertEquals(2.2f, result.y(), 0.00001);
    }

    @Test
    public void testToVector3i_Valid() {
        Vector3<Integer> result = TypeUtils.toVector3i("1,2,3");
        assertEquals(1, result.x().intValue());
        assertEquals(2, result.y().intValue());
        assertEquals(3, result.z().intValue());
    }

    @Test
    public void testToVector4i_Invalid() {
        Vector4<Integer> result = TypeUtils.toVector4i("1,2,3");
        assertEquals(0, result.x().intValue());
        assertEquals(0, result.y().intValue());
        assertEquals(0, result.z().intValue());
        assertEquals(0, result.w().intValue());
    }

    @Test
    public void testToVector5f_Valid() {
        Vector5<Float> result = TypeUtils.toVector5f("1.0,2.0,3.0,4.0,5.0");
        assertEquals(1f, result.x(), 0.00001);
        assertEquals(2f, result.y(), 0.00001);
        assertEquals(3f, result.z(), 0.00001);
        assertEquals(4f, result.w(), 0.00001);
        assertEquals(5f, result.v(), 0.00001);
    }

    @Test
    public void testToVector5i_Invalid() {
        Vector5<Integer> result = TypeUtils.toVector5i("1,2,3");
        assertEquals(0, result.x().intValue());
        assertEquals(0, result.y().intValue());
        assertEquals(0, result.z().intValue());
        assertEquals(0, result.w().intValue());
        assertEquals(0, result.v().intValue());
    }

    @Test
    public void testToVector3f_Valid() {
        Vector3<Float> vector = TypeUtils.toVector3f("1.1, 2.2, 3.3");
        assertEquals(1.1f, vector.x());
        assertEquals(2.2f, vector.y());
        assertEquals(3.3f, vector.z());
    }

    @Test
    public void testToVector3f_Invalid() {
        Vector3<Float> vector = TypeUtils.toVector3f("1.1, 2.2");
        assertEquals(0f, vector.x());
        assertEquals(0f, vector.y());
        assertEquals(0f, vector.z());
    }

    @Test
    public void testToVector4f_Valid() {
        Vector4<Float> vector = TypeUtils.toVector4f("1.1,2.2,3.3,4.4");
        assertEquals(1.1f, vector.x());
        assertEquals(2.2f, vector.y());
        assertEquals(3.3f, vector.z());
        assertEquals(4.4f, vector.w());
    }

    @Test
    public void testToVector4f_Invalid() {
        Vector4<Float> vector = TypeUtils.toVector4f("1.1,2.2,3.3");
        assertEquals(0f, vector.x());
        assertEquals(0f, vector.y());
        assertEquals(0f, vector.z());
        assertEquals(0f, vector.w());
    }

    @Test
    public void testToVector1i_Valid() {
        Vector1<Integer> vector = TypeUtils.toVector1i("42");
        assertEquals(42, vector.x().intValue());
    }

    @Test
    public void testToVector1i_Invalid() {
        Vector1<Integer> vector = TypeUtils.toVector1i("");
        assertEquals(0, vector.x().intValue());
    }

    @Test
    public void testToVector2i_Valid() {
        Vector2<Integer> vector = TypeUtils.toVector2i("1,2");
        assertEquals(1, vector.x().intValue());
        assertEquals(2, vector.y().intValue());
    }

    @Test
    public void testToVector2i_Invalid() {
        Vector2<Integer> vector = TypeUtils.toVector2i("1");
        assertEquals(0, vector.x().intValue());
        assertEquals(0, vector.y().intValue());
    }


    @Test
    public void testToColor3fFromString_Valid() {
        Color3f color = TypeUtils.toColor3f("0.1,0.2,0.3");
        assertEquals(0.1f, color.r);
        assertEquals(0.2f, color.g);
        assertEquals(0.3f, color.b);
    }

    @Test
    public void testToColor3fFromString_Invalid() {
        Color3f color = TypeUtils.toColor3f("invalid");
        assertEquals(0.0f, color.r);
        assertEquals(0.0f, color.g);
        assertEquals(0.0f, color.b);
    }

    @Test
    public void testToColor3fFromInt() {
        Color3f color = TypeUtils.toColor3f(0x112233);
        assertEquals(0x11, (int) color.r);
        assertEquals(0x22, (int) color.g);
        assertEquals(0x33, (int) color.b);
    }

    @Test
    public void testToColor4fFromInt() {
        Color4f color = TypeUtils.toColor4f(0x44112233);
        assertEquals(0x11, (int) color.r);
        assertEquals(0x22, (int) color.g);
        assertEquals(0x33, (int) color.b);
        assertEquals(0x44, (int) color.a);
    }

    @Test
    public void testToColor3iFromString_Valid() {
        Color3i color = TypeUtils.toColor3i("1,2,3");
        assertEquals(1, color.r);
        assertEquals(2, color.g);
        assertEquals(3, color.b);
    }

    @Test
    public void testToColor3iFromString_Invalid() {
        Color3i color = TypeUtils.toColor3i("1,2");
        assertEquals(0, color.r);
        assertEquals(0, color.g);
        assertEquals(0, color.b);
    }

    @Test
    public void testToColor4fFromString_Valid() {
        Color4f color = TypeUtils.toColor4f("0.1,0.2,0.3,0.4");
        assertEquals(0.1f, color.r);
        assertEquals(0.2f, color.g);
        assertEquals(0.3f, color.b);
        assertEquals(0.4f, color.a);
    }

    @Test
    public void testToColor4fFromString_Invalid() {
        Color4f color = TypeUtils.toColor4f("1,2,3");
        assertEquals(0f, color.r);
        assertEquals(0f, color.g);
        assertEquals(0f, color.b);
        assertEquals(0f, color.a);
    }

    @Test
    public void testToColor4iFromString_Valid() {
        Color4i color = TypeUtils.toColor4i("1,2,3,4");
        assertEquals(1, color.r);
        assertEquals(2, color.g);
        assertEquals(3, color.b);
        assertEquals(4, color.a);
    }

    @Test
    public void testToColor4iFromString_Invalid() {
        Color4i color = TypeUtils.toColor4i("1,2,3");
        assertEquals(0, color.r);
        assertEquals(0, color.g);
        assertEquals(0, color.b);
        assertEquals(0, color.a);
    }
    @Test
    public void testLongToKB() {
        String result = TypeUtils.longToKB(1500);
        assertTrue(result.startsWith("1.5"));
        assertTrue(result.endsWith("kb"));
    }

    @Test
    public void testLongToSeconds() {
        String result = TypeUtils.longToSeconds(3000);
        assertTrue(result.startsWith("3"));
    }

    @Test
    public void testLongToMin() {
        String result = TypeUtils.longToMin(120000);
        assertTrue(result.startsWith("2"));
    }

    @Test
    public void testLongToKB_Zero() {
        String result = TypeUtils.longToKB(0);
        assertTrue(result.startsWith("0"));
        assertTrue(result.endsWith("kb"));
    }


    @Test
    public void testToBooleanTrueVariants() {
        assertTrue(TypeUtils.toBoolean("true"));
        assertTrue(TypeUtils.toBoolean(" true "));
        assertTrue(TypeUtils.toBoolean("\ntrue\r"));
        assertTrue(TypeUtils.toBoolean("\tTrUe"));
    }

    @Test
    public void testToBooleanFalseVariants() {
        assertFalse(TypeUtils.toBoolean("false"));
        assertFalse(TypeUtils.toBoolean("yes"));
        assertFalse(TypeUtils.toBoolean(""));
        assertFalse(TypeUtils.toBoolean(null));
    }
}
