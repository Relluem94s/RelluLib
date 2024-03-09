package de.relluem94.rellulib.sounds;

public enum NoteTone {

    NONE,
    C_2,
    C_SHARP_2,
    D_2,
    D_SHARP_2,
    E_2,
    F_2,
    F_SHARP_2,
    G_2,
    G_SHARP_2,
    A_2,
    A_SHARP_2,
    B_2,
    C_3,
    C_SHARP_3,
    D_3,
    D_SHARP_3,
    E_3,
    F_3,
    F_SHARP_3,
    G_3,
    G_SHARP_3,
    A_3,
    A_SHARP_3,
    B_3,
    C_4,
    C_SHARP_4,
    D_4,
    D_SHARP_4,
    E_4,
    F_4,
    F_SHARP_4,
    G_4,
    G_SHARP_4,
    A_4,
    A_SHARP_4,
    B_4,
    C_5,
    C_SHARP_5,
    D_5,
    D_SHARP_5,
    E_5,
    F_5,
    F_SHARP_5,
    G_5,
    G_SHARP_5,
    A_5,
    A_SHARP_5,
    B_5,
    C_6,
    C_SHARP_6,
    D_6,
    D_SHARP_6,
    E_6,
    F_6,
    F_SHARP_6,
    G_6,
    G_SHARP_6,
    A_6,
    A_SHARP_6,
    B_6;

    private static final NoteTone[] values = values();

    public NoteTone next() {
        return values[(this.ordinal() + 1) % values.length];
    }

    public static float getTone(NoteTone s) {
        float hz = 0;
        if (null != s) //  2
        {
            switch (s) {
                case NONE:
                    hz = 0F;
                    break;
                case C_2:
                    hz = 65.41F;
                    break;
                case C_SHARP_2:
                    hz = 69.30F;
                    break;
                case D_2:
                    hz = 73.42F;
                    break;
                case D_SHARP_2:
                    hz = 77.78F;
                    break;
                case E_2:
                    hz = 82.41F;
                    break;
                case F_2:
                    hz = 87.31F;
                    break;
                case F_SHARP_2:
                    hz = 92.50F;
                    break;
                case G_2:
                    hz = 98.00F;
                    break;
                case G_SHARP_2:
                    hz = 103.83F;
                    break;
                case A_2:
                    hz = 110.00F;
                    break;
                case A_SHARP_2:
                    hz = 116.54F;
                    break;
                //  3
                case B_2:
                    hz = 123.47F;
                    break;
                case C_3:
                    hz = 130.81F;
                    break;
                case C_SHARP_3:
                    hz = 138.59F;
                    break;
                case D_3:
                    hz = 146.83F;
                    break;
                case D_SHARP_3:
                    hz = 155.56F;
                    break;
                case E_3:
                    hz = 164.81F;
                    break;
                case F_3:
                    hz = 174.61F;
                    break;
                case F_SHARP_3:
                    hz = 185.00F;
                    break;
                case G_3:
                    hz = 196.00F;
                    break;
                case G_SHARP_3:
                    hz = 207.65F;
                    break;
                case A_3:
                    hz = 220.00F;
                    break;
                case A_SHARP_3:
                    hz = 233.08F;
                    break;
                // 4
                case B_3:
                    hz = 246.94F;
                    break;
                case C_4:
                    hz = 261.63F;
                    break;
                case C_SHARP_4:
                    hz = 277.18F;
                    break;
                case D_4:
                    hz = 293.66F;
                    break;
                case D_SHARP_4:
                    hz = 311.13F;
                    break;
                case E_4:
                    hz = 329.63F;
                    break;
                case F_4:
                    hz = 349.23F;
                    break;
                case F_SHARP_4:
                    hz = 369.99F;
                    break;
                case G_4:
                    hz = 392.00F;
                    break;
                case G_SHARP_4:
                    hz = 415.30F;
                    break;
                case A_4:
                    hz = 440.00F;
                    break;
                case A_SHARP_4:
                    hz = 466.16F;
                    break;
                // 5
                case B_4:
                    hz = 493.88F;
                    break;
                case C_5:
                    hz = 523.25F;
                    break;
                case C_SHARP_5:
                    hz = 554.37F;
                    break;
                case D_5:
                    hz = 587.33F;
                    break;
                case D_SHARP_5:
                    hz = 622.25F;
                    break;
                case E_5:
                    hz = 659.25F;
                    break;
                case F_5:
                    hz = 698.46F;
                    break;
                case F_SHARP_5:
                    hz = 739.99F;
                    break;
                case G_5:
                    hz = 783.99F;
                    break;
                case G_SHARP_5:
                    hz = 830.61F;
                    break;
                case A_5:
                    hz = 880.00F;
                    break;
                case A_SHARP_5:
                    hz = 932.33F;
                    break;
                // 6
                case B_5:
                    hz = 987.77F;
                    break;
                case C_6:
                    hz = 1046.50F;
                    break;
                case C_SHARP_6:
                    hz = 1108.73F;
                    break;
                case D_6:
                    hz = 1174.66F;
                    break;
                case D_SHARP_6:
                    hz = 1244.51F;
                    break;
                case E_6:
                    hz = 1318.51F;
                    break;
                case F_6:
                    hz = 1396.91F;
                    break;
                case F_SHARP_6:
                    hz = 1479.98F;
                    break;
                case G_6:
                    hz = 1567.98F;
                    break;
                case G_SHARP_6:
                    hz = 1661.22F;
                    break;
                case A_6:
                    hz = 1760.00F;
                    break;
                case A_SHARP_6:
                    hz = 1864.66F;
                    break;
                case B_6:
                    hz = 1975.53F;
                    break;
                default:
                    break;
            }
        }
        return hz;
    }
}
