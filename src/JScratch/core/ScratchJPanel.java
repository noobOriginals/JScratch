package JScratch.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScratchJPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private static long WindowCount = 0;

	public void run() {
		long currentFrameTime;
		long lastFrameTime = System.nanoTime();
		long deltaFrame;
		long frame = 0;

		long lastTime = System.nanoTime();
		long delta;
		long currentTime;
		double drawInterval = 1000000000 / setFPS;

		while(appThread != null) {
			currentFrameTime = System.nanoTime();
			deltaFrame = currentFrameTime - lastFrameTime;
			lastFrameTime = currentFrameTime;
			frame += deltaFrame;

			currentTime = System.nanoTime();
			delta = currentTime - lastTime;
			if(delta >= drawInterval) {
				// display FPS
				fps ++;
				if(frame > 1000000000) {
					displayFps = fps;
					fps = 0;
					frame = 0;
				}
				// do something
				update();
//				repaint
//				repaint();

				lastTime = System.nanoTime();
			} else {
				try {
					Thread.sleep((long)(Math.ceil(drawInterval) - delta) / 1000000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	void startApp() {
		appThread.start();
	}

	Thread appThread = new Thread(this, "appThread");

	private Color bgcolor;
	private Dimension size;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Graphics2D g2d;

	private int setFPS;
	private int fps;
	@SuppressWarnings("unused")
	private int displayFps;

	private NColor col = new NColor();

	KeyInput keyIn;

	ScratchJPanel(Dimension size, Color bgcolor, int threadSpeed) {
		// Increment window count:
		WindowCount++;
		// Check if more than 1 window is created:
		if(WindowCount > 1) {
			System.err.println("Cannot create multiple ScratchJPanel type odjects!");
			return;
		}
		this.bgcolor = bgcolor;
		this.size = size;
		setFPS = threadSpeed;
		this.keyIn = new KeyInput();
		this.setPreferredSize(size);
		this.setBackground(bgcolor);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyIn);
		this.setFocusable(true);
	}

	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(sprites != null) {
					for(Sprite s : sprites) {
						s.update();
					}
				}
				if(outerSR != null) {
					outerSR.update();
				}
			}
		});
		this.revalidate();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		if(sprites != null) {
			for(Sprite s : sprites) {
				s.draw(g2d, this.getWidth(), this.getHeight());
			}
		}
		if(outerSD != null) {
			outerSD.draw(g2d, col, bgcolor);
		}
//		g2d.setColor(col.invertColor(bgcolor));
//		g2d.drawString("FPS: " + displayFps, 10, 20);
		g2d.dispose();
	}

	private SRunnable outerSR;
	void addSRunnable(SRunnable scr) {
		outerSR = scr;
	}
	private SDrawable outerSD;
	void addSDrawable(SDrawable scr) {
		outerSD = scr;
	}

	public boolean keyPressed(char c) {
		String upper = c + "";
		String lower = c + "";
		upper = upper.toUpperCase();
		lower = lower.toLowerCase();
		if(keyIn.getKeyPress().get(upper.charAt(0)) != null && keyIn.getKeyPress().get(upper.charAt(0)) == true) {
			return true;
		} else if(keyIn.getKeyPress().get(lower.charAt(0)) != null && keyIn.getKeyPress().get(lower.charAt(0)) == true) {
			return true;
		} else {
			return false;
		}
	}
	public boolean keyPressed(int i) {
		if(keyIn.getKeyCodePress().get(i) != null && keyIn.getKeyCodePress().get(i) == true) {
			return true;
		} else {
			return false;
		}
	}
	public boolean keyReleased(char c) {
		String upper = c + "";
		String lower = c + "";
		upper = upper.toUpperCase();
		lower = lower.toLowerCase();
		if(keyIn.getKeyPress().get(upper.charAt(0)) != null && keyIn.getKeyPress().get(upper.charAt(0)) == false) {
			return true;
		} else if(keyIn.getKeyPress().get(lower.charAt(0)) != null && keyIn.getKeyPress().get(lower.charAt(0)) == false) {
			return true;
		} else {
			return false;
		}
	}
	public boolean keyReleased(int i) {
		if(keyIn.getKeyCodePress().get(i) != null && keyIn.getKeyCodePress().get(i) == false) {
			return true;
		} else {
			return false;
		}
	}


	public void setBgColor(Color c) {
		bgcolor = c;
		this.setBackground(c);
	}
	public void setSize(Dimension d) {
		size = d;
		this.setPreferredSize(d);
	}
	public void addSprite(Sprite s) {
		sprites.add(s);
	}

	public Dimension getSize() {
		return size;
	}
	public Color getBgColor() {
		return bgcolor;
	}

	public boolean touching(Sprite a, Sprite b) {
		int x1, y1, x2, y2;
		int w1, h1, w2, h2;
		x1 = a.getX();
		y1 = a.getY();
		x2 = b.getX();
		y2 = b.getY();
		w1 = a.width();
		h1 = a.height();
		w2 = b.width();
		h2 = b.height();
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
}
