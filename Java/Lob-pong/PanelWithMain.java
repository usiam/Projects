  /*Name: Uzair Tahamid Siam
  NetID: usiam
  Lab section: MW 4:50-6:05 p.m
  Project: 03
  
  Contributors/Collaborators -  
 
  Name: Haolong Liu
  NetID: hliu57
  
  Name: Omri Goldenberg
  NetID: ogolden3*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.*;
import javax.sound.sampled.*;

//FIXME: How to stop music 
public class PanelWithMain extends JFrame implements KeyListener {
	Canvas canvas = new Canvas();
	boolean a = false;
	boolean d = false;
	Random randGen = new Random();
	int time = 0;
	boolean check = false;
	// final double gravity = 69.1;

	PanelWithMain() {

		addKeyListener(this);
		setFocusable(true);
		setTitle("Lob Pong");
		setSize(1000, 700);
		setLayout(new BorderLayout());
		try {
			File cartoon;
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;
			
			cartoon = new File("Intergalactic-Odyssey.wav");
			stream = AudioSystem.getAudioInputStream(cartoon);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception ea) {
			System.out.println(ea.getMessage());
		}
		Timer music = new Timer(88000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File cartoon;
					AudioInputStream stream;
					AudioFormat format;
					DataLine.Info info;
					Clip clip;
					cartoon = new File("Intergalactic-Odyssey.wav");
					stream = AudioSystem.getAudioInputStream(cartoon);
					format = stream.getFormat();
					info = new DataLine.Info(Clip.class, format);
					clip = (Clip) AudioSystem.getLine(info);
					clip.open(stream);
					clip.start();
				} catch (Exception ea) {
					System.out.println(ea.getMessage());
				}
			}
		});

		Timer t = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (a && canvas.xPaddle >= 0) // changed here
					canvas.xPaddle -= 8;
				if (d && canvas.xPaddle <= getWidth() - 115) // changed here
					canvas.xPaddle += 8;
				canvas.ySpeed -= 10 * 50 * (1.0 / 90);
				canvas.x += canvas.xSpeed * (1.0 / 50);
				canvas.y -= (canvas.ySpeed * (1.0 / 50));
				if ((canvas.x > getWidth() - 40) || (canvas.x < 0))// optimization may be needed
					canvas.xSpeed = -canvas.xSpeed;
				if (canvas.y < 0 && !check) {
					canvas.ySpeed = -canvas.ySpeed * (randGen.nextInt(3) / 10.0 + 0.8);
					check = true;//
				}
				if ((intersects((int) canvas.x + 10, (int) canvas.y + 10, 10, canvas.xPaddle, getHeight() - 60, 100,
						30)) && !check)// bug needs fixing
				{
					check = true;
					int randNum = randGen.nextInt(6); //
					canvas.ySpeed = (-canvas.ySpeed * (randGen.nextInt(6) / 10.0 + 0.7)); //
					canvas.xSpeed = canvas.xSpeed; //
					if (canvas.ySpeed <= 500) //
						canvas.ySpeed = 500;
					if (randNum < 2) //
						canvas.xSpeed = -canvas.xSpeed;
					try {
						File cartoon = new File("cartoon.wav");
						AudioInputStream stream;
						AudioFormat format;
						DataLine.Info info;
						Clip clip;
						stream = AudioSystem.getAudioInputStream(cartoon);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip) AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch (Exception ea) {
					
					}

					canvas.points++;

				}
				boolean hit = false;
				if ((canvas.x >= canvas.rect1[0]) && (canvas.x <= canvas.rect1[0] + canvas.rect1[2])
						&& (Math.pow(canvas.y - canvas.rect1[1], 2) < 100) && !check) {
					canvas.ySpeed = -canvas.ySpeed;
					hit = true;
				}
				if ((canvas.x >= canvas.rect2[0]) && (canvas.x <= canvas.rect2[0] + canvas.rect2[2])
						&& (Math.pow(canvas.y - canvas.rect2[1], 2) < 100) && !check) {
					canvas.ySpeed = -canvas.ySpeed;
					hit = true;
				}
				if ((canvas.x >= canvas.rect3[0]) && (canvas.x <= canvas.rect3[0] + canvas.rect3[2])
						&& (Math.pow(canvas.y - canvas.rect3[1], 2) < 100) && !check) {
					hit = true;
					canvas.ySpeed = -canvas.ySpeed;
				}
				if ((canvas.x >= canvas.rect1[0]) && (canvas.x <= canvas.rect1[0] + canvas.rect1[2])
						&& (Math.pow(canvas.y - (canvas.rect1[1] + canvas.rect1[3]), 2) < 100) && !check) {
					canvas.ySpeed = -canvas.ySpeed;
					hit = true;
				}
				if ((canvas.x >= canvas.rect2[0]) && (canvas.x <= canvas.rect2[0] + canvas.rect2[2])
						&& (Math.pow(canvas.y - (canvas.rect2[1] + canvas.rect2[3]), 2) < 100) && !check) {
					canvas.ySpeed = -canvas.ySpeed;
					hit = true;
				}
				if ((canvas.x >= canvas.rect3[0]) && (canvas.x <= canvas.rect3[0] + canvas.rect3[2])
						&& (Math.pow(canvas.y - (canvas.rect3[1] + canvas.rect3[3]), 2) < 100) && !check) {
					hit = true;
					canvas.ySpeed = -canvas.ySpeed;
				}

				if (canvas.y >= canvas.rect1[1] && canvas.y <= canvas.rect1[1] + canvas.rect1[3]
						&& Math.pow(canvas.x - canvas.rect1[0], 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}
				if (canvas.y >= canvas.rect2[1] && canvas.y <= canvas.rect2[1] + canvas.rect2[3]
						&& Math.pow(canvas.x - canvas.rect2[0], 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}
				if (canvas.y >= canvas.rect3[1] && canvas.y <= canvas.rect3[1] + canvas.rect3[3]
						&& Math.pow(canvas.x - canvas.rect3[0], 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}
				if (canvas.y >= canvas.rect1[1] && canvas.y <= canvas.rect1[1] + canvas.rect1[3]
						&& Math.pow(canvas.x - (canvas.rect1[0] + canvas.rect1[2]), 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}
				if (canvas.y >= canvas.rect2[1] && canvas.y <= canvas.rect2[1] + canvas.rect2[3]
						&& Math.pow(canvas.x - (canvas.rect2[0] + canvas.rect2[2]), 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}
				if (canvas.y >= canvas.rect3[1] && canvas.y <= canvas.rect3[1] + canvas.rect3[3]
						&& Math.pow(canvas.x - (canvas.rect3[0] + canvas.rect3[2]), 2) < 100 && !check) {
					canvas.xSpeed = -canvas.xSpeed;
					hit = true;
				}

				if (hit) {
					try {
						File cartoon = new File("cartoon.wav");
						AudioInputStream stream;
						AudioFormat format;
						DataLine.Info info;
						Clip clip;
						stream = AudioSystem.getAudioInputStream(cartoon);
						format = stream.getFormat();
						info = new DataLine.Info(Clip.class, format);
						clip = (Clip) AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					} catch (Exception ea) {
					
					}
					check = true;
				}
				if (canvas.y >= getHeight()) {
					canvas.lives--;
					canvas.y = 0;
					canvas.x = 0;
					canvas.ySpeed = -150;
				}
				if (check) {
					time++;
					if (time > 5) {
						time = 0;
						check = false;
					}
				}
				repaint();
				canvas.time -= 20 / 1000.0;
			}
		});
		add(canvas);
		t.start();
		music.start();
	}

	// main
	public static void main(String[] args) {
		PanelWithMain p = new PanelWithMain();
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setVisible(true);
	}

	public boolean intersects(int xC, int yC, int r, int x, int y, int w, int h) {// checks for nearest point on rect
																					// and check if distance between it
																					// and center of circle is less than
																					// radius
		int nearestX = Math.max(x, Math.min(xC, x + w));
		int nearestY = Math.max(y, Math.min(yC, y + h));
		int dX = xC - nearestX;
		int dY = yC - nearestY;
		return (dX * dX + dY * dY) < (r * r);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			a = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			d = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			a = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			d = false;
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (canvas.ySpeed > 0)
				canvas.ySpeed += 50;
			else
				canvas.ySpeed -= 50;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (canvas.ySpeed > 0)
				canvas.ySpeed -= 50;
			else
				canvas.ySpeed += 50;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
