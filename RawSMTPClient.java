import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.Base64;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class RawSMTPClient {
    public static void main(String[] args) {
        final String smtpServer = "smtp.gmail.com";
        final int smtpPort = 587;
        final String from = "sharvarijadhav2111@gmail.com";
        final String to = "sdjadhav9472@gmail.com";
        final String username = "sharvarijadhav2111@gmail.com";
        final String password = "$harvar!11"; // Use app-specific password if 2FA is enabled
        final String subject = "Test Email from Java";
        final String body = "This is a test email sent from a simple Java program.";

        try (Socket socket = new Socket(smtpServer, smtpPort);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Server response: " + reader.readLine());

            // Send EHLO command
            writer.write("EHLO localhost\r\n");
            writer.flush();
            String response;
            while ((response = reader.readLine()).startsWith("250-")) {
                System.out.println("Server response: " + response);
            }
            System.out.println("Server response: " + response);

            // Send STARTTLS command
            writer.write("STARTTLS\r\n");
            writer.flush();
            System.out.println("Server response: " + reader.readLine());

            // Upgrade to TLS connection
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, smtpServer, smtpPort, true);
            BufferedWriter sslWriter = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader sslReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Re-send EHLO command over TLS
            sslWriter.write("EHLO localhost\r\n");
            sslWriter.flush();
            while ((response = sslReader.readLine()).startsWith("250-")) {
                System.out.println("Server response: " + response);
            }
            System.out.println("Server response: " + response);

            // Authenticate using LOGIN method
            sslWriter.write("AUTH LOGIN\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            sslWriter.write(Base64.getEncoder().encodeToString(username.getBytes()) + "\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            sslWriter.write(Base64.getEncoder().encodeToString(password.getBytes()) + "\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            // Send MAIL FROM command
            sslWriter.write("MAIL FROM:<" + from + ">\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            // Send RCPT TO command
            sslWriter.write("RCPT TO:<" + to + ">\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            // Send DATA command
            sslWriter.write("DATA\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            // Send email headers and body
            sslWriter.write("From: " + from + "\r\n");
            sslWriter.write("To: " + to + "\r\n");
            sslWriter.write("Subject: " + subject + "\r\n");
            sslWriter.write("\r\n"); // End of headers
            sslWriter.write(body + "\r\n");
            sslWriter.write(".\r\n"); // End of message
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

            // Send QUIT command
            sslWriter.write("QUIT\r\n");
            sslWriter.flush();
            System.out.println("Server response: " + sslReader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
