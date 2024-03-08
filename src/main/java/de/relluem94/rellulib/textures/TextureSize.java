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
        int length = 0;
        if (null != s) {
            switch (s) {
                case NANO:
                    length = 2;
                    break;
                case MICRO:
                    length = 4;
                    break;
                case ZENTI:
                    length = 8;
                    break;
                case KILO:
                    length = 16;
                    break;
                case SMALL:
                    length = 32;
                    break;
                case NORMAL:
                    length = 64;
                    break;
                case DOUBLE:
                    length = 128;
                    break;
                case TRIPPLE:
                    length = 256;
                    break;
                case QUADRUPLE:
                    length = 512;
                    break;
                case QUINTUPLE:
                    length = 1024;
                    break;
                case SEXTUPLE:
                    length = 2048;
                    break;
                case SEPTUPLE:
                    length = 4096;
                    break;
                case OCTUPLE:
                    length = 8192;
                    break;
                default:
                    break;
            }
        }
        return length;
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