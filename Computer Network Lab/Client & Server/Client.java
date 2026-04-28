import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 7777);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        // Send message to server
        dos.writeUTF("How are you Server?");

        // Receive reply from server
        String serverMsg = dis.readUTF();
        System.out.println("Server says: " + serverMsg);

        s.close();
    }
}