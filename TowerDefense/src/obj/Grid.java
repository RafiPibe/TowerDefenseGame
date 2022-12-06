package obj;

import java.awt.image.BufferedImage;

public class Grid {
	
	private BufferedImage sprite;
	private int id;
	private String name;
	
	public Grid(BufferedImage sprite, int id, String name) {
		this.sprite = sprite;
		this.id = id;
		this.name = name;
	}
	
	// Getter
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	
}
