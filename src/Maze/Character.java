package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Character {
	
	private int tileX, tileY;
	
	private Image player;
	public Character() {
		
		tileX = 1;
		tileY = 1;
		
		ImageIcon img = new ImageIcon("Photos//runner.png");
		player = img.getImage();
	}
	
	public Image getPlayer() {
		return player;
	}
	
	public int getTileX() {
		return tileX;
	}
	
	public int getTileY() {
		return tileY;
	}
	
	public void move(int x, int y) {
		tileX = x;
		tileY = y;
	}

}
