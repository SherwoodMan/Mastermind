import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MasterGUI extends JFrame implements ActionListener {

	private DrawingMasterField drawnMaster;

	private Color[] colors = { Color.BLACK, Color.WHITE };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	public void initializeMasterBoard() {
		drawnMaster = new DrawingMasterField();

		tempName = new JButton[2][6];
		selectedColor = Color.GRAY;
		selectedColumn = -1;
		String[] colorsA = { "BLACK", "WHITE" };
		String[] colorsNA = { "1", "2", "3", "4" };
		for (String s : colorsA) {
			colorsS.add(s);
		}
		for (String h : colorsNA) {
			colorsN.add(h);
		}
		JFrame frame = new JFrame("MasterFrame");
		frame.setSize(1200, 700);
		frame.setLayout(null);

		tempName[0][0] = new JButton("CLEAR");
		tempName[0][0].addActionListener(this);
		tempName[0][0].setBackground(Color.PINK);
		tempName[0][0].setActionCommand("CLEAR");
		tempName[0][0].setBounds(10, 340, 85, 30);
		frame.add(tempName[0][0]);

		for (int y = 1; y < 5; y++) {
			
			tempName[0][y] = new JButton("" + y);
			tempName[0][y].addActionListener(this);
			tempName[0][y].setBackground(Color.GRAY);
			tempName[0][y].setActionCommand("" + y);
			tempName[0][y].setBounds(10, 90+ y*50, 180, 25);
			frame.add(tempName[0][y]);
		}
		
		tempName[0][5] = new JButton("CHECK");
		tempName[0][5].addActionListener(this);
		tempName[0][5].setBackground(Color.PINK);
		tempName[0][5].setActionCommand("CHECK");
		tempName[0][5].setBounds(105, 340, 85, 30);
		frame.add(tempName[0][5]);

		for (int x = 0; x < 2; x++) {
			
			tempName[1][x] = new JButton("");
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));
			tempName[1][x].setBounds(200+x*150, 25, 100, 50);
			frame.add(tempName[1][x]);
		}
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		drawnMaster.setBounds(200, 100, 1100, 600);
		frame.add(drawnMaster);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

	}

	public void clearBoard() {
		for (int x = 1; x < 5; x++) {
			tempName[0][x].setBackground(Color.GRAY);
		}
		selectedColumn = -1;
		selectedColor = Color.GRAY;
	}

	public void actionPerformed(ActionEvent e) {
		if (colorsS.contains(e.getActionCommand())) {
			selectedColor = colors[colorsS.indexOf(e.getActionCommand())];
		} else if (colorsN.contains(e.getActionCommand())) {
			Integer i = new Integer(e.getActionCommand());
			selectedColumn = i.intValue();
		}
		if (selectedColumn != -1 && !(selectedColor.equals(Color.GRAY))) {
			tempName[0][selectedColumn].setBackground(selectedColor);
			selectedColumn = -1;
			selectedColor = Color.GRAY;
		}
		if ("CLEAR".equals(e.getActionCommand())) {
			this.clearBoard();
		}
		if ("CHECK".equals(e.getActionCommand())) {
			Pins toCompare = new Pins(tempName[0][1].getBackground(), tempName[0][2].getBackground(),
					tempName[0][3].getBackground(), tempName[0][4].getBackground());
			drawnMaster.paintPins(Mastermind.getRound(), toCompare);
			Mastermind.setRound(Mastermind.getRound() + 1);
			this.clearBoard();
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}

	}

}