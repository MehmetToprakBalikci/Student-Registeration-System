abstract class Person {
    private String firstName;
    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Constructor
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



}

