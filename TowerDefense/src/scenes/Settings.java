package scenes;

import java.awt.Color;
import java.awt.Graphics;

import guii.Buttons;
import main.Game;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods{

	private Buttons bMenu;
	
	public Settings(Game game) {
		super(game);
		
		initButtons();
	}

	private void initButtons() {
		bMenu = new Buttons("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		Color yes = new Color(255, 255, 194);
		g.setColor(yes);
		g.fillRect(0, 0, 640, 640);
		
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}
	}
	
	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}
	}
	
	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBool();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}
