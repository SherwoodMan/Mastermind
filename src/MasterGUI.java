import ServerClient.MessageModel;
import ServerClient.ReceivedMessageListener;
import ServerClient.Server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MasterGUI extends JFrame implements ActionListener {

	private JFrame frame;
	private DrawingMasterField drawnMaster;

	private Color[] colors = { Color.BLACK, Color.WHITE };

	private ArrayList<String> colorsS = new ArrayList<String>();

	private ArrayList<String> colorsN = new ArrayList<String>();

	private JButton[][] tempName;
	private Color selectedColor;
	private int selectedColumn;

	private Server server;
	private boolean connected = false;
	private boolean myTurn = false;

	private final Color[] colorsValues = { Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.darkGray };
	private final String[] colorsNames = { "RED", "BLUE", "GREEN", "YELLOW", "MAGENTA", "DARK_GRAY" };

	public void initializeMasterBoard() {


		init_server();

        System.out.println("checking");
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
		frame = new JFrame("MASTERmind");
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
			tempName[0][y].setBackground(Color.GRAY);
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

		for (int x = 0; x < 2; x++) {

			tempName[1][x] = new JButton("");
			tempName[1][x].addActionListener(this);
			tempName[1][x].setBackground(colors[x]);
			tempName[1][x].setActionCommand(colorsS.get(x));
			tempName[1][x].setBounds(200+x*150, 30, 100, 50);
			frame.add(tempName[1][x]);
		}

		enableButtons(false);


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
            System.out.println(selectedColor.toString());
		}
		if ("CLEAR".equals(e.getActionCommand())) {
			this.clearBoard();
		}
		if ("CHECK".equals(e.getActionCommand())) {

			Color c1 = tempName[0][1].getBackground();
			Color c2 = tempName[0][2].getBackground();
			Color c3 = tempName[0][3].getBackground();
			Color c4 = tempName[0][4].getBackground();

			Pins toCompare = new Pins(c1, c2, c3, c4);
			drawnMaster.paintPins(Mastermind.round, toCompare);
			int round = Mastermind.round;

			MessageModel message = new MessageModel(round, c1, c2, c3, c4);
			server.sendObject(message);
			enableButtons(false);
			Mastermind.mainGui.showTurnChange(frame);

			Mastermind.round = round + 1;
			this.clearBoard();
			selectedColumn = -1;
			selectedColor = Color.WHITE;

		}

    }

	private void init_server(){
		if (server == null){
			server = new Server();

			server.setListener(message -> parseMessage(message));

			Thread serverThread = new Thread(server);
			serverThread.setDaemon(true);
			serverThread.start();
		}
	}

	private void parseMessage(MessageModel message){
		System.out.println(message.toString());
		Order o = new Order(message.getColor1(), message.getColor2(), message.getColor3(), message.getColor4());
		drawnMaster.paintOrder(message.round, o);
		// activate button to be able to send response to client
		enableButtons(true);
		Mastermind.mainGui.showTurn(frame);
	}

	// nicht nur Variable setzen sondern auch alle von dieser Variable abhängigen Komponenten darüber informieren,
	// dass diese Variable einen neuen Wert hat
	private void setConnected(boolean c){
		this.connected = c;
		enableButtons(c);
	}

	/*
	* message: "int round,String color1, String color2..."
	* */

	private void enableButtons(boolean c){
		for(JButton[] buttonsList : tempName){
			for(JButton button : buttonsList){
				if (button != null){
					button.setEnabled(c);
				}
			}
		}
	}



}

