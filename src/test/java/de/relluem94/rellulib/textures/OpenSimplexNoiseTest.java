package de.relluem94.rellulib.textures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

class OpenSimplexNoiseTest {
    private OpenSimplexNoise noise;
    private static final double DELTA = 1e-10;

    protected static final byte[] gradients2D = new byte[]{
            5, 2, 2, 5,
            -5, 2, -2, 5,
            5, -2, 2, -5,
            -5, -2, -2, -5,};

    protected static final byte[] gradients3D = new byte[]{
            -11, 4, 4, -4, 11, 4, -4, 4, 11,
            11, 4, 4, 4, 11, 4, 4, 4, 11,
            -11, -4, 4, -4, -11, 4, -4, -4, 11,
            11, -4, 4, 4, -11, 4, 4, -4, 11,
            -11, 4, -4, -4, 11, -4, -4, 4, -11,
            11, 4, -4, 4, 11, -4, 4, 4, -11,
            -11, -4, -4, -4, -11, -4, -4, -4, -11,
            11, -4, -4, 4, -11, -4, 4, -4, -11,};

    protected static final byte[] gradients4D = new byte[]{
            3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3,
            -3, 1, 1, 1, -1, 3, 1, 1, -1, 1, 3, 1, -1, 1, 1, 3,
            3, -1, 1, 1, 1, -3, 1, 1, 1, -1, 3, 1, 1, -1, 1, 3,
            -3, -1, 1, 1, -1, -3, 1, 1, -1, -1, 3, 1, -1, -1, 1, 3,
            3, 1, -1, 1, 1, 3, -1, 1, 1, 1, -3, 1, 1, 1, -1, 3,
            -3, 1, -1, 1, -1, 3, -1, 1, -1, 1, -3, 1, -1, 1, -1, 3,
            3, -1, -1, 1, 1, -3, -1, 1, 1, -1, -3, 1, 1, -1, -1, 3,
            -3, -1, -1, 1, -1, -3, -1, 1, -1, -1, -3, 1, -1, -1, -1, 3,
            3, 1, 1, -1, 1, 3, 1, -1, 1, 1, 3, -1, 1, 1, 1, -3,
            -3, 1, 1, -1, -1, 3, 1, -1, -1, 1, 3, -1, -1, 1, 1, -3,
            3, -1, 1, -1, 1, -3, 1, -1, 1, -1, 3, -1, 1, -1, 1, -3,
            -3, -1, 1, -1, -1, -3, 1, -1, -1, -1, 3, -1, -1, -1, 1, -3,
            3, 1, -1, -1, 1, 3, -1, -1, 1, 1, -3, -1, 1, 1, -1, -3,
            -3, 1, -1, -1, -1, 3, -1, -1, -1, 1, -3, -1, -1, 1, -1, -3,
            3, -1, -1, -1, 1, -3, -1, -1, 1, -1, -3, -1, 1, -1, -1, -3,
            -3, -1, -1, -1, -1, -3, -1, -1, -1, -1, -3, -1, -1, -1, -1, -3
    };


    @BeforeEach
    void setUp() throws Exception {
        noise = new OpenSimplexNoise(12345L);
    }


    @Test
    void testDefaultConstructor() {
        OpenSimplexNoise defaultNoise = new OpenSimplexNoise();
        assertNotNull(defaultNoise.perm, "Permutation array should be initialized");
        assertNotNull(defaultNoise.permGradIndex3D, "3D gradient index array should be initialized");
        assertEquals(256, defaultNoise.perm.length, "Permutation array should have 256 elements");
        assertEquals(256, defaultNoise.permGradIndex3D.length, "3D gradient index array should have 256 elements");
    }

