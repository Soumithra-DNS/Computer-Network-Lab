package Email;

import java.io.*;
import java.util.Base64;
import javax.net.ssl.*;

public class Email {
    static DataOutputStream out;
    static BufferedReader in;

    public static void main(String[] args) throws Exception {
        String email = "s2210976136@ru.ac.bd";
        String password = "jiezxjfeecryzcwa";

        String user = Base64.getEncoder().encodeToString(email.getBytes());
        String pass = Base64.getEncoder().encodeToString(password.getBytes());

        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);

        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        read();

        send("EHLO smtp.gmail.com");
        readmulti();

        send("AUTH LOGIN");
        read();

        send(user);
        read();

        send(pass);
        read();

        send("MAIL FROM:<s2210976136@ru.ac.bd>");
        read();

        // TO
        send("RCPT TO:<soumithradebnathshanto31@gmail.com>");
        read();

        // CC
        send("RCPT TO:<shantodn2003@gmail.com>");
        read();

        // BCC
        send("RCPT TO:<soumithradebnathshanto2003@gmail.com>");
        read();

        send("DATA");
        read();

        send("FROM: s2210976136@ru.ac.bd");
        send("TO: soumithradebnathshanto31@gmail.com");
        send("CC: shantodn2003@gmail.com");
        send("Subject: Email test");
        send("");

        send("This is test email");

        send(".");
        read();

        send("QUIT");
        read();

    }

    static void read() throws Exception {
        System.out.println(in.readLine());
    }

    static void send(String msg) throws Exception {
        out.writeBytes(msg + "\r\n");
    }

    static void readmulti() throws Exception {
        String line;
        while ((line = in.readLine()) != null) {
            if (line.startsWith("250 "))
                break;
        }
    }

}
