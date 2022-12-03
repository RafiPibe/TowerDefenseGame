package scenes;

import java.awt.Graphics;

public interface SceneMethods {
	// can easily add methods in all scenes here
	public void render(Graphics g);
	public void mouseClicked(int x, int y);
	public void mouseMoved(int x, int y);
	public void mousePressed(int x, int y);
	public void mouseReleased(int x, int y);
}
