  /*Name: Uzair Tahamid Siam
  NetID: usiam
  Lab section: MW 4:50-6:05 p.m
  Project: 03
  
  Contributors/Collaborators -  
 
  Name: Haolong Liu
  NetID: hliu57
  
  Name: Omri Goldenberg
  NetID: ogolden3*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPaneWithBackrgound extends Canvas {
	Image img;

	public JPaneWithBackrgound(){
		img = Toolkit.getDefaultToolkit().getImage("background1.jpg");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, 1000,700, this);
	}
}