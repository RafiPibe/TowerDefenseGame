package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import guii.ToolBar;
import helperMethods.LoadSave;
import main.Game;
import obj.Grid;

public class Editor extends GameScene implements SceneMethods {
	
	private int[][] world;
	private Grid selectedGrid;
	private int mouseX, mouseY, lastGridX, lastGridY, lastGridId;
	private boolean drawSelected = false;
	private ToolBar toolbar;
	
	public Editor(Game game) {
		super(game);
		loadDefaultWorld();
		toolbar = new ToolBar(0, 640, 640, 100, this);
	}

	private void loadDefaultWorld() {
		world = LoadSave.GetWorldData("new_world");
	}

	@Override
	public void render(Graphics g) {
		drawWorld(g);
		toolbar.draw(g);
		drawSelectedGrid(g);
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
	
	private void drawSelectedGrid(Graphics g) {
		if(selectedGrid != null && drawSelected) {
			g.drawImage(selectedGrid.getSprite(), mouseX, mouseY, 32, 32, null);
		}
	}
	
	public void saveWorld() {
		LoadSave.SaveWorld("new_world", world);
		game.getPlaying().setWorld(world);
	}
	
	public void setSelectedGrid(Grid grid) {
		this.selectedGrid = grid;
		drawSelected = true;
	}
	
	private void changeGrid(int x, int y) {
		if(selectedGrid != null) {
			int gridX = x / 32;
			int gridY = y / 32;
		
			if(lastGridX == gridX && lastGridY == gridY && lastGridId == selectedGrid.getId()) {
				return;
			}
			
			lastGridX = gridX;
			lastGridY = gridY;
			
			world[gridY][gridX] = selectedGrid.getId();
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(y >= 640) {
			toolbar.mouseClicked(x, y);
		} else {
			changeGrid(mouseX, mouseY);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if(y >= 640) {
			toolbar.mouseMoved(x, y);
			drawSelected = false;
		} else {
			drawSelected = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(y >= 640) {
			toolbar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		toolbar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {
			
		} else {
			changeGrid(x, y);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R)
			toolbar.rotateSprite();
	}

}
