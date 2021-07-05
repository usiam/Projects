import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

@SuppressWarnings("serial")
public class Canvas extends JComponent {

	final double gravAcc = 9.81;
	Color color;
	int angle;
	int velocity;
	String typeOfExplosion;
	int time;

	public void drawLaunchArea() {// draws a green surface
		Graphics g = this.getGraphics();

		g.setColor(new Color(0, 102, 0));
		g.fillRect(0, getHeight() - 100, getWidth(), getHeight() - 100);
	}

	public void setDarkMode() {// sets night sky mode
		Graphics g = this.getGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);
		g.fillOval(4 * getWidth() / 5, getHeight() / 5, 150, 150);
	}

	public void setLightMode() {// sets day sky mode
		Graphics g = this.getGraphics();
		Color colorBlue = new Color(51, 153, 255);
		g.setColor(colorBlue);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.ORANGE);
		g.fillOval(4 * getWidth() / 5, getHeight() / 5, 150, 150);
	}

	public void drawNow() {// draws the trajectory
		Graphics g = this.getGraphics();
		int x = 0, y = getHeight() - 100;
		double radianAngle = angle * Math.PI / 180.0;
		g.setColor(color);

		for (double t = 0.1; t <= time; t += 0.01) {
			x = (int) (velocity * Math.cos(radianAngle) * t);
			y = getHeight() - 80 - (int) (velocity * Math.sin(radianAngle) * t - 0.5 * gravAcc * t * t);
			if (x > getWidth())
				break;
			if (y > getHeight())
				break;
			g.fillOval(x, y, 1, 1);

		}

		// depending on the selected radio-box calls one of the methods
		if (typeOfExplosion.equals("Standard")) {
			Standard(x, y, color);
		}
		if (typeOfExplosion.equals("Rings")) {
			Rings(x, y);
		}
		if (typeOfExplosion.equals("Star")) {
			Star(x, y, color);
		}
		if (typeOfExplosion.equals("Pokeball")) {
			Pokeball(x, y);
		}
		if (typeOfExplosion.equals("Disney")) {
			Disney(x, y);
		}

	}

	public void Standard(int x, int y, Color color) {// standard explosion
		Graphics g = this.getGraphics();
		g.setColor(color);
		for (int i = 0; i <= 10; i++) {
			g.drawLine(x, y, x - (getWidth() / 8) + (30 * i), y + (getHeight() / 2) - (5 * i));
			g.drawLine(x, y, x + (getWidth() / 8) + (30 * i), y - (getHeight() / 2) + (5 * i));
			g.drawLine(x, y, x + getWidth() / 15 + i * 30, y - getHeight() / 15 + i * 10);
			g.drawLine(x, y, x - getWidth() / 15 + i * 30, y - getHeight() / 15 + i * 10);
		}
	}

	public void Rings(int x, int y) {//draws concentric rings
		Graphics g = this.getGraphics();
		for (int i = 0; i < 6; i++) {
			if (i % 4 == 0)
				g.setColor(Color.RED);
			else if (i % 4 == 1)
				g.setColor(Color.BLUE);
			else if (i % 4 == 2)
				g.setColor(Color.YELLOW);
			else
				g.setColor(Color.GREEN);
			g.drawOval(x - getHeight() / 4 * i / 5, y - getWidth() / 20 * i / 5, getHeight() / 2 * i / 5,
					getWidth() / 10 * i / 5);
		}
	}

	public void Pokeball(int x, int y) { //draws a pokeball
		Graphics img = this.getGraphics();
		int p = getWidth() / 15;
		// Creates top half of Pokeball by just creating a whole oval.
		img.setColor(Color.RED);
		img.fillOval(x - (p / 2), y - (p / 2), p, p);
		// Creates bottom half of pokeball by drawing an arc (half circle) over top of
		// the bottom half of the red oval.
		img.setColor(Color.WHITE);
		img.fillArc(x - (p / 2), y - (p / 2), p, p, 0, -180);
		// Creates the black outline of the button in the center.
		img.setColor(Color.BLACK);
		img.fillOval(x - (p / 10), y - (p / 10), p / 5, p / 5);
		// Creates the white inside of the button in the center. It is one pixel smaller
		// than the black circle to show the line.
		img.setColor(Color.WHITE);
		img.fillOval(x - (p / 10) + 1, y - (p / 10) + 1, (p / 5) - 2, (p / 5) - 2);
	}

	public void Star(int x, int y, Color color) {// draws a polygon which happens to be a star
		Graphics g = this.getGraphics();
		int xPnts[] = { x, x + getWidth() / 55, x + getWidth() / 20, x + getWidth() / 50, x + getWidth() / 30, x,
				x - getWidth() / 30, x - getWidth() / 50, x - getWidth() / 20, x - getWidth() / 55, x };
		int yPnts[] = { y - getHeight() / 7, y - getHeight() / 40, y - getHeight() / 40, y + getHeight() / 24,
				y + getHeight() / 7, y + getHeight() / 10, y + getHeight() / 7, y + getHeight() / 24,
				y - getHeight() / 40, y - getHeight() / 40, y - getHeight() / 7 };

		g.setColor(color);
		g.fillPolygon(xPnts, yPnts, xPnts.length);
	}

	public void Disney(int x, int y) { //draws mickey mouse's head (no face)
		Graphics g = this.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(x - getWidth() / 20, y - getHeight() / 4, getWidth() / 10, getHeight() / 3);

		g.setColor(Color.BLACK);
		g.fillOval(x - getWidth() / 20 - getWidth() / 60, y - getHeight() / 4 - getHeight() / 10, getWidth() / 20,
				getHeight() / 6);
		g.fillOval(x - getWidth() / 19 - getWidth() / 60 + getWidth() / 12, y - getHeight() / 4 - getHeight() / 10,
				getWidth() / 20, getHeight() / 6);
	}

}
