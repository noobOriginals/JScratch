package JScratch.core;

import java.awt.Color;

public class NColor {
	public Color invertColor(Color c) {
		if(c == Color.gray) {
			c = Color.black;
		}
		int r, g, b, buffer;
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();
		buffer = r;
		r = 255 - buffer;
		buffer = g;
		g = 255 - buffer;
		buffer = b;
		b = 255 - buffer;
		return new Color(r, g, b);
	}
}
