package JScratch.core;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class SKeys {

	private static HashMap<String, Integer> keys = new HashMap<String, Integer>();

	static void init() {
		keys.clear();
		keys.put("ALT", KeyEvent.VK_ALT);
		keys.put("BACK_QUOTE", KeyEvent.VK_BACK_QUOTE);
		keys.put("BACK_SLASH", KeyEvent.VK_BACK_SLASH);
		keys.put("BACK_SPACE", KeyEvent.VK_BACK_SPACE);
		keys.put("BRACE_LEFT", KeyEvent.VK_BRACELEFT);
		keys.put("BRACE_RIGHT", KeyEvent.VK_BRACERIGHT);
		keys.put("CLOSE_BRACKET", KeyEvent.VK_CLOSE_BRACKET);
		keys.put("OPEN_BRACKET", KeyEvent.VK_OPEN_BRACKET);
		keys.put("TAB", KeyEvent.VK_TAB);
		keys.put("CAPS_LOCK", KeyEvent.VK_CAPS_LOCK);
		keys.put("COLON", KeyEvent.VK_COLON);
		keys.put("COMMA", KeyEvent.VK_COMMA);
		keys.put("CONTROL", KeyEvent.VK_CONTROL);
		keys.put("DELETE", KeyEvent.VK_DELETE);
		keys.put("DOLLAR", KeyEvent.VK_DOLLAR);
		keys.put("DOWN", KeyEvent.VK_DOWN);
		keys.put("ENTER", KeyEvent.VK_ENTER);
		keys.put("ESCAPE", KeyEvent.VK_ESCAPE);
		keys.put("EXCLAMATION_MARK", KeyEvent.VK_EXCLAMATION_MARK);
		keys.put("QUOTE", KeyEvent.VK_QUOTE);
		keys.put("SPACE", KeyEvent.VK_SPACE);
		keys.put("UNDERSCORE", KeyEvent.VK_UNDERSCORE);
		keys.put("WINDOWS", KeyEvent.VK_WINDOWS);
		keys.put("UP", KeyEvent.VK_UP);
		keys.put("DOWN", KeyEvent.VK_DOWN);
		keys.put("LEFT", KeyEvent.VK_LEFT);
		keys.put("RIGHT", KeyEvent.VK_RIGHT);
	}

	public static int getKey(String key) {
		return keys.get(key);
	}
}
