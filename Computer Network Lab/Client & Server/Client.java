import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 7777);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command (HI/Date/Time/IP/exit): ");
            String msg = sc.nextLine();

            dos.writeUTF(msg);
            dos.flush();

            if (msg.equalsIgnoreCase("exit")) {
                break;
            }

            String response = dis.readUTF();
            System.out.println("Server: " + response);
        }

        s.close();
        sc.close();
    }
}