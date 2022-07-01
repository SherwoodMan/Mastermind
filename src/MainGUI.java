import java.awt.Image;
import java.awt.Component;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/*
 * Diese Klasse kümmert sich um alle Spielerunspezifischen Oberflächenelemente 
 */

public class MainGUI{
	private MindGUI mindGui;
	private MasterGUI masterGui;
	

	/*
	 * Diese Methode erstellt das Beginnfenster
	 */

	public void initializeStart() {
		JFrame box = new JFrame();
		box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon logo = new ImageIcon(
				new ImageIcon("lib\\Mastermind_logo.png").getImage().getScaledInstance(400, 320, Image.SCALE_DEFAULT));
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


	/*
	 * Diese Methode bewirkt ein einfaches Dialogfenster zur Spielerinnerung
	 */

	public void showTurn(Component c) {
		JOptionPane.showMessageDialog(c, "Du bist jetzt an der Reihe.", "Spielerinnerung", JOptionPane.OK_OPTION);
	}


	/*
	 * Diese Methode bewirkt ein einfaches Dialogfenster zur Spielerwechselerinnerung
	 */

	public void showTurnChange(Component c) {
		JOptionPane.showMessageDialog(c, "Dein Mitspieler ist nun an der Reihe.", "Spielerwechselerinnerung",
				JOptionPane.OK_OPTION);
	}


	/*
	 * Diese Methode bewirkt ein einfaches Dialogfenster zu fehlenden Farben beim absenden einer Farbreihenfolge
	 */

	public void missingColorAlert(Component c) {
		JOptionPane.showMessageDialog(c, "Bitte wähle 4 Farben aus.", "Missing Color Alert",
				JOptionPane.OK_OPTION);
	}


	/*
	 * Diese Methode bewirkt ein Fenster zur Auswahl der Farbreihenfolge
	 */

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
