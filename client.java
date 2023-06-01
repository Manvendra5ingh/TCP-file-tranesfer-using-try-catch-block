import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            FileInputStream fileInput = new FileInputStream("client_file.txt");
            BufferedInputStream bufferedFileInput = new BufferedInputStream(fileInput);

            OutputStream fileOutput = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bufferedFileInput.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

            fileOutput.flush();

            bufferedFileInput.close();
            fileOutput.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
