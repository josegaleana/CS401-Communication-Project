import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatRoom implements Serializable {
    private String chatID; // what is this going to be?
    private String filename; // filename on server
    private List<String> participants;
    private List<Message> messages;

    public ChatRoom() {
        this.messages = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    // Constructor for a new chatroom from a user
    public ChatRoom(List<String> participants) {
        this.participants = participants;
        this.messages = new ArrayList<>();
        this.chatID = UUID.randomUUID().toString();
    }

    // Constructor for a complete object - loading from server
    public ChatRoom(List<String> participants, List<Message> messages, String chatID, String filename) {
        this.chatID = chatID;
        this.filename = filename;
        this.participants = participants;
        this.messages = messages;
    }

    // Setters
    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    // Public Methods
    // Getters
    public String getChatID() {
        return chatID;
    }

    public String getFilename() {
        return filename;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public List<Message> getMesssages() {
        return messages;
    }

    // Other public methods
    // public boolean addMessage(Message msg) {
    //     if (!messageExists(msg.getID())) { 
    //         messages.add(msg);
    //         return true;
    //     }
    //     return false; // not added
    // }
    public void addMessage(Message msg) {
        messages.add(msg);
    }

    public boolean addMember(User user) {
        if (!isParticipant(user.getUsername())){
            participants.add(user.getUsername());
            return true;
        }
        return false;
    }

    public boolean messageExists(String messageID) {
        for (Message m : messages) {
            if (messageID == m.getID())
                return true;
        }
        // not found
        return false;
    }

    public boolean isParticipant(String userID) {
        for (String user : participants) {
            if (userID == user)
                return true;
        }
        // not found
        return false;
    }

    // private methods
}