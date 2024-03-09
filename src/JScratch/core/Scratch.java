package JScratch.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Scratch {

	private static long WindowCount = 0;

	// Window and panel for window:
	private JFrame window;
	private ScratchJPanel panel;

	// General info such as name, width, height, etc:
	private String name;
	private boolean visible, resizable;
	private int width, height;
	private Color bgcolor;
	private BufferedImage icon;

	// Starts the Thread in the panel:
	@Deprecated
	public void startApp() {
		panel.startApp();
	}

	// Constructor for Scratch:
	public Scratch(String name, int width, int height, Color bgcolor, BufferedImage icon, int fps, boolean resizable){
		SKeys.init();

		// Increment window count:
		WindowCount++;
		// Check if more than 1 window is created:
		if(WindowCount > 1) {
			System.err.println("Cannot create multiple Scratch type odjects!");
			return;
		}
		// Set defaults:
		this.name = name;
		this.visible = true;
		this.resizable = resizable;
		this.width = width;
		this.height = height;
		if(bgcolor == null) {
			this.bgcolor = Color.black;
		} else {
			this.bgcolor = bgcolor;
		}
		this.icon = icon;

		// Initialize window:
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(name);
		window.setResizable(resizable);
		if(icon != null) {
			window.setIconImage(icon);
		}

		// Initialize panel:
		panel = new ScratchJPanel(new Dimension(width, height), bgcolor, fps);

		// Add panel to window:
		window.add(panel);
		window.pack();

		// Show window:
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	// Add the object that implements SRunnable for running by thread in panel:
	@Deprecated
	public void addSRunnable(SRunnable scr) {
		panel.addSRunnable(scr);
	}
	
	// Add a SDrawable object:
	public void addSDrawable(SDrawable scr) {
		panel.addSDrawable(scr);
	}

	// Refresh screen, not recommended:
	public void refresh() {
		panel.revalidate();
		panel.repaint();
	}

	// Add one or multiple "Sprite" objects:
	public void addSprite(Sprite s) {
		panel.addSprite(s);
	}

	// Configure window:
	public void setResizeable(boolean r) {
		resizable = r;
		window.setResizable(r);
	}
	public void setVisible(boolean v) {
		visible = v;
		window.setVisible(v);
	}
	public void setName(String s) {
		name = s;
		window.setTitle(s);
	}
	public void setIcon(BufferedImage i) {
		icon = i;
		window.setIconImage(i);
	}
	public void resize(Dimension d) {
		width = d.width;
		height = d.height;
		panel.setSize(new Dimension(width, height));
	}
	public void setBgCol(Color c) {
		if(c == null) {
			bgcolor = Color.black;
		} else {
			bgcolor = c;
		}
		panel.setBgColor(bgcolor);
	}

	// Get key input:
	public boolean keyPressed(char c) {
		return panel.keyPressed(c);
	}
	public boolean keyReleased(char c) {
		return panel.keyReleased(c);
	}
	public boolean keyPressed(int i) {
		return panel.keyPressed(i);
	}
	public boolean keyReleased(int i) {
		return panel.keyReleased(i);
	}

	// Get info about this object:
	public BufferedImage icon() {
		return icon;
	}
	public Color bgcolor() {
		return bgcolor;
	}
	public String name() {
		return name;
	}
	public boolean resizable() {
		return resizable;
	}
	public boolean visible() {
		return visible;
	}
	public Dimension size() {
		return new Dimension(width, height);
	}
	public int width() {
		return width;
	}
	public int height() {
		return height;
	}

	@Deprecated
	public boolean AboundstouchingB(Sprite a, Sprite b) {
		return panel.touching(a, b);
	}
}
