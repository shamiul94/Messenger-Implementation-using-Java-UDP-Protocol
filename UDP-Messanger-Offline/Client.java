

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    String ClientName;
    int ClientReceivingPort  ;
    String CRPortString ;
    String ServerIP;
    String ServerName;
    Message msg ;
    InetAddress ServerAddress;
    byte[] Byte ;
    
    DatagramPacket DataPack ;
    DatagramSocket IncomingSocket , OutgoingSocket ;

    Client(String string, String port, String ip, String string2) throws UnknownHostException, SocketException {
        Byte = new byte[10000];
        ClientName = string;
        CRPortString = port ;
        ClientReceivingPort = Integer.parseInt(CRPortString);
        ServerIP = ip;
        ServerName = string2;
        ServerAddress = InetAddress.getByName(ServerIP);
        
        
        OutgoingSocket = new DatagramSocket();
        IncomingSocket = new DatagramSocket(ClientReceivingPort);
        System.out.println(ClientReceivingPort);
        
        
        
        msg = new Message(ServerName, ServerName, ClientName, CRPortString);
        
        
        Byte = msg.get_Byte_Array();
        DataPack = new DatagramPacket(Byte, Byte.length , ServerAddress, 5050);
        
        try { 
            OutgoingSocket.send(DataPack);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(this).start();
    }

    @Override
    public void run() {
        
        
        ChatInputAndSend SendingtoServer = new ChatInputAndSend(OutgoingSocket, ServerAddress, ServerName, ClientName);
        
        ChatReceiveAndOutput ReceivingfromServer = new ChatReceiveAndOutput(IncomingSocket);
        
        
        try {
            
            
            SendingtoServer.th.join();
            ReceivingfromServer.th.join();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        if (args.length > 0) {
            new Client(args[0], args[1], args[2], args[3]);
        } 
    }

}
