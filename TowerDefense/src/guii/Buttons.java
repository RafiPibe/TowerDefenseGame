package guii;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Buttons {
	public int x, y, width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;
	
	// Buttons used on normal occasion
	public Buttons(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// just to make sure id doesn't get into this constructor
		this.id = -1;
		
		initBounds();
	}
	
	// Buttons used for Grids of sprite
	public Buttons(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		initBounds();
	}
	
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g) {
		// Body
		drawBody(g);
		
		// Outline
		drawOutline(g);
		
		// Text
		drawText(g);
		
		
	}

	private void drawBody(Graphics g) {
		if(mouseOver) {
			g.setColor(Color.gray);
		} else {
			g.setColor(Color.white);
		}
		
		g.fillRect(x, y, width, height);
		
	}
	
	private void drawOutline(Graphics g) {
		if(mousePressed) {
			g.setColor(Color.black);
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		} else {
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
		
	}

	private void drawText(Graphics g) {
		int textWidth = g.getFontMetrics().stringWidth(text);
		int textHeight = g.getFontMetrics().getHeight();
//		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		g.drawString(text, x - textWidth / 2 + width / 2, y + textHeight / 2 + height / 2);
		
	}
	
	public void resetBool() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	
	// Getter
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getId() {
		return id;
	}
}

