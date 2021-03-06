import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;


/*
 * Diese Klasse bearbeitet das Spielfeld für die "Master" - Oberfläche
 */

public class DrawingMasterField extends JPanel {


    /*
     * Das Feld ist an diese beidem Attribute gebunden, damit man es einfach verschieben kann
     */

    private int staticX = 0;
    private int staticY = 20;


    /*
     * Diese Methode malt nach und nach das Spielfeld
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color background = new Color(87, 33, 8);
        g2d.setColor(background);
        Rectangle2D field = new Rectangle2D.Double(staticX, staticY, 900, 300);
        g2d.fill(field);
        g2d.setStroke(new BasicStroke(2));
        g.fillPolygon(new int[] { staticX, 900 + staticX, 910 + staticX, 10 + staticX }, new int[] { 300 + staticY, 300 + staticY, 320 + staticY, 320 + staticY}, 4);
        g.fillPolygon(new int[] { 900 + staticX, 910 + staticX, 910 + staticX, 900 + staticX}, new int[] { 300 + staticY, 320 + staticY, 10 + staticY, staticY}, 4);
        g2d.setColor(Color.BLACK);
        Rectangle2D fieldSurrounding = new Rectangle2D.Double(staticX, staticY, 900, 300);
        g2d.draw(fieldSurrounding);
        g.drawPolygon(new int[] { staticX, 900 + staticX, 910 + staticX, 10 + staticX}, new int[] { 300 + staticY, 300 + staticY, 320 + staticY, 320 + staticY}, 4);
        g.drawPolygon(new int[] { 900 + staticX, 910 + staticX, 910 + staticX, 900 + staticX}, new int[] { 300 + staticY, 320 + staticY, 10 + staticY, staticY }, 4);
        g2d.setColor(Color.GRAY);
        int x = 800 + staticX;
        int y = 20 + staticY;

        Color[] colorsToGuess= Mastermind.getToGuess().orderInColor();
        for (int i = 0; i < colorsToGuess.length; i++) {
            g2d.setColor(colorsToGuess[i]);
            g2d.fill(new Ellipse2D.Double(x , y + i * 50, 25, 25));
            g2d.setColor(Color.GRAY);
        }

        x = 25 + staticX;
        for (int i = 0; i < Mastermind.guesses[1].length; i++) {
            for (int j = 0; j < Mastermind.guesses.length; j++) {

                g2d.setColor(Mastermind.guesses[j][i]);
                g2d.fill(new Ellipse2D.Double(x + i * 60, y + j * 50, 25, 25));
                g2d.setColor(Color.GRAY);

            }
        }
        y = 220 + staticY;
        for (int i = 0; i < Mastermind.reaction[1].length; i++) {

            g2d.setColor(Mastermind.reaction[0][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y, 10, 10));
            g2d.setColor(Mastermind.reaction[1][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y, 10, 10));
            g2d.setColor(Mastermind.reaction[2][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y + 15, 10, 10));
            g2d.setColor(Mastermind.reaction[3][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y + 15, 10, 10));
            g2d.setColor(Color.GRAY);
        }
    }


    /*
     * Diese Methode kümmert sich um das Platzieren der Kontrollstifte
     */

    public void paintPins(int turn, Pins pinObject) {
        int blackPins = pinObject.getBlackPins();
        int whitePins = pinObject.getWhitePins();
        if (whitePins > 0) {
            Mastermind.reaction[0][turn] = Color.WHITE;
            whitePins--;
            if (whitePins > 0) {
                Mastermind.reaction[1][turn] = Color.WHITE;
                whitePins--;
                if (whitePins > 0) {
                    Mastermind.reaction[2][turn] = Color.WHITE;
                    whitePins--;
                    if (whitePins > 0) {
                        Mastermind.reaction[3][turn] = Color.WHITE;
                    } else if (blackPins > 0) {
                        Mastermind.reaction[3][turn] = Color.BLACK;
                    }
                } else if (blackPins > 0) {
                    Mastermind.reaction[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.reaction[3][turn] = Color.BLACK;
                    }
                }
            } else if (blackPins > 0) {
                Mastermind.reaction[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    Mastermind.reaction[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.reaction[3][turn] = Color.BLACK;
                    }

                }
            }
        } else if (blackPins > 0) {
            Mastermind.reaction[0][turn] = Color.BLACK;
            blackPins--;
            if (blackPins > 0) {
                Mastermind.reaction[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    Mastermind.reaction[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.reaction[3][turn] = Color.BLACK;
                    }

                }
            }
        }
        repaint();
    }


    /*
     * Diese Methode kümmert sich um das Platzieren der Farbstifte
     */

    public void paintOrder( int turn, Order orderToChange){
        Color[] colorOrderToChange = orderToChange.orderInColor();
        for (int i = 0; i < colorOrderToChange.length; i++) {
            Mastermind.guesses[i][turn] = colorOrderToChange[i];
        }
        repaint();
    }
}
