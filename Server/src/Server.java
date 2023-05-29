import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<Thread> threads = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is Listening...");
        while(true){
            Socket client = serverSocket.accept();
            Thread t = new Thread(new ClientHandler(client));
            t.start();
            threads.add(t);
            System.out.println("New client joined!");
        }
    }
}