    @Test
    void testSeedConstructor() {
        OpenSimplexNoise seededNoise = new OpenSimplexNoise(12345L);
        assertNotNull(seededNoise.perm, "Permutation array should be initialized");
        assertNotNull(seededNoise.permGradIndex3D, "3D gradient index array should be initialized");
        assertEquals(256, seededNoise.perm.length, "Permutation array should have 256 elements");
        assertEquals(256, seededNoise.permGradIndex3D.length, "3D gradient index array should have 256 elements");

        OpenSimplexNoise sameSeedNoise = new OpenSimplexNoise(12345L);
        for (int i = 0; i < 256; i++) {
            assertEquals(seededNoise.perm[i], sameSeedNoise.perm[i], "Permutations should be identical for the same seed");
            assertEquals(seededNoise.permGradIndex3D[i], sameSeedNoise.permGradIndex3D[i], "3D gradient indices should be identical");
        }
    }

    @Test
    void testPermConstructor() {
        short[] perm = new short[256];
        for (short i = 0; i < 256; i++) {
            perm[i] = i;
        }
        OpenSimplexNoise permNoise = new OpenSimplexNoise(perm);
        assertSame(perm, permNoise.perm, "Permutation array should be the one provided");
        assertNotNull(permNoise.permGradIndex3D, "3D gradient index array should be initialized");
        assertEquals(256, permNoise.permGradIndex3D.length, "3D gradient index array should have 256 elements");

        for (int i = 0; i < 256; i++) {
            int expectedIndex = ((perm[i] & 0xFF) % 24) * 3;
            if (expectedIndex != permNoise.permGradIndex3D[i]) {
                System.out.println("Mismatch at i=" + i + ", perm[i]=" + perm[i] + ", expectedIndex=" + expectedIndex + ", actual=" + permNoise.permGradIndex3D[i]);
            }
            assertEquals(expectedIndex, permNoise.permGradIndex3D[i], "3D gradient index should be correctly computed at i=" + i);
        }
    }

    @Test
    void testPermutationValidity() {
        short[] perm = noise.perm;
        boolean[] seen = new boolean[256];
        for (short value : perm) {
            assertTrue(value >= 0 && value < 256, "Permutation values should be in range [0, 255]");
            assertFalse(seen[value], "Permutation should contain unique values");
            seen[value] = true;
        }
    }

    @Test
    void testEval2D() {
        double value1 = noise.eval(0.5, 0.5);
        assertTrue(value1 >= -1.0 && value1 <= 1.0, "2D noise output should be in range [-1, 1]");
        double value2 = noise.eval(0.5, 0.5);
        assertEquals(value1, value2, DELTA, "2D noise should be consistent for same input");
        double value3 = noise.eval(1.5, 1.5);
        assertNotEquals(value1, value3, "2D noise should vary with different inputs");
    }

    @Test
    void testEval3D() {
        double value1 = noise.eval(0.5, 0.5, 0.5);
        assertTrue(value1 >= -1.0 && value1 <= 1.0, "3D noise output should be in range [-1, 1]");
        double value2 = noise.eval(0.5, 0.5, 0.5);
        assertEquals(value1, value2, DELTA, "3D noise should be consistent for same input");
        double value3 = noise.eval(1.5, 1.5, 1.5);
        assertNotEquals(value1, value3, "3D noise should vary with different inputs");
    }

    @Test
    void testEval4D() {
        double value1 = noise.eval(0.5, 0.5, 0.5, 0.5);
        assertTrue(value1 >= -1.0 && value1 <= 1.0, "4D noise output should be in range [-1, 1]");
        double value2 = noise.eval(0.5, 0.5, 0.5, 0.5);
        assertEquals(value1, value2, DELTA, "4D noise should be consistent for same input");
        double value3 = noise.eval(1.5, 1.5, 1.5, 1.5);
        assertNotEquals(value1, value3, "4D noise should vary with different inputs");
    }

