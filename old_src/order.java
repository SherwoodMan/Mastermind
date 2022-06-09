import java.awt.Color;
import java.util.Arrays;

public class Order {

    private int[] orderArray = new int[4];

    public Order(int zero, int one, int two, int three) {
        this.orderArray[0] = zero;
        this.orderArray[1] = one;
        this.orderArray[2] = two;
        this.orderArray[3] = three;

    }

    public Order(Color zeroC, Color oneC, Color twoC, Color threeC) {
        Color[] colorArray = { zeroC, oneC, twoC, threeC };
        for (int i = 0; i < colorArray.length; i++) {
            if (colorArray[i] == Color.red) {
                this.setOrderArrayNumber(i, 0);
            } else if (colorArray[i] == Color.blue) {
                this.setOrderArrayNumber(i, 1);
            } else if (colorArray[i] == Color.green) {
                this.setOrderArrayNumber(i, 2);
            } else if (colorArray[i] == Color.yellow) {
                this.setOrderArrayNumber(i, 3);
            } else if (colorArray[i] == Color.MAGENTA) {
                this.setOrderArrayNumber(i, 4);
            } else if (colorArray[i] == Color.darkGray) {
                this.setOrderArrayNumber(i, 5);
            } else {
                this.setOrderArrayNumber(i, -3);
            }
        }

        System.out.println(this.toString());

    }

    public Order() {
    }

    public int[] getOrderArray() {
        return orderArray;
    }

    public int getOrderArrayNumber(int x) {
        if (x < orderArray.length && x >= 0) {
            return orderArray[x];
        } else {
            System.out.println("Außerhalb vom Array");
            return -1;
        }

    }

    public void setOrderArrayNumber(int x, int toSet) {
        if (x < orderArray.length && x >= 0) {
            orderArray[x] = toSet;
        } else {
            System.out.println("Außerhalb vom Array");
        }

    }

    public void setOrderArray(int[] orderArray) {
        this.orderArray = orderArray;
    }

    public void setOrderArrayWithInts(int zero, int one, int two, int three) {
        this.orderArray[0] = zero;
        this.orderArray[1] = one;
        this.orderArray[2] = two;
        this.orderArray[3] = three;
    }

    @Override
    public String toString() {
        return "Order [orderArray=" + Arrays.toString(orderArray) + "]";
    }

    public Order colorInOrder(Color zeroC, Color oneC, Color twoC, Color threeC) {
        Order colorOrder = new Order();
        Color[] colorArray = { zeroC, oneC, twoC, threeC };
        for (int i = 0; i < colorArray.length; i++) {
            if (colorArray[i].equals(Color.red)) {
                colorOrder.setOrderArrayNumber(i, 0);
            } else if (colorArray[i].equals(Color.blue)) {
                colorOrder.setOrderArrayNumber(i, 1);
            } else if (colorArray[i].equals(Color.green)) {
                colorOrder.setOrderArrayNumber(i, 2);
            } else if (colorArray[i].equals(Color.yellow)) {
                colorOrder.setOrderArrayNumber(i, 3);
            } else if (colorArray[i].equals(Color.MAGENTA)) {
                colorOrder.setOrderArrayNumber(i, 4);
            } else if (colorArray[i].equals(Color.darkGray)) {
                colorOrder.setOrderArrayNumber(i, 5);
            } else {
                colorOrder.setOrderArrayNumber(i, -3);
            }
        }

        return colorOrder;
    }

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
}