package scenes;

import java.awt.Color;
import java.awt.Graphics;

import guii.BottomBar;
import guii.Buttons;
import handlers.GridHandler;
import handlers.WorldBuilder;
import main.Game;
import obj.Grid;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {

	private int[][] world;
	private GridHandler gridHandler;
	private Grid selectedGrid;
	private BottomBar bottomBar;
	private int mouseX, mouseY, lastGridX, lastGridY, lastGridId;
	private boolean drawSelected = false;
	
	public Playing(Game game) {
		super(game);
		
		world = WorldBuilder.getWorldData();
		gridHandler = new GridHandler();
		bottomBar = new BottomBar(0, 640, 640, 100, this);
		
	}

	@Override
	public void render(Graphics g) {
		
		for(int y = 0; y < world.length; y++) {
			for(int x = 0; x < world[y].length; x++) {
				int id = world[y][x];
				g.drawImage(gridHandler.getSprite(id), x * 32, y * 32, null);
			}
		}
		bottomBar.draw(g);
		drawSelectedGrid(g);
	}

	
	private void drawSelectedGrid(Graphics g) {
		if(selectedGrid != null && drawSelected) {
			g.drawImage(selectedGrid.getSprite(), mouseX, mouseY, 32, 32, null);
		}
	}
	
	public void setSelectedGrid(Grid grid) {
		this.selectedGrid = grid;
		drawSelected = true;
	}

	// Getter
	public GridHandler getGridHandler() {
		return gridHandler;
	}
	
	// Mouse Inputs
	@Override
	public void mouseClicked(int x, int y) {
		if(y >= 640) {
			bottomBar.mouseClicked(x, y);
		} else {
			changeGrid(mouseX, mouseY);
		}
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
	public void mouseMoved(int x, int y) {
		if(y >= 640) {
			bottomBar.mouseMoved(x, y);
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
			bottomBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottomBar.mouseReleased(x, y);
	}



	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {
			
		} else {
			changeGrid(x, y);
		}
	}
}
