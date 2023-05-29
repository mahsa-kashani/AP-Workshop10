import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1" , 6000);
            System.out.println("Connected To server!");


        } catch (IOException e) {
            System.out.println("Couldn't connect to server");
        }

    }
}
