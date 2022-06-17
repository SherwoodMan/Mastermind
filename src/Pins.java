import java.awt.Color;

public class Pins {

    private int whitePins;
    private int blackPins;
    
    public Pins(int whitePins, int blackPins) {
    
    if(whitePins == 4 && blackPins == 4){
    this.blackPins = -1;
    this.whitePins = -1;
    }
    else if (whitePins == 1 && blackPins > 3 || blackPins == 1 && whitePins > 3) {
    this.blackPins = -1;
    this.whitePins = -1;
    }
    else if (whitePins == 2 && blackPins > 2 || blackPins == 2 && whitePins > 2) {
    this.blackPins = -1;
    this.whitePins = -1;
    }
    this.blackPins = blackPins;
    this.whitePins = whitePins;
    }

    public Pins(Color zeroC, Color oneC, Color twoC, Color threeC){
        if (zeroC.equals(Color.WHITE)) {
            this.whitePins ++;
        } else if (zeroC.equals(Color.BLACK)){
            this.blackPins ++;
        }
        if (oneC.equals(Color.WHITE)) {
            this.whitePins ++;
        } else if (oneC.equals(Color.BLACK)){
            this.blackPins ++;
        }
        if (twoC.equals(Color.WHITE)) {
            this.whitePins ++;
        } else if (twoC.equals(Color.BLACK)){
            this.blackPins ++;
        }
        if (threeC.equals(Color.WHITE)) {
            this.whitePins ++;
        } else if (threeC.equals(Color.BLACK)){
            this.blackPins ++;
        }

    }
    
    public int getWhitePins() {
    return whitePins;
    }
    
    public void setWhitePins(int whitePins) {
    if(whitePins == 4 && this.blackPins == 4){
    this.whitePins = -1;
    }
    else if (whitePins == 1 && this.blackPins > 3 || this.blackPins == 1 && whitePins > 3) {
    this.whitePins = -1;
    }
    else if (whitePins == 2 && this.blackPins > 2 || this.blackPins == 2 && whitePins > 2) {
    this.whitePins = -1;
    }
    this.whitePins = whitePins;
    }
    
    public int getBlackPins() {
    return blackPins;
    }
    
    public void setBlackPins(int blackPins) {
    if(this.whitePins == 4 && blackPins == 4){
    this.blackPins = -1;
    }
    else if (this.whitePins == 1 && blackPins > 3 || blackPins == 1 && this.whitePins > 3) {
    this.blackPins = -1;
    }
    else if (this.whitePins == 2 && blackPins > 2 || blackPins == 2 && this.whitePins > 2) {
    this.blackPins = -1;
    }
    this.blackPins = blackPins;
    }
}