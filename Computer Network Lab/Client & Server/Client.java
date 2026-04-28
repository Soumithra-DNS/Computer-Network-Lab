import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 7777);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // Send message to server
        dos.writeUTF("How are you Server?");

        // Receive reply from server
        String serverMsg = dis.readUTF();
        System.out.println("Server says: " + serverMsg);

        socket.close();
    }
}