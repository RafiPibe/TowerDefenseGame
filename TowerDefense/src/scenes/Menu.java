package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import guii.Buttons;
import main.Game;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;
	
	private Buttons bPlaying, bSettings, bQuit;
	
	public Menu(Game game) {
		super(game);
		random = new Random();
		importImg();
		loadSprites();
		initButtons();
	}

	private void initButtons() {
		
		int widthButton = 150;
		int heightButton = widthButton / 3;
		int x = 640 / 2 - widthButton / 2;
		int y = 150;
		int ySpace = 100;
		
		bPlaying = new Buttons("Play", x, y, widthButton, heightButton);
		bSettings = new Buttons("Settings", x, y + ySpace, widthButton, heightButton);
		bQuit = new Buttons("Quit", x, y + ySpace * 2, widthButton, heightButton);
	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
		
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/sprite.png");
		
		// try to import the image
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			//if it goes wrong then it will print an error message
			e.printStackTrace();
		}
	}
	
	private void loadSprites() {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}	
	}
	
	private int getRndInt() {
		return random.nextInt(100);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		}
		else if (bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		}
		else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		}
		else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		}	
		else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		}
		else if(bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		}
		else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bPlaying.resetBool();
		bSettings.resetBool();
		bQuit.resetBool();
		
	}
}
