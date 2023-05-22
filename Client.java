import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out;
    public static void main(String args[]) throws Exception {
        int port = 25;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);


                String text;

                do {
                    text = reader.readLine();
                    System.out.println("text  " + text);
                   File file = new File(text);
                    System.out.println(file);

                    byte[] buffer = new byte[4 * 1024];
                    ZipInputStream zis = new ZipInputStream(fileInputStream);
                    ZipEntry zipEntry = zis.getNextEntry();
//                    String reverseText = new StringBuilder(text).reverse().toString();
//                    writer.println("Server: " + reverseText);
                    zis.closeEntry();
                    zis.close();
                } while (!text.equals("bye"));

                socket.close();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
