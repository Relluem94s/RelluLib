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
        int type = 0;
        if (null != s) {
            switch (s) {
                case SINUS:
                    type = 0;
                    break;
                case LINEAR:
                    type = 1;
                    break;
                case SQUARE:
                    type = 2;
                    break;
                case SAW:
                    type = 3;
                    break;
                default:
                    break;
            }
        }

        return type;
    }
}
