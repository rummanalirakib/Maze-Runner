package Maze;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
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
		 Maze m = new Maze();
		 m.InitialUIScreen();
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

       JLabel titleLabel = new JLabel("All the options for maze runner");
       titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
       titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

       JButton CreateNewMaze = new JButton("Create a New Maze");
       JButton AStartOnlybutton = new JButton("Start the Map With A* Search but only one final goal");
       JButton AStartbutton = new JButton("Start the Map With A* Search");
       JButton BFSbutton = new JButton("Start the Map With BFS Search");
       JButton DFSbutton = new JButton("Start the Map With DFS Search");

       // Set preferred size for the buttons
       Dimension buttonSize = new Dimension(350, 70);
       CreateNewMaze.setPreferredSize(buttonSize);
       AStartOnlybutton.setPreferredSize(buttonSize);
       AStartbutton.setPreferredSize(buttonSize);
       BFSbutton.setPreferredSize(buttonSize);
       DFSbutton.setPreferredSize(buttonSize);

       CreateNewMaze.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
               try {
                   File MazeCreate = new File("MazePathCreate.exe");
                   Desktop.getDesktop().open(MazeCreate);
                   Thread.sleep(2000);
               } catch (Exception ex) {

               }
           }
       });

       AStartOnlybutton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
               try {
                   File MazePathCreate = new File("MazePathResultAStar.exe");
                   Desktop.getDesktop().open(MazePathCreate);
                   Thread.sleep(2000);
               } catch (Exception ex) {

               }
               f.dispose();
               Maze m = new Maze();
               m.InitiateMaze();
           }
       });

       AStartbutton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Close the JFrame
               try {
                   File MazePathCreate = new File("MazerPathResulttoallAStarAdvanced.exe");
                   Desktop.getDesktop().open(MazePathCreate);
                   Thread.sleep(2000);
               } catch (Exception ex) {

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
               } catch (Exception ex) {

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
               } catch (Exception ex) {

               }
               f.dispose();
               Maze m = new Maze();
               m.InitiateMaze();
           }
       });

       JPanel panel = new JPanel();
       panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
       panel.setLayout(new GridLayout(0, 1, 10, 10)); // Set GridLayout for one column

       panel.add(titleLabel);
       panel.add(CreateNewMaze);
       panel.add(AStartOnlybutton);
       panel.add(AStartbutton);
       panel.add(BFSbutton);
       panel.add(DFSbutton);

       f.add(panel, BorderLayout.CENTER);
       f.setSize(500, 500); // Adjusted size
       f.setLocationRelativeTo(null);
       f.setVisible(true);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
