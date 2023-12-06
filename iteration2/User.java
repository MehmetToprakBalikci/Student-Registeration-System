
public interface User {
	
    String[] getActionList();
    void runUserAction(int actionNumber, Controller controller);
    
    public boolean compareCredentials(String username, String password);
    // Placeholder for user's actions
    abstract void startActions(Controller controller);
    
    public void setUserName(String userName);
    public void setPassword(String password);
    public String getUserName();
    public String getPassword();
}