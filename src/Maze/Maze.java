package Maze;
import java.awt.Desktop;
import java.io.File;

import javax.swing.*;

public class Maze {
   public static void main(String[] args) {
	   File MazeCreate = new File("MazePathCreate.exe");
	   File MazePathCreate = new File("MazerPathResulttoallAStarAdvanced.exe");
	   try {
		 Desktop.getDesktop().open(MazeCreate);
		 Desktop.getDesktop().open(MazePathCreate);
		 Thread.sleep(2000);
	     new Maze();
	   }
	   catch(Exception ex) {
		   System.out.println(ex);
	   }
   }
   
   public Maze() {
	   JFrame f = new JFrame();
	   f.setTitle("Maze Game");
	   f.add(new Board());
	   f.setSize(1200, 1200);
	   f.setLocationRelativeTo(null);
	   f.setVisible(true);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
