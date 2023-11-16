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
    abstract void startActions(Controller controller);
    public boolean compareCredentials(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);


    }



    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

