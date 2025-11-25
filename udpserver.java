
import java.net.*;

public class udpserver {
    public static void main(String[] args) throws Exception {
        System.out.println("Server: Starting...");
        DatagramSocket ds = new DatagramSocket(9876);

        byte[] buf = new byte[1024];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);

        System.out.println("Received: " + new String(p.getData(), 0, p.getLength()));

        ds.close();
    }
}

