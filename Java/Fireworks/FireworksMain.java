import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FireworksMain {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Fireworks!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);

		FireworksGUI guiButtons = new FireworksGUI();
		Canvas canvas = new Canvas();
		FireworksGUI2 guiButtons2 = new FireworksGUI2();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		canvas.setLayout(new BorderLayout());
		
		mainPanel.add(guiButtons, BorderLayout.NORTH);
		mainPanel.add(canvas, BorderLayout.CENTER);
		mainPanel.add(guiButtons2, BorderLayout.SOUTH);
				
		guiButtons2.launchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				canvas.angle = guiButtons.angleSlider.getValue();
				canvas.velocity = guiButtons.velocitySlider.getValue();
				canvas.time = guiButtons.timeSlider.getValue();
				canvas.color = guiButtons2.color;
				canvas.typeOfExplosion = guiButtons2.string;
				canvas.drawNow();
			}

		
		});

		guiButtons2.resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guiButtons.angleSlider.setValue(25);
				guiButtons.currentAngle.setText("Current angle: 25");
				guiButtons.timeSlider.setValue(5);
				guiButtons.currentTime.setText("Current time: 5");
				guiButtons.velocitySlider.setValue(100);
				guiButtons.currentVelocity.setText("Current velocity: 100");
				guiButtons2.color = Color.RED;
				guiButtons2.colorBoxRed.setSelected(true);
				guiButtons2.string = "Standard";
				guiButtons2.explosionBoxStandard.setSelected(true);
				canvas.repaint();
				
			}

		});

		guiButtons2.darkMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setDarkMode();
				canvas.drawLaunchArea();
			}
		});

		guiButtons2.lightMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setLightMode();
				canvas.drawLaunchArea();
			}
		});

		frame.add(mainPanel);
		frame.setVisible(true);

	}
}
