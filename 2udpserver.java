
import java.net.*;
import java.util.*;

public class udpserver2 {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket(5656);
        Scanner sc = new Scanner(System.in);
        byte[] buf = new byte[1024];

        while (true) {

            DatagramPacket rp = new DatagramPacket(buf, buf.length);
            ds.receive(rp);
            String cmsg = new String(rp.getData(), 0, rp.getLength());
            System.out.println("Client: " + cmsg);
            if (cmsg.equalsIgnoreCase("bye")) break;

            System.out.print("Server: ");
            String smsg = sc.nextLine();
            byte[] out = smsg.getBytes();
            ds.send(new DatagramPacket(out, out.length, rp.getAddress(), rp.getPort()));
            if (smsg.equalsIgnoreCase("bye")) break;
        }

        ds.close();
    }
}


