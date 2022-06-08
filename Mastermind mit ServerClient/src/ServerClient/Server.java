package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private ServerSocket server;
    private Socket client;

    private ReceivedMessageListener listener;

    @Override
    public void run() {
        if (server == null) {
            try {
                server = new ServerSocket(1500);
                client = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            while(true){
                String message = in.readUTF();
                handleReceivedMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleReceivedMessage(String message){
        System.out.println(message);
        if (listener != null){
            listener.onMessageReceived(message);
        }
    }

    public void sendMessage(String message){
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListener(ReceivedMessageListener listener){
        this.listener = listener;
    }
}
