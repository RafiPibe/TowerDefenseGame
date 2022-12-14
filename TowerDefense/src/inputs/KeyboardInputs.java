package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;

public class KeyboardInputs implements KeyListener{
	private Game game;
	
	public KeyboardInputs(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// For debugging purposes
		if(e.getKeyCode() == KeyEvent.VK_U) GameStates.gameState = MENU;
		else if(e.getKeyCode() == KeyEvent.VK_I) GameStates.gameState = EDITOR;
		else if(e.getKeyCode() == KeyEvent.VK_O) GameStates.gameState = PLAYING;
		else if(e.getKeyCode() == KeyEvent.VK_P) GameStates.gameState = SETTINGS;
		
		if(GameStates.gameState == EDITOR)
			game.getEditor().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
