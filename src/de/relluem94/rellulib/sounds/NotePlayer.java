package de.relluem94.rellulib.sounds;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class NotePlayer {

	private static int bpm_modif = 1;

	public static int getBpm_modif() {
		return bpm_modif;
	}

	public static void setBpm_modif(int bpm_modif) {
		NotePlayer.bpm_modif = bpm_modif;
	}

	public static void play(NoteTone[] tone, NoteLength[] length, float volume) throws LineUnavailableException {

		if (tone.length == length.length) {
			float frequency = 44100;
			byte[] buf;
			AudioFormat af;
			buf = new byte[tone.length];
			af = new AudioFormat(frequency, 16, 2, true, false);

			SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
			sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();

			for (int a = 0; a < tone.length; a++) {
				float hz = NoteTone.getTone(tone[a]);
				int ms = NoteLength.getLength(length[a]);

				for (int i = 0; i < ms * frequency / 1000; i++) {
					for (int b = 0; b < tone.length; b++) {
						double angle = i / (frequency / hz) * 2.0 * Math.PI;
						buf[b] = (byte) (Math.sin(angle) * volume);
					}
					sdl.write(buf, 0, tone.length);
				}
			}

			sdl.drain();
			sdl.stop();
			sdl.close();
		}
	}

	public static void generateSineTone(NoteTone tone, NoteLength length, float vol, boolean harm) throws LineUnavailableException {

		float hz = NoteTone.getTone(tone);
		int ms = NoteLength.getLength(length);

		ms = ms - bpm_modif;

		float frequency = 44100;
		byte[] buf;
		AudioFormat af;
		if (harm) {
			buf = new byte[2];
			af = new AudioFormat(frequency, 8, 2, true, false);
		} else {
			buf = new byte[1];
			af = new AudioFormat(frequency, 8, 1, true, false);
		}
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i = 0; i < ms * frequency / 1000; i++) {
			double angle = i / (frequency / hz) * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * vol);

			if (harm) {
				double angle2 = (i) / (frequency / hz) * 2.0 * Math.PI;
				buf[1] = (byte) (Math.sin(2 * angle2) * vol * 0.6);
				sdl.write(buf, 0, 2);
			} else {
				sdl.write(buf, 0, 1);
			}
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}

	public static void generateLinearTone(NoteTone tone, NoteLength length, float vol) throws LineUnavailableException {

		float hz = NoteTone.getTone(tone);
		int ms = NoteLength.getLength(length);

		ms = ms - bpm_modif;

		float frequency = 44100;
		byte[] buf;
		AudioFormat af;
		buf = new byte[1];
		af = new AudioFormat(frequency, 8, 1, true, false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i = 0; i < ms * frequency / 1000; i++) {
			double angle = i / (frequency / hz) * 4.0;
			buf[0] = (byte) ((angle) * (vol));
			sdl.write(buf, 0, 1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
}
