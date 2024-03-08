package de.relluem94.rellulib.sounds;

public enum NoteType {

    SINUS,
    LINEAR,
    SQUARE,
    SAW;

    private static NoteType[] vals = values();

    public NoteType next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static int getType(NoteType s) {
        if (null == s) {
            return 0;
        }

        return switch (s) {
            case LINEAR -> 1;
            case SQUARE -> 2;
            case SAW -> 3;
            default -> 0;
        };
    }
}
