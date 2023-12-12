public class Message {

	private String title;
    private String message;
    private User sender;
    private User receiver;
    private boolean isRead;

    public Message(String title, String message, User sender, User receiver) {
        this.title = title;
    	this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.isRead = false;
    }
    
    
    public void readMessage() {
    	this.isRead = true;
    }
    

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", " +
                "Sender: " + sender.toString() + ", " +
                "Receiver: " + receiver.toString() + ", " +
                "Read: " + isRead;
    }
}
