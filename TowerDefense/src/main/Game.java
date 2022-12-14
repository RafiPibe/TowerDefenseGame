package main;

import javax.swing.JFrame;

import handlers.GridHandler;
import helperMethods.LoadSave;
import scenes.Editor;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	private Thread gameThread;
	
	// FPS and UPS Cap
	private final double FPSCap = 120.0;
	private final double UPSCap = 60.0;
	
	// Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editor editor;
	
	private GridHandler gridHandler;
	
	// The game window
	public Game() {
		
		initClasses();
		createDefaultWorld();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		add(gameScreen);
		pack();
		
		setVisible(true);
		
	}
	
	private void createDefaultWorld() {
		int[] arr = new int[400];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		
		LoadSave.CreateWorld("new_world", arr);
	}
	
	private void initClasses() {
		gridHandler = new GridHandler();
		
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editor = new Editor(this);
		
	}
	
	
	private void start() {
		
		gameThread = new Thread(this) {};
		gameThread.start();
		
	}

	private void updateGame() {
		switch(GameStates.gameState) {
		case EDITOR:
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case SETTINGS:
			break;
		default:
			break;
		
		}
	}

	// main function
	public static void main(String[] args) {
		
		Game game = new Game();
		game.gameScreen.initInputs();
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
	
	
	// Getter Setter
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	public Editor getEditor() {
		return editor;
	}
	
	public GridHandler getGridHandler() {
		return gridHandler;
	}
	
}
