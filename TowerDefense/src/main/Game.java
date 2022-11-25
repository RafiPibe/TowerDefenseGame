package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	private BufferedImage img;
	
	private Thread gameThread;
	
	// FPS and UPS Cap
	private final double FPSCap = 120.0;
	private final double UPSCap = 60.0;
	
	// The game window
	public Game() {
		
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

	private void updateGame() {
		//System.out.println("Game Updated.");
	}

	// main function
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
		
		double frameDuration = 1000000000.0 / FPSCap;
		double updateDuration = 1000000000.0 / UPSCap;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;
		
		// Game loop
		while(true) {
			//render
			if(System.nanoTime() - lastFrame >= frameDuration) {	
				lastFrame = System.nanoTime();
				repaint();
				frames++;
			}
			
			//update
			if(System.nanoTime() - lastUpdate >= updateDuration) {
				updateGame();
				lastUpdate = System.nanoTime();
				updates++;
			}
			
			// FPS and UPS Counter
			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("=====================" + "\nFPS : " + frames + " | UPS : " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
}
