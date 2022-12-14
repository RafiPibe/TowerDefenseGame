package helperMethods;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteFixer {
	
	// Rotate
	public static BufferedImage getRotatedImg(BufferedImage img, int rotateAngle) {
		int width = img.getWidth();
		int height = img.getHeight();
		
		BufferedImage newImg = new BufferedImage(width, height, img.getType());
		Graphics2D g2d = newImg.createGraphics();
		
		g2d.rotate(Math.toRadians(rotateAngle), width / 2, height / 2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return newImg;
	}
	
	// Making an Img
	public static BufferedImage makeImg(BufferedImage[] imgs) {
		int width = imgs[0].getWidth();
		int height = imgs[0].getHeight();
		
		BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for(BufferedImage img : imgs) {
			g2d.drawImage(img, 0, 0, null);
		}
		
		g2d.dispose();
		return newImg;
	}
	
	// Rotate 2nd Img
	public static BufferedImage getMakeRotateImg(BufferedImage[] imgs, int rotateAngle, int rotateAtIndex) {
		int width = imgs[0].getWidth();
		int height = imgs[0].getHeight();
		
		BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for(int i = 0; i < imgs.length; i++) {
			if(rotateAtIndex == i) {
				g2d.rotate(Math.toRadians(rotateAngle), width / 2, height / 2);
			}
			g2d.drawImage(imgs[i], 0, 0, null);
			
			if(rotateAtIndex == i) {
				g2d.rotate(Math.toRadians(-rotateAngle), width / 2, height / 2);
			}
		}

		g2d.dispose();
		return newImg;
		
	}
	
}
