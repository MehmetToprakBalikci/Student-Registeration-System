public class Message {

    private String message;
    private String sender;
    private String receiver;
    private boolean isRead;

    public Message(String message, String sender, String receiver, String date, String time) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.isRead = false;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Message: " + message + "\n" +
                "Sender: " + sender + "\n" +
                "Receiver: " + receiver + "\n"
                + "Read: " + isRead;
    }
}
