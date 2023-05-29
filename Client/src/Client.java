import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1" , 1234);
            System.out.println("Connected To server!");
            ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
            Thread listener = new Thread(new Listener(socket));
            listener.start();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your name : ");
            String name = sc.nextLine();
            oout.writeObject(new MessagePacket(name,name));
            oout.flush();
            while(true){
                String message = sc.nextLine();
                oout.writeObject(new MessagePacket(message,name));
                oout.flush();
                if(message.equals("#exit")){
                    System.out.println("Disconnected");
                    socket.close();
                    listener.interrupt();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't connect to server");
        }

    }
}
