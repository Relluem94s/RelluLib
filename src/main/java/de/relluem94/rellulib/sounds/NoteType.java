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
        if (null != s) {
            switch (s) {
                case Sinus:
                    type = 0;
                    break;
                case Linear:
                    type = 1;
                    break;
                case Square:
                    type = 2;
                    break;
                case Saw:
                    type = 3;
                    break;
                default:
                    break;
            }
        }

        return type;
    }
}
