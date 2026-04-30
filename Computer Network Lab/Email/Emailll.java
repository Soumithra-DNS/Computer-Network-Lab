package Email;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Enumeration;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Emailll {

    static DataOutputStream out;
    static BufferedReader in;

    public static void main(String[] args) throws Exception {

        // 🔐 Gmail credentials (USE APP PASSWORD ONLY)
        String email = "s2210976136@ru.ac.bd";
        String password = "YOUR_APP_PASSWORD_HERE";

        // 🌐 System info
        String ip = InetAddress.getLocalHost().getHostAddress();
        String time = LocalDateTime.now().toString();
        String mac = getMac();

        // 🔐 Base64 encoding
        String user = Base64.getEncoder().encodeToString(email.getBytes());
        String pass = Base64.getEncoder().encodeToString(password.getBytes());

        // 🔌 SMTP connection
        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault()
                .createSocket("smtp.gmail.com", 465);

        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        read();

        // Handshake
        send("EHLO smtp.gmail.com");
        readmulti();

        // Login
        send("AUTH LOGIN");
        read();

        send(user);
        read();

        send(pass);
        read();

        // Sender
        send("MAIL FROM:<" + email + ">");
        read();

        // Recipients
        send("RCPT TO:<soumithradebnathshanto31@gmail.com>");
        read();

        send("RCPT TO:<shantodn2003@gmail.com>");
        read();

        send("RCPT TO:<soumithradebnathshanto2003@gmail.com>");
        read();

        // Email data start
        send("DATA");
        read();

        // Headers
        send("FROM: " + email);
        send("TO: soumithradebnathshanto31@gmail.com");
        send("CC: shantodn2003@gmail.com");
        send("Subject: Java SMTP Test Email");
        send("");

        // Body
        send("Hello, this is a test email from Java SMTP client.");
        send(" ");
        send("IP Address: " + ip);
        send("System Time: " + time);
        send("MAC Address: " + mac);

        // End message
        send(".");
        read();

        // Close connection
        send("QUIT");
        read();
    }

    // 📌 MAC Address method
    static String getMac() {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

            while (nets.hasMoreElements()) {
                NetworkInterface net = nets.nextElement();
                byte[] mac = net.getHardwareAddress();

                if (mac != null) {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i],
                                (i < mac.length - 1) ? "-" : ""));
                    }

                    return sb.toString();
                }
            }
        } catch (Exception e) {
            return "MAC Error";
        }
        return "Not Found";
    }

    // 📤 send command
    static void send(String msg) throws Exception {
        out.writeBytes(msg + "\r\n");
        out.flush();
    }

    // 📥 read response
    static void read() throws Exception {
        System.out.println(in.readLine());
    }

    // 📥 multi-line response
    static void readmulti() throws Exception {
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("250 ")) break;
        }
    }
}