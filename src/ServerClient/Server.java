package ServerClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private ServerSocket server;
    private Socket client;

    private ReceivedMessageListener listener;

    private ObjectOutputStream out;

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

    private void handleReceivedMessage(MessageModel message){
        System.out.println(message);
        if (listener != null){
            listener.onMessageReceived(message);
        }
    }


    public void sendObject(Serializable message){
        try {
            out.reset();
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListener(ReceivedMessageListener listener){
        this.listener = listener;
    }
}
