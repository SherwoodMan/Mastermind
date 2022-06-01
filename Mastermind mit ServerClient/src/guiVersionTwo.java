import ServerClient.Server;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class guiVersionTwo extends JFrame {

	private mindGUI mindGui;
	private masterGUI masterGui;

	public void initializeStart() {
		JFrame box = new JFrame();

		ImageIcon logo = new ImageIcon(
				new ImageIcon("Mastermind_logo.png").getImage().getScaledInstance(400, 320, Image.SCALE_DEFAULT));
		Object[] options = { "Spieler 1", "Spieler 2" };
		int n = JOptionPane.showOptionDialog(box, "Welcher Spieler bist du?", "Mastermind",
				JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, logo, options, options[0]);

		if (n == JOptionPane.YES_OPTION) {
			masterGui = new masterGUI();
			masterGui.initializeMasterBoard();
		} else if (n == JOptionPane.NO_OPTION) {
			mindGui = new mindGUI();
			mindGui.initializeMindBoard();
		}
	}

}
