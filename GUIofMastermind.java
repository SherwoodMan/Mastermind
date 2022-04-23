
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class GUIofMastermind extends JFrame implements ActionListener {

	private int turn;
	private GridLayout grid;
	private GridLayout grid1;
	private GridLayout grid2;
	private JTable Signale;
	private JTable Stecker;
	private Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	public JFrame initializeBoard() {
		turn = 1;
		tempName = new JButton[2][6];
		grid = new GridLayout(2, 6);
		grid1 = new GridLayout(1, 1);
		grid2 = new GridLayout(1, 2);
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
		frame.setLayout(new GridLayout(2, 2));
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Signale",
				TitledBorder.CENTER, TitledBorder.TOP));
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Stecker",
				TitledBorder.CENTER, TitledBorder.TOP));
		Stecker = new JTable(10, 4);
		Signale = new JTable(10, 4);
		panel2.add(Stecker);
		panel3.add(Signale);
		frame.add(panel2);
		frame.add(panel3);

		panel.setLayout(grid);
		panel2.setLayout(grid1);
		panel3.setLayout(grid2);

		// panel.setSize(50, 100);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(panel2, BorderLayout.WEST);
		frame.getContentPane().add(panel3, BorderLayout.PAGE_END);
		frame.setTitle("Mastermind");

		tempName[0][0] = new JButton("CLEAR");
		tempName[0][0].addActionListener(this);
		tempName[0][0].setBackground(Color.PINK);
		tempName[0][0].setActionCommand("CLEAR");
		panel.add(tempName[0][0]);

		for (int y = 1; y < 5; y++) {
			tempName[0][y] = new JButton("" + y);
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
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));
			panel.add(tempName[1][x]);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setPreferredSize(new Dimension (1300,600));
		frame.pack();
		frame.setVisible(true);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		return frame;
	}

	public void actionPerformed(ActionEvent e) {
		// getSource action listener: if in tempName[1][?], set selectedColor, if in
		// tempName[0][1-4], set selectedRo
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
			for (int x = 1; x < 5; x++) {
				tempName[0][x].setBackground(Color.WHITE);
			}
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}

	}

	public static void main(String[] args) {
		GUIofMastermind newgame = new GUIofMastermind();
		JFrame box = new JFrame();
		ImageIcon logo = new ImageIcon("Mastermind_logo.png");

		Object[] options = { "Spieler 1", "Spieler 2" };
		int n = JOptionPane.showOptionDialog(box, "Welcher Spieler bist du?", "Mastermind",
				JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, logo, options, options[0]);

		if (n == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else if (n == JOptionPane.NO_OPTION) {
			System.out.println("funktioniert");

			newgame.initializeBoard();
		}
	}
}
