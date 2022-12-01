package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GameScreen extends JPanel {
	
	private Game game;
	private Dimension size;
	
	private KeyboardInputs keyboardInputs;
	private MouseInputs mouseInputs;
	
	public GameScreen(Game game) {
		this.game = game;
		
		setPanelSize();	
		
	}
	
	public void initInputs() {
		
		keyboardInputs = new KeyboardInputs();
		mouseInputs = new MouseInputs();
		
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		addKeyListener(keyboardInputs);
		
		requestFocus();
		
	}
	
	private void setPanelSize() {
		
		size = new Dimension(640, 640);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		game.getRender().render(g);
		
	}
	
}
