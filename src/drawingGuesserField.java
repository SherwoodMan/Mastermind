
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

public class drawingGuesserField extends JPanel {



    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color background = new Color(87, 33, 8);
        g2d.setColor(background);
        Rectangle2D field = new Rectangle2D.Double(50, 100, 900, 300);
        g2d.fill(field);
        g2d.setStroke(new BasicStroke(2));
        g.fillPolygon(new int[] { 50, 950, 960, 60 }, new int[] { 400, 400, 420, 420 }, 4);
        g.fillPolygon(new int[] { 950, 960, 960, 950 }, new int[] { 400, 420, 110, 100 }, 4);
        g2d.setColor(Color.BLACK);
        Rectangle2D fieldSurrounding = new Rectangle2D.Double(50, 100, 900, 300);
        g2d.draw(fieldSurrounding);
        g.drawPolygon(new int[] { 50, 950, 960, 60 }, new int[] { 400, 400, 420, 420 }, 4);
        g.drawPolygon(new int[] { 950, 960, 960, 950 }, new int[] { 400, 420, 110, 100 }, 4);
        g2d.setColor(background);
        g.fillPolygon(new int[] { 800, 875, 900 }, new int[] { 300, 270, 300 }, 3);
        g.fillPolygon(new int[] { 800, 875, 900 }, new int[] { 120, 90, 120 }, 3);

        g2d.setColor(Color.BLACK);
        g.drawPolygon(new int[] { 800, 875, 900 }, new int[] { 300, 270, 300 }, 3);
        g.drawPolygon(new int[] { 800, 875, 900 }, new int[] { 120, 90, 120 }, 3);
        g2d.setColor(background);
        g.fillPolygon(new int[] { 800, 800, 875, 875 }, new int[] { 120, 300, 270, 90 }, 4);
        g2d.setColor(Color.BLACK);
        g.drawPolygon(new int[] { 800, 800, 875, 875 }, new int[] { 120, 300, 270, 90 }, 4);

        g2d.setColor(Color.GRAY);
        int x = 75;
        int y = 120;
        for (int i = 0; i < mastermind.getGuesses()[1].length; i++) {
            for (int j = 0; j < mastermind.getGuesses().length; j++) {

                g2d.setColor(mastermind.getGuesses()[j][i]);
                g2d.fill(new Ellipse2D.Double(x + i * 60, y + j * 50, 25, 25));
                g2d.setColor(Color.GRAY);

            }
        }
        y = 320;
        for (int i = 0; i < mastermind.getReaction()[1].length; i++) {

            g2d.setColor(mastermind.getReaction()[0][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y, 10, 10));
            g2d.setColor(mastermind.getReaction()[1][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y, 10, 10));
            g2d.setColor(mastermind.getReaction()[2][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y + 15, 10, 10));
            g2d.setColor(mastermind.getReaction()[3][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y + 15, 10, 10));
            g2d.setColor(Color.GRAY);
        }
    }

    

    public void paintPins(int turn, pins pinObject) {
        int blackPins = pinObject.getBlackPins();
        int whitePins = pinObject.getWhitePins();
        if (whitePins > 0) {
            mastermind.getReaction()[0][turn] = Color.WHITE;
            whitePins--;
            if (whitePins > 0) {
                mastermind.getReaction()[1][turn] = Color.WHITE;
                whitePins--;
                if (whitePins > 0) {
                    mastermind.getReaction()[2][turn] = Color.WHITE;
                    whitePins--;
                    if (whitePins > 0) {
                        mastermind.getReaction()[3][turn] = Color.WHITE;
                    } else if (blackPins > 0) {
                        mastermind.getReaction()[3][turn] = Color.BLACK;
                    }
                } else if (blackPins > 0) {
                    mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        mastermind.getReaction()[3][turn] = Color.BLACK;
                    }
                }
            } else if (blackPins > 0) {
                mastermind.getReaction()[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        mastermind.getReaction()[3][turn] = Color.BLACK;
                    }

                }
            }
        } else if (blackPins > 0) {
            mastermind.getReaction()[0][turn] = Color.BLACK;
            blackPins--;
            if (blackPins > 0) {
                mastermind.getReaction()[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        mastermind.getReaction()[3][turn] = Color.BLACK;
                    }

                }
            }
        }
        repaint();
    }

    public void paintOrder( int turn, order orderToChange){
        Color[] colorOrderToChange = orderToChange.orderInColor();
        for (int i = 0; i < colorOrderToChange.length; i++) {
            mastermind.getGuesses()[i][turn] = colorOrderToChange[i];
        }
        
        repaint();
    }
}
