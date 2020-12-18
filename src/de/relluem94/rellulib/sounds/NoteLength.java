package de.relluem94.rellulib.sounds;

public enum NoteLength {

    None,
    Quarter,
    Dotted_Quarter,
    Whole,
    Dotted_Half,
    Half,
    Eighth,
    Sixteenth;

    private static NoteLength[] vals = values();

    public NoteLength next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static int getLength(NoteLength s) {
        int length = 0;
        if (s == NoteLength.None) {
            length = 0;
        } else if (s == NoteLength.Quarter) {
            length = 286;
        } else if (s == NoteLength.Dotted_Quarter) {
            length = 429;
        } else if (s == NoteLength.Whole) {
            length = 1144;
        } else if (s == NoteLength.Dotted_Half) {
            length = 858;
        } else if (s == NoteLength.Half) {
            length = 572;
        } else if (s == NoteLength.Eighth) {
            length = 143;
        } else if (s == NoteLength.Sixteenth) {
            length = 72;
        }

        return length;
    }
}
