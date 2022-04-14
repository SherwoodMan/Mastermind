public class mastermind {

    private static boolean multiplePins = false;
    private static boolean easyMode;
    static control con = new control();
    public mastermind() {
    }

    public static void main(String[] args) {
        
        order sample = new order(-1, -1, -1, -1);
            sample = randomOrder(sample);
        
        
        

        System.out.println(sample.toString());
        int versuch = 0;
        do {
            versuch++;
            order toCompare = randomGuess();
            System.out.println("Versuch:" + versuch);
            System.out.println(sample.toString());
            System.out.println(toCompare.toString());
            
            con.compare(sample, toCompare, easyMode);

        } while (con.getBlackPins() < 4);

    }

    public static order randomGuess(){
        int zeroC = (int) (Math.random() * 6);
            int oneC = (int) (Math.random() * 6);
            int twoC = (int) (Math.random() * 6);
            int threeC = (int) (Math.random() * 6);
            return new order(zeroC, oneC, twoC, threeC);
            

    }

    public static order randomOrder(order randomOrder) {
        int zero, one, two, three;

        do {
            zero = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), zero) && multiplePins == true);
        randomOrder.getOrderArray()[0] = zero;
        do {
            one = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), one) && multiplePins == true);
        randomOrder.getOrderArray()[1] = one;
        do {
            two = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), two) && multiplePins == true);
        randomOrder.getOrderArray()[2] = two;
        do {
            three = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), three) && multiplePins == true);
        randomOrder.getOrderArray()[3] = three;

        return randomOrder;
    }
    
    public boolean isMultiplePins() {
        return multiplePins;
    }

    public void setMultiplePins(boolean multiplePins) {
        this.multiplePins = multiplePins;
    }

    public static boolean isEasyMode() {
        return easyMode;
    }

    public static void setEasyMode(boolean easyMode) {
        mastermind.easyMode = easyMode;
    }

}