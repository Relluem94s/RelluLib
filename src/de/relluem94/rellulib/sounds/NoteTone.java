package de.relluem94.rellulib.sounds;

public enum NoteTone {

    None,
    C_2,
    C_Sharp_2,
    D_2,
    D_Sharp_2,
    E_2,
    F_2,
    F_Sharp_2,
    G_2,
    G_Sharp_2,
    A_2,
    A_Sharp_2,
    B_2,
    C_3,
    C_Sharp_3,
    D_3,
    D_Sharp_3,
    E_3,
    F_3,
    F_Sharp_3,
    G_3,
    G_Sharp_3,
    A_3,
    A_Sharp_3,
    B_3,
    C_4,
    C_Sharp_4,
    D_4,
    D_Sharp_4,
    E_4,
    F_4,
    F_Sharp_4,
    G_4,
    G_Sharp_4,
    A_4,
    A_Sharp_4,
    B_4,
    C_5,
    C_Sharp_5,
    D_5,
    D_Sharp_5,
    E_5,
    F_5,
    F_Sharp_5,
    G_5,
    G_Sharp_5,
    A_5,
    A_Sharp_5,
    B_5,
    C_6,
    C_Sharp_6,
    D_6,
    D_Sharp_6,
    E_6,
    F_6,
    F_Sharp_6,
    G_6,
    G_Sharp_6,
    A_6,
    A_Sharp_6,
    B_6;

    private static NoteTone[] vals = values();

    public NoteTone next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static float getTone(NoteTone s) {
        float hz = 0;
        //  2
        if (s == NoteTone.None) {
            hz = 0F;
        } else if (s == NoteTone.C_2) {
            hz = 65.41F;
        } else if (s == NoteTone.C_Sharp_2) {
            hz = 69.30F;
        } else if (s == NoteTone.D_2) {
            hz = 73.42F;
        } else if (s == NoteTone.D_Sharp_2) {
            hz = 77.78F;
        } else if (s == NoteTone.E_2) {
            hz = 82.41F;
        } else if (s == NoteTone.F_2) {
            hz = 87.31F;
        } else if (s == NoteTone.F_Sharp_2) {
            hz = 92.50F;
        } else if (s == NoteTone.G_2) {
            hz = 98.00F;
        } else if (s == NoteTone.G_Sharp_2) {
            hz = 103.83F;
        } else if (s == NoteTone.A_2) {
            hz = 110.00F;
        } else if (s == NoteTone.A_Sharp_2) {
            hz = 116.54F;
        } else if (s == NoteTone.B_2) {
            hz = 123.47F;
        } //  3
        else if (s == NoteTone.C_3) {
            hz = 130.81F;
        } else if (s == NoteTone.C_Sharp_3) {
            hz = 138.59F;
        } else if (s == NoteTone.D_3) {
            hz = 146.83F;
        } else if (s == NoteTone.D_Sharp_3) {
            hz = 155.56F;
        } else if (s == NoteTone.E_3) {
            hz = 164.81F;
        } else if (s == NoteTone.F_3) {
            hz = 174.61F;
        } else if (s == NoteTone.F_Sharp_3) {
            hz = 185.00F;
        } else if (s == NoteTone.G_3) {
            hz = 196.00F;
        } else if (s == NoteTone.G_Sharp_3) {
            hz = 207.65F;
        } else if (s == NoteTone.A_3) {
            hz = 220.00F;
        } else if (s == NoteTone.A_Sharp_3) {
            hz = 233.08F;
        } else if (s == NoteTone.B_3) {
            hz = 246.94F;
        } // 4
        else if (s == NoteTone.C_4) {
            hz = 261.63F;
        } else if (s == NoteTone.C_Sharp_4) {
            hz = 277.18F;
        } else if (s == NoteTone.D_4) {
            hz = 293.66F;
        } else if (s == NoteTone.D_Sharp_4) {
            hz = 311.13F;
        } else if (s == NoteTone.E_4) {
            hz = 329.63F;
        } else if (s == NoteTone.F_4) {
            hz = 349.23F;
        } else if (s == NoteTone.F_Sharp_4) {
            hz = 369.99F;
        } else if (s == NoteTone.G_4) {
            hz = 392.00F;
        } else if (s == NoteTone.G_Sharp_4) {
            hz = 415.30F;
        } else if (s == NoteTone.A_4) {
            hz = 440.00F;
        } else if (s == NoteTone.A_Sharp_4) {
            hz = 466.16F;
        } else if (s == NoteTone.B_4) {
            hz = 493.88F;
        } // 5
        else if (s == NoteTone.C_5) {
            hz = 523.25F;
        } else if (s == NoteTone.C_Sharp_5) {
            hz = 554.37F;
        } else if (s == NoteTone.D_5) {
            hz = 587.33F;
        } else if (s == NoteTone.D_Sharp_5) {
            hz = 622.25F;
        } else if (s == NoteTone.E_5) {
            hz = 659.25F;
        } else if (s == NoteTone.F_5) {
            hz = 698.46F;
        } else if (s == NoteTone.F_Sharp_5) {
            hz = 739.99F;
        } else if (s == NoteTone.G_5) {
            hz = 783.99F;
        } else if (s == NoteTone.G_Sharp_5) {
            hz = 830.61F;
        } else if (s == NoteTone.A_5) {
            hz = 880.00F;
        } else if (s == NoteTone.A_Sharp_5) {
            hz = 932.33F;
        } else if (s == NoteTone.B_5) {
            hz = 987.77F;
        } // 6
        else if (s == NoteTone.C_6) {
            hz = 1046.50F;
        } else if (s == NoteTone.C_Sharp_6) {
            hz = 1108.73F;
        } else if (s == NoteTone.D_6) {
            hz = 1174.66F;
        } else if (s == NoteTone.D_Sharp_6) {
            hz = 1244.51F;
        } else if (s == NoteTone.E_6) {
            hz = 1318.51F;
        } else if (s == NoteTone.F_6) {
            hz = 1396.91F;
        } else if (s == NoteTone.F_Sharp_6) {
            hz = 1479.98F;
        } else if (s == NoteTone.G_6) {
            hz = 1567.98F;
        } else if (s == NoteTone.G_Sharp_6) {
            hz = 1661.22F;
        } else if (s == NoteTone.A_6) {
            hz = 1760.00F;
        } else if (s == NoteTone.A_Sharp_6) {
            hz = 1864.66F;
        } else if (s == NoteTone.B_6) {
            hz = 1975.53F;
        }
        return hz;
    }
}
