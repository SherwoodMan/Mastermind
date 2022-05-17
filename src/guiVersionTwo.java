import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class guiVersionTwo extends JFrame implements ActionListener {

	private int turn;
	private Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	public void initializeStart() {
		JFrame box = new JFrame();

		ImageIcon logo = new ImageIcon(
				new ImageIcon("Mastermind_logo.png").getImage().getScaledInstance(400, 320, Image.SCALE_DEFAULT));
		Object[] options = { "Spieler 1", "Spieler 2" };
		int n = JOptionPane.showOptionDialog(box, "Welcher Spieler bist du?", "Mastermind",
				JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, logo, options, options[0]);

		if (n == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else if (n == JOptionPane.NO_OPTION) {
			System.out.println("funktioniert");

			this.initializeBoard();
		}
	}

	private void initializeBoard() {
		drawingField drawnField = new drawingField();
		tempName = new JButton[2][6];
		selectedColor = Color.WHITE;
		selectedColumn = -1;
		String[] colorsA = { "RED", "BLUE", "GREEN", "YELLOW", "MAGENTA", "DARK_GRAY" };
		String[] colorsNA = { "1", "2", "3", "4" };
		for (String s : colorsA) {
			colorsS.add(s);
		}
		for (String h : colorsNA) {
			colorsN.add(h);
		}
		JFrame frame = new JFrame();
		frame.setSize(1200, 700);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();

		tempName[0][0] = new JButton("CLEAR");
		tempName[0][0].addActionListener(this);
		tempName[0][0].setBackground(Color.PINK);
		tempName[0][0].setActionCommand("CLEAR");
		panel.add(tempName[0][0]);

		for (int y = 1; y < 5; y++) {
			tempName[0][y] = new JButton("" + y);
			tempName[0][y].setPreferredSize(new DimensionUIResource(100, 25));
			tempName[0][y].addActionListener(this);
			tempName[0][y].setBackground(Color.WHITE);
			tempName[0][y].setActionCommand("" + y);
			panel.add(tempName[0][y]);
		}

		tempName[0][5] = new JButton("CHECK");
		tempName[0][5].addActionListener(this);
		tempName[0][5].setBackground(Color.PINK);
		tempName[0][5].setActionCommand("CHECK");
		panel.add(tempName[0][5]);

		for (int x = 0; x < 6; x++) {
			tempName[1][x] = new JButton("");
			tempName[1][x].setPreferredSize(new DimensionUIResource(100, 25));
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));

			panel.add(tempName[1][x]);
		}
		drawnField.paintPins(2, new pins(2, 2));

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(drawnField, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

	}

	public void clearBoard() {
		for (int x = 1; x < 5; x++) {
			tempName[0][x].setBackground(Color.WHITE);
		}
		selectedColumn = -1;
		selectedColor = Color.WHITE;
	}

	public void actionPerformed(ActionEvent e) {
		if (colorsS.contains(e.getActionCommand())) {
			selectedColor = colors[colorsS.indexOf(e.getActionCommand())];
		} else if (colorsN.contains(e.getActionCommand())) {
			Integer i = new Integer(e.getActionCommand());
			selectedColumn = i.intValue();
		}
		if (selectedColumn != -1 && !(selectedColor.equals(Color.WHITE))) {
			tempName[0][selectedColumn].setBackground(selectedColor);
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}
		if ("CLEAR".equals(e.getActionCommand())) {
			this.clearBoard();
		}
		if ("CHECK".equals(e.getActionCommand())) {
			boolean checkable = true;
			for (int x = 1; x < 5; x++) {
				if (tempName[0][x].getBackground().equals(Color.WHITE)) {
					System.out.println("Error");
					checkable = false;
				}
			}
			if (checkable) {
				order toCompare = new order(tempName[0][1].getBackground(), tempName[0][2].getBackground(),
						tempName[0][3].getBackground(), tempName[0][4].getBackground());
				mastermind.getCon().compare(mastermind.getToGuess(), toCompare);
				this.clearBoard();
			}
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}

	}

	public static void main(String[] args) {
		guiVersionTwo gui = new guiVersionTwo();
		gui.initializeBoard();

	}
}
