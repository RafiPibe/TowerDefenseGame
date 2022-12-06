package guii;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import obj.Grid;
import scenes.Playing;

public class BottomBar {
	private int x, y, width, height;
	private Playing playing;
	private Buttons bMenu;
	
	private Grid selectedGrid;
	
	private ArrayList<Buttons> gridButtons = new ArrayList<>();
	
	public BottomBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new Buttons("Menu", 2, 642, 100, 30);
		
		int width = 50;
		int height = 50;
		int xStart = 110;
		int yStart = 650;
		int xSpace = (int)(width * 1.1f);
		
		
		// for-Loop
		int i = 0;
		for (Grid grid : playing.getGridHandler().grids) {
			gridButtons.add(new Buttons(grid.getName(), xStart + xSpace * i, yStart, width, height, i++));
		}
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		drawGridButtons(g);
		drawSelectedGrid(g);
	}
	
	private void drawSelectedGrid(Graphics g) {
		if (selectedGrid != null) {
			g.drawImage(selectedGrid.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.white);
			g.drawRect(550, 650, 50, 50);
		}
	}

	private void drawGridButtons(Graphics g) {
		for (Buttons b : gridButtons) {
			
			// Sprite
			g.drawImage(getButtonImg(b.getId()), b.x, b.y, b.width, b.height, null);
			
			// MouseOver
			if(b.isMouseOver()) {
				g.setColor(Color.white);
			} else {
				g.setColor(Color.black);
			}
			
			// Outline	
			g.drawRect(b.x, b.y, b.width, b.height);
			
			// MousePressed
			if (b.isMousePressed()) {
				g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
				g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
			}
			
		}
	}
	
	public BufferedImage getButtonImg (int id) {
		return playing.getGridHandler().getSprite(id);
	}
	
	public void draw(Graphics g) {
		
		// Background
		g.setColor(new Color(47, 47, 47));
		g.fillRect(x, y, width, height);
	
		// Buttons
		drawButtons(g);
	}
	
	// Mouse Inputs
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		} else {
			for (Buttons b : gridButtons) {
				if (b.getBounds().contains(x, y)) {
					selectedGrid = playing.getGridHandler().getGrid(b.getId());
					playing.setSelectedGrid(selectedGrid);
					return;
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for (Buttons b : gridButtons) {
			b.setMouseOver(false);
		}
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else {
			for (Buttons b : gridButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else {
			for (Buttons b : gridButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBool();
		for (Buttons b : gridButtons) {
			b.resetBool();
		}
	}
}
