package JScratch.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyInput implements KeyListener {

	private HashMap<Character, Boolean> keyPress = new HashMap<Character, Boolean>();
	private HashMap<Integer, Boolean> keyCodePress = new HashMap<Integer, Boolean>();

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		keyPress.put(e.getKeyChar(), true);
		keyCodePress.put(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		keyPress.put(e.getKeyChar(), false);
		keyCodePress.put(e.getKeyCode(), false);
	}

	public HashMap<Character, Boolean> getKeyPress() {
		return keyPress;
	}
	public HashMap<Integer, Boolean> getKeyCodePress() {
		return keyCodePress;
	}
}
