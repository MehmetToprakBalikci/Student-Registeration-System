abstract class Person {
    protected String name;
    protected String lastName;
    private String userName;
    private String password;

    // Constructor
    public Person(String name, String lastName,String userName,String password) {
        this.name = name;
        this.lastName = lastName;
        this.userName=userName;
        this.password=password;
    }

    public Person(String name, String lastName) {
        this.name=name;
        this.lastName=lastName;
    }

    // Placeholder for person's actions
    void startActions(Controller controller) {

    }
    public boolean compareCredentials(String username, String password) {
    	
    	return true;
    }

    // Method for sign-in process
    abstract Person signIn(String username, String password);

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

