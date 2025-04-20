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

    private static final TextureSize[] vals = values();

    public TextureSize next() {
        return vals[(this.ordinal() + 1) % vals.length];

    }

    public static int getSize(TextureSize s) {
        if(s == null){
            return 0;
        }

        return switch (s) {
            case NANO -> 2;
            case MICRO -> 4;
            case ZENTI -> 8;
            case KILO -> 16;
            case SMALL -> 32;
            case NORMAL -> 64;
            case DOUBLE -> 128;
            case TRIPPLE -> 256;
            case QUADRUPLE -> 512;
            case QUINTUPLE -> 1024;
            case SEXTUPLE -> 2048;
            case SEPTUPLE -> 4096;
            case OCTUPLE -> 8192;
        };
    }

    public static TextureSize getTextureSize(int length) {
        return switch (length) {
            case 4 -> TextureSize.MICRO;
            case 8 -> TextureSize.ZENTI;
            case 16 -> TextureSize.KILO;
            case 32 -> TextureSize.SMALL;
            case 64 -> TextureSize.NORMAL;
            case 128 -> TextureSize.DOUBLE;
            case 256 -> TextureSize.TRIPPLE;
            case 512 -> TextureSize.QUADRUPLE;
            case 1024 -> TextureSize.QUINTUPLE;
            case 2048 -> TextureSize.SEXTUPLE;
            case 4096 -> TextureSize.SEPTUPLE;
            case 8192 -> TextureSize.OCTUPLE;
            default -> TextureSize.NANO;
        };
    }
}