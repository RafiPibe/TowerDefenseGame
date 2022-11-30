package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class Settings extends GameScene implements SceneMethods{

	public Settings(Game game) {
		super(game);
	}

	@Override
	public void render(Graphics g) {
		Color yes = new Color(255, 255, 194);
		g.setColor(yes);
		g.fillRect(0, 0, 640, 640);
	}

}
