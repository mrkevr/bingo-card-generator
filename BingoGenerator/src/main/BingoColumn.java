package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BingoColumn extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] element = new JButton[5];
	JLabel topLetter;
	Color markerColor = Color.GREEN;

	BingoColumn(char bingoLetter, int x, int y, Color color) {

		this.setLayout(new GridLayout(6, 1, 5, 5));
		this.setBounds(x, y, 50, 300);
		this.setBackground(color);

		// generates 5 random numbers in the column
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		int generatedNumber = 0;
		while (numbers.size() < 5) {

			switch (bingoLetter) {

			case 'B':
				generatedNumber = randomGenerator.nextInt(1, 16);
				break;
			case 'I':
				generatedNumber = randomGenerator.nextInt(16, 31);
				break;
			case 'N':
				generatedNumber = randomGenerator.nextInt(31, 46);
				break;
			case 'G':
				generatedNumber = randomGenerator.nextInt(46, 61);
				break;
			case 'O':
				generatedNumber = randomGenerator.nextInt(61, 76);
				break;
			}

			if (!numbers.contains(generatedNumber)) {
				numbers.add(generatedNumber);
			}
		}

		topLetter = new JLabel("" + bingoLetter);
		topLetter.setFont(new Font("Verdana", Font.BOLD, 40));
		topLetter.setHorizontalAlignment(JLabel.CENTER);
		this.add(topLetter);

		for (int i = 0; i < 5; i++) {

			element[i] = new JButton("" + numbers.get(i));
			element[i].setFont(new Font("Verdana", Font.BOLD, 25));
			element[i].setMargin(new Insets(0, 0, 0, 0));
			element[i].setHorizontalAlignment(JLabel.CENTER);
			element[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			element[i].setFocusable(false);
			element[i].setBackground(Color.WHITE);
			element[i].addActionListener(this);

			if (bingoLetter == 'N' && i == 2) {

				element[i].setText("FREE");
				element[i].setFont(new Font("Verdana", Font.BOLD, 12));
			}
			this.add(element[i]);
		}
	}

	public void setMarkerColor(Color markerColor) {

		this.markerColor = markerColor;

		for (int i = 0; i < 5; i++) {

			if (element[i].getBackground() != Color.WHITE) {

				element[i].setBackground(markerColor);

			}
		}
	}

	public void removeMarker() {
		for (JButton e : element) {
			e.setBackground(Color.WHITE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// puts/removes marker to the button/element
		for (JButton elem : element) {
			if (e.getSource() == elem) {

				if (elem.getBackground() == markerColor) {
					elem.setBackground(Color.WHITE);
				} else
					elem.setBackground(markerColor);

			}
		}
	}
}
