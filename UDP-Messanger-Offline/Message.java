

import java.net.DatagramPacket;

/**
 *
 * @author RummanBUET
 */
public class Message {

    String ServerName;
    String ReceiverName, SenderName ;
    DatagramPacket Data;
    String TotalMessage , Body ;
    String Temp;
    int ClientPort ;
    byte[] Arr = new byte[10000];

    public Message(String via, String to, String from, String Anything) {
        ServerName = "Via: " + via;
        ReceiverName = "\nTo: " + to;
        SenderName = "\nFrom: " + from;

        if (via.equals(to)) {
            Temp = ServerName + ReceiverName + SenderName + "\nPort: " + Anything;
            Arr = Temp.getBytes();
            ClientPort = Integer.parseInt(Anything) ;
            
        } else {
            Temp = ServerName + ReceiverName + SenderName + "\nBody: " + Anything;
            Arr = Temp.getBytes();
        }
    }

    public Message(byte Byte[] , int length) {
        Arr = Byte ;
        TotalMessage = new String(Byte);
        ServerName  = TotalMessage.substring(TotalMessage.indexOf("Via: ")+"Via: ".length(), TotalMessage.indexOf("To: ") - 1 );
        ReceiverName = TotalMessage.substring(TotalMessage.indexOf("To: ")+"To: ".length(), TotalMessage.indexOf("From: ") - 1 );
        
        if(ServerName.equals(ReceiverName)){
            String str ; 
            SenderName = TotalMessage.substring(TotalMessage.indexOf("From: ")+"From: ".length(), TotalMessage.indexOf("Port: ") - 1 );
            str = TotalMessage.substring(TotalMessage.indexOf("Port: ")+"Port: ".length(), length  );
            ClientPort = Integer.parseInt(str);
        }
        else 
        {
            SenderName = TotalMessage.substring(TotalMessage.indexOf("From: ")+"From: ".length(), TotalMessage.indexOf("Body: ") - 1 );
            Body = TotalMessage.substring(TotalMessage.indexOf("Body: ")+"Body: ".length(), length  );
        }
    }
    

    byte[] get_Byte_Array() {
        return Arr;
    }
}