    @Test
    void testExtrapolate2D() throws Exception {
        Method extrapolate2D = OpenSimplexNoise.class.getDeclaredMethod("extrapolate", int.class, int.class, double.class, double.class);
        extrapolate2D.setAccessible(true);

        int xsb = 0, ysb = 0;
        double dx = 0.5, dy = 0.5;
        double result = (double) extrapolate2D.invoke(noise, xsb, ysb, dx, dy);
        assertTrue(Double.isFinite(result), "2D extrapolate should produce finite values");

        int index = noise.perm[(noise.perm[xsb & 0xFF] + ysb) & 0xFF] & 0x0E;
        double expected = gradients2D[index] * dx + gradients2D[index + 1] * dy;
        assertEquals(expected, result, DELTA, "2D extrapolate should compute correct gradient dot product");
    }

    @Test
    void testExtrapolate3D() throws Exception {
        Method extrapolate3D = OpenSimplexNoise.class.getDeclaredMethod("extrapolate", int.class, int.class, int.class, double.class, double.class, double.class);
        extrapolate3D.setAccessible(true);

        int xsb = 0, ysb = 0, zsb = 0;
        double dx = 0.5, dy = 0.5, dz = 0.5;
        double result = (double) extrapolate3D.invoke(noise, xsb, ysb, zsb, dx, dy, dz);
        assertTrue(Double.isFinite(result), "3D extrapolate should produce finite values");

        int index = noise.permGradIndex3D[(noise.perm[(noise.perm[xsb & 0xFF] + ysb) & 0xFF] + zsb) & 0xFF];
        double expected = gradients3D[index] * dx + gradients3D[index + 1] * dy + gradients3D[index + 2] * dz;
        assertEquals(expected, result, DELTA, "3D extrapolate should compute correct gradient dot product");
    }

    @Test
    void testExtrapolate4D() throws Exception {
        Method extrapolate4D = OpenSimplexNoise.class.getDeclaredMethod("extrapolate", int.class, int.class, int.class, int.class, double.class, double.class, double.class, double.class);
        extrapolate4D.setAccessible(true);

        int xsb = 0, ysb = 0, zsb = 0, wsb = 0;
        double dx = 0.5, dy = 0.5, dz = 0.5, dw = 0.5;
        double result = (double) extrapolate4D.invoke(noise, xsb, ysb, zsb, wsb, dx, dy, dz, dw);
        assertTrue(Double.isFinite(result), "4D extrapolate should produce finite values");

        int index = noise.perm[(noise.perm[(noise.perm[(noise.perm[xsb & 0xFF] + ysb) & 0xFF] + zsb) & 0xFF] + wsb) & 0xFF] & 0xFC;
        double expected = gradients4D[index] * dx + gradients4D[index + 1] * dy + gradients4D[index + 2] * dz + gradients4D[index + 3] * dw;
        assertEquals(expected, result, DELTA, "4D extrapolate should compute correct gradient dot product");
    }

    @Test
    void testEdgeCases() {
        double largeValue = 1e6;
        double value2D = noise.eval(largeValue, largeValue);
        assertTrue(Double.isFinite(value2D) && value2D >= -1.0 && value2D <= 1.0, "2D noise should handle large coordinates");
        double value3D = noise.eval(largeValue, largeValue, largeValue);
        assertTrue(Double.isFinite(value3D) && value3D >= -1.0 && value3D <= 1.0, "3D noise should handle large coordinates");
        double value4D = noise.eval(largeValue, largeValue, largeValue, largeValue);
        assertTrue(Double.isFinite(value4D) && value4D >= -1.0 && value4D <= 1.0, "4D noise should handle large coordinates");

        assertTrue(Double.isFinite(noise.eval(0.0, 0.0)), "2D noise should handle zero coordinates");
        assertTrue(Double.isFinite(noise.eval(0.0, 0.0, 0.0)), "3D noise should handle zero coordinates");
        assertTrue(Double.isFinite(noise.eval(0.0, 0.0, 0.0, 0.0)), "4D noise should handle zero coordinates");
    }

    @Test
    void testEval2D_InSumGreaterThan1() {
        double value = noise.eval(10.1, 10.1);
        assertTrue(value >= -1 && value <= 1, "2D eval should be in range for inSum > 1");
    }

