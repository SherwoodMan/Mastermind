public class mastermind {

    private static control con = new control();
    private static order toGuess = new order();

    public static void main(String[] args) {
        randomOrder(toGuess);
        System.out.println(toGuess.toString());
        guiVersionTwo newgame = new guiVersionTwo();
        newgame.initializeStart();

    }

    public static order randomOrder(order randomOrder) {
        int zero, one, two, three;

        do {
            zero = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), zero));
        randomOrder.getOrderArray()[0] = zero;
        do {
            one = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), one));
        randomOrder.getOrderArray()[1] = one;
        do {
            two = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), two));
        randomOrder.getOrderArray()[2] = two;
        do {
            three = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), three));
        randomOrder.getOrderArray()[3] = three;

        return randomOrder;
    }

    public static order getToGuess() {
        return toGuess;
    }

    public static control getCon() {
        return con;
    }
}