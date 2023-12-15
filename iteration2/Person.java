abstract class Person {
    private String firstName;
    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Constructors
    public Person() {

    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

