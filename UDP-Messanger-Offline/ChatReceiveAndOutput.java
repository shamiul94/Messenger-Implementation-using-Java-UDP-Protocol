

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RummanBUET
 */
public class ChatReceiveAndOutput implements Runnable {

    DatagramPacket DataPack ;
    DatagramSocket IncomingSocket ;
    Message msg ;
    Thread th ; 
   
    
    public ChatReceiveAndOutput(DatagramSocket sock) {
        IncomingSocket = sock ;
        
        th = new Thread(this);
        th.start();
    }
    

    @Override
    public void run() {
        while(true){
            byte[] Byte ;
            Byte = new byte[1024] ;
            DataPack = new DatagramPacket(Byte, Byte.length);
            try {
                IncomingSocket.receive(DataPack);
                System.out.println("RECEIVED");
            } catch (IOException ex) {
                Logger.getLogger(ChatReceiveAndOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Byte = DataPack.getData();
            
            msg = new Message(Byte , Byte.length);
            
            System.out.println( msg.SenderName + " says: " + msg.Body);
        }
    }
    
}
