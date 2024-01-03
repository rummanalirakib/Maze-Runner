package Maze;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Maze implements ActionListener{
   public static void main(String[] args) {
	   File MazeCreate = new File("MazePathCreate.exe");
	   try {
		 Desktop.getDesktop().open(MazeCreate);

		 Maze m = new Maze();
	   //  new Maze();
		 m.InitialUIScreen();
	   }
	   catch(Exception ex) {
		   System.out.println(ex);
	   }
   }
   
   public void InitiateMaze() {
	   JFrame f = new JFrame();
	   f.setTitle("Maze Game");
	   f.add(new Board());
	   f.setSize(1500, 1050);
	   f.setLocationRelativeTo(null);
	   f.setVisible(true);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public void InitialUIScreen() {
	   JFrame f = new JFrame();
	   
	   JButton AStartbutton = new JButton("Start the Map With A* Search");
	   JButton BFSbutton = new JButton("Start the Map With BFS Search");
	   JButton DFSbutton = new JButton("Start the Map With DFS Search");
	   
	   // Set preferred size for the buttons
       Dimension buttonSize = new Dimension(500, 50);
       AStartbutton.setPreferredSize(buttonSize);
       BFSbutton.setPreferredSize(buttonSize);
       DFSbutton.setPreferredSize(buttonSize);
	   
	   AStartbutton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
        	   try {
	        	   File MazePathCreate = new File("MazerPathResulttoallAStarAdvanced.exe");
	      		   Desktop.getDesktop().open(MazePathCreate);
	      		   Thread.sleep(2000);
        	   }
        	   catch(Exception ex) {
        		   
        	   }
               f.dispose();
               Maze m = new Maze();
               m.InitiateMaze();
           }
       });
	   
	   BFSbutton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
        	   try {
	        	   File MazePathCreate = new File("MazePathResultBfs.exe");
	      		   Desktop.getDesktop().open(MazePathCreate);
	      		   Thread.sleep(2000);
        	   }
        	   catch(Exception ex) {
        		   
        	   }
               f.dispose();
               Maze m = new Maze();
               m.InitiateMaze();
           }
       });
	   
	   DFSbutton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
        	   try {
	        	   File MazePathCreate = new File("MazePathResultDfs.exe");
	      		   Desktop.getDesktop().open(MazePathCreate);
	      		   Thread.sleep(2000);
        	   }
        	   catch(Exception ex) {
        		   
        	   }
               f.dispose();
               Maze m = new Maze();
               m.InitiateMaze();
           }
       });


	   JPanel panel = new JPanel();
	   panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set BoxLayout for vertical arrangement
	   
	   // Add vertical glue to center the buttons
       panel.add(Box.createVerticalGlue());

       // Add buttons with a vertical strut for the gap
       int verticalGap = 10;
       panel.add(AStartbutton);
       panel.add(Box.createVerticalStrut(verticalGap));
       panel.add(BFSbutton);
       panel.add(Box.createVerticalStrut(verticalGap));
       panel.add(DFSbutton);

       // Add vertical glue to center the buttons
       panel.add(Box.createVerticalGlue());

	   
	   f.add(panel, BorderLayout.CENTER);
	   f.setSize(400, 550);
	   f.setLocationRelativeTo(null);
	   f.setVisible(true);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
   }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
