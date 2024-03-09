package JScratch.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Sprite {

	private ArrayList<Costume> costumes = new ArrayList<Costume>();
	private String name;
	private boolean visible;
	private int costume;
	private double size;
	private int x, y;
	private double direction;
//	private int xInc, yInc;
//	private double dirInc;

	public Sprite(String name) {
		this.name = name;
		visible = true;
		x = 0;
		y = 0;
		costume = 0;
		direction = 1d;
		size = 1d;
	}
	public Sprite(String name, boolean visible) {
		this.name = name;
		this.visible = visible;
		x = 0;
		y = 0;
		costume = 0;
		size = 1d;
	}
	public Sprite(String name, boolean visible, int x, int y) {
		this.name = name;
		this.visible = visible;
		this.x = x;
		this.y = y;
		costume = 0;
		size = 1d;
	}
	public Sprite(String name, int x, int y) {
		this.name = name;
		visible = true;
		this.x = x;
		this.y = y;
		costume = 0;
		size = 1d;
	}

	public void update() {

	}

	public void draw(Graphics2D g2d, int w, int h) {
		int xOffset = (int) (costumes.get(costume).width() / 2 * size);
		int yOffset = (int) (costumes.get(costume).height() / 2 * size);
		if(visible) {
			g2d.drawImage(costumes.get(costume).getCostume(), x - xOffset + w / 2, y - yOffset + h / 2, (int) (costumes.get(costume).width() * size), (int) (costumes.get(costume).height() * size), null);
		}
	}

	public void move(int steps) {
		x += Math.sin(direction) * steps;
		y += Math.cos(direction) * steps;
	}
	public void move(int steps, double direction) {
		x += Math.sin(direction) * steps;
		y += Math.cos(direction) * steps;
	}
	public void pointInDirection(double dir) {
		direction = dir;
	}
	public void turnLeft(double deg) {
		direction -= deg;
	}
	public void turnRight(double deg) {
		direction += deg;
	}
	public void changeX(int i) {
		x += i;
	}
	public void changeY(int i) {
		y += i;
	}
	public void setX(int i) {
		x = i;
	}
	public void setY(int i) {
		y = i;
	}

	@Deprecated
	public boolean boundstouching(Sprite x) {
		int x1, y1, x2, y2;
		int w1, h1, w2, h2;
		x1 = this.getX();
		y1 = this.getY();
		x2 = x.getX();
		y2 = x.getY();
		w1 = this.width();
		h1 = this.height();
		w2 = x.width();
		h2 = x.height();
		if(x1 > x2 && x1 - w1 / 2 - w2 / 2 > x2) {
			return false;
		} else if(x1 < x2 && x1 + w1 / 2 + w2 / 2 < x2) {
			return false;
		} else if(y1 > y2 && y1 - h1 / 2 - h2 / 2 > y2){
			return false;
		} else if(y1 < y2 && y1 + h1 / 2 + h2 / 2 < y2) {
			return false;
		} else {
			return true;
		}
	}
	public boolean boxtouching(int boxOffset, Sprite x) {
		int x1, y1, x2, y2;
		int w1, h1, w2, h2;
		x1 = this.getX();
		y1 = this.getY();
		x2 = x.getX();
		y2 = x.getY();
		w1 = this.width();
		h1 = this.height();
		w2 = x.width();
		h2 = x.height();
		if(x1 > x2 && x1 - w1 / 2 - w2 / 2 + boxOffset > x2) {
			return false;
		} else if(x1 < x2 && x1 + w1 / 2 + w2 / 2 - boxOffset < x2) {
			return false;
		} else if(y1 > y2 && y1 - h1 / 2 - h2 / 2 + boxOffset > y2){
			return false;
		} else if(y1 < y2 && y1 + h1 / 2 + h2 / 2 - boxOffset < y2) {
			return false;
		} else {
			return true;
		}
	}


	/***************************************************************************************************
	 * The method below this comment and above the next comment below this one needs to be finished !! *
	 * *************************************************************************************************/
	@SuppressWarnings({ "unused" })
	@Deprecated
	private boolean touching(Sprite s) {
		int x1, y1, x2, y2;
		int w1, h1, w2, h2;
		x1 = this.getX();
		y1 = this.getY();
		x2 = s.getX();
		y2 = s.getY();
		w1 = this.width();
		h1 = this.height();
		w2 = s.width();
		h2 = s.height();
		for(Point p1 : this.getCostume().getOuterPixels()) {
			for(Point p2 : s.getCostume().getOuterPixels()) {
				if(x1 + p1.x >= x2 + p2.x) {
					return true;
				} else if(true) {

				}
			}
		}
		return false;
	}
	/******************************************************************************************************
	 * The method above this comment and below the next comment on above this one needs to be finished !! *
	 * ****************************************************************************************************/

	public boolean touchingColor(Color c) {
		return false;
	}

	public void setCostume(int c) throws Exception {
		if(c < 0 || c >= costumes.size()) {
			throw new Exception("Index " + c + " out of bounds for current costume list!");
		} else {
			costume = c;
		}
	}
	public void resize(int s) {
		size = s / 100;
	}
	public void addCostume(Costume c) {
		for(int i = 0; i < costumes.size(); i++) {
			if(costumes.get(i).name() == c.name()) {
				costumes.set(i, c);
				return;
			}
		}
		costumes.add(c);
	}

	public int getCostumeNr() {
		return costume;
	}
	public double getSize() {
		return size;
	}
	public Costume getCostume(int i) throws Exception {
		if(i >= costumes.size()) {
			throw new Exception("Index " + i + " out of bounds for length " + costumes.size());
		}
		return costumes.get(i);
	}
	public Costume getCostume() {
		return costumes.get(costume);
	}
	public Costume getCostume(String name) throws Exception {
		for(Costume c : costumes) {
			if(c.name() == name) {
				return c;
			}
		}
		throw new Exception("Costume not found!");
	}

	public String name() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int width() {
		return (int) (costumes.get(costume).width() * size);
	}
	public int height() {
		return (int) (costumes.get(costume).height() * size);
	}
}