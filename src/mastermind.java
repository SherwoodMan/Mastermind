public class mastermind {

    private static boolean multiplePins = true;
    
    
    public mastermind() {
    }


    public static void main(String[] args) {
        control con = new control();
        order sample = new order(-1,-1, -1, -1);

        int zero, one,two,three;

        do{
            zero = (int) (Math.random() * 6);
        }while (con.doubledPins(sample.getOrderArray(), zero) && multiplePins == true);
        sample.getOrderArray()[0] = zero;
        do{
            one = (int) (Math.random() * 6);
        }while (con.doubledPins(sample.getOrderArray(), one) && multiplePins == true);
        sample.getOrderArray()[1] = one;
        do{
            two = (int) (Math.random() * 6);
        }while (con.doubledPins(sample.getOrderArray(), two) && multiplePins == true);
        sample.getOrderArray()[2] = two;
        do{
            three = (int) (Math.random() * 6);
        }while (con.doubledPins(sample.getOrderArray(), three) && multiplePins == true); 
        sample.getOrderArray()[3] = three;
        

        System.out.println(sample.toString());
        int versuch = 0;
        do{
            versuch++;
            System.out.println("Versuch:" + versuch);
            int zeroC = (int) (Math.random() * 6);
            int oneC = (int) (Math.random() * 6);
            int twoC = (int) (Math.random() * 6);
            int threeC = (int) (Math.random() * 6);
            order toCompare = new order(zeroC, oneC, twoC, threeC);
            System.out.println(sample.toString());
            System.out.println(toCompare.toString());
            con.compare(sample, toCompare);
            

        } while (con.getBlackPins() < 4);

    }

    public boolean isMultiplePins() {
        return multiplePins;
    }


    public void setMultiplePins(boolean multiplePins) {
        this.multiplePins = multiplePins;
    }

    


}