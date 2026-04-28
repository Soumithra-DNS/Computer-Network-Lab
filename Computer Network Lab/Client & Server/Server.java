import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket socket = new ServerSocket(7777);
        System.out.println("Server started...");

        Socket new_Socket = socket.accept();
        System.out.println("Client connected");

        DataInputStream dis = new DataInputStream(new_Socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(new_Socket.getOutputStream());

        while (true) {
            String request = dis.readUTF();

            if (request.equalsIgnoreCase("HI")) {
                dos.writeUTF("Hello");
            } 
            else if (request.equalsIgnoreCase("Date")) {
                dos.writeUTF("Server Date: " + LocalDate.now());
            } 
            else if (request.equalsIgnoreCase("Time")) {
                dos.writeUTF("Server Time: " + LocalTime.now());
            } 
            else if (request.equalsIgnoreCase("IP")) {
                String ip = InetAddress.getLocalHost().getHostAddress();
                dos.writeUTF("Server IP: " + ip);
            } 
            else if (request.equalsIgnoreCase("exit")) {
                break;
            } 
            else {
                dos.writeUTF("Invalid Request");
            }

            dos.flush();
        }

        socket.close();
        new_Socket.close();
        System.out.println("Server closed.");
    }
}