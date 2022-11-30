package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	private BufferedImage img;
	
	private Thread gameThread;
	
	// FPS and UPS Cap
	private final double FPSCap = 120.0;
	private final double UPSCap = 60.0;
	
	private KeyboardInputs keyboardInputs;
	private MouseInputs mouseInputs;
	
	// The game window
	public Game() {
		
		importImg();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		gameScreen = new GameScreen(img);
		
		add(gameScreen);
		pack();
		
		setVisible(true);
	}
	
	private void initInputs() {
		keyboardInputs = new KeyboardInputs();
		mouseInputs = new MouseInputs();
		
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		addKeyListener(keyboardInputs);
		
		requestFocus();
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
		game.initInputs();
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
		
		long currentTime;
		
		// Game loop
		while(true) {
			currentTime = System.nanoTime();
			
			//render
			if(currentTime - lastFrame >= frameDuration) {	
				lastFrame = currentTime;
				repaint();
				frames++;
			}
			
			//update
			if(currentTime - lastUpdate >= updateDuration) {
				updateGame();
				lastUpdate = currentTime;
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
