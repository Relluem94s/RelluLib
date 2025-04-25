package de.relluem94.rellulib.sounds;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotePlayerTest {

    @Mock
    private SourceDataLine sourceDataLine;

    private MockedStatic<AudioSystem> audioSystemMock;

    @BeforeEach
    void setUp() {
        audioSystemMock = mockStatic(AudioSystem.class);
        audioSystemMock.when(() -> AudioSystem.getSourceDataLine(any(AudioFormat.class)))
                .thenReturn(sourceDataLine);
        NotePlayer.setBpmModif(1);
    }

    @AfterEach
    void tearDown() {
        if (audioSystemMock != null) {
            audioSystemMock.close();
        }
    }

    @Test
    void checkForUtilityClass(){
        assertThrows(IllegalStateException.class, NotePlayer::new);
    }

    @Test
    void testBpmModifGetterSetter() {
        NotePlayer.setBpmModif(5);
        assertEquals(5, NotePlayer.getBpmModif());

        NotePlayer.setBpmModif(0);
        assertEquals(0, NotePlayer.getBpmModif());

        NotePlayer.setBpmModif(-10);
        assertEquals(-10, NotePlayer.getBpmModif());
    }

    @Test
    void testPlayWithMatchingArrays() throws LineUnavailableException {
        NoteTone[] tones = {NoteTone.A_4};
        NoteLength[] lengths = {NoteLength.QUARTER};

        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doReturn(1).when(sourceDataLine).write(any(byte[].class), eq(0), eq(1));
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.play(tones, lengths, 100.0f);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, atLeastOnce()).write(any(byte[].class), eq(0), eq(1));
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testPlayWithMismatchedArrays() {
        NoteTone[] tones = {NoteTone.A_4};
        NoteLength[] lengths = {NoteLength.QUARTER, NoteLength.EIGHTH};

        assertDoesNotThrow(() -> NotePlayer.play(tones, lengths, 100.0f));
        verifyNoInteractions(sourceDataLine);
    }

    @Test
    void testPlayWithNoteToneNone() throws LineUnavailableException {
        NoteTone[] tones = {NoteTone.NONE};
        NoteLength[] lengths = {NoteLength.QUARTER};

        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doReturn(1).when(sourceDataLine).write(any(byte[].class), eq(0), eq(1));
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.play(tones, lengths, 100.0f);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, atLeastOnce()).write(any(byte[].class), eq(0), eq(1));
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testPlayWithNoteLengthNone() throws LineUnavailableException {
        NoteTone[] tones = {NoteTone.A_4};
        NoteLength[] lengths = {NoteLength.NONE};

        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.play(tones, lengths, 100.0f);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, never()).write(any(byte[].class), anyInt(), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateSineToneWithoutHarmonics() throws LineUnavailableException {
        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doReturn(1).when(sourceDataLine).write(any(byte[].class), eq(0), eq(1));
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateSineTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f, false);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, atLeastOnce()).write(any(byte[].class), eq(0), eq(1));
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateSineToneWithHarmonics() throws LineUnavailableException {
        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doReturn(2).when(sourceDataLine).write(any(byte[].class), eq(0), eq(2));
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateSineTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f, true);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, atLeastOnce()).write(any(byte[].class), eq(0), eq(2));
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateSineToneWithNoteLengthNone() throws LineUnavailableException {
        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateSineTone(NoteTone.A_4, NoteLength.NONE, 100.0f, false);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, never()).write(any(byte[].class), anyInt(), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateSineToneWithLargeBpmModif() throws LineUnavailableException {
        NotePlayer.setBpmModif(1000);

        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateSineTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f, false);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, never()).write(any(byte[].class), anyInt(), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateLinearTone() throws LineUnavailableException {
        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doReturn(1).when(sourceDataLine).write(any(byte[].class), eq(0), eq(1));
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateLinearTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, atLeastOnce()).write(any(byte[].class), eq(0), eq(1));
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testGenerateLinearToneWithNoteLengthNone() throws LineUnavailableException {
        doNothing().when(sourceDataLine).open(any(AudioFormat.class));
        doNothing().when(sourceDataLine).start();
        doNothing().when(sourceDataLine).drain();
        doNothing().when(sourceDataLine).stop();
        doNothing().when(sourceDataLine).close();

        NotePlayer.generateLinearTone(NoteTone.A_4, NoteLength.NONE, 100.0f);

        verify(sourceDataLine).open(any(AudioFormat.class));
        verify(sourceDataLine).start();
        verify(sourceDataLine, never()).write(any(byte[].class), anyInt(), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    @Test
    void testPlayThrowsLineUnavailableException() throws LineUnavailableException {
        NoteTone[] tones = {NoteTone.A_4};
        NoteLength[] lengths = {NoteLength.QUARTER};

        doThrow(new LineUnavailableException()).when(sourceDataLine).open(any(AudioFormat.class));

        assertThrows(LineUnavailableException.class, () -> NotePlayer.play(tones, lengths, 100.0f));
    }

    @Test
    void testGenerateSineToneThrowsLineUnavailableException() throws LineUnavailableException {
        doThrow(new LineUnavailableException()).when(sourceDataLine).open(any(AudioFormat.class));

        assertThrows(LineUnavailableException.class, () -> NotePlayer.generateSineTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f, false));
    }

    @Test
    void testGenerateLinearToneThrowsLineUnavailableException() throws LineUnavailableException {
        doThrow(new LineUnavailableException()).when(sourceDataLine).open(any(AudioFormat.class));

        assertThrows(LineUnavailableException.class, () -> NotePlayer.generateLinearTone(NoteTone.A_4, NoteLength.QUARTER, 100.0f));
    }

    @Test
    void testNoteToneGetTone() {
        assertEquals(0.0f, NoteTone.getTone(NoteTone.NONE), 0.01f);
        assertEquals(440.0f, NoteTone.getTone(NoteTone.A_4), 0.01f);
        assertEquals(261.63f, NoteTone.getTone(NoteTone.C_4), 0.01f);
        assertEquals(1760.0f, NoteTone.getTone(NoteTone.A_6), 0.01f);
        assertEquals(0.0f, NoteTone.getTone(null), 0.01f);
    }

    @Test
    void testNoteToneNext() {
        assertEquals(NoteTone.C_2, NoteTone.NONE.next());
        assertEquals(NoteTone.A_4, NoteTone.G_SHARP_4.next());
        assertEquals(NoteTone.NONE, NoteTone.B_6.next());
    }

    @Test
    void testNoteLengthGetLength() {
        assertEquals(0, NoteLength.getLength(NoteLength.NONE));
        assertEquals(286, NoteLength.getLength(NoteLength.QUARTER));
        assertEquals(429, NoteLength.getLength(NoteLength.DOTTED_QUARTER));
        assertEquals(1144, NoteLength.getLength(NoteLength.WHOLE));
        assertEquals(858, NoteLength.getLength(NoteLength.DOTTED_HALF));
        assertEquals(572, NoteLength.getLength(NoteLength.HALF));
        assertEquals(143, NoteLength.getLength(NoteLength.EIGHTH));
        assertEquals(72, NoteLength.getLength(NoteLength.SIXTEENTH));
        assertEquals(0, NoteLength.getLength(null));
    }

    @Test
    void testNoteLengthNext() {
        assertEquals(NoteLength.QUARTER, NoteLength.NONE.next());
        assertEquals(NoteLength.DOTTED_QUARTER, NoteLength.QUARTER.next());
        assertEquals(NoteLength.NONE, NoteLength.SIXTEENTH.next());
    }
}