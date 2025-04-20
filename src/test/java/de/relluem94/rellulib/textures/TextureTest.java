package de.relluem94.rellulib.textures;

import de.relluem94.rellulib.color.Color3i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TextureTest {

    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, Texture::new);
    }

    @Test
    void generateRGB() {
        BufferedImage texture = new BufferedImage(10,10, BufferedImage.TYPE_INT_RGB);
        BufferedImage newTexture = Texture.generateRGB(texture);

        Assertions.assertNotEquals(texture, newTexture);
    }

    @Test
    void generateSimplexNoise() {
        BufferedImage texture = Texture.generateSimplexNoise(Color3i.BLACK, Color3i.WHITE, TextureSize.MICRO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_ARGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.MICRO), texture.getHeight());
    }

    @Test
    void generateChessImage() {
        BufferedImage texture = Texture.generateChessImage(Color3i.BLACK, Color3i.WHITE, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateBigChessImage() {
    }

    @Test
    void generateNoiseImage() {
    }

    @Test
    void generateStripesImage() {
    }

    @Test
    void generateStripesAlphaImage() {
    }

    @Test
    void generateSolidImage() {
    }

    @Test
    void generateAlterColorImage() {
    }
}