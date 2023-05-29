import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectInputStream oin;
    private ObjectOutputStream oout;
    private String name;

    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void broadCast(MessagePacket packet, ClientHandler from) throws IOException {
        for(ClientHandler client : clients){
            if(client==from)
                continue;
            client.oout.writeObject(packet);
            client.oout.flush();
        }
    }

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.oin = new ObjectInputStream(socket.getInputStream());
        this.oout = new ObjectOutputStream(socket.getOutputStream());
        clients.add(this);
    }

    @Override
    public void run() {
        try {
            MessagePacket namePacket = (MessagePacket) oin.readObject();
            this.name = namePacket.getSender();
            ClientHandler.broadCast(new MessagePacket("Joined the chat", this.name),this);
            while(true){
                MessagePacket message = (MessagePacket) oin.readObject();
                if(message.getMessage().equals("#exit")){
                    ClientHandler.broadCast(new MessagePacket("Left the chat",this.name),this);
                    System.out.println("A Client Left the chat");
                    break;
                }
                ClientHandler.broadCast(new MessagePacket(message.getMessage(),this.name),this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
