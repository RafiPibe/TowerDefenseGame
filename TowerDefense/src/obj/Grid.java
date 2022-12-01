package obj;

import java.awt.image.BufferedImage;

public class Grid {
	
	private BufferedImage sprite;
	
	public Grid(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
}
