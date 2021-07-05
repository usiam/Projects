import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class FireworksGUI2 extends JPanel {
	protected JButton launchButton;
	protected JButton resetButton;
	protected JButton darkMode;
	protected JButton lightMode;
	
	protected Color color;
	protected String string;
	
	protected JLabel colorLabel;
	protected JLabel explosionTypeLabel;
	protected JLabel divider3;
	protected JLabel divider4;
	
	protected JRadioButton colorBoxRed;
	protected JRadioButton colorBoxBlue;
	protected JRadioButton colorBoxGreen;
	protected JRadioButton colorBoxPink;
	protected JRadioButton colorBoxYellow;

	// check boxes for each explosion type
	protected JRadioButton explosionBoxStandard;
	protected JRadioButton explosionBoxRings;
	protected JRadioButton explosionBoxPokeball;
	protected JRadioButton explosionBoxStar;
	protected JRadioButton explosionBoxDisney;

	public FireworksGUI2() {
		
		setLayout(new GridLayout(3, 4));
		ButtonGroup group = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();

		colorLabel = new JLabel("Choose color: ");
		explosionTypeLabel = new JLabel("Choose explosion type: ");

		divider3 = new JLabel("||");
		divider4 = new JLabel("||");

		launchButton = new JButton("LAUNCH!");
		resetButton = new JButton("RESET!");
		darkMode = new JButton("Toggle night sky mode.");
		lightMode = new JButton("Toggle day sky mode.");

		colorBoxRed = new JRadioButton("Red");
		colorBoxBlue = new JRadioButton("Blue");
		colorBoxGreen = new JRadioButton("Green");
		colorBoxPink = new JRadioButton("Pink");
		colorBoxYellow = new JRadioButton("Yellow");

		explosionBoxStandard = new JRadioButton("Standard");
		explosionBoxRings = new JRadioButton("Rings");
		explosionBoxPokeball = new JRadioButton("Pokeball");
		explosionBoxStar = new JRadioButton("Star");
		explosionBoxDisney = new JRadioButton("Disney");

		add(colorLabel);

		group.add(colorBoxRed);
		group.add(colorBoxBlue);
		group.add(colorBoxGreen);
		group.add(colorBoxPink);
		group.add(colorBoxYellow);

		add(colorBoxRed);
		add(colorBoxBlue);
		add(colorBoxGreen);
		add(colorBoxPink);
		add(colorBoxYellow);


		add(explosionTypeLabel);

		group2.add(explosionBoxStandard);
		group2.add(explosionBoxRings);
		group2.add(explosionBoxPokeball);
		group2.add(explosionBoxStar);
		group2.add(explosionBoxDisney);

		add(explosionBoxStandard);
		add(explosionBoxRings);
		add(explosionBoxPokeball);
		add(explosionBoxStar);
		add(explosionBoxDisney);

		add(darkMode);
		add(lightMode);
		

		add(launchButton);
		add(resetButton);

		
		CheckerClass check = new CheckerClass();

		colorBoxRed.addItemListener(check);
		colorBoxBlue.addItemListener(check);
		colorBoxGreen.addItemListener(check);
		colorBoxPink.addItemListener(check);
		colorBoxYellow.addItemListener(check);

		explosionBoxStandard.addItemListener(check);
		explosionBoxRings.addItemListener(check);
		explosionBoxPokeball.addItemListener(check);
		explosionBoxStar.addItemListener(check);
		explosionBoxDisney.addItemListener(check);

	}

	public class CheckerClass implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent i) {

			// colorBox
			if (i.getSource().equals(colorBoxRed)) {
				if (colorBoxRed.isSelected()) {
					color = Color.RED;
				}
			} else if (i.getSource().equals(colorBoxBlue)) {
				if (colorBoxBlue.isSelected()) {
					color = Color.BLUE;
				}
			} else if (i.getSource().equals(colorBoxGreen)) {
				if (colorBoxGreen.isSelected()) {
					color = Color.GREEN;
				}

			} else if (i.getSource().equals(colorBoxPink)) {
				if (colorBoxPink.isSelected()) {
					color = Color.PINK;
				}
			} else if (i.getSource().equals(colorBoxYellow)) {
				if (colorBoxYellow.isSelected()) {
					color = Color.YELLOW;
				}
			}

			// explosionBox
			if (i.getSource().equals(explosionBoxStandard)) {
				if (explosionBoxStandard.isSelected()) {
					string = "Standard";
				}
			}
			if (i.getSource().equals(explosionBoxRings)) {
				if (explosionBoxRings.isSelected()) {
					string = "Rings";
				}
			}
			if (i.getSource().equals(explosionBoxPokeball)) {
				if (explosionBoxPokeball.isSelected()) {
					string = "Pokeball";
				}
			}
			if (i.getSource().equals(explosionBoxStar)) {
				if (explosionBoxStar.isSelected()) {
					string = "Star";
				}
			}
			if (i.getSource().equals(explosionBoxDisney)) {
				if (explosionBoxDisney.isSelected()) {
					string = "Disney";
				}
			}
		}
	}// item event ends
}
