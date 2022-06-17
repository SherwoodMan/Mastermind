import ServerClient.Client;
import ServerClient.MessageModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MindGUI extends JFrame implements ActionListener {

	private DrawingMindField drawnMind;

	private Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private ArrayList<String> colorsS = new ArrayList<String>();
	private ArrayList<String> colorsN = new ArrayList<String>();
	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	private JFrame frame;

	private Client client;

	

	public void initializeMindBoard() {

		init_client();

		drawnMind = new DrawingMindField();

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
		frame = new JFrame("masterMIND");
		frame.setSize(1200, 500);
		frame.setLayout(null);

		tempName[0][0] = new JButton("CLEAR");
		tempName[0][0].addActionListener(this);
		tempName[0][0].setBackground(Color.ORANGE);
		tempName[0][0].setActionCommand("CLEAR");
		tempName[0][0].setBounds(10, 340, 85, 30);
		frame.add(tempName[0][0]);

		for (int y = 1; y < 5; y++) {
			tempName[0][y] = new JButton("" + y);
			tempName[0][y].addActionListener(this);
			tempName[0][y].setBackground(Color.WHITE);
			tempName[0][y].setActionCommand("" + y);
			tempName[0][y].setBounds(10, 90+ y*50, 180, 25);
			frame.add(tempName[0][y]);
		}

		tempName[0][5] = new JButton("CHECK");
		tempName[0][5].addActionListener(this);
		tempName[0][5].setBackground(Color.ORANGE);
		tempName[0][5].setActionCommand("CHECK");
		tempName[0][5].setBounds(105, 340, 85, 30);
		frame.add(tempName[0][5]);

		for (int x = 0; x < 6; x++) {
			tempName[1][x] = new JButton("");
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));
			tempName[1][x].setBounds(200+x*150, 30, 100, 50);
			frame.add(tempName[1][x]);
		}
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		drawnMind.setBounds(200, 100, 1100, 600);
		frame.add(drawnMind);
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

				Color c1 = tempName[0][1].getBackground();
				Color c2 = tempName[0][2].getBackground();
				Color c3 = tempName[0][3].getBackground();
				Color c4 = tempName[0][4].getBackground();

				Order toCompare = new Order(c1, c2, c3, c4);
				drawnMind.paintOrder(Mastermind.getRound(), toCompare);

				int round = Mastermind.getRound();
				Mastermind.setRound(round + 1);

				MessageModel message = new MessageModel(round, c1, c2, c3, c4);
				client.sendObject(message);
				// deactivate all buttons after sending a message
				enableButtons(false);

				this.clearBoard();
			}
			selectedColumn = -1;
			selectedColor = Color.WHITE;
		}

	}

	private void enableButtons(boolean c){
		for(JButton[] buttonsList : tempName){
			for(JButton button : buttonsList){
				if (button != null){
					button.setEnabled(c);
				}
			}
		}
	}
//dry: don't repeat yourself
	private void init_client(){
		if (client == null){
			client = new Client();
			client.setListener(message -> {
				parseMessage(message);
			});
			Thread clientThread = new Thread(client);
			clientThread.setDaemon(true);
			clientThread.start();
		}
	}
	private void parseMessage(MessageModel message){
		System.out.println(message.toString());
		Pins p = new Pins(message.getColor1(), message.getColor2(), message.getColor3(),message.getColor4());
		drawnMind.paintPins(message.getRound(), p);
		//check winner
		if (
				message.getColor1().equals(Color.WHITE) &&
				message.getColor2().equals(Color.WHITE) &&
				message.getColor3().equals(Color.WHITE) &&
				message.getColor4().equals(Color.WHITE)
		){
			win();
		}else if (message.getRound() >= 11){
			lose();
			return;
		}
		enableButtons(true);
	}

	private void win(){
		System.out.println("gewonnen");
		clearFrame();
		JLabel label = new JLabel("Win");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 96));
		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(label);
		frame.repaint();
	}

	private void clearFrame(){
		for (JButton[] buttonsList : tempName){
			for (JButton button : buttonsList){
				if (button != null){
					frame.remove(button);
				}
			}
		}
		frame.remove(drawnMind);
	}

	private void lose(){
		System.out.println("verloren");
		clearFrame();
		JLabel label = new JLabel("Lose");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 96));
		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(label);
		frame.repaint();
	}


}
