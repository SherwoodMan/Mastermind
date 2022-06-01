
import javax.swing.JFrame;

public class toGuess {

    public static void main(String[] args) {

        JFrame chooseColor = new JFrame();
        chooseColor.setTitle("Choose colors...");

        chooseColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chooseColor.setSize(300, 200);
        chooseColor.setLocation(50, 50);
        chooseColor.setVisible(true);
        chooseColor.setLocationRelativeTo(null);
        chooseColor.setResizable(false);

    }
}