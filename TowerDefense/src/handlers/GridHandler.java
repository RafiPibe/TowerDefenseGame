package handlers;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.util.ArrayList;

import helperMethods.LoadSave;
import helperMethods.SpriteFixer;
import obj.Grid;

import static helperMethods.Constants.Grids.*;

public class GridHandler {
	
	public Grid 
	GRASS, 
	
	PATH_LEFT_TO_RIGHT,
	PATH_TOP_TO_BOT,
	PATH_BOT_TO_RIGHT,
	PATH_LEFT_TO_BOT,
	PATH_LEFT_TO_TOP,
	PATH_TOP_TO_RIGHT,
	
	WATER, 
	WATER_CORNER_BOTLEFT,
	WATER_CORNER_TOPLEFT,
	WATER_CORNER_TOPRIGHT,
	WATER_CORNER_BOTRIGHT,
	
	WATERSIDE_TOP,
	WATERSIDE_RIGHT,
	WATERSIDE_LEFT,
	WATERSIDE_BOT,
	
	WATERISLE_TOPLEFT,
	WATERISLE_TOPRIGHT,
	WATERISLE_BOTLEFT,
	WATERISLE_BOTRIGHT;
	
	public BufferedImage sprite;
	public ArrayList<Grid> grids = new ArrayList<>();
	
	public ArrayList<Grid> pathStraight = new ArrayList<>();
	public ArrayList<Grid> pathCorner = new ArrayList<>();
	public ArrayList<Grid> waterCorner = new ArrayList<>();
	public ArrayList<Grid> waterSide = new ArrayList<>();
	public ArrayList<Grid> waterIsle = new ArrayList<>();
	
	public GridHandler() {
		
		loadSprite();
		createGrid();
	}

	private void createGrid() {
		int id = 0;
		grids.add(GRASS = new Grid(getSprite(9, 0), id++, GRASS_GRID));
		grids.add(WATER = new Grid(getSprite(0, 0), id++, WATER_GRID));
		
		pathStraight.add(PATH_LEFT_TO_RIGHT = new Grid(getSprite(8, 0), id++, PATH_GRID));
		pathStraight.add(PATH_TOP_TO_BOT = new Grid(SpriteFixer.getRotatedImg(getSprite(8, 0), 90), id++, PATH_GRID));
		
		pathCorner.add(PATH_BOT_TO_RIGHT = new Grid(getSprite(7, 0), id++, PATH_GRID));
		pathCorner.add(PATH_LEFT_TO_BOT = new Grid(SpriteFixer.getRotatedImg(getSprite(7, 0), 90), id++, PATH_GRID));
		pathCorner.add(PATH_LEFT_TO_TOP = new Grid(SpriteFixer.getRotatedImg(getSprite(7, 0), 180), id++, PATH_GRID));
		pathCorner.add(PATH_TOP_TO_RIGHT = new Grid(SpriteFixer.getRotatedImg(getSprite(7, 0), 270), id++, PATH_GRID));
		
		waterCorner.add(WATER_CORNER_BOTLEFT = new Grid(SpriteFixer.makeImg(getImgs(0, 0, 5, 0)), id++, WATER_GRID));
		waterCorner.add(WATER_CORNER_TOPLEFT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 5, 0), 90, 1), id++, WATER_GRID));
		waterCorner.add(WATER_CORNER_TOPRIGHT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 5, 0), 180, 1), id++, WATER_GRID));
		waterCorner.add(WATER_CORNER_BOTRIGHT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 5, 0), 270, 1), id++, WATER_GRID));
		
		waterSide.add(WATERSIDE_TOP = new Grid(SpriteFixer.makeImg(getImgs(0, 0, 6, 0)), id++, WATER_GRID));
		waterSide.add(WATERSIDE_RIGHT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 6, 0), 90, 1), id++, WATER_GRID));
		waterSide.add(WATERSIDE_BOT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 6, 0), 180, 1), id++, WATER_GRID));
		waterSide.add(WATERSIDE_LEFT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 6, 0), 270, 1), id++, WATER_GRID));
		
		waterIsle.add(WATERISLE_TOPLEFT = new Grid(SpriteFixer.makeImg(getImgs(0, 0, 4, 0)), id++, WATER_GRID));
		waterIsle.add(WATERISLE_TOPRIGHT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 4, 0), 90, 1), id++, WATER_GRID));
		waterIsle.add(WATERISLE_BOTRIGHT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 4, 0), 180, 1), id++, WATER_GRID));
		waterIsle.add(WATERISLE_BOTLEFT = new Grid(SpriteFixer.getMakeRotateImg(getImgs(0, 0, 4, 0), 270, 1), id++, WATER_GRID));
		
		grids.addAll(pathStraight);
		grids.addAll(pathCorner);
		grids.addAll(waterCorner);
		grids.addAll(waterSide);
		grids.addAll(waterIsle);
		
		
	}
	
	private BufferedImage[] getImgs(int firstX, int firstY, int secX, int secY) {
		return new BufferedImage[] {
			getSprite(firstX, firstY), getSprite(secX, secY)
		};
	}

	private void loadSprite() {
		sprite = LoadSave.getSprite();
	}
	
	// Getter Setter
	public Grid getGrid(int id) {
		return grids.get(id);
	}
	
	public BufferedImage getSprite(int id) {
		return grids.get(id).getSprite();
	}
	
	private BufferedImage getSprite(int x, int y) {
		return sprite.getSubimage(x * 32, y * 32, 32, 32);
	}

	public ArrayList<Grid> getPathStraight() {
		return pathStraight;
	}

	public ArrayList<Grid> getPathCorner() {
		return pathCorner;
	}

	public ArrayList<Grid> getWaterCorner() {
		return waterCorner;
	}

	public ArrayList<Grid> getWaterSide() {
		return waterSide;
	}

	public ArrayList<Grid> getWaterIsle() {
		return waterIsle;
	}

	
	
}
