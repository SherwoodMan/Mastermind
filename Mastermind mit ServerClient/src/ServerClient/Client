package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private Socket server;
    private ReceivedMessageListener listener;
    private OnConnectionListener connectionListener;

    public void run() {
        try {
            server = new Socket("localhost", 1500);
            if (connectionListener != null){
                connectionListener.onConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
        try{
            DataInputStream in = new DataInputStream(server.getInputStream());
            while(true){
                String message = in.readUTF();
                handleReceivedMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleReceivedMessage(String message){
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

    public void setListener(ReceivedMessageListener listener){
        this.listener = listener;
    }

    public void setConnectionListener(OnConnectionListener connectionListener){
        this.connectionListener = connectionListener;
    }

}

