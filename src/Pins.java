import java.awt.Color;


/*
 * Diese Klasse speichert die Reaktionspins in einem Objekt ab
 */

public class Pins {

    private int whitePins;
    private int blackPins;


    /*
     * Konstruktor der direkt mit numerischen Werten ein Pins-Objekt baut und überprüft, ob nicht zu viele Pins eingegeben wurden 
     */

    public Pins(int whitePins, int blackPins) {

        if (whitePins == 4 && blackPins == 4) {
            this.blackPins = -1;
            this.whitePins = -1;
        } else if (whitePins == 1 && blackPins > 3 || blackPins == 1 && whitePins > 3) {
            this.blackPins = -1;
            this.whitePins = -1;
        } else if (whitePins == 2 && blackPins > 2 || blackPins == 2 && whitePins > 2) {
            this.blackPins = -1;
            this.whitePins = -1;
        }
        this.blackPins = blackPins;
        this.whitePins = whitePins;
    }


    /*
     * Koinstruktor der aus den Farben der Pins ein Pins-Objekt baut
     */

    public Pins(Color zeroC, Color oneC, Color twoC, Color threeC) {
        if (zeroC.equals(Color.WHITE)) {
            this.whitePins++;
        } else if (zeroC.equals(Color.BLACK)) {
            this.blackPins++;
        }
        if (oneC.equals(Color.WHITE)) {
            this.whitePins++;
        } else if (oneC.equals(Color.BLACK)) {
            this.blackPins++;
        }
        if (twoC.equals(Color.WHITE)) {
            this.whitePins++;
        } else if (twoC.equals(Color.BLACK)) {
            this.blackPins++;
        }
        if (threeC.equals(Color.WHITE)) {
            this.whitePins++;
        } else if (threeC.equals(Color.BLACK)) {
            this.blackPins++;
        }

    }


    /*
     * getter für die weißen Pins
     */

    public int getWhitePins() {
        return whitePins;
    }


    /*
     * getter für die schwarzen Pins
     */

    public int getBlackPins() {
        return blackPins;
    }

}