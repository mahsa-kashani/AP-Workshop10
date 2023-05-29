import java.io.Serializable;
import java.util.Date;

public class MessagePacket implements Serializable {
    private String message;
    private String date;
    private String sender;

    public MessagePacket(String message, String sender) {
        this.message = message;
        this.sender = sender;
        this.date = new Date().toString();
    }

    public MessagePacket(String message) {
        this.message = message;
        this.date = new Date().toString();
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }
}
