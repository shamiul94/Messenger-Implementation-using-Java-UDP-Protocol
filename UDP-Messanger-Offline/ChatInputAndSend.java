

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author RummanBUET
 */
public class ChatInputAndSend implements Runnable {

    Message msg;
    String ServerName;
    String SenderName;
    DatagramPacket DataPack;
    DatagramSocket SendingtoServerSocket;
    InetAddress ServerAddress;
    Thread th ;

    public ChatInputAndSend(DatagramSocket socket, InetAddress add, String Server_name, String Sender_name) {
        ServerName = Server_name;
        SenderName = Sender_name;
        ServerAddress = add;

        SendingtoServerSocket = socket;// Client's socket but sends data to server.
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        while (true) {
            Scanner conin = new Scanner(System.in);
            String input = conin.nextLine();
            input = input + "$" ;
            StringTokenizer Token = new StringTokenizer(input, "$", false);
            String ReceiverName = Token.nextToken(), TextMessage = Token.nextToken();

            msg = new Message(ServerName, ReceiverName, SenderName, TextMessage);

            DataPack = new DatagramPacket(msg.get_Byte_Array(), msg.get_Byte_Array().length, ServerAddress, 5050);

            try {
                SendingtoServerSocket.send(DataPack);
            } catch (IOException ex) {
            }

        }
    }

}
