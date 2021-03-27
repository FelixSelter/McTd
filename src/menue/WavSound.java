package menue;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class WavSound {

	TargetDataLine line;

	private File file;
	private Clip clip;
	private long pause;

	public WavSound() {
	}

	public WavSound(File file) {
		this.file = file;
	}

	public void setFile(File path) {

		this.file = path;

	}

	public File getPath() {

		return file;

	}

	public void play() {

		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));

		} catch (Exception e) {

			e.printStackTrace();

		}

		clip.start();

	}

	public void stop() {

		clip.stop();

	}

	public void loop() {

		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));

		} catch (Exception e) {

			e.printStackTrace();

		}

		clip.loop(2147483647);

	}

	public void pause() {

		pause = clip.getMicrosecondPosition();

		clip.stop();

	}

	public void resume() {

		clip.setMicrosecondPosition(pause);

		clip.start();

	}

	public long getPausedPosition() {
		return pause / 1000000;
	}

	public void test() {

		if (!(file == null)) {

		} else {

			System.err.println(
					"chickencoder.sound.WavSound: Theres no path to a file please add one by calling .setPath();");

			System.exit(0);

		}

		if (file.exists()) {

		} else {

			System.err.println("chickencoder.sound.WavSound: The file " + file + " isnt existing");

			System.exit(0);

		}

		if (String.valueOf(file).substring(String.valueOf(file).length() - 4, String.valueOf(file).length())
				.equals(".wav")) {

		} else {

			System.err.println("chickencoder.sound.WavSound: The file " + file + " isnt a .wav file");

			System.exit(0);

		}

	}

	public void record(int recordingvalue) {

		Thread recorder1 = new Thread(new Runnable() {

			@Override
			public void run() {
				AudioFormat audioformat = new AudioFormat(recordingvalue, 8, 2, true, true);

				AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

				try {
					AudioFormat format = audioformat;
					DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

					if (!AudioSystem.isLineSupported(info)) {
						System.err
								.println("chickencoder.sound.WavSound: The methode record is not supported at your pc");

						System.exit(0);
					}
					line = (TargetDataLine) AudioSystem.getLine(info);
					line.open(format);
					line.start();

					AudioInputStream ais = new AudioInputStream(line);

					AudioSystem.write(ais, fileType, file);

				} catch (LineUnavailableException ex) {
					ex.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}
		});
		recorder1.start();
	}

	public void stopRecording() {

		line.stop();
		line.close();

		System.out.println("stop");

	}

}
