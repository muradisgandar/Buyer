/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.tcpconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author murad_isgandar
 */
public class TCPClient {

    private static Socket socket;

    public static void startConnection() throws Exception {
            String host = "localhost";
            int port = 6789;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
    }

    public static void recieveMessage() throws Exception{
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            
            System.out.println("Received from server: " + message + "\n");

    }

    public static void sendMessage(String message) throws Exception{

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String sendMessage = message + "\n";
            bw.write(sendMessage);
            bw.flush();
    }

}
