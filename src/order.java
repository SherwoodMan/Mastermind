public class order{
    
    private int[] orderArray = new int[4];

    public order(int zero, int one, int two, int three) {
        this.orderArray[0] = zero;
        this.orderArray[1] = one;
        this.orderArray[2] = two;
        this.orderArray[3] = three;

    }

    public int[] getOrderArray() {
        return orderArray;
    }

    public int getOrderArrayNumber(int x){
        if (x < orderArray.length && x >= 0) {
            return orderArray[x];
        } else {
            System.out.println("Außerhalb vom Array");
            return -1;
        }
        
    }

    public void setOrderArray(int[] orderArray) {
        this.orderArray = orderArray;
    }

    
    

}