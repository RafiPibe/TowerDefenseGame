package obj;

import java.awt.image.BufferedImage;

public class Grid {
	
	private BufferedImage sprite;
	private int id, gridType;
	
	public Grid(BufferedImage bufferedImages, int id, int gridType) {
		this.sprite = bufferedImages;
		this.id = id;
		this.gridType = gridType; 
	}
	
	// Getter
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public int getGridType() {
		return gridType;
	}
	
	public int getId() {
		return id;
	}
	
}
