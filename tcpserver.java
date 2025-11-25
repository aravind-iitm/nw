package EXAM;

import java.net.*;
import java.io.*;

public class tcpserver {
    public static void main(String[] args) throws Exception {
        System.out.println("Server: Starting...");
        ServerSocket ss = new ServerSocket(9876);
        Socket s = ss.accept();
        System.out.println("Server: Connection established with client at " + s.getInetAddress().getHostAddress());
        String msg = new DataInputStream(s.getInputStream()).readUTF();
        System.out.println("Received: " + msg);

        s.close();
        ss.close();
    }
}