    @Test
    void testEval2D_ZinsNotClosest() {
        double value = noise.eval(0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_InSumLessEqual1() {
        double value = noise.eval(0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_InSumBetween1And2() {
        double value = noise.eval(0.7, 0.7, 0.6);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_InSumGreaterEqual2() {
        double value = noise.eval(1.0, 1.0, 1.0);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_InSumLessEqual1() {
        double value = noise.eval(0.05, 0.05, 0.05, 0.05);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_InSumBetween1And3() {
        double value = noise.eval(0.8, 0.8, 0.2, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_InSumGreaterEqual3() {
        double value = noise.eval(1.1, 1.1, 1.1, 1.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_InSumGreaterEqual3_Branch() {
        double value = noise.eval(1.0, 1.0, 1.0, 0.5);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_Branch_cAnd2_EqualsZero() {
        double value = noise.eval(0.9, 0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Branch_cAnd1_EqualsZero() {
        double value = noise.eval(0.1, 0.3, 0.6); // c ergibt & 0x01 == 0
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_SpecificBranch_ScoreComparison() {
        double value = noise.eval(0.4, 0.4, 0.7); // x=y=0.4, z=0.7
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_ExtraVertex1Contributes() {
        double value = noise.eval(0.7, 0.7, 0.7);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_OnePointEachSide() {
        double value = noise.eval(0.4, 0.6, 0.5);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_BothPointsOnFarSide() {
        double value = noise.eval(0.6, 0.6, 0.6);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_p3GreaterThan1() {
        double value = noise.eval(0.1, 0.7, 0.6);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Branch_cAnd2_NotZero() {
        double value = noise.eval(0.6, 0.7, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Branch_cAnd1_NotZero() {
        double value = noise.eval(0.7, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Branch_cAnd4_NotZero() {
        double value = noise.eval(0.1, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_ScoreZinsCondition() {
        double value = noise.eval(0.1, 0.4, 0.3);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_ScoreZinsAlternativeBranch() {
        double value = noise.eval(0.5, 0.2, 0.4);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn0_Positive() {
        double value = noise.eval(0.3, 0.3, 0.3);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn1_Positive() {
        double value = noise.eval(0.8, 0.3, 0.3);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn2_Positive() {
        double value = noise.eval(0.3, 0.8, 0.3);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval2D_ZinsGreaterThanXinsOrYins() {
        double value = noise.eval(0.2, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval2D_ZinsLessThanXinsOrYins() {
        double value = noise.eval(0.8, 0.7);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval2D_Attn0Positive() {
        double value = noise.eval(0.05, 0.05);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval2D_BothClosestVerticesFallback() {
        double value = noise.eval(0.5, 0.5);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_BranchCAnd2EqualsZero_Y() {
        double value = noise.eval(0.8, 0.1, 0.05);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn0Positive() {
        double value = noise.eval(0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn1Positive() {
        double value = noise.eval(0.95, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_Attn2Positive() {
        double value = noise.eval(0.1, 0.95, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_ScoreZinsCondition_A() {
        double value = noise.eval(0.2, 0.5, 0.4);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_WinsLessThanScore() {
        double value = noise.eval(1.0, 1.0, 0.6);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval3D_cAnd2FallbackElse() {
        double value = noise.eval(0.5, 0.5, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_ElseIfC2And2Set() {
        double value = noise.eval(0.1, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_ElseIfC2And4Set() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_ElseC2NoneSet() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C01Zero() {
        double value = noise.eval(0.1, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C02Zero_C01Set() {
        double value = noise.eval(0.9, 0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C02Set() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C04Zero_C03Set() {
        double value = noise.eval(0.6, 0.6, 0.6, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C04Set() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C08Zero() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.3);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_BothPointsOnSmallerSide_C08Set() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2And1Set() {
        double value = noise.eval(0.9, 0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2And2Set() {
        double value = noise.eval(0.1, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2And4Set() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2NoneSet() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C01Zero() {
        double value = noise.eval(0.2, 0.8, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C01Set() {
        double value = noise.eval(0.8, 0.2, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C02Zero_C01Set() {
        double value = noise.eval(0.8, 0.1, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C02Zero_C01Zero() {
        double value = noise.eval(0.1, 0.1, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C02Set() {
        double value = noise.eval(0.1, 0.8, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C04Zero_C03Set() {
        double value = noise.eval(0.6, 0.6, 0.1, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C04Zero_C03NotSet() {
        double value = noise.eval(0.2, 0.2, 0.1, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C04Set() {
        double value = noise.eval(0.2, 0.2, 0.9, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C08Zero() {
        double value = noise.eval(0.2, 0.2, 0.2, 0.4);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_SmallerSide_C08Set() {
        double value = noise.eval(0.2, 0.2, 0.2, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C01Zero() {
        double value = noise.eval(0.1, 0.8, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C01Set() {
        double value = noise.eval(0.8, 0.1, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C02Zero_C01Set() {
        double value = noise.eval(0.8, 0.1, 0.8, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C02Set() {
        double value = noise.eval(0.8, 0.8, 0.8, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C04Zero_C03Set() {
        double value = noise.eval(0.6, 0.6, 0.1, 0.8);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C04Set() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C08Zero() {
        double value = noise.eval(0.2, 0.2, 0.2, 0.4);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C08Set() {
        double value = noise.eval(0.2, 0.2, 0.2, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C01Set() {
        double value = noise.eval(0.9, 0.2, 0.2, 0.2);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C02Set() {
        double value = noise.eval(0.2, 0.9, 0.2, 0.2);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C04Set() {
        double value = noise.eval(0.2, 0.2, 0.9, 0.2);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_NoneSet() {
        double value = noise.eval(0.2, 0.2, 0.2, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C01NotSet() {
        double value = noise.eval(0.1, 0.9, 0.9, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C02Set_C01Set() {
        double value = noise.eval(0.9, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C02Set_C01NotSet() {
        double value = noise.eval(0.1, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C02NotSet() {
        double value = noise.eval(0.9, 0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C04Set_C03Set() {
        double value = noise.eval(0.6, 0.6, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C04Set_C03NotSet() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C04NotSet() {
        double value = noise.eval(0.9, 0.9, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C1_C08NotSet() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C01NotSet() {
        double value = noise.eval(0.1, 0.9, 0.9, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C02NotSet() {
        double value = noise.eval(0.9, 0.1, 0.9, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C04NotSet() {
        double value = noise.eval(0.9, 0.9, 0.1, 0.9);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSide_C2_C08NotSet() {
        double value = noise.eval(0.9, 0.9, 0.9, 0.1);
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C01Set_C2_C01Clear() {
        double value = noise.eval(0.9, 0.1, 0.1, 0.1); // c1: 0x01, c2: 0x00
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C01Clear_C2_C02Clear() {
        double value = noise.eval(0.1, 0.9, 0.1, 0.1); // c1: 0x02, c2: 0x00
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C02Set_C01Set_C2_C04Clear() {
        double value = noise.eval(0.9, 0.9, 0.1, 0.1); // c1: 0x03, c2: 0x00
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C04Set_C03Clear_C2_C08Clear() {
        double value = noise.eval(0.1, 0.1, 0.9, 0.1); // c1: 0x04, c2: 0x00
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C04Set_C03Set() {
        double value = noise.eval(0.6, 0.6, 0.9, 0.1); // c1: 0x07
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C1_C08Set() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.9); // c1: 0x08
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2_C01Set() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.2); // c2: 0x01 → triggers no `(c2 & 0x01) == 0`
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2_C02Set() {
        double value = noise.eval(0.1, 0.2, 0.1, 0.1); // c2: 0x02
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2_C04Set() {
        double value = noise.eval(0.1, 0.1, 0.2, 0.1); // c2: 0x04
        assertTrue(Double.isFinite(value));
    }

    @Test
    void testEval4D_MixedSides_C2_C08Set() {
        double value = noise.eval(0.1, 0.1, 0.1, 0.8); // c2: 0x08
        assertTrue(Double.isFinite(value));
    }


















}