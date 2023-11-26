package Maze;

import java.util.*;
import java.util.List;

import javax.swing.*;

import java.awt.*;
import java.io.*;

public class Map {
    
	private Scanner m, mPath;
	private String Map[] = new String[35];
	private Image grass, wall, finish, treasure;
	private String MapPath[] = new String[100000];
	List<int[]> valuesList = new ArrayList<>();
	char[][] finalMap = new char[35][35];
	
	public Map() {
		ImageIcon img = new ImageIcon("Photos\\grass.jpeg");
		grass = img.getImage();
		img = new ImageIcon("Photos\\wall.jpg");
		wall = img.getImage();
		img = new ImageIcon("Photos\\finishline.jpg");
		finish = img.getImage();
		img = new ImageIcon("Photos\\treasure.png");
		treasure = img.getImage();
		openFile();
		readFile();
		closeFile();
	}
	
	public Image getGrass() {
		return grass;
	}
	
	public Image getWall() {
		return wall;
	}
	
	public Image getfinish() {
		return finish;
	}
	
	public Image getTreasure() {
		return treasure;
	}
	
	public void setMapValue(int x, int y) {
        finalMap[x][y]='g';
	}
	
	public char getMap(int x, int y) {
		char mapValue = finalMap[x][y];
		return mapValue;
	}
	
	public void openFile() {
		try {
		  m = new Scanner(new File("Maze Paths\\NewMaze.txt"));
		  mPath = new Scanner(new File("Maze Paths\\output.txt"));
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int i=0; i<30; i++) {
				Map[i] = m.next();
				String line= Map[i];
				for(int j=0;j<line.length();j++) {
			        finalMap[i][j]=line.charAt(j);
				}
			}
		}
		
        while (mPath.hasNextLine()) {
            String line = mPath.nextLine();

            String[] values = line.split(" ");
            if (values.length == 2) {
                int value1 = Integer.parseInt(values[0]);
                int value2 = Integer.parseInt(values[1]);

                int[] pair = {value1, value2};
                valuesList.add(pair);
            } else {
                System.out.println("Invalid input on line: " + line);
            }
        }
	}
	
	public List<int[]> getvaluesList() {
		return valuesList;
	}
	
	public void closeFile() {
		m.close();
		mPath.close();
	}
	
}
