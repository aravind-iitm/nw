package EXAM;

import java.net.*;
import java.io.*;
import java.util.*;

public class tcpserver2 {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5656);
        Socket s = ss.accept();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {
            String cmsg = dis.readUTF();
            System.out.println("Client: " + cmsg);
            if (cmsg.equalsIgnoreCase("bye")) break;

            System.out.print("Server: ");
            String smsg = sc.nextLine();
            dos.writeUTF(smsg);
            if (smsg.equalsIgnoreCase("bye")) break;
        }

        s.close();
        ss.close();
    }
}

