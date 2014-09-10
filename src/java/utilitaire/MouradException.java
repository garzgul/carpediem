package utilitaire;

import java.util.HashMap;

public class MouradException extends Exception{
    
    private HashMap <String, String> messages;
    
    public MouradException() {
        super();        
    }

    public MouradException(HashMap<String, String> messages) {
        this();
        this.messages = messages;
    }

    public MouradException(HashMap<String, String> messages, String msg) {
        super(msg);
        this.messages = messages;
    }
    
    
    
    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
    
}
