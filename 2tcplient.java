import java.net.*;
import java.io.*;
import java.util.*;

public class tcplient2 {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 5656);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("Client: ");
            String cmsg = sc.nextLine();
            dos.writeUTF(cmsg);
            if (cmsg.equalsIgnoreCase("bye")) break;

            String smsg = dis.readUTF();
            System.out.println("Server: " + smsg);
            if (smsg.equalsIgnoreCase("bye")) break;
        }

        s.close();
    }
}


