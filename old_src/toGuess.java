import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.DimensionUIResource;

public class ToGuess extends JFrame implements ActionListener {


	private Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	private void initializeBoard() {
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
		frame.setTitle("Select colors...");
		frame.setSize(500, 200);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0, 2));

		JLabel uberschriftColor = new JLabel(" Bitte wählen Sie vier Farben aus!");
		panel.add(uberschriftColor);

		tempName[0][0] = new JButton("CLEAR");
		tempName[0][0].addActionListener(this);
		tempName[0][0].setBackground(Color.PINK);
		tempName[0][0].setActionCommand("CLEAR");
		panel2.add(tempName[0][0]);

		for (int y = 1; y < 5; y++) {
			tempName[0][y] = new JButton("" + y);
			tempName[0][y].setPreferredSize(new DimensionUIResource(80, 80));
			tempName[0][y].addActionListener(this);
			tempName[0][y].setBackground(Color.WHITE);
			tempName[0][y].setActionCommand("" + y);
			panel1.add(tempName[0][y]);
		}

		tempName[0][5] = new JButton("SEND");
		tempName[0][5].addActionListener(this);
		tempName[0][5].setBackground(Color.PINK);
		tempName[0][5].setActionCommand("SEND");
		panel2.add(tempName[0][5]);

		for (int x = 0; x < 6; x++) {
			tempName[1][x] = new JButton("");
			tempName[1][x].setPreferredSize(new DimensionUIResource(50, 25));
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));

			panel3.add(tempName[1][x]);
		}
		JButton hilfe = new JButton("?");
		panel2.add(hilfe);
		hilfe.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				JFrame hilfeFenster = new JFrame();
				hilfeFenster.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				hilfeFenster.setTitle("Hilfe");
				hilfeFenster.setSize(300, 400);
				hilfeFenster.setLocationRelativeTo(null);
				hilfeFenster.setVisible(true);

				JLabel hilfeText = new JLabel(
						"<html>Code-Setzer:<BR>Der Code-Setzer gewinnt eine Runde,<BR> wenn es dem Code-Knacker nicht gelingt,<BR> den Code zu entschlüsseln.</html>");
				hilfeFenster.add(hilfeText);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel, BorderLayout.PAGE_START);
		frame.add(panel1, BorderLayout.LINE_START);
		frame.add(panel2, BorderLayout.PAGE_END);
		frame.add(panel3, BorderLayout.LINE_END);
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
			boolean usable = true;
			for (int x = 1; x < 5; x++) {
				if (tempName[0][x].getBackground().equals(Color.WHITE)) {
					System.out.println("Error");
				 usable = false;
				}
			}
			if(usable) {
				Order toCompare = new Order(tempName[0][1].getBackground(), tempName[0][2].getBackground(),
						tempName[0][3].getBackground(), tempName[0][4].getBackground());
				Mastermind.getCon().compare(Mastermind.getToGuess(), toCompare);
				this.clearBoard();
			}
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}

	}

	public static void main(String[] args) {
		ToGuess gui = new ToGuess();
		gui.initializeBoard();

	}

}