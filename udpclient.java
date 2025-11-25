package EXAM;

import java.net.*;
import java.util.*;

public class udpclient {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();

        System.out.print("Enter message: ");
        Scanner sc = new Scanner(System.in);
        byte[] data = sc.nextLine().getBytes();

        ds.send(new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 9876));

        ds.close();
    }
}
