import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server{

    private Checker con = new Checker();
    private Order toGuess = new Order();
    private Color[][] guesses = new Color[4][12];
    private Color[][] reaction = new Color[4][12];
    private int round = 800;

    protected ServerImpl() throws RemoteException, NotBoundException {
        super();
        this.greyingArray();
        Registry registry = LocateRegistry.createRegistry(2000);
        registry.rebind("Server", this);
        System.out.println("Server runs!");
        
    }



    public Checker getCon() {
        return con;
    }



    public void setCon(Checker con) {
        this.con = con;
    }



    public Order getToGuess() {
        return toGuess;
    }



    public void setToGuess(Order toGuess) {
        this.toGuess = toGuess;
    }



    public Color[][] getGuesses() {
        return guesses;
    }



    public void setGuesses(Color[][] guesses) {
        this.guesses = guesses;
    }



    public Color[][] getReaction() {
        return reaction;
    }



    public void setReaction(Color[][] reaction) {
        this.reaction = reaction;
    }



    public int getRound() {
        return round;
    }



    public void setRound(int round) {
        this.round = round;
    }



    

    public Order randomOrder(Order randomOrder) {
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

    private void greyingArray() {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[1].length; j++) {
                if (guesses[i][j] == null) {
                    guesses[i][j] = Color.GRAY;
                }
            }
        }
        for (int i = 0; i < reaction.length; i++) {
            for (int j = 0; j < reaction[1].length; j++) {
                if (reaction[i][j] == null) {
                    reaction[i][j] = Color.GRAY;
                }
            }
        }
    }

    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new ServerImpl();
    }
}
