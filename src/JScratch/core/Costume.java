package JScratch.core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class Costume {

	private BufferedImage costume;
	private String name;
	private int height, width;

	private HashSet<Point> Pixels = new HashSet<Point>();
	
	private void scan(int offset) {
		int x, y;
		for(x = offset, y = offset; y < height - offset; y++) {
			if(costume.getRGB(x, y) != 0) {
				Pixels.add(new Point(x ,y));
			}
		}
		for(x = offset + 1, y = height - offset - 1; x < width - offset; x++) {
			if(costume.getRGB(x, y) != 0) {
				Pixels.add(new Point(x ,y));
			}
		}
		for(x = width - offset - 1, y = height - offset - 2; y >= offset; y--) {
			if(costume.getRGB(x, y) != 0) {
				Pixels.add(new Point(x ,y));
			}
		}
		for(x = width - offset - 2, y = offset; x >= offset + 1; x--) {
			if(costume.getRGB(x, y) != 0) {
				Pixels.add(new Point(x ,y));
			}
		}
	}

	public Costume(String name, String directory) {
		try {
			costume = ImageIO.read(Costume.class.getResourceAsStream(directory));
		} catch (IOException e) {
			e.printStackTrace();
		}
		height = costume.getHeight();
		width = costume.getWidth();
		this.name = name;
		
		int maxoffset = (width < height) ? (int) Math.floor(width / 2) : (int) Math.floor(height / 2);
		for(int offset = 0; offset <= maxoffset; offset ++) {
			scan(offset);
		}
	}


	public HashSet<Point> getPixels() {
		return Pixels;
	}
	public BufferedImage getCostume() {
		return costume;
	}
	public String name() {
		return name;
	}
	public int height() {
		return height;
	}
	public int width() {
		return width;
	}

}