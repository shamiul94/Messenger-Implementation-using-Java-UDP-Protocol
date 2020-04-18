

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
    
    Hashtable<String , Info> Map ;

    String ServerName;
    int Listen_Port = 5050;
    
    DatagramSocket incoming_socket;
    DatagramSocket outgoing_socket;
    
    
    DatagramPacket DataPack;

    Server(String string) {
        ServerName = string;
        
        Map = new Hashtable() ;
        
        try {
            outgoing_socket = new DatagramSocket();
            incoming_socket = new DatagramSocket(Listen_Port);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(this).start();
    }

    void Manager() throws IOException {

        byte[] Byte = new byte[1024];
        Message msg ;
        DataPack = new DatagramPacket(Byte, Byte.length);

        incoming_socket.receive(DataPack);
        
        msg = new Message(DataPack.getData(), DataPack.getLength());//**********
        
        if(!(msg.ServerName).equals(this.ServerName)){
            System.out.println("Warning: Server name mismatch. Message dropped.");
        }else if((msg.ReceiverName).equals(this.ServerName)) {
            Map.put(msg.SenderName, new Info(DataPack.getAddress() , msg.ClientPort));
            System.out.println(Map) ;
        }
        else {
            Info DestinationInfo ;
            DestinationInfo = Map.get(msg.ReceiverName);
            if(DestinationInfo == null){
                System.out.println("Warning: Client2 name mismatch. Message dropped.");
            }
            else {
                
                //if(msg.SenderName.equals(Byte)) System.out.println("FOUND");
                DataPack.setAddress(DestinationInfo.ClientAddress);
                DataPack.setPort(DestinationInfo.ClientPort);
                
                
                
                outgoing_socket.send(DataPack);
                
                System.out.println(DataPack.getAddress() + " " +DataPack.getPort() );
                System.out.println("SENT");
            }
        }
        
    }

    @Override
    public void run() {
        while (true) {
            try {
                
                Manager();
                
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new Server(args[0]);
        }
    }

}
