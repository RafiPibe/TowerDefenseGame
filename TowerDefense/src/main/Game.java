package main;

import javax.swing.JFrame;

public class Game extends JFrame{
	
	private GameScreen gameScreen;
	
	// The game window
	public Game() {
		setSize(640,640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		gameScreen = new GameScreen();
		add(gameScreen);
	}
	
	// main function
	public static void main(String[] args) {
		Game game = new Game();
		
	}

}
