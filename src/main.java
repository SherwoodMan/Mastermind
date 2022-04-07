public class main {

    public static void main(String[] args) {
        int zero = (int) (Math.random() * 6);
        int one = (int) (Math.random() * 6);
        int two = (int) (Math.random() * 6);
        int three = (int) (Math.random() * 6);

        order sample = new order(zero, one, two, three);
        control con;
        do{
            int zeroC = (int) (Math.random() * 6);
            int oneC = (int) (Math.random() * 6);
            int twoC = (int) (Math.random() * 6);
            int threeC = (int) (Math.random() * 6);
            order toCompare = new order(zeroC, oneC, twoC, threeC);

            con  = new control(sample, toCompare);

        } while (con.getBlackPins() < 4);
        

    }

}