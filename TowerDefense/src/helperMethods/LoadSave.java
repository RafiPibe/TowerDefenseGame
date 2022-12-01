package helperMethods;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	public static BufferedImage getSprite() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("sprite.png");
		
		// try to import the image
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			//if it goes wrong then it will print an error message
			e.printStackTrace();
		}
		
		return img;
	}
}
