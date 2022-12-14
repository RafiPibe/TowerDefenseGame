package handlers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helperMethods.*;
import scenes.Playing;
import static helperMethods.Constants.Direction.*;
import static helperMethods.Constants.Grids.*;

public class EnemyHandler {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private float speed = 0.5f;

	public EnemyHandler(Playing playing) {
		this.playing = playing;
		enemyImgs = new BufferedImage[4];
		addEnemy(3 * 32, 9 * 32);
		loadEnemyImgs();
	}

	private void loadEnemyImgs() {
		BufferedImage sprite = LoadSave.getSprite();
		enemyImgs[0] = sprite.getSubimage(0, 32, 32, 32);
		enemyImgs[1] = sprite.getSubimage(32, 32, 32, 32);
		enemyImgs[2] = sprite.getSubimage(2 * 32, 32, 32, 32);
		enemyImgs[3] = sprite.getSubimage(3 * 32, 32, 32, 32);
	}

	public void update() {
		for (Enemy e : enemies) {
			if (isNextTileRoad(e)) {
				//TODO: Change this. The return is pointless here.
				// Brain farts sometimes when you code and record at the
				// same time. 
			}
		}
	}

	public boolean isNextTileRoad(Enemy e) {
		int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection()));
		int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDirection()));

		if (getGridType(newX, newY) == PATH_GRID) {
			e.move(speed, e.getLastDirection());
		} else if (isAtEnd(e)) {
			// reached the end
		} else {
			setNewDirectionAndMove(e);
		}
		return false;
	}

	private void setNewDirectionAndMove(Enemy e) {
		int direction = e.getLastDirection();

		int xCord = (int) (e.getX() / 32);
		int yCord = (int) (e.getY() / 32);

		fixEnemyOffsetTile(e, direction, xCord, yCord);

		if (direction == LEFT || direction == RIGHT) {
			int newY = (int) (e.getY() + getSpeedAndHeight(UP));
			if (getGridType((int) e.getX(), newY) == PATH_GRID)
				e.move(speed, UP);
			else
				e.move(speed, DOWN);
		} else {
			int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT));
			if (getGridType(newX, (int) e.getY()) == PATH_GRID)
				e.move(speed, RIGHT);
			else
				e.move(speed, LEFT);

		}

	}

	private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
		switch (dir) {
		case RIGHT:
			if (xCord < 19)
				xCord++;
			break;
		case DOWN:
			if (yCord < 19)
				yCord++;
			break;
		}

		e.setPosition(xCord * 32, yCord * 32);

	}

	private boolean isAtEnd(Enemy e) {
		return false;
	}

	private int getGridType(int x, int y) {
		return playing.getGridType(x, y);
	}

	private float getSpeedAndHeight(int dir) {
		if (dir == UP)
			return -speed;
		else if (dir == DOWN)
			return speed + 32;

		return 0;
	}

	private float getSpeedAndWidth(int dir) {
		if (dir == LEFT)
			return -speed;
		else if (dir == RIGHT)
			return speed + 32;

		return 0;
	}

	public void addEnemy(int x, int y) {
		enemies.add(new Enemy(x, y, 0, 0));
	}

	public void draw(Graphics g) {
		for (Enemy e : enemies)
			drawEnemy(e, g);

	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[0], (int) e.getX(), (int) e.getY(), null);
	}

}
