import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener implements Runnable{
    Socket socket;
    ObjectInputStream oin;

    public Listener(Socket socket) throws IOException {
        this.socket = socket;
        oin = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while(true){
            try {
                MessagePacket message = (MessagePacket) oin.readObject();
                System.out.println(message.getDate()+"    "+message.getSender() + " : "+ message.getMessage());
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }
}
