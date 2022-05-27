import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Checker con = new Checker();
    private Order toGuess = new Order();
    private MainGUI gui = new MainGUI();
    private int role = -1;
    private Color[][] guesses = new Color[4][12];
    private Color[][] reaction = new Color[4][12];
    private int round;
    private static Server server;


    public Client(String name) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry("localhost", 2000);
        server = (Server) registry.lookup("Server");
        System.out.println(name + "Angemeldet");
        
    }

    

    public Checker getCon() throws RemoteException {
        con =  server.getCon();
        return con;
    }



    public void setCon(Checker con) throws RemoteException {
         server.setCon(con);
        this.con = con;
    }



    public Order getToGuess() throws RemoteException {
        toGuess = server.getToGuess();
        return toGuess;
    }



    public void setToGuess(Order toGuess) throws RemoteException {
        server.setToGuess(toGuess);
        this.toGuess = toGuess;
    }



    public int getRole() {
        // TODO
        return role;
    }



    public void setRole(int role) {
        // TODO
        this.role = role;
    }



    public Color[][] getGuesses() throws RemoteException {
        guesses = server.getGuesses();
        return guesses;
    }



    public void setGuesses(Color[][] guesses) throws RemoteException {
        server.setGuesses(guesses);
        this.guesses = guesses;
    }



    public Color[][] getReaction() throws RemoteException {
        reaction = server.getReaction();
        return reaction;
    }



    public void setReaction(Color[][] reaction) throws RemoteException {
        server.setReaction(reaction);
        this.reaction = reaction;
    }



    public int getRound() throws RemoteException {
        round = server.getRound();
        return round;
    }



    public void setRound(int round) throws RemoteException {
        server.setRound(round);
        this.round = round;
    }



    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client c = new Client(null);
        System.out.println(c.getRound());
        c.setRound(2);
        int i = server.getRound();
        System.out.println(i);

    }


    
}
