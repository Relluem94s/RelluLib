package de.relluem94.rellulib.textures;

public enum TextureSize {

    /**
     * Nano: 2 Pixel
     */
    NANO,
    /**
     * Micro: 4 Pixel
     */
    MICRO,
    /**
     * Zenti: 8 Pixel
     */
    ZENTI,
    /**
     * Kilo: 16 Pixel
     */
    KILO,
    /**
     * Small: 32 Pixel
     */
    SMALL,
    /**
     * Normal: 64 Pixel
     */
    NORMAL,
    /**
     * Double: 128 Pixel
     */
    DOUBLE,
    /**
     * Tripple: 256 Pixel
     */
    TRIPPLE,
    /**
     * Quadruple: 512 Pixel
     */
    QUADRUPLE,
    /**
     * Quintuple: 1024 Pixel
     */
    QUINTUPLE,
    /**
     * Sextuple: 2048 Pixel
     */
    SEXTUPLE,
    /**
     * Septuple: 4096 Pixel
     */
    SEPTUPLE,
    /**
     * Octuple: 8192 Pixel
     */
    OCTUPLE;

    private static TextureSize[] vals = values();

    public TextureSize next() {
        return vals[(this.ordinal() + 1) % vals.length];

    }

    public static int getSize(TextureSize s) {
        int length = 0;
        if (s == TextureSize.NANO) {
            length = 2;
        } else if (s == TextureSize.MICRO) {
            length = 4;
        } else if (s == TextureSize.ZENTI) {
            length = 8;
        } else if (s == TextureSize.KILO) {
            length = 16;
        } else if (s == TextureSize.SMALL) {
            length = 32;
        } else if (s == TextureSize.NORMAL) {
            length = 64;
        } else if (s == TextureSize.DOUBLE) {
            length = 128;
        } else if (s == TextureSize.TRIPPLE) {
            length = 256;
        } else if (s == TextureSize.QUADRUPLE) {
            length = 512;
        } else if (s == TextureSize.QUINTUPLE) {
            length = 1024;
        } else if (s == TextureSize.SEXTUPLE) {
            length = 2048;
        } else if (s == TextureSize.SEPTUPLE) {
            length = 4096;
        } else if (s == TextureSize.OCTUPLE) {
            length = 8192;
        }
        return length;
    }

    public static TextureSize getTextureSize(int length) {
        TextureSize s = TextureSize.NANO;
        if (length == 2) {
            s = TextureSize.NANO;
        } else if (length == 4) {
            s = TextureSize.MICRO;
        } else if (length == 8) {
            s = TextureSize.ZENTI;
        } else if (length == 16) {
            s = TextureSize.KILO;
        } else if (length == 32) {
            s = TextureSize.SMALL;
        } else if (length == 64) {
            s = TextureSize.NORMAL;
        } else if (length == 128) {
            s = TextureSize.DOUBLE;
        } else if (length == 256) {
            s = TextureSize.TRIPPLE;
        } else if (length == 512) {
            s = TextureSize.QUADRUPLE;
        } else if (length == 1024) {
            s = TextureSize.QUINTUPLE;
        } else if (length == 2048) {
            s = TextureSize.SEXTUPLE;
        } else if (length == 4096) {
            s = TextureSize.SEPTUPLE;
        } else if (length == 8192) {
            s = TextureSize.OCTUPLE;
        }
        return s;
    }

}
