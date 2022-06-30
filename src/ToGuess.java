import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;


/*
 * Diese Klasse bearbietet alle Aktionen die für die Farbauswahl der zu erratenen Farbreihenfolge gebraucht wird 
 */

public class ToGuess implements ActionListener {


	private Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	private ToGuessListener colorsChosenListener = null;

	private JFrame frame;


	/*
	 * Diese Methode baut das Fenster mit der Farbauswahl und den Buttons für die Interaktion auf
	 */

	public void initializeBoard() {
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
		frame = new JFrame();
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

		tempName[0][5] = new JButton("SUBMIT");
		tempName[0][5].addActionListener(this);
		tempName[0][5].setBackground(Color.PINK);
		tempName[0][5].setActionCommand("SUBMIT");
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
		hilfe.addActionListener(e-> {
				JFrame hilfeFenster = new JFrame();
				hilfeFenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				hilfeFenster.setTitle("Hilfe");
				hilfeFenster.setSize(300, 400);
				hilfeFenster.setLocationRelativeTo(null);
				hilfeFenster.setVisible(true);
				JLabel hilfeText = new JLabel();
				try {
					hilfeText = new JLabel(readFile());
				} catch (FileNotFoundException e1) {
					
				}
				hilfeFenster.add(hilfeText);
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel, BorderLayout.PAGE_START); 
		frame.add(panel1, BorderLayout.LINE_START);
		frame.add(panel2, BorderLayout.PAGE_END);
		frame.add(panel3, BorderLayout.LINE_END);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

	}


	/*
	 * Diese Methode setz die Farben der Buttons wieder auf die default-Einstellung
	 */

	public void clearBoard() {
		for (int x = 1; x < 5; x++) {
			tempName[0][x].setBackground(Color.WHITE);
		}
		selectedColumn = -1;
		selectedColor = Color.WHITE;
	}


	/*
	 * Diese Methode verarbeitet alle Interaktionen mit dem Fenster 
	 */

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
		if ("SUBMIT".equals(e.getActionCommand())) {
			for (int x = 1; x < 5; x++) {
				if (tempName[0][x].getBackground().equals(Color.WHITE)) { 
					Mastermind.mainGui.missingColorAlert(frame);
					return;
				}
			}
			submit();
		}
	}


	/*
	 * Diese Methode gibt die Ausgewählte Farbreihenfolge weiter
	 */

	private void submit(){
		if (colorsChosenListener != null){
			Color c1 = tempName[0][1].getBackground();
			Color c2 = tempName[0][2].getBackground();
			Color c3 = tempName[0][3].getBackground();
			Color c4 = tempName[0][4].getBackground();
			Order order = new Order(c1, c2, c3, c4);
			colorsChosenListener.onToGuessChosen(order);
		}
		frame.dispose();
	}


	/*
	 * Diese Methode liest die Anleitung aus 
	 */
	private String readFile() throws FileNotFoundException{
		String Anleitung = "<html>";
		File doc = new File("lib\\Anleitung.txt");
		Scanner obj = new Scanner(doc);
			while (obj.hasNextLine()){
			    Anleitung = Anleitung + "<br>" +obj.nextLine();
			}
		
		Anleitung = Anleitung + "<html>";
        return Anleitung;
	} 


	/*
	 * setter für den ActionListener
	 */

	public void setToGuessListener(ToGuessListener listener) {
		this.colorsChosenListener = listener;
	}
}
