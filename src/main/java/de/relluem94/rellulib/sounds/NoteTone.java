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
        if (s == null) {
            return 0F;
        }

        return switch (s) {
            case NONE -> 0F;
            case C_2 -> 65.41F;
            case C_SHARP_2 -> 69.30F;
            case D_2 -> 73.42F;
            case D_SHARP_2 -> 77.78F;
            case E_2 -> 82.41F;
            case F_2 -> 87.31F;
            case F_SHARP_2 -> 92.50F;
            case G_2 -> 98.00F;
            case G_SHARP_2 -> 103.83F;
            case A_2 -> 110.00F;
            case A_SHARP_2 -> 116.54F;
            case B_2 -> 123.47F;
            case C_3 -> 130.81F;
            case C_SHARP_3 -> 138.59F;
            case D_3 -> 146.83F;
            case D_SHARP_3 -> 155.56F;
            case E_3 -> 164.81F;
            case F_3 -> 174.61F;
            case F_SHARP_3 -> 185.00F;
            case G_3 -> 196.00F;
            case G_SHARP_3 -> 207.65F;
            case A_3 -> 220.00F;
            case A_SHARP_3 -> 233.08F;
            case B_3 -> 246.94F;
            case C_4 -> 261.63F;
            case C_SHARP_4 -> 277.18F;
            case D_4 -> 293.66F;
            case D_SHARP_4 -> 311.13F;
            case E_4 -> 329.63F;
            case F_4 -> 349.23F;
            case F_SHARP_4 -> 369.99F;
            case G_4 -> 392.00F;
            case G_SHARP_4 -> 415.30F;
            case A_4 -> 440.00F;
            case A_SHARP_4 -> 466.16F;
            case B_4 -> 493.88F;
            case C_5 -> 523.25F;
            case C_SHARP_5 -> 554.37F;
            case D_5 -> 587.33F;
            case D_SHARP_5 -> 622.25F;
            case E_5 -> 659.25F;
            case F_5 -> 698.46F;
            case F_SHARP_5 -> 739.99F;
            case G_5 -> 783.99F;
            case G_SHARP_5 -> 830.61F;
            case A_5 -> 880.00F;
            case A_SHARP_5 -> 932.33F;
            case B_5 -> 987.77F;
            case C_6 -> 1046.50F;
            case C_SHARP_6 -> 1108.73F;
            case D_6 -> 1174.66F;
            case D_SHARP_6 -> 1244.51F;
            case E_6 -> 1318.51F;
            case F_6 -> 1396.91F;
            case F_SHARP_6 -> 1479.98F;
            case G_6 -> 1567.98F;
            case G_SHARP_6 -> 1661.22F;
            case A_6 -> 1760.00F;
            case A_SHARP_6 -> 1864.66F;
            case B_6 -> 1975.53F;
        };
    }
}
