package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	
	private Random random;
	private BufferedImage img;
	
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	public GameScreen(BufferedImage img) {
		this.img = img;
		
		loadSprites();
		
		random = new Random();
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
		
//		g.drawImage(sprites.get(19), 0, 0, null);
		
		// getSubimage will get a certain pixel in that image
//		BufferedImage i = img.getSubimage(32 * 9, 32 * 1, 32, 32);
		
//		g.drawImage(i, 0, 0, null);
		
		// Color for the y axis
		for(int y = 0; y < 20; y++) {
			for(int x = 0; x < 20; x++) {
//				// custom color can be used this way
				g.setColor(new Color(255, 125, 125));
			
//				// if we want the color to be randomized, then we can use this
				g.setColor(getRndColor());
				g.fillRect(x * 32, y * 32, 32, 32);
			}
		}
	}
	
	// To get random color
	private Color getRndColor() {
		int r = random.nextInt(256); 
		int g = random.nextInt(256); 
		int b = random.nextInt(256);
		
		return new Color(r, g, b);
	}
}
