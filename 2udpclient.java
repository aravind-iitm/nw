

import java.net.*;
import java.util.*;

public class udpclient2 {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);
        byte[] buf = new byte[1024];

        while (true) {

            System.out.print("Client: ");
            String cmsg = sc.nextLine();
            ds.send(new DatagramPacket(cmsg.getBytes(), cmsg.length(), ip, 5656));
            if (cmsg.equalsIgnoreCase("bye")) break;

            DatagramPacket rp = new DatagramPacket(buf, buf.length);
            ds.receive(rp);
            String smsg = new String(rp.getData(), 0, rp.getLength());
            System.out.println("Server: " + smsg);
            if (smsg.equalsIgnoreCase("bye")) break;
        }

        ds.close();
    }
}


