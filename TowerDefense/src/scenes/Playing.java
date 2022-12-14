package scenes;

import java.awt.Graphics;

import guii.Buttons;
import main.Game;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
	
	private Buttons bPlaying, bEdit, bSettings, bQuit;
	
	public Menu(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		
		int widthButton = 150;
		int heightButton = widthButton / 3;
		int x = 640 / 2 - widthButton / 2;
		int y = 150;
		int ySpace = 100;
		
		bPlaying = new Buttons("Play", x, y, widthButton, heightButton);
		bEdit = new Buttons("Edit", x, y + ySpace, widthButton, heightButton);
		bSettings = new Buttons("Settings", x, y + ySpace * 2, widthButton, heightButton);
		bQuit = new Buttons("Quit", x, y + ySpace * 3, widthButton, heightButton);
	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bEdit.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		}
		else if (bEdit.getBounds().contains(x, y)) {
			SetGameState(EDITOR);
		}
		else if (bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		}
		else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		}
		else if (bEdit.getBounds().contains(x, y)) {
			bEdit.setMouseOver(true);
		}
		else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		}	
		else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		}
		if (bEdit.getBounds().contains(x, y)) {
			bEdit.setMousePressed(true);
		}
		else if(bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		}
		else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bPlaying.resetBool();
		bEdit.resetBool();
		bSettings.resetBool();
		bQuit.resetBool();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}
}
