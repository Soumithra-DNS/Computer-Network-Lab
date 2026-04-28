import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket socket = new ServerSocket(7777);
        System.out.println("Server waiting...");

        Socket new_socket = socket.accept();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        // Receive from client
        String clientMsg = dis.readUTF();
        System.out.println("Client says: " + clientMsg);

        // Send reply to client
        dos.writeUTF("Hello Client, I am fine!");

        socket.close();
        new_socket.close();
    }
}