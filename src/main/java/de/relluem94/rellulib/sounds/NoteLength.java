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

    private static final NoteLength[] vals = values();

    public NoteLength next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static int getLength(NoteLength s) {
        int length = 0;

        if (null != s) {
            switch (s) {
                case None:
                    length = 0;
                    break;
                case Quarter:
                    length = 286;
                    break;
                case Dotted_Quarter:
                    length = 429;
                    break;
                case Whole:
                    length = 1144;
                    break;
                case Dotted_Half:
                    length = 858;
                    break;
                case Half:
                    length = 572;
                    break;
                case Eighth:
                    length = 143;
                    break;
                case Sixteenth:
                    length = 72;
                    break;
                default:
                    break;
            }
        }

        return length;
    }
}
