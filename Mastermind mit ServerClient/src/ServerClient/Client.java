package ServerClient;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket server;
    private ReceivedMessageListener listener;
    private OnConnectionListener connectionListener;

    private ObjectOutputStream out;

    public void run() {
        try {
            server = new Socket("localhost", Constants.PORT);
            if (connectionListener != null){
                connectionListener.onConnection();
            }
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

    private void handleReceivedMessage(MessageModel message){
        if (listener != null){
            listener.onMessageReceived(message);
        }
    }

    public void sendMessage(String message){
        try {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
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

    public void setConnectionListener(OnConnectionListener connectionListener){
        this.connectionListener = connectionListener;
    }

}

