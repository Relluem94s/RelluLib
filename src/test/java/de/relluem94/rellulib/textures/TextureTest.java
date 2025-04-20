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
        BufferedImage texture = Texture.generateChessImage(Color3i.RELLU_GREEN, Color3i.RELLU_GRAY, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateBigChessImage() {
        BufferedImage texture = Texture.generateBigChessImage(Color3i.RELLU_ORANGE, Color3i.RELLU_GRAY, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateNoiseImage() {
        BufferedImage texture = Texture.generateNoiseImage(Color3i.RELLU_BLUE, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateStripesImage() {
        BufferedImage texture = Texture.generateStripesImage(Color3i.RED, Color3i.BLUE, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateStripesAlphaImage() {
        BufferedImage texture = Texture.generateStripesAlphaImage(Color3i.LIME, Color3i.PURPLE, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_ARGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateSolidImage() {
        BufferedImage texture = Texture.generateSolidImage(Color3i.LIGHT_GREY, TextureSize.KILO);
        Assertions.assertNotNull(texture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, texture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), texture.getHeight());
    }

    @Test
    void generateAlterColorImage() {
        BufferedImage texture = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        BufferedImage newTexture = Texture.generateAlterColorImage(Color3i.BLACK, texture);
        Assertions.assertNotNull(newTexture);
        Assertions.assertNotEquals(texture, newTexture);
        Assertions.assertEquals(BufferedImage.TYPE_INT_RGB, newTexture.getType());
        Assertions.assertEquals(TextureSize.getSize(TextureSize.KILO), newTexture.getHeight());
    }
}