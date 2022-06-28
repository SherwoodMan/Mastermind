import java.awt.Color;
import java.util.Arrays;


/*
 * Diese Klasse speichert eine Farbreihenfolge numerisch ab um die Arbeit mit der REihenfolge zu erleichtern z.B. für die Kontrollfunktionen
 */

public class Order {

    private int[] orderArray = new int[4];


    /*
     * Konstruktor direkt mit numerischen Werten
     */

    public Order(int zero, int one, int two, int three) {
        this.orderArray[0] = zero;
        this.orderArray[1] = one;
        this.orderArray[2] = two;
        this.orderArray[3] = three;

    }


    /*
     *  Konstruktor mit Farbwerten, die numerisch umgewandelt werden
     */

    public Order(Color zeroC, Color oneC, Color twoC, Color threeC) {
        Color[] colorArray = { zeroC, oneC, twoC, threeC };
        for (int i = 0; i < colorArray.length; i++) {
            if (colorArray[i].equals(Color.red)) {
                this.setOrderArrayNumber(i, 0);
            } else if (colorArray[i].equals(Color.blue)) {
                this.setOrderArrayNumber(i, 1);
            } else if (colorArray[i].equals(Color.green)) {
                this.setOrderArrayNumber(i, 2);
            } else if (colorArray[i].equals(Color.yellow)) {
                this.setOrderArrayNumber(i, 3);
            } else if (colorArray[i].equals(Color.MAGENTA)) {
                this.setOrderArrayNumber(i, 4);
            } else if (colorArray[i].equals(Color.darkGray)) {
                this.setOrderArrayNumber(i, 5);
            } else {
                this.setOrderArrayNumber(i, -3);
            }
        }

        System.out.println(this.toString());

    }


    /*
     * normaler Konstruktor ohne Werte
     */

    public Order() {
    }


    /*
     * getter für das Werte-Array 
     */
    public int[] getOrderArray() {
        return orderArray;
    }


    /*
     * getter für einen Wert des Arrays an einer bestimmten Stelle 
     */

    public int getOrderArrayNumber(int x) {
        if (x < orderArray.length && x >= 0) {
            return orderArray[x];
        } else {
            System.out.println("Außerhalb vom Array");
            return -1;
        }

    }

    
    /*
     * setter für das Werte-Array 
     */
    
    public void setOrderArray(int[] orderArray) {
        this.orderArray = orderArray;
    }


    /*
     * setter für das Werte-Array mit Einzelwerten
     */

    public void setOrderArrayWithInts(int zero, int one, int two, int three) {
        this.orderArray[0] = zero;
        this.orderArray[1] = one;
        this.orderArray[2] = two;
        this.orderArray[3] = three;
    }
    

    /*
     * setter für einen Wert des Arrays an einer bestimmten Stelle 
     */

    public void setOrderArrayNumber(int x, int toSet) {
        if (x < orderArray.length && x >= 0) {
            orderArray[x] = toSet;
        } else {
            System.out.println("Außerhalb vom Array");
        }

    }

    
    /*
     * Gibt ein Order-Objekt zurück, welches aus Farben kreiert wurde
     */

    public Order colorInOrder(Color zeroC, Color oneC, Color twoC, Color threeC) {
        return new Order(zeroC, oneC, twoC, threeC);
    }


    /*
     * gibt aus einem numerischen Order-Objekt die Farben zurück 
     */

    public Color[] orderInColor() {
        Color[] colorArray = new Color[4];
        for (int i = 0; i < colorArray.length; i++) {
            if (orderArray[i] == 0) {
                colorArray[i] = Color.red;
            } else if (orderArray[i] == 1) {
                colorArray[i] = Color.blue;
            } else if (orderArray[i] == 2) {
                colorArray[i] = Color.green;
            } else if (orderArray[i] == 3) {
                colorArray[i] = Color.yellow;
            } else if (orderArray[i] == 4) {
                colorArray[i] = Color.MAGENTA;
            } else if (orderArray[i] == 5) {
                colorArray[i] = Color.darkGray;
            }
        }
        return colorArray;
    }


    /*
     * Die toString Methode für die Klasse
     */

    @Override
    public String toString() {
        return "order = [" + Arrays.toString(orderArray) + "]";
    }
}