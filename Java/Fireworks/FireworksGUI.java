import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class FireworksGUI extends JPanel {

	protected JLabel angleLabel;
	protected JLabel velocityLabel;
	protected JLabel timeLabel;
	protected JLabel currentAngle;
	protected JLabel currentVelocity;
	protected JLabel currentTime;

	// sliders to set angle and velocity
	protected JSlider angleSlider;
	protected JSlider velocitySlider;
	protected JSlider timeSlider;

	public FireworksGUI() {
		setLayout(new GridLayout(3, 1));

		angleLabel = new JLabel("Choose your angle: ");
		velocityLabel = new JLabel("Choose your velocity: ");
		timeLabel = new JLabel("Choose your time: ");

		currentAngle = new JLabel("Current angle: 25");
		currentVelocity = new JLabel("Current velocity: 100");
		currentTime = new JLabel("Current time: 5");

		angleSlider = new JSlider(JSlider.HORIZONTAL, 25, 80, 25);
		angleSlider.setMajorTickSpacing(5);
		angleSlider.setPaintTicks(true);

		velocitySlider = new JSlider(JSlider.HORIZONTAL, 100, 130, 100);
		velocitySlider.setMajorTickSpacing(5);
		velocitySlider.setPaintTicks(true);

		timeSlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 5);
		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintTicks(true);

		add(angleLabel);
		add(angleSlider);
		add(currentAngle);

		add(velocityLabel);
		add(velocitySlider);
		add(currentVelocity);

		add(timeLabel);
		add(timeSlider);
		add(currentTime);

		ChangerClass changer = new ChangerClass();

		velocitySlider.addChangeListener(changer);
		angleSlider.addChangeListener(changer);
		timeSlider.addChangeListener(changer);

	}

	public class ChangerClass implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent a) {
			if (a.getSource().equals(velocitySlider)) {
				currentVelocity.setText("Current velocity: " + velocitySlider.getValue());
			}
			if (a.getSource().equals(angleSlider)) {
				currentAngle.setText("Current angle: " + angleSlider.getValue());
			}
			if (a.getSource().equals(timeSlider)) {
				currentTime.setText("Current time: " + timeSlider.getValue());
			}
		}

	}

}