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
        TextureSize s = TextureSize.NANO;
        switch (length) {
            case 2:
                s = TextureSize.NANO;
                break;
            case 4:
                s = TextureSize.MICRO;
                break;
            case 8:
                s = TextureSize.ZENTI;
                break;
            case 16:
                s = TextureSize.KILO;
                break;
            case 32:
                s = TextureSize.SMALL;
                break;
            case 64:
                s = TextureSize.NORMAL;
                break;
            case 128:
                s = TextureSize.DOUBLE;
                break;
            case 256:
                s = TextureSize.TRIPPLE;
                break;
            case 512:
                s = TextureSize.QUADRUPLE;
                break;
            case 1024:
                s = TextureSize.QUINTUPLE;
                break;
            case 2048:
                s = TextureSize.SEXTUPLE;
                break;
            case 4096:
                s = TextureSize.SEPTUPLE;
                break;
            case 8192:
                s = TextureSize.OCTUPLE;
                break;
            default:
                break;
        }
        return s;
    }

}
