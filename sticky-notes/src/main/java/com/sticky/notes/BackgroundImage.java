package com.sticky.notes;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BackgroundImage extends JFrame {
	private static final long serialVersionUID = 1L;

	JFrame frame;
	JLabel displayField;
	
	public BackgroundImage() {
		frame = new JFrame("Sticky-Notes App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			ImageIcon BGimage = new ImageIcon("C:/Users/DANIEL/Downloads/Group 3.png");
			displayField = new JLabel(BGimage);
			frame.add(displayField);
		} catch (Exception e) {
			System.out.println("Image cannot be found");
		}
		
		frame.setSize(1600, 1600);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		BackgroundImage i = new BackgroundImage();
	}

}
