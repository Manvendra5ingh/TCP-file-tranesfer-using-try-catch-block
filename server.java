import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket clientSocket = serverSocket.accept();

            BufferedInputStream fileInput = new BufferedInputStream(clientSocket.getInputStream());

            FileOutputStream fileOutput = new FileOutputStream("server_file.txt");
            BufferedOutputStream bufferedFileOutput = new BufferedOutputStream(fileOutput);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInput.read(buffer)) != -1) {
                bufferedFileOutput.write(buffer, 0, bytesRead);
            }

            bufferedFileOutput.flush();

            fileInput.close();
            bufferedFileOutput.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
