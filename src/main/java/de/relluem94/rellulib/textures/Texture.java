package de.relluem94.rellulib.textures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import de.relluem94.rellulib.color.Color3i;

public class Texture {

    private Texture() {
        throw new IllegalStateException("Utility class");
    }


    private static final Random random = new Random();
    private static final double FEATURE_SIZE = 24;

    public static BufferedImage generateRGB(BufferedImage img) {
        BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getWidth(); y++) {
            for (int x = 0; x < img.getHeight(); x++) {
                image.setRGB(x, y, img.getRGB(x, y));
            }
        }

        return image;
    }

    public static BufferedImage generateSimplexNoise(Color3i color, Color3i color2, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        OpenSimplexNoise noise = new OpenSimplexNoise();

        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
        int alpha = 255;
        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE);

                int r = color.r * (int) (value + 1);
                int g = color.g * (int) (value + 1);
                int b = color.b * (int) (value + 1);
                int a = 255 * (int) (value + 1);
                if (a == 0) {
                    image.setRGB(x, y, (alpha << 28) | (color2.r << 16) | (color2.g << 8) | color2.b);
                } else {
                    image.setRGB(x, y, (a << 28) | (r << 16) | (g << 8) | b);
                }
            }
        }
        return image;
    }

    //TODO Nicht akurat.. fixxen.. 
    public static BufferedImage generateChessImage(Color3i color1, Color3i color2, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);

        int res = resolution / 2 - 1;

        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                if (x <= res) {
                    if (y <= res) {
                        image.setRGB(x, y, (color1.r << 16) | (color1.g << 8) | color1.b);
                    } else {
                        image.setRGB(x, y, (color2.r << 16) | (color2.g << 8) | color2.b);
                    }
                } else {
                    if (y > res) {
                        image.setRGB(x, y, (color1.r << 16) | (color1.g << 8) | color1.b);
                    } else {
                        image.setRGB(x, y, (color2.r << 16) | (color2.g << 8) | color2.b);
                    }
                }

            }
        }
        return image;
    }

    public static BufferedImage generateBigChessImage(Color3i color1, Color3i color2, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        boolean fx = false;
        boolean fxl = false;

        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                if (y == (resolution) - 1) {
                    fxl = !fxl;
                }
                if (fxl) {
                    fxl = !fxl;
                    fx = !fx;
                }
                if (fx) {
                    image.setRGB(x, y, (color1.r << 16) | (color1.g << 8) | color1.b);
                    fx = !fx;
                } else {
                    image.setRGB(x, y, (color2.r << 16) | (color2.g << 8) | color2.b);
                    fx = !fx;
                }
            }
        }
        return image;
    }

    public static BufferedImage generateNoiseImage(Color3i color, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                int rot = random.nextInt(30) * (color.r - 0 * 1) + 0;
                int gruen = random.nextInt(30) * (color.g - 0 * 1) + 0;
                int blau = random.nextInt(30) * (color.b - 0 * 1) + 0;
                image.setRGB(x, y, (rot << 16) | (gruen << 8) | blau);
            }
        }
        return image;
    }

    public static BufferedImage generateStripesImage(Color3i color1, Color3i color2, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        int res = resolution / 2;
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                if (x <= res) {
                    image.setRGB(x, y, (color2.r << 16) | (color2.g << 8) | color2.b);
                } else {
                    image.setRGB(x, y, (color1.r << 16) | (color1.g << 8) | color1.b);
                }
            }
        }
        return image;
    }

    public static BufferedImage generateStripesAlphaImage(Color3i color1, Color3i color2, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        int res = resolution / 2;
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                if (x <= res) {
                    image.setRGB(x, y, (0xFF << 28) | (color2.r << 16) | (color2.g << 8) | color2.b);
                } else {
                    image.setRGB(x, y, (0xFF << 28) | (color1.r << 16) | (color1.g << 8) | color1.b);
                }

            }
        }
        return image;
    }

    public static BufferedImage generateSolidImage(Color3i color, TextureSize size) {
        int resolution = TextureSize.getSize(size);
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                image.setRGB(x, y, (color.r << 16) | (color.g << 8) | color.b);
            }
        }
        return image;
    }

    public static BufferedImage generateAlterColorImage(Color3i color, BufferedImage img) {
        BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Color rgb;

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                rgb = new Color(img.getRGB(x, img.getHeight() - (y + 1)));
                image.setRGB(x, y, (color.r + rgb.getRed() << 16) | (color.g + rgb.getGreen() << 8) | color.b + rgb.getBlue());
            }
        }
        return image;
    }
}
