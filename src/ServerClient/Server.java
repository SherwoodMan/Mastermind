package ServerClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/*
    Server Klasse stellt einen ServerSocket her, der dann
    Verbindunganfragen von Clients akzeptiert, empfängt Nachrichten von Clients
    und informiert den Listener über die emfangene Nachricht.
    Diese Klasse läuft in einem eigenen Thread.
*/
public class Server implements Runnable {

    private ServerSocket server;
    // Client der akzeptiert wurde, von dem Nachrichten ermfangen werden können.
    private Socket client;

    // Zuhörer, der interessiert ist emfangene Nachrichten zu lesen.
    private ReceivedMessageListener listener;

    // stream um Objekte über das Netz zu verschicken.
    private ObjectOutputStream out;

    /*
    * enhält die Implementierung des Threads bzw. des Servers.
    * */
    @Override
    public void run() {
        if (server == null) {
            try {
                server = new ServerSocket(Constants.PORT);
                client = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            out = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            while(true){
                MessageModel message =  (MessageModel) in.readObject();
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
        System.out.println(message);
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
