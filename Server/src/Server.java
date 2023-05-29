import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server is Listening...");
            while(true){
                Socket client = serverSocket.accept();

            }
        } catch (IOException e) {
            System.out.println("The port is occupied");
        }

    }
}
