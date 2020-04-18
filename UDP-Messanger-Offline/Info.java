

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.InetAddress;



/**
 *
 * @author RummanBUET
 */
public class Info {
    InetAddress ClientAddress ;
    int ClientPort ;
    
    Info(InetAddress Add , int port){
        ClientAddress = Add ;
        ClientPort = port ;
    }
    
    
}
