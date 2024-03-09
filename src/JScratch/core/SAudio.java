package JScratch.core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SAudio {
	private File audio;
	private AudioInputStream audioStream;
	private Clip clip;
	private String name;

	public SAudio(String name, String directory) {
		this.name = name;
		try {
			audioStream = AudioSystem.getAudioInputStream(SAudio.class.getResourceAsStream(directory));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		audioStream.mark(1000000000);
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	public SAudio(String directory) {
		this.name = "_@file";
		audio = new File(directory);
		try {
			audioStream = AudioSystem.getAudioInputStream(audio);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		audioStream.mark(1000000000);
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	void play() {
		clip.start();
	}
	void stop() {
		clip.stop();
	}
	void close() {
		clip.close();
	}
	void hardStop() {
		clip.flush();
	}

	boolean isRunning() {
		return clip.isActive();
	}

	public String name() {
		return name;
	}
}
