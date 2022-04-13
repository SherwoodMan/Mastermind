public class durchschnitt {

    public static void main(String[] args) {
        double durchschnitt = 0;
        for (int i = 1; i <= 10000000; i++) {
            durchschnitt = durchschnitt + name();
            System.out.println("Zwischenstand(" + i + "):" + durchschnitt / i);
        }

    }

    public static int name() {
        control con = new control();
        order sample = new order(-1, -1, -1, -1);

        int zero, one, two, three;

        boolean multiplePins = false;
        do {
            zero = (int) (Math.random() * 6);
        } while (con.doubledPins(sample.getOrderArray(), zero) && multiplePins == true);
        sample.getOrderArray()[0] = zero;
        do {
            one = (int) (Math.random() * 6);
        } while (con.doubledPins(sample.getOrderArray(), one) && multiplePins == true);
        sample.getOrderArray()[1] = one;
        do {
            two = (int) (Math.random() * 6);
        } while (con.doubledPins(sample.getOrderArray(), two) && multiplePins == true);
        sample.getOrderArray()[2] = two;
        do {
            three = (int) (Math.random() * 6);
        } while (con.doubledPins(sample.getOrderArray(), three) && multiplePins == true);
        sample.getOrderArray()[3] = three;

        // System.out.println(sample.toString());
        int versuch = 0;
        do {
            versuch++;
            int zeroC = (int) (Math.random() * 6);
            int oneC = (int) (Math.random() * 6);
            int twoC = (int) (Math.random() * 6);
            int threeC = (int) (Math.random() * 6);
            order toCompare = new order(zeroC, oneC, twoC, threeC);
            // System.out.println(sample.toString());
            // System.out.println(toCompare.toString());
            con.compare(sample, toCompare, true);

        } while (con.getBlackPins() < 4);
        return versuch;
    }
}
