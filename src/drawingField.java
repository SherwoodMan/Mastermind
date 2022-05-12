
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class drawingField extends JPanel {

    Color[][] guesses = new Color[4][12];

    Graphics graphics;
    Color c = Color.GRAY;

    public void paintComponent(Graphics g) {

        graphics = g;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(87, 33, 8));
        Rectangle2D field = new Rectangle2D.Double(50, 100, 900, 300);
        g2d.fill(field);
        graphics.fillPolygon(new int[] {50, 950, 960, 60}, new int[] {400, 400, 420, 420}, 4);
        graphics.fillPolygon(new int[] {950, 960, 960, 950}, new int[] {400, 420, 110, 100}, 4);
        graphics.fillPolygon(new int[] {800, 875, 900}, new int[] {300, 270, 300}, 3);
        graphics.fillPolygon(new int[] {800, 875, 900}, new int[] {120, 90, 120}, 3);
        graphics.fillPolygon(new int[] {800, 800, 875, 875}, new int[] {120, 300, 270, 90}, 4);
        g2d.setColor(Color.BLACK);
        graphics.drawPolygon(new int[] {50, 950, 960, 60}, new int[] {400, 400, 420, 420}, 4);
        graphics.drawPolygon(new int[] {950, 960, 960, 950}, new int[] {400, 420, 110, 100}, 4);
        graphics.drawPolygon(new int[] {800, 875, 900}, new int[] {300, 270, 300}, 3);
        graphics.drawPolygon(new int[] {800, 875, 900}, new int[] {120, 90, 120}, 3);
        graphics.drawPolygon(new int[] {800, 800, 875, 875}, new int[] {120, 300, 270, 90}, 4);
        
        
        g2d.setColor(Color.GRAY);
        int x = 75;
        int y = 120;
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].length; j++) {
                g2d.fill(new Ellipse2D.Double(x + j * 60, y + i * 50, 25, 25));
            }
        }
        y = 320;
        for (int i = 0; i < guesses[1].length; i++) {
            g2d.setColor(c);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y, 10, 10));
            g2d.setColor(c);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y, 10, 10));
            g2d.setColor(c);
            g2d.fill(new Ellipse2D.Double(i * 60 + x, y + 15, 10, 10));
            g2d.setColor(c);
            g2d.fill(new Ellipse2D.Double(i * 60 + x + 15, y + 15, 10, 10));
        }
    }


    public void paintPins(int turn, pins pinObject) {
        int blackPins = pinObject.getBlackPins();
        int whitePins = pinObject.getWhitePins();
        turn--;
        int y = 220;
        int x = 75;
        if (whitePins > 0) {
            c = Color.WHITE;
            repaint(turn * 60 + x, y, 10, 10);
            whitePins--;
            c = Color.GRAY;
            if (whitePins > 0) {
                c = Color.WHITE;
                repaint(turn * 60 + x + 15, y, 10, 10);
                whitePins--;
                c = Color.GRAY;
                if (whitePins > 0) {
                    c = Color.WHITE;
                    repaint(turn * 60 + x, y + 15, 10, 10);
                    whitePins--;
                    c = Color.GRAY;
                    if (whitePins > 0) {
                        c = Color.WHITE;
                        repaint(turn * 60 + x + 15, y + 15, 10, 10);
                        c = Color.GRAY;
                    } else if (blackPins > 0) {
                        c = Color.BLACK;
                        repaint(turn * 60 + x + 15, y + 15, 10, 10);
                        c = Color.GRAY;
                    }
                } else if (blackPins > 0) {
                    c = Color.BLACK;
                    repaint(turn * 60 + x, y + 15, 10, 10);
                    blackPins--;
                    c = Color.GRAY;
                    if (blackPins > 0) {
                        c = Color.BLACK;
                        repaint(turn * 60 + x + 15, y + 15, 10, 10);
                        c = Color.GRAY;
                    }
                }
            } else if (blackPins > 0) {
                c = Color.BLACK;
                repaint(turn * 60 + x + 15, y, 10, 10);
                blackPins--;
                c = Color.GRAY;
                if (blackPins > 0) {
                    c = Color.BLACK;
                    repaint(turn * 60 + x, y + 15, 10, 10);
                    blackPins--;
                    c = Color.GRAY;
                    if (blackPins > 0) {
                        c = Color.BLACK;
                        repaint(turn * 60 + x + 15, y + 15, 10, 10);
                        c = Color.GRAY;

                    }
                    
                }
            }
        } else if (blackPins > 0) {
            c = Color.BLACK;
            repaint(turn * 60 + x, y, 10, 10);
            blackPins--;
            c = Color.GRAY;
            if (blackPins > 0) {
                c = Color.BLACK;
                repaint(turn * 60 + x + 15, y, 10, 10);
                blackPins--;
                c = Color.GRAY;
                if (blackPins > 0) {
                    c = Color.BLACK;
                    repaint(turn * 60 + x, y + 15, 10, 10);
                    blackPins--;
                    c = Color.GRAY;
                    if (blackPins > 0) {
                        c = Color.BLACK;
                        repaint(turn * 60 + x + 15, y + 15, 10, 10);
                        c = Color.GRAY;
                    }
                }
            }
        }
    }

}
