package ServerClient;
/*
* Schnittstelle für Zuhörer der empfangenen Nachrichten
* */
public interface ReceivedMessageListener {
    void onMessageReceived(MessageModel message);

}
