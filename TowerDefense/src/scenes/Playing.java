package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import guii.ActionBar;
import helperMethods.LoadSave;
import main.Game;

public class Playing extends GameScene implements SceneMethods {

	private int[][] world;
	private ActionBar bottomBar;
	private int mouseX, mouseY;
	
	public Playing(Game game) {
		super(game);
		loadDefaultWorld();
		
		bottomBar = new ActionBar(0, 640, 640, 100, this);
		
	}

	private void loadDefaultWorld() {
		world = LoadSave.GetWorldData("new_world");
	}

	@Override
	public void render(Graphics g) {
		drawWorld(g);
		bottomBar.draw(g);
		
	}
	
	private void drawWorld(Graphics g) {
		for(int y = 0; y < world.length; y++) {
			for(int x = 0; x < world[y].length; x++) {
				int id = world[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}
	
	private BufferedImage getSprite(int spriteID) {
		return game.getGridHandler().getSprite(spriteID);
	}
	
	
	// Mouse Inputs
	@Override
	public void mouseClicked(int x, int y) {
		if(y >= 640) {
			bottomBar.mouseClicked(x, y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if(y >= 640) {
			bottomBar.mouseMoved(x, y);
		} else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}
	
	@Override
	public void mousePressed(int x, int y) {
		if(y >= 640) {
			bottomBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottomBar.mouseReleased(x, y);
	}



	@Override
	public void mouseDragged(int x, int y) {
	
	}	
	
	public void setWorld(int[][] world) {
		this.world = world;
	}
}
