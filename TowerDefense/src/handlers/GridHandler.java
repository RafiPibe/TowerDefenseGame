package handlers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helperMethods.LoadSave;
import obj.Grid;

public class GridHandler {
	
	public Grid GRASS, WATER, PATH;
	public BufferedImage sprite;
	public ArrayList<Grid> grids = new ArrayList<>();
	
	public GridHandler() {
		
		loadSprite();
		createGrid();
	}

	private void createGrid() {
		
		grids.add(GRASS = new Grid(getSprite(8, 1)));
		grids.add(WATER = new Grid(getSprite(0, 6)));
		grids.add(PATH = new Grid(getSprite(9, 0)));
		
	}

	private void loadSprite() {
		sprite = LoadSave.getSprite();
	}
	
	// Getter Setter
	public BufferedImage getSprite(int id) {
		return grids.get(id).getSprite();
	}
	
	private BufferedImage getSprite(int x, int y) {
		return sprite.getSubimage(x * 32, y * 32, 32, 32);
	}
}
