package de.relluem94.rellulib.sounds;

public enum NoteType {

    Sinus,
    Linear,
    Square,
    Saw;

    private static NoteType[] vals = values();

    public NoteType next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static int getType(NoteType s) {
        int type = 0;
        if (s == NoteType.Sinus) {
            type = 0;
        } else if (s == NoteType.Linear) {
            type = 1;
        } else if (s == NoteType.Square) {
            type = 2;
        } else if (s == NoteType.Saw) {
            type = 3;
        }

        return type;
    }
}
