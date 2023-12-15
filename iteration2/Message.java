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


    @Override
    public String toString() {
        return "Title: " + title + ", " +
                "Sender: " + sender.toString() + ", " +
                "Receiver: " + receiver.toString() + ", " +
                "Read: " + isRead;
    }
}
