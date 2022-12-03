package scenes;

import java.awt.Color;
import java.awt.Graphics;

import guii.Buttons;
import handlers.GridHandler;
import handlers.WorldBuilder;
import main.Game;
import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods{

	private int[][] world;
	private GridHandler gridHandler;
	private Buttons bMenu;
	
	public Playing(Game game) {
		super(game);
		
		initButtons();
		world = WorldBuilder.getWorldData();
		gridHandler = new GridHandler();
		
	}

	private void initButtons() {
		bMenu = new Buttons("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		
		for(int y = 0; y < world.length; y++) {
			for(int x = 0; x < world[y].length; x++) {
				int id = world[y][x];
				g.drawImage(gridHandler.getSprite(id), x * 32, y * 32, null);
			}
		}
		
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
}
