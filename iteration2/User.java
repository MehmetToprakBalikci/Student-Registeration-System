//for iteration2, we have added two new methods just like the ones we added for the Student and Advisor class: 
//sendMessage and receiveMessage.


public interface User {
	
    String[] getActionList();
    void runUserAction(int actionNumber, Controller controller);
    
    public boolean compareCredentials(String username, String password);
    // Placeholder for user's actions
    abstract void startActions(Controller controller);
    
    public void setUserName(String userName);
    public void setPassword(String password);

    //iteration 2 part:
    public void sendMessage(Message message, User user);
    public void receiveMessage(Message message);

}