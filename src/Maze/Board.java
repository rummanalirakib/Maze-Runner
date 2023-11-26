package Maze;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
	 private Timer timer;
	 private Map m;
	 private Character character;
	 private String Message = "";
	 
	 private Font font = new Font("Serif", Font.BOLD, 48);
	 private Font textFont = new Font("Serif", Font.BOLD, 20);
	 private boolean win = false;
	 private boolean noPath = false;
	 private int totalTreasure = 0;
	 List<int[]> valuesList = new ArrayList<>();

	 public Board() {
		 m = new Map();
		 character = new Character();
		 addKeyListener(new AL());
		 setFocusable(true);
		 
		 timer = new Timer(525, this);
		 timer.start();
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(win==false) {
			for(int y=0; y<30; y++) {
				for(int x=0; x<30; x++) {
					if(m.getMap(x, y)=='g') {
						g.drawImage(m.getGrass(), x * 32, y * 32, null);
					}
					
					if(m.getMap(x, y)=='w') {
						g.drawImage(m.getWall(), x * 32, y * 32, null);
					}
					
					if(m.getMap(x, y)=='f') {
						g.drawImage(m.getfinish(), x * 32, y * 32, null);
					}
					
					if(m.getMap(x, y)=='d') {
						g.drawImage(m.getTreasure(), x * 32, y * 32, null);
					}
				}
			}
			g.drawImage(character.getPlayer(), character.getTileX() * 32, character.getTileY() * 32, null);
			g.setColor(Color.BLUE);
			g.setFont(textFont);
			g.drawString("Treasure Collected: " + totalTreasure, 960, 500);
		}
		
		if(win) {
			g.setColor(Color.BLUE);
			g.setFont(font);
			g.drawString(Message, 250, 600);
			g.drawString("Treasure Collected: " + totalTreasure, 360, 400);
		}
		
		if(noPath) {
			g.setColor(Color.BLUE);
			g.setFont(font);
			g.drawString("Sorry the path does not exist which will lead to the goal", 50, 500);
		}
	}
	
	public class AL extends KeyAdapter {
		 private Timer moveTimer;
		 public int moveCounter;

		    public AL() {
		    	moveCounter = 0;
		    	int[] indx = {0};
		    	moveTimer = new Timer(300, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	int pair[] = valuesList.get(indx[0]);
		                character.move(pair[0], pair[1]);
		                indx[0]++;
		                repaint();
		                moveCounter++;
		                if (m.getMap(pair[0], pair[1])=='f') {
		                	moveTimer.setRepeats(false);
		                	indx[0]=0;
		                	moveCounter=0;
		                	System.out.print("Goal Reached");
		                	win=true;
		                	Message = "Maze Runner Reached The Goal";
		                	repaint();
		                }
		                
		        		if(m.getMap(pair[0], pair[1])=='d'){
		        			totalTreasure += 1;
		        			m.setMapValue(character.getTileX(), character.getTileY());
		        		}
		            }
		        });
		    	moveTimer.setRepeats(true);
		    }

		    public void keyPressed(KeyEvent e) {
		        int keycode = e.getKeyCode();

		        if (keycode == KeyEvent.VK_ENTER) {
		        	valuesList = m.getvaluesList();
		        	System.out.println(valuesList.size());
		        	if(valuesList.size()>0) {
		        		moveTimer.start();	
		        	}
		        	else {
		        		noPath = true;
		        		repaint();
		        	}
		        }
		    }
		
		public void keyReleased(KeyEvent e) {
			
		}
		
		public void keyTyped(KeyEvent e) {
			
		}
	}
}
