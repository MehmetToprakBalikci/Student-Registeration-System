import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Base class for all people in the system
class Person {
    protected String name;
    protected String lastName;

    // Method to create person
    public static Person create(String name, String lastName) {
        return new Person(name, lastName);
    }

    // Constructor
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    // Placeholder for person's actions
    public void startActions() {
        // Implementation needed
    }

    // Method for sign-in process
    public boolean signin(String username, String password) {
        // Implementation needed
        return false;
    }
}