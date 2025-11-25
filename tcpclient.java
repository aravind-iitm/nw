package EXAM;

import java.net.*;
import java.io.*;
import java.util.*;

public class tcpclient {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 9876);

        System.out.print("Enter message: ");
        String msg = new Scanner(System.in).nextLine();

        new DataOutputStream(s.getOutputStream()).writeUTF(msg);

        s.close();
    }
}
