import java.awt.Color;

public class pins {

    private int whitePins;
    private int blackPins;
    
    public pins(int whitePins, int blackPins) {
    
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

    public pins(Color zeroC, Color oneC, Color twoC, Color threeC){
        if (zeroC == Color.WHITE) {
            this.whitePins ++;
        } else {
            this.blackPins ++;
        }
        if (oneC == Color.WHITE) {
            this.whitePins ++;
        } else {
            this.blackPins ++;
        }
        if (twoC == Color.WHITE) {
            this.whitePins ++;
        } else {
            this.blackPins ++;
        }
        if (threeC == Color.WHITE) {
            this.whitePins ++;
        } else {
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