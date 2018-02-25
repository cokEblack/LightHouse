package de.cau.infprogoo.lighthouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
 public  Window(int width, int height,Game game) {
	 JFrame window = new JFrame("BreakOut");
	 
	 
	 
	 window.setPreferredSize(new Dimension(width,height));
	 window.setMaximumSize(new Dimension(width,height));
	 window.setMinimumSize(new Dimension(width,height));
	 

	 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 window.setResizable(true);
	 window.setLocationRelativeTo(null);
	 window.add(game);
	 window.setVisible(true);
	 window.requestFocus();
	 game.start();
 }		
 
}
