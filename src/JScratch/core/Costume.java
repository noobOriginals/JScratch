package JScratch.core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Costume {

	private BufferedImage costume;
	private String name;
	private int height, width;

	private ArrayList<Point> outerPixels = new ArrayList<Point>();

	public Costume(String name, String directory) {
		try {
			costume = ImageIO.read(Costume.class.getResourceAsStream(directory));
		} catch (IOException e) {
			e.printStackTrace();
		}
		height = costume.getHeight();
		width = costume.getWidth();
		this.name = name;

		int rgb = 0;
		for(int y = 0, x = 0; y < height; y++) {
			rgb = costume.getRGB(x, y);
			while(rgb == 0) {
				x++;
				rgb = costume.getRGB(x, y);
				if(x + 1 == width && rgb == 0) {
					x = 0;
					break;
				}
			}
			if(rgb != 0 ) {
				outerPixels.add(new Point(x, y));
			}
		}
		for(int y = height - 1, x = 1; x < width; x++) {
			rgb = costume.getRGB(x, y);
			while(rgb == 0) {
				y--;
				rgb = costume.getRGB(x, y);
				if(y == 0 && rgb == 0) {
					y = height - 1;
					break;
				}
			}
			if(rgb != 0 ) {
				outerPixels.add(new Point(x, y));
			}
		}
		for(int y = height - 2, x = width - 1; y >= 0; y--) {
			rgb = costume.getRGB(x, y);
			while(rgb == 0) {
				x--;
				rgb = costume.getRGB(x, y);
				if(x == 0 && rgb == 0) {
					x = width - 1;
					break;
				}
			}
			if(rgb != 0 ) {
				outerPixels.add(new Point(x, y));
			}
		}
		for(int y = 0, x = width - 2; x >= 1; x--) {
			rgb = costume.getRGB(x, y);
			while(rgb == 0) {
				y++;
				rgb = costume.getRGB(x, y);
				if(y + 1 == width && rgb == 0) {
					y = 0;
					break;
				}
			}
			if(rgb != 0 ) {
				outerPixels.add(new Point(x, y));
			}
		}
	}


	public ArrayList<Point> getOuterPixels() {
		return outerPixels;
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