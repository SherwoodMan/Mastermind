

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{

    void setCon(Checker con) throws RemoteException;

    Order getToGuess() throws RemoteException;

    Checker getCon() throws RemoteException;

    void setToGuess(Order toGuess) throws RemoteException;

    Color[][] getGuesses() throws RemoteException;

    void setGuesses(Color[][] guesses) throws RemoteException;

    Color[][] getReaction() throws RemoteException;

    void setReaction(Color[][] reaction) throws RemoteException;

    int getRound() throws RemoteException;

    void setRound(int round) throws RemoteException;
    
}
