package guii;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import obj.Grid;
import scenes.Editor;

public class ToolBar extends Bar {
	private Editor editor;
	private Buttons bMenu, bSave;
	private Grid selectedGrid;
	
//	private ArrayList<Buttons> gridButtons = new ArrayList<>();
	
	private Map<Buttons, ArrayList<Grid>> map = new HashMap<Buttons, ArrayList<Grid>>();
	
	private Buttons 
	bGrass, 
	bWater,
	bPathStraight, 
	bPathCorner,
	bWaterCorner,
	bWaterSide,
	bWaterIsle;
	
	private Buttons currentButton;
	private int currentIndex = 0;
	
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
		
		// for-Loop (Selection of Grids)
		int i = 0;
		bGrass = new Buttons("Grass", xStart, yStart, width, height, i++);
		bWater = new Buttons("Water", xStart + xSpace, yStart, width, height, i++);
		
		initMapButton(bPathStraight, editor.getGame().getGridHandler().getPathStraight(), xStart, yStart, xSpace, width, height, i++);
		initMapButton(bPathCorner, editor.getGame().getGridHandler().getPathCorner(), xStart, yStart, xSpace, width, height, i++);
		initMapButton(bWaterCorner, editor.getGame().getGridHandler().getWaterCorner(), xStart, yStart, xSpace, width, height, i++);
		initMapButton(bWaterSide, editor.getGame().getGridHandler().getWaterSide(), xStart, yStart, xSpace, width, height, i++);
		initMapButton(bWaterIsle, editor.getGame().getGridHandler().getWaterIsle(), xStart, yStart, xSpace, width, height, i++);
		
	
	}
	
	private void initMapButton(Buttons b, ArrayList<Grid> list, int x, int y, int xSpace, int width, int height, int id) {
		b = new Buttons("", x + xSpace * id, y, width, height, id);
		map.put(b, list);

	}
	
	public void saveWorld() {
		editor.saveWorld();
	}
	
	public void rotateSprite() {
		currentIndex++;
		if(currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		selectedGrid = map.get(currentButton).get(currentIndex);
		editor.setSelectedGrid(selectedGrid);
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
		
		drawDefaultButtons(g, bGrass);
		drawDefaultButtons(g, bWater);
		drawSelectedGrid(g);
		drawMapButtons(g);
	}
	
	private void drawDefaultButtons(Graphics g, Buttons b) {
		g.drawImage(getButtonImg(b.getId()), b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);
	}

	private void drawMapButtons(Graphics g) {
		for(Map.Entry<Buttons, ArrayList<Grid>> entry : map.entrySet()) {
			Buttons b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();
			
			// Sprite
			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			
			drawButtonFeedback(g, b);
		}
	}

	private void drawButtonFeedback(Graphics g, Buttons b) {
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
	
	private void drawSelectedGrid(Graphics g) {
		if (selectedGrid != null) {
			g.drawImage(selectedGrid.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.white);
			g.drawRect(550, 650, 50, 50);
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
		else if (bWater.getBounds().contains(x, y)) {
			selectedGrid = editor.getGame().getGridHandler().getGrid(bWater.getId());
			editor.setSelectedGrid(selectedGrid);
			return;
		} else if (bGrass.getBounds().contains(x, y)) {
			selectedGrid = editor.getGame().getGridHandler().getGrid(bGrass.getId());
			editor.setSelectedGrid(selectedGrid);
			return;
		}else {
			for(Buttons b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					selectedGrid = map.get(b).get(0);
					editor.setSelectedGrid(selectedGrid);
					currentButton = b;
					currentIndex = 0;
					return;
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bWater.setMouseOver(false);
		bGrass.setMouseOver(false);
		

		for (Buttons b : map.keySet()) {
			b.setMouseOver(false);
		}

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if (bWater.getBounds().contains(x, y))
			bWater.setMouseOver(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMouseOver(true);
		else {
			for (Buttons b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
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
		else if (bWater.getBounds().contains(x, y))
			bWater.setMousePressed(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMousePressed(true);
		else {
			for (Buttons b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}
	}


	public void mouseReleased(int x, int y) {
		bMenu.resetBool();
		bSave.resetBool();
		bWater.resetBool();
		bGrass.resetBool();
		for (Buttons b : map.keySet()) {
			b.resetBool();
		}
	}
	
}
