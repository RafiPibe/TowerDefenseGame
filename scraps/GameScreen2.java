package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	
	private Random random;
	private BufferedImage img;
	
	private Dimension size;
	
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	private Render render;
	
	public GameScreen(BufferedImage img) {
		this.img = img;
		render = new Render(this);
		
		setPanelSize();
		
		loadSprites();
		
		random = new Random();
	}
	
	private void setPanelSize() {
		size = new Dimension(640, 640);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	private void loadSprites() {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// To make the sprite appear
		for(int y = 0; y < 20; y++) {
			for(int x = 0; x < 20; x++) {
				g.drawImage(sprites.get(getRndInt()), x * 32, y * 32, null);
			}
		}
	}
	

	private int getRndInt() {
		return random.nextInt(100);
	}
}
