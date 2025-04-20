package de.relluem94.rellulib.textures;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextureSizeTest {

    @Test
    public void testEnumValues() {
        TextureSize[] expectedValues = {
                TextureSize.NANO, TextureSize.MICRO, TextureSize.ZENTI, TextureSize.KILO,
                TextureSize.SMALL, TextureSize.NORMAL, TextureSize.DOUBLE, TextureSize.TRIPPLE,
                TextureSize.QUADRUPLE, TextureSize.QUINTUPLE, TextureSize.SEXTUPLE,
                TextureSize.SEPTUPLE, TextureSize.OCTUPLE
        };
        assertArrayEquals(expectedValues, TextureSize.values());
    }

    @Test
    public void testGetSize() {
        assertEquals(2, TextureSize.getSize(TextureSize.NANO));
        assertEquals(4, TextureSize.getSize(TextureSize.MICRO));
        assertEquals(8, TextureSize.getSize(TextureSize.ZENTI));
        assertEquals(16, TextureSize.getSize(TextureSize.KILO));
        assertEquals(32, TextureSize.getSize(TextureSize.SMALL));
        assertEquals(64, TextureSize.getSize(TextureSize.NORMAL));
        assertEquals(128, TextureSize.getSize(TextureSize.DOUBLE));
        assertEquals(256, TextureSize.getSize(TextureSize.TRIPPLE));
        assertEquals(512, TextureSize.getSize(TextureSize.QUADRUPLE));
        assertEquals(1024, TextureSize.getSize(TextureSize.QUINTUPLE));
        assertEquals(2048, TextureSize.getSize(TextureSize.SEXTUPLE));
        assertEquals(4096, TextureSize.getSize(TextureSize.SEPTUPLE));
        assertEquals(8192, TextureSize.getSize(TextureSize.OCTUPLE));
        assertEquals(0, TextureSize.getSize(null));
    }

    @Test
    public void testGetTextureSize() {
        assertEquals(TextureSize.NANO, TextureSize.getTextureSize(2));
        assertEquals(TextureSize.MICRO, TextureSize.getTextureSize(4));
        assertEquals(TextureSize.ZENTI, TextureSize.getTextureSize(8));
        assertEquals(TextureSize.KILO, TextureSize.getTextureSize(16));
        assertEquals(TextureSize.SMALL, TextureSize.getTextureSize(32));
        assertEquals(TextureSize.NORMAL, TextureSize.getTextureSize(64));
        assertEquals(TextureSize.DOUBLE, TextureSize.getTextureSize(128));
        assertEquals(TextureSize.TRIPPLE, TextureSize.getTextureSize(256));
        assertEquals(TextureSize.QUADRUPLE, TextureSize.getTextureSize(512));
        assertEquals(TextureSize.QUINTUPLE, TextureSize.getTextureSize(1024));
        assertEquals(TextureSize.SEXTUPLE, TextureSize.getTextureSize(2048));
        assertEquals(TextureSize.SEPTUPLE, TextureSize.getTextureSize(4096));
        assertEquals(TextureSize.OCTUPLE, TextureSize.getTextureSize(8192));
        assertEquals(TextureSize.NANO, TextureSize.getTextureSize(0));
        assertEquals(TextureSize.NANO, TextureSize.getTextureSize(3));
    }

    @Test
    public void testNext() {
        assertEquals(TextureSize.MICRO, TextureSize.NANO.next());
        assertEquals(TextureSize.ZENTI, TextureSize.MICRO.next());
        assertEquals(TextureSize.KILO, TextureSize.ZENTI.next());
        assertEquals(TextureSize.SMALL, TextureSize.KILO.next());
        assertEquals(TextureSize.NORMAL, TextureSize.SMALL.next());
        assertEquals(TextureSize.DOUBLE, TextureSize.NORMAL.next());
        assertEquals(TextureSize.TRIPPLE, TextureSize.DOUBLE.next());
        assertEquals(TextureSize.QUADRUPLE, TextureSize.TRIPPLE.next());
        assertEquals(TextureSize.QUINTUPLE, TextureSize.QUADRUPLE.next());
        assertEquals(TextureSize.SEXTUPLE, TextureSize.QUINTUPLE.next());
        assertEquals(TextureSize.SEPTUPLE, TextureSize.SEXTUPLE.next());
        assertEquals(TextureSize.OCTUPLE, TextureSize.SEPTUPLE.next());
        assertEquals(TextureSize.NANO, TextureSize.OCTUPLE.next());
    }
}