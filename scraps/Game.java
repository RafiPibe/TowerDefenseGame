package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	private BufferedImage img;

	private double frameDuration;
	private long lastFrame;
	private double updateDuration;
	private long lastUpdate;
	
	private int updates;
	private long lastTimeUPS;
	
	private Thread gameThread;
	
	// The game window
	public Game() {
		
		//FPS and UPS cap
		frameDuration = 1000000000.0 / 120.0;
		updateDuration = 1000000000.0 / 60.0;
		
		importImg();
		
		setSize(640,640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		gameScreen = new GameScreen(img);
		add(gameScreen);
		setVisible(true);
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/sprite.png");
		
		// try to import the image
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			//if it goes wrong then it will print an error message
			e.printStackTrace();
		}
	}
	
	private void start() {
		gameThread = new Thread(this) {};
		gameThread.start();
	}
	
	// loopGame will make sure to balance the game so that slow / fast devices doesn't matter
	private void loopGame() {
		while(true) {
			if(System.nanoTime() - lastUpdate >= updateDuration) {
				updateGame();
				
				UPSCounter();
			} else {
				// -
			}
			
			if(System.nanoTime() - lastFrame >= frameDuration) {	
				lastFrame = System.nanoTime();
				repaint();
			} else {
				// -
			}
		}
	}
	
	// To show UPS counter of the game
	private void UPSCounter() {
		if(System.currentTimeMillis() - lastTimeUPS >= 1000) {
			System.out.println("UPS : " + updates);
			updates = 0;
			lastTimeUPS = System.currentTimeMillis();
		}
	}

	private void updateGame() {
		updates++;
		lastUpdate = System.nanoTime();
		//System.out.println("Game Updated.");
	}

	// main function
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
		//render
		//update
		
		//checking FPS and UPS
	}

}
