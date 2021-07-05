  /*Name: Uzair Tahamid Siam
  NetID: usiam
  Lab section: MW 4:50-6:05 p.m
  Project: 03
  
  Contributors/Collaborators -  
 
  Name: Haolong Liu
  NetID: hliu57
  
  Name: Omri Goldenberg
  NetID: ogolden3*/

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.util.Random;

public class Canvas extends JComponent {
	double x = 0;
	double y = 0;
	double xSpeed = 180;
	double ySpeed = -50;
	int lives = 3;
	int xPaddle = 0;
	double time = 120;
	int points = 0;
	int rect1[] = new int[4];
	int rect2[] = new int[4];
	int rect3[] = new int[4];
	int check = 0;
	Random rnd = new Random();
	BufferedImage img = null;
		
	public Canvas() {
		try {
			img = ImageIO.read(new File("background2.jpg"));
			
		} catch (Exception e) {
			
			System.out.println("File not found.");
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		if (check == 0) {
			rect1[0] = rnd.nextInt((getWidth() - 100) / 3);
			rect2[0] = rnd.nextInt((getWidth() - 100)/3) + ((getWidth() - 100) / 3);
			rect3[0] = rnd.nextInt((getWidth() - 100)/3) + 2 * ((getWidth() - 100) / 3);
			
			rect1[1] = rnd.nextInt(2 *(getHeight() / 3));
			rect2[1] = rnd.nextInt(2 *(getHeight() / 3));
			rect3[1] = rnd.nextInt(2 *(getHeight() / 3));
			rect1[2] = 80;
			rect2[2] = 80;
			rect3[2] = 80;
			rect1[3] = 50;
			rect2[3] = 50;
			rect3[3] = 50;
			check++;
		}
		if (time < 0) {
			int width = getWidth();
			int height = getHeight();

			// clear the background
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);

			// center a string in a box
			int boxWidth = width;
			int boxHeight = height;
			int boxX = (width - boxWidth) / 2;
			int boxY = (height - boxHeight) / 2;

			g.setColor(Color.darkGray);
			g.fillRect(boxX, boxY, boxWidth, boxHeight);
			g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 50));
			g.setColor(Color.orange);
			drawCenteredString(g, "TIME OVER! Your score is " + points + ".", boxX, boxY, boxWidth, boxHeight);

		} else if (lives > 0) {
			
			g.setColor(Color.WHITE);
			g.fillRect(rect1[0], rect1[1], rect1[2], rect1[3]);
			g.fillRect(rect2[0], rect2[1], rect2[2], rect2[3]);
			g.fillRect(rect3[0], rect3[1], rect3[2], rect3[3]);
			g.setFont(new Font("TimesRoman", Font.BOLD, 80));
			g.drawString(Integer.toString(points), 0, 80);
			g.setColor(Color.GREEN);
			g.fillRect((int) (getWidth() - 120 + ((120 - time) / 120) * 120), 100,
					(int) (120 - ((120 - time) / 120) * 120), 30);
			g.setColor(Color.RED);
			g.fillOval((int) x, (int) y, 20, 20);
			g.setColor(Color.BLUE);
			g.fillRect(xPaddle, getHeight() - 30, 100, 30);
			g.setColor(Color.BLACK);
			/*d*/
			g.setColor(Color.RED);
			for (int i = 0; i < lives; i++) {
				g.fillOval(getWidth() - 40 - 50 * i, 30, 40, 40);
			}
		}
		// centered text (NEW)
		else {
			// get the dimensions of the draw area
			int width = getWidth();
			int height = getHeight();

			// clear the background
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);

			// center a string in a box
			int boxWidth = width;
			int boxHeight = height;
			int boxX = (width - boxWidth) / 2;
			int boxY = (height - boxHeight) / 2;

			g.setColor(Color.darkGray);
			g.fillRect(boxX, boxY, boxWidth, boxHeight);
			g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 50));
			g.setColor(Color.orange);
			drawCenteredString(g, "GAME OVER! Your score is " + points + ".", boxX, boxY, boxWidth, boxHeight);
		}
	}

	public void drawCenteredString(Graphics page, String s, int x, int y, int width, int height) {
		// Find the size of string s in the font of the Graphics context "page"
		FontMetrics fm = page.getFontMetrics(page.getFont());
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, page);
		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());

		// Center text horizontally and vertically within provided rectangular bounds
		int textX = x + (width - textWidth) / 2;
		int textY = y + (height - textHeight) / 2 + fm.getAscent();
		page.drawString(s, textX, textY);
	}
}
