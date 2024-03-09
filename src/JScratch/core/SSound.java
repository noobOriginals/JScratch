package JScratch.core;

public class SSound {

	private String name, directory;
	private SAudio sound;

	public SSound(String name, String directory) {
		this.name = name;
		this.directory = directory;
		sound = new SAudio(name, directory);
	}
	public SSound(String directory) {
		this.name = "_@file";
		this.directory = directory;
		sound = new SAudio(directory);
	}

	public void play() {
		sound.play();
	}
	public void stop() {
		sound.stop();
	}
	public void stopReset() {
		if(!sound.isRunning()) {
			sound.hardStop();
		}
		if(name == "_@file") {
			sound = new SAudio(directory);
		} else {
			sound = new SAudio(name, directory);
		}
	}
}
