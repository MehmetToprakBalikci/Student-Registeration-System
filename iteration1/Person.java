abstract class Person {
    private String firstName;
    private String lastName;
    private String userName;


    private String password;


    // Constructor
    public Person(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {

    }

    public boolean compareCredentials(String username, String password) {
        if (this.userName == null || this.password == null) return false;
        return this.userName.equals(username) && this.password.equals(password);


    }


    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

