import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    Socket socket;
    ObjectInputStream oin;
    ObjectOutputStream oout;

    public static ArrayList<ClientHandler> clients = new ArrayList<>();


    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.oin = new ObjectInputStream(socket.getInputStream());
        this.oout = new ObjectOutputStream(socket.getOutputStream());
        clients.add(this);
    }

    @Override
    public void run() {

    }
}
