
public class control {
    private int whitePins;
    private int blackPins;

    
    public control(order sample, order toCompare) {
        compare(sample, toCompare);
    }


    public void compare( order sample, order toCompare)  {
        setBlackPins(0);
        setWhitePins(0);
        if (sample.getOrderArray().equals(toCompare.getOrderArray())) {
            setBlackPins(4);
            setWhitePins(0);
        } else {
            int[] workarray = toCompare.getOrderArray();
            int bP = 0;
            int wP = 0;
            for (int i = 0; i < workarray.length; i++) {
                if (workarray[i] == sample.getOrderArrayNumber(i)) {
                    workarray[i] = -1;    
                    bP++;
                }
            }
            for (int i = 0; i < workarray.length; i++) {
                for (int j = 0; j < sample.getOrderArray().length; j++) {
                    if (workarray[i] == sample.getOrderArrayNumber(j)) {
                        workarray[i] = -1;
                        wP++;
                    }
                }
            }
            setBlackPins(bP);
            setWhitePins(wP);
            
        }
        System.out.println("White Pins  = " + whitePins);
        System.out.println("Black Pins  = " + blackPins);
        
        
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
    
    
}