import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// MainGUI klasse muss nicht die Klasse JFrame erweitern, wenn JFrame als Variable erstellt wird.
public class MainGUI{
	private MindGUI mindGui;
	private MasterGUI masterGui;

	public void initializeStart() {
		//JFrame erstellt als Variable
		JFrame box = new JFrame();
		box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon logo = new ImageIcon(
				new ImageIcon("Mastermind_logo.png").getImage().getScaledInstance(400, 320, Image.SCALE_DEFAULT));
		Object[] options = { "Master", "Mind" };
		int n = JOptionPane.showOptionDialog(box, "Welcher Spieler bist du?", "Mastermind",
				JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, logo, options, options[0]);

		box.dispose();

		if (n == JOptionPane.YES_OPTION) {
			chooseToGuessColors();
		} else if (n == JOptionPane.NO_OPTION) {
			mindGui = new MindGUI();
			mindGui.initializeMindBoard();
		}
	}

	public void showTurn() {
		new JOptionPane();
		JOptionPane.showMessageDialog(null, "Du bist jetzt an der Reihe.", "Spielerinnerung", JOptionPane.OK_OPTION);
	}

	public void showTurnChange() {
		new JOptionPane();
		JOptionPane.showMessageDialog(null, "Dein Mitspieler ist nun an der Reihe.", "Spielerwechselerinnerung",
				JOptionPane.OK_OPTION);
	}

	public void missingColorAlert() {
		new JOptionPane();
		JOptionPane.showMessageDialog(null, "Bitte wähle 4 Farben aus.", "Missing Color Alert",
				JOptionPane.OK_OPTION);
	}

	private void chooseToGuessColors(){
		ToGuess guessGui = new ToGuess();
		guessGui.setToGuessListener(order -> {
			System.out.println("MainGui, guessguiListener: " + order.toString());
			Mastermind.setToGuess(order);
			masterGui = new MasterGUI();
			masterGui.initializeMasterBoard();
		});
		guessGui.initializeBoard();
	}



}
