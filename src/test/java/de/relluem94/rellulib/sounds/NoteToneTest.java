package de.relluem94.rellulib.sounds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteToneTest {
    @Test
    void testNext() {
        assertEquals(NoteTone.C_2, NoteTone.NONE.next());
        assertEquals(NoteTone.C_SHARP_2, NoteTone.C_2.next());
        assertEquals(NoteTone.D_2, NoteTone.C_SHARP_2.next());
        assertEquals(NoteTone.NONE, NoteTone.B_6.next());
    }

    @Test
    void testGetTone() {
        assertEquals(0F, NoteTone.getTone(NoteTone.NONE), 0.01F);
        assertEquals(65.41F, NoteTone.getTone(NoteTone.C_2), 0.01F);
        assertEquals(69.30F, NoteTone.getTone(NoteTone.C_SHARP_2), 0.01F);
        assertEquals(73.42F, NoteTone.getTone(NoteTone.D_2), 0.01F);
        assertEquals(77.78F, NoteTone.getTone(NoteTone.D_SHARP_2), 0.01F);
        assertEquals(82.41F, NoteTone.getTone(NoteTone.E_2), 0.01F);
        assertEquals(87.31F, NoteTone.getTone(NoteTone.F_2), 0.01F);
        assertEquals(92.50F, NoteTone.getTone(NoteTone.F_SHARP_2), 0.01F);
        assertEquals(98.00F, NoteTone.getTone(NoteTone.G_2), 0.01F);
        assertEquals(103.83F, NoteTone.getTone(NoteTone.G_SHARP_2), 0.01F);
        assertEquals(110.00F, NoteTone.getTone(NoteTone.A_2), 0.01F);
        assertEquals(116.54F, NoteTone.getTone(NoteTone.A_SHARP_2), 0.01F);
        assertEquals(123.47F, NoteTone.getTone(NoteTone.B_2), 0.01F);
        assertEquals(130.81F, NoteTone.getTone(NoteTone.C_3), 0.01F);
        assertEquals(138.59F, NoteTone.getTone(NoteTone.C_SHARP_3), 0.01F);
        assertEquals(146.83F, NoteTone.getTone(NoteTone.D_3), 0.01F);
        assertEquals(155.56F, NoteTone.getTone(NoteTone.D_SHARP_3), 0.01F);
        assertEquals(164.81F, NoteTone.getTone(NoteTone.E_3), 0.01F);
        assertEquals(174.61F, NoteTone.getTone(NoteTone.F_3), 0.01F);
        assertEquals(185.00F, NoteTone.getTone(NoteTone.F_SHARP_3), 0.01F);
        assertEquals(196.00F, NoteTone.getTone(NoteTone.G_3), 0.01F);
        assertEquals(207.65F, NoteTone.getTone(NoteTone.G_SHARP_3), 0.01F);
        assertEquals(220.00F, NoteTone.getTone(NoteTone.A_3), 0.01F);
        assertEquals(233.08F, NoteTone.getTone(NoteTone.A_SHARP_3), 0.01F);
        assertEquals(246.94F, NoteTone.getTone(NoteTone.B_3), 0.01F);
        assertEquals(261.63F, NoteTone.getTone(NoteTone.C_4), 0.01F);
        assertEquals(277.18F, NoteTone.getTone(NoteTone.C_SHARP_4), 0.01F);
        assertEquals(293.66F, NoteTone.getTone(NoteTone.D_4), 0.01F);
        assertEquals(311.13F, NoteTone.getTone(NoteTone.D_SHARP_4), 0.01F);
        assertEquals(329.63F, NoteTone.getTone(NoteTone.E_4), 0.01F);
        assertEquals(349.23F, NoteTone.getTone(NoteTone.F_4), 0.01F);
        assertEquals(369.99F, NoteTone.getTone(NoteTone.F_SHARP_4), 0.01F);
        assertEquals(392.00F, NoteTone.getTone(NoteTone.G_4), 0.01F);
        assertEquals(415.30F, NoteTone.getTone(NoteTone.G_SHARP_4), 0.01F);
        assertEquals(440.00F, NoteTone.getTone(NoteTone.A_4), 0.01F);
        assertEquals(466.16F, NoteTone.getTone(NoteTone.A_SHARP_4), 0.01F);
        assertEquals(493.88F, NoteTone.getTone(NoteTone.B_4), 0.01F);
        assertEquals(523.25F, NoteTone.getTone(NoteTone.C_5), 0.01F);
        assertEquals(554.37F, NoteTone.getTone(NoteTone.C_SHARP_5), 0.01F);
        assertEquals(587.33F, NoteTone.getTone(NoteTone.D_5), 0.01F);
        assertEquals(622.25F, NoteTone.getTone(NoteTone.D_SHARP_5), 0.01F);
        assertEquals(659.25F, NoteTone.getTone(NoteTone.E_5), 0.01F);
        assertEquals(698.46F, NoteTone.getTone(NoteTone.F_5), 0.01F);
        assertEquals(739.99F, NoteTone.getTone(NoteTone.F_SHARP_5), 0.01F);
        assertEquals(783.99F, NoteTone.getTone(NoteTone.G_5), 0.01F);
        assertEquals(830.61F, NoteTone.getTone(NoteTone.G_SHARP_5), 0.01F);
        assertEquals(880.00F, NoteTone.getTone(NoteTone.A_5), 0.01F);
        assertEquals(932.33F, NoteTone.getTone(NoteTone.A_SHARP_5), 0.01F);
        assertEquals(987.77F, NoteTone.getTone(NoteTone.B_5), 0.01F);
        assertEquals(1046.50F, NoteTone.getTone(NoteTone.C_6), 0.01F);
        assertEquals(1108.73F, NoteTone.getTone(NoteTone.C_SHARP_6), 0.01F);
        assertEquals(1174.66F, NoteTone.getTone(NoteTone.D_6), 0.01F);
        assertEquals(1244.51F, NoteTone.getTone(NoteTone.D_SHARP_6), 0.01F);
        assertEquals(1318.51F, NoteTone.getTone(NoteTone.E_6), 0.01F);
        assertEquals(1396.91F, NoteTone.getTone(NoteTone.F_6), 0.01F);
        assertEquals(1479.98F, NoteTone.getTone(NoteTone.F_SHARP_6), 0.01F);
        assertEquals(1567.98F, NoteTone.getTone(NoteTone.G_6), 0.01F);
        assertEquals(1661.22F, NoteTone.getTone(NoteTone.G_SHARP_6), 0.01F);
        assertEquals(1760.00F, NoteTone.getTone(NoteTone.A_6), 0.01F);
        assertEquals(1864.66F, NoteTone.getTone(NoteTone.A_SHARP_6), 0.01F);
        assertEquals(1975.53F, NoteTone.getTone(NoteTone.B_6), 0.01F);
    }

    @Test
    void testGetToneNull() {
        assertEquals(0F, NoteTone.getTone(null), 0.01F);
    }

    @Test
    void testEnumValues() {
        for (NoteTone note : NoteTone.values()) {
            assertNotNull(note);
        }
        assertEquals(61, NoteTone.values().length);
    }
}