package guii;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import obj.Grid;
import scenes.Editor;

public class ToolBar extends Bar {
	private Editor editor;
	private Buttons bMenu, bSave;
	private Grid selectedGrid;
	
	private ArrayList<Buttons> gridButtons = new ArrayList<>();
	
	public ToolBar(int x, int y, int width, int height, Editor editor) {
		super(x, y, width, height);
		this.editor = editor;
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new Buttons("Menu", 2, 642, 100, 30);
		bSave = new Buttons("Save", 2, 674, 100, 30);
		
		int width = 50;
		int height = 50;
		int xStart = 110;
		int yStart = 650;
		int xSpace = (int)(width * 1.1f);
		
		
		// for-Loop
		int i = 0;
		for (Grid grid : editor.getGame().getGridHandler().grids) {
			gridButtons.add(new Buttons(grid.getName(), xStart + xSpace * i, yStart, width, height, i++));
		}
	}
	
	public void saveWorld() {
		editor.saveWorld();
	}
	
	public void draw(Graphics g) {
		
		// Background
		g.setColor(new Color(47, 47, 47));
		g.fillRect(x, y, width, height);
	
		// Buttons
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
		
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
		return editor.getGame().getGridHandler().getSprite(id);
	}

	// Mouse Inputs
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (bSave.getBounds().contains(x, y))
			saveWorld();
		else {
			for (Buttons b : gridButtons) {
				if (b.getBounds().contains(x, y)) {
					selectedGrid = editor.getGame().getGridHandler().getGrid(b.getId());
					editor.setSelectedGrid(selectedGrid);
					return;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		for (Buttons b : gridButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else {
			for (Buttons b : gridButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else {
			for (Buttons b : gridButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBool();
		bSave.resetBool();
		for (Buttons b : gridButtons) {
			b.resetBool();
		}

	}

	
}
