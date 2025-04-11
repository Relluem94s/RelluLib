package de.relluem94.rellulib.sounds;

public enum NoteLength {

    NONE,
    QUARTER,
    DOTTED_QUARTER,
    WHOLE,
    DOTTED_HALF,
    HALF,
    EIGHTH,
    SIXTEENTH;

    private static final NoteLength[] vals = values();

    public NoteLength next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static int getLength(NoteLength s) {
        int length = 0;

        if (null != s) {
            switch (s) {
                case QUARTER:
                    length = 286;
                    break;
                case DOTTED_QUARTER:
                    length = 429;
                    break;
                case WHOLE:
                    length = 1144;
                    break;
                case DOTTED_HALF:
                    length = 858;
                    break;
                case HALF:
                    length = 572;
                    break;
                case EIGHTH:
                    length = 143;
                    break;
                case SIXTEENTH:
                    length = 72;
                    break;
                default:
                    break;
            }
        }

        return length;
    }
}
