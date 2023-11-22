abstract class Person {
    protected String name;
    protected String lastName;
    private String userName;


    private String password;

    // Constructor
    public Person(String name, String lastName, String userName, String password) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person() {

    }



    // Placeholder for person's actions
    abstract void startActions(Controller controller);

    public boolean compareCredentials(String username, String password) {
        if (this.userName == null || this.password == null) return false;
        return this.userName.equals(username) && this.password.equals(password);


    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

