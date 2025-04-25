package de.relluem94.rellulib.sounds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NoteTypeTest {

    @Test
    void next() {
        Assertions.assertEquals(NoteType.SQUARE, NoteType.LINEAR.next());
    }

    @Test
    void getType() {
        Assertions.assertEquals(0, NoteType.getType(NoteType.SINUS));
        Assertions.assertEquals(1, NoteType.getType(NoteType.LINEAR));
        Assertions.assertEquals(2, NoteType.getType(NoteType.SQUARE));
        Assertions.assertEquals(3, NoteType.getType(NoteType.SAW));
        Assertions.assertEquals(0, NoteType.getType(null));
    }
}