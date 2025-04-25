package de.relluem94.rellulib.vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorMathTest {


    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, VectorMath::new);
    }

    @Test
    public void testAddVector1Short() {
        Vector1<Short> v1 = new Vector1<>((short)1);
        Vector1<Short> v2 = new Vector1<>((short)2);
        Vector1<Short> result = VectorMath.add(v1, v2, Short.class);
        assertEquals((short)3, result.x());
    }

    @Test
    public void testAddVector1Integer() {
        Vector1<Integer> v1 = new Vector1<>(1);
        Vector1<Integer> v2 = new Vector1<>(2);
        Vector1<Integer> result = VectorMath.add(v1, v2, Integer.class);
        assertEquals(3, result.x());
    }

    @Test
    public void testAddVector2Float() {
        Vector2<Float> v1 = new Vector2<>(1.5f, 2.5f);
        Vector2<Float> v2 = new Vector2<>(2.5f, 1.5f);
        Vector2<Float> result = VectorMath.add(v1, v2, Float.class);
        assertEquals(4.0f, result.x());
        assertEquals(4.0f, result.y());
    }

    @Test
    public void testAddVector3Double() {
        Vector3<Double> v1 = new Vector3<>(1.1, 2.2, 3.3);
        Vector3<Double> v2 = new Vector3<>(0.9, 0.8, 0.7);
        Vector3<Double> result = VectorMath.add(v1, v2, Double.class);
        assertEquals(2.0, result.x());
        assertEquals(3.0, result.y());
        assertEquals(4.0, result.z());
    }

    @Test
    public void testAddVector4Mixed() {
        Vector4<Integer> v1 = new Vector4<>(1, 2, 3, 4);
        Vector3<Float> v2 = new Vector3<>(1.0f, 2.0f, 3.0f);
        Vector4<Integer> result = VectorMath.add(v1, v2, Integer.class);
        assertEquals(2, result.x());
        assertEquals(4, result.y());
        assertEquals(6, result.z());
        assertEquals(4, result.w());
    }

    @Test
    public void testAddVector5Long() {
        Vector5<Long> v1 = new Vector5<>(1L, 2L, 3L, 4L, 5L);
        Vector5<Long> v2 = new Vector5<>(5L, 4L, 3L, 2L, 1L);
        Vector5<Long> result = VectorMath.add(v1, v2, Long.class);
        assertEquals(6L, result.x());
        assertEquals(6L, result.y());
        assertEquals(6L, result.z());
        assertEquals(6L, result.w());
        assertEquals(6L, result.v());
    }

    @Test
    public void testAddVector2WithVector1_Integer() {
        Vector2<Integer> v1 = new Vector2<>(1, 2);
        Vector1<Integer> v2 = new Vector1<>(3);
        Vector2<Integer> result = VectorMath.add(v1, v2, Integer.class);
        assertEquals(new Vector2<>(4, 2), result);
    }

    @Test
    public void testAddVector3WithVector1_Float() {
        Vector3<Float> v1 = new Vector3<>(1.1f, 2.2f, 3.3f);
        Vector1<Float> v2 = new Vector1<>(4.4f);
        Vector3<Float> result = VectorMath.add(v1, v2, Float.class);
        assertEquals(new Vector3<>(5.5f, 2.2f, 3.3f), result);
    }

    @Test
    public void testAddVector3WithVector2_Double() {
        Vector3<Double> v1 = new Vector3<>(1.0, 2.0, 3.0);
        Vector2<Double> v2 = new Vector2<>(4.0, 5.0);
        Vector3<Double> result = VectorMath.add(v1, v2, Double.class);
        assertEquals(new Vector3<>(5.0, 7.0, 3.0), result);
    }

    @Test
    public void testAddVector4WithVector1_Long() {
        Vector4<Long> v1 = new Vector4<>(1L, 2L, 3L, 4L);
        Vector1<Long> v2 = new Vector1<>(5L);
        Vector4<Long> result = VectorMath.add(v1, v2, Long.class);
        assertEquals(new Vector4<>(6L, 2L, 3L, 4L), result);
    }

    @Test
    public void testAddVector4WithVector2_Short() {
        Vector4<Short> v1 = new Vector4<>((short)1, (short)2, (short)3, (short)4);
        Vector2<Short> v2 = new Vector2<>((short)5, (short)6);
        Vector4<Short> result = VectorMath.add(v1, v2, Short.class);
        assertEquals(new Vector4<>((short)6, (short)8, (short)3, (short)4), result);
    }

    @Test
    public void testAddVector4WithVector4_Float() {
        Vector4<Float> v1 = new Vector4<>(1.1f, 2.2f, 3.3f, 4.4f);
        Vector4<Float> v2 = new Vector4<>(5.5f, 6.6f, 7.7f, 8.8f);
        Vector4<Float> result = VectorMath.add(v1, v2, Float.class);
        assertEquals(6.6f, result.x(), 0.00001);
        assertEquals(8.8f, result.y(), 0.00001);
        assertEquals(11.0f, result.z(), 0.00001);
        assertEquals(13.2f, result.w(), 0.00001);
    }

    @Test
    public void testAddVector5WithVector1_Integer() {
        Vector5<Integer> v1 = new Vector5<>(1, 2, 3, 4, 5);
        Vector1<Integer> v2 = new Vector1<>(6);
        Vector5<Integer> result = VectorMath.add(v1, v2, Integer.class);
        assertEquals(new Vector5<>(7, 2, 3, 4, 5), result);
    }

    @Test
    public void testAddVector5WithVector2_Float() {
        Vector5<Float> v1 = new Vector5<>(1.1f, 2.2f, 3.3f, 4.4f, 5.5f);
        Vector2<Float> v2 = new Vector2<>(6.6f, 7.7f);
        Vector5<Float> result = VectorMath.add(v1, v2, Float.class);

        assertEquals(7.7f, result.x(), 0.00001);
        assertEquals(9.9f, result.y(), 0.00001);
        assertEquals(3.3f, result.z(), 0.00001);
        assertEquals(4.4f, result.w(), 0.00001);
        assertEquals(5.5f, result.v(), 0.00001);
    }

    @Test
    public void testAddVector5WithVector3_Float() {
        Vector5<Float> v1 = new Vector5<>(1.1f, 2.2f, 3.3f, 4.4f, 5.5f);
        Vector3<Float> v2 = new Vector3<>(6.6f, 7.7f, 8.8f);
        Vector5<Float> result = VectorMath.add(v1, v2, Float.class);

        assertEquals(7.7f, result.x(), 0.00001);
        assertEquals(9.9f, result.y(), 0.00001);
        assertEquals(12.1f, result.z(), 0.00001);
        assertEquals(4.4f, result.w(), 0.00001);
        assertEquals(5.5f, result.v(), 0.00001);
    }

    @Test
    public void testAddVector5WithVector4_Float() {
        Vector5<Float> v1 = new Vector5<>(1.1f, 2.2f, 3.3f, 4.4f, 5.5f);
        Vector4<Float> v2 = new Vector4<>(6.6f, 7.7f, 8.8f, 9.9f);
        Vector5<Float> result = VectorMath.add(v1, v2, Float.class);

        assertEquals(7.7f, result.x(), 0.00001);
        assertEquals(9.9f, result.y(), 0.00001);
        assertEquals(12.1f, result.z(), 0.00001);
        assertEquals(14.3f, result.w(), 0.00001);
        assertEquals(5.5f, result.v(), 0.00001);
    }

    @Test
    public void testAddVector1NotANumber() {
        Vector1<NotANumber> v1 = new Vector1<>(new NotANumber());
        Vector1<NotANumber> v2 = new Vector1<>(new NotANumber());
        Assertions.assertThrows(IllegalArgumentException.class, () -> VectorMath.add(v1, v2, NotANumber.class), "IllegalArgumentException error was expected");
    }

    private static class NotANumber extends Number{

        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }
}