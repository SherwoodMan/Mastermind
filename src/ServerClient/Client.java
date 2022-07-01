package ServerClient;

import java.io.*;
import java.net.Socket;
/*
* Diese Klasse verbindet sich mit dem Server, der an Adresse IP_ADRESS und PORT auf Anfragen wartet.
* */
public class Client implements Runnable {

    private Socket server;
    // Zuhörer, der interessiert ist emfangene Nachrichten zu lesen.
    private ReceivedMessageListener listener;

    // stream um Objekte über das Netz zu verschicken.
    private ObjectOutputStream out;

    /*
     * enhält die Implementierung des Threads bzw. des Servers.
     * */
    @Override
    public void run() {
        try {
            // localhost = "127.0.0.1"
            server = new Socket(Constants.IP_ADDRESS, Constants.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
        try {
            out = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            while(true){
                MessageModel message = (MessageModel) in.readObject();
                handleReceivedMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * handelt emfangene Nachrichten.
     * Leitet die Nachrichten an Listener weiter.
     * */
    private void handleReceivedMessage(MessageModel message){
        if (listener != null){
            listener.onMessageReceived(message);
        }
    }

    /*
     * verschickt Objekte an dem Client.
     * */
    public void sendObject(Serializable message){
        try {
            out.reset();
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * registriert ein Listener.
     * */
    public void setListener(ReceivedMessageListener listener){
        this.listener = listener;
    }


}

