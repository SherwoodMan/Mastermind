
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

public class DrawingMindField extends JPanel {

    private int staticX = 0;
    private int staticY = 20;



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color background = new Color(87, 33, 8);
        g2d.setColor(background);
        Rectangle2D field = new Rectangle2D.Double(staticX, staticY, 900, 300);
        g2d.fill(field);
        g2d.setStroke(new BasicStroke(2));
        g.fillPolygon(new int[] { staticX, 900 + staticX, 910 + staticX, 10 + staticX}, new int[] { 300 + staticY, 300 + staticY, 320 + staticY, 320 + staticY}, 4);
        g.fillPolygon(new int[] { 900 + staticX, 910 + staticX, 910 + staticX, 900 + staticX}, new int[] { 300 + staticY, 320 + staticY , 10 + staticY, staticY }, 4);
        g2d.setColor(Color.BLACK);
        Rectangle2D fieldSurrounding = new Rectangle2D.Double(staticX, staticY, 900, 300);
        g2d.draw(fieldSurrounding);
        g.drawPolygon(new int[] { staticX, 900 + staticX, 910 + staticX, 10 + staticX}, new int[] { 300 + staticY, 300 + staticY, 320 + staticY, 320 + staticY}, 4);
        g.drawPolygon(new int[] { 900 + staticX, 910 + staticX, 910 + staticX, 900 + staticX}, new int[] { 300 + staticY, 320 + staticY, 10 + staticY, staticY }, 4);
        g2d.setColor(background);
        g.fillPolygon(new int[] { 750 + staticX, 825 + staticX, 850 + staticX}, new int[] { 200 + staticY, 170 + staticY, 300 + staticY}, 3);
        g.fillPolygon(new int[] { 750 + staticX, 825 + staticX, 850 + staticX}, new int[] { 20 + staticY, -10 + staticY, 20 + staticY}, 3);

        g2d.setColor(Color.BLACK);
        g.drawPolygon(new int[] { 750 + staticX, 825 + staticX, 850 + staticX}, new int[] { 200 + staticY, 170 + staticY, 200 + staticY}, 3);
        g.drawPolygon(new int[] { 750 + staticX, 825 + staticX, 850 + staticX}, new int[] { 20 + staticY, -10 + staticY, 20 + staticY}, 3);
        g2d.setColor(background);
        g.fillPolygon(new int[] { 750 + staticX, 750 + staticX, 825 + staticX, 825 + staticX}, new int[] { 20 + staticY, 200 + staticY, 170 + staticY, -10 + staticY }, 4);
        g2d.setColor(Color.BLACK);
        g.drawPolygon(new int[] { 750 + staticX, 750 + staticX, 825 + staticX, 825 + staticX}, new int[] { 20 + staticY, 200 + staticY, 170 + staticY, -10 + staticY}, 4);

        g2d.setColor(Color.GRAY);
        int x = 25 + staticX;
        int y = 20 + staticY;
        for (int i = 0; i < Mastermind.getGuesses()[1].length; i++) {
            for (int j = 0; j < Mastermind.getGuesses().length; j++) {

                g2d.setColor(Mastermind.getGuesses()[j][i]);
                g2d.fill(new Ellipse2D.Double(x + i * 60, y + j * 50, 25, 25));
                g2d.setColor(Color.GRAY);

            }
        }
        y = 220 + staticY;
        for (int i = 0; i < Mastermind.getReaction()[1].length; i++) {

            g2d.setColor(Mastermind.getReaction()[0][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y, 10, 10));
            g2d.setColor(Mastermind.getReaction()[1][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y, 10, 10));
            g2d.setColor(Mastermind.getReaction()[2][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y + 15, 10, 10));
            g2d.setColor(Mastermind.getReaction()[3][i]);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y + 15, 10, 10));
            g2d.setColor(Color.GRAY);
        }
    }

    

    public void paintPins(int turn, Pins pinObject) {
        int blackPins = pinObject.getBlackPins();
        int whitePins = pinObject.getWhitePins();
        if (whitePins > 0) {
            Mastermind.getReaction()[0][turn] = Color.WHITE;
            whitePins--;
            if (whitePins > 0) {
                Mastermind.getReaction()[1][turn] = Color.WHITE;
                whitePins--;
                if (whitePins > 0) {
                    Mastermind.getReaction()[2][turn] = Color.WHITE;
                    whitePins--;
                    if (whitePins > 0) {
                        Mastermind.getReaction()[3][turn] = Color.WHITE;
                    } else if (blackPins > 0) {
                        Mastermind.getReaction()[3][turn] = Color.BLACK;
                    }
                } else if (blackPins > 0) {
                    Mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.getReaction()[3][turn] = Color.BLACK;
                    }
                }
            } else if (blackPins > 0) {
                Mastermind.getReaction()[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    Mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.getReaction()[3][turn] = Color.BLACK;
                    }

                }
            }
        } else if (blackPins > 0) {
            Mastermind.getReaction()[0][turn] = Color.BLACK;
            blackPins--;
            if (blackPins > 0) {
                Mastermind.getReaction()[1][turn] = Color.BLACK;
                blackPins--;
                if (blackPins > 0) {
                    Mastermind.getReaction()[2][turn] = Color.BLACK;
                    blackPins--;
                    if (blackPins > 0) {
                        Mastermind.getReaction()[3][turn] = Color.BLACK;
                    }

                }
            }
        }
        repaint();
    }

    public void paintOrder( int turn, Order orderToChange){
        Color[] colorOrderToChange = orderToChange.orderInColor();
        for (int i = 0; i < colorOrderToChange.length; i++) {
            Mastermind.getGuesses()[i][turn] = colorOrderToChange[i];
        }
        
        repaint();
    }
}
