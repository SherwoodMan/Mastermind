/*
 * Diese Klasse war gedacht um automatisch zu Kontrollieren ob das geratene richtig ist
 * Sie funktioniert, in Sonderfällen (bei doppelten Pins) kann es nur zu komischen ERgebnissen kommen
 * Könnte für ein Spiel gegen eine AI genutzt werden
 */
public class Control {
    private int whitePins;
    private int blackPins;

    
    public Pins compare(Order sample, Order toCompare) {
        int[] workarraySample = { sample.getOrderArrayNumber(0), sample.getOrderArrayNumber(1),
                sample.getOrderArrayNumber(2), sample.getOrderArrayNumber(3) };
        int[] workarray = toCompare.getOrderArray();
        setBlackPins(0);
        setWhitePins(0);
        if (sample.getOrderArray().equals(toCompare.getOrderArray())) {
            setBlackPins(4);
            setWhitePins(0);
        } else {

            int bP = 0;
            int wP = 0;
            for (int i = 0; i < workarray.length; i++) {
                if (workarray[i] == workarraySample[i]) {
                    workarray[i] = -1;
                        workarraySample[i] = -2;

                    bP++;
                }
            }
            for (int i = 0; i < workarray.length; i++) {
                for (int j = 0; j < workarraySample.length; j++) {
                    if (workarray[i] == workarraySample[j]) {
                        workarray[i] = -1;
                            workarraySample[j] = -2;
                        
                        wP++;
                    }
                }
            }
            setBlackPins(bP);
            setWhitePins(wP);

        }
        System.out.println("White Pins = " + whitePins);
        System.out.println("Black Pins = " + blackPins);
        return new Pins(whitePins,blackPins);
    }

    public int getWhitePins() {
        return whitePins;
    }

    public void setWhitePins(int whitePins) {
        this.whitePins = whitePins;
    }

    public int getBlackPins() {
        return blackPins;
    }

    public void setBlackPins(int blackPins) {
        this.blackPins = blackPins;
    }

    public boolean doubledPins(int[] sample, int pin) {
        for (int index = 0; index < sample.length; index++) {
            if (sample[index] == pin) {
                return true;
            }
        }
        return false;
    }
}

